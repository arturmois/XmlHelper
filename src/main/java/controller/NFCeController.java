package controller;

import DAO.NFCeDAO;
import DAO.NFCeProcessadaDAO;
import DAO.NFCeProdutoDAO;
import DAO.PagamentoDAO;
import database.FirebirdConnection;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.NFCeModel;
import model.NFCeProcessadaModel;
import model.NFCeProdutoModel;
import model.PagamentoModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import util.LogUtil;

/**
 *
 * @author artur moisés
 */
public class NFCeController {

    public void processXML(File xmlFile) throws Exception {
        Document document = parseXML(xmlFile);
        Element nfeProc = document.getDocumentElement();

        // Get protNFe element
        Element protNFe = (Element) nfeProc.getElementsByTagName("protNFe").item(0);

        // Get NFe element
        Element NFe = (Element) nfeProc.getElementsByTagName("NFe").item(0);

        // Extract nProt value
        String nProt = getElementValue(protNFe, "nProt");

        // Create enviNFe element in the same document
        Element enviNFe = document.createElement("enviNFe");
        enviNFe.setAttribute("id", "000012251");

        // Import NFe node into the same document before appending it
        Node importedNFe = document.importNode(NFe, true);
        enviNFe.appendChild(importedNFe);

        // Convert the node to a string and log the result
        String convertedNode = convertNodeToString(enviNFe);

        // Extract NFCe data
        NFCeModel nfce = extractNFCEData(NFe);
        nfce.setXml(convertedNode.replaceAll(">(\\s+)<", "><"));
        try (Connection conn = FirebirdConnection.getConnection()) {
            NFCeDAO nfceDAO = new NFCeDAO(conn);
            NFCeProcessadaDAO nfceProcessadaDAO = new NFCeProcessadaDAO(conn);
            NFCeProdutoDAO nfceProdutoDAO = new NFCeProdutoDAO(conn);
            PagamentoDAO pagamentoDAO = new PagamentoDAO(conn);

            int nfceCodigo = nfceDAO.insert(nfce);

            String idValue = getElementValue(nfeProc, "chNFe");
            NFCeProcessadaModel nfceProcessada = extractNFCEProcessadaData(NFe, nfceCodigo, idValue, nProt);
            nfceProcessada.setXml(convertNodeToString((Element) nfeProc.getElementsByTagName("protNFe").item(0)));
            nfceProcessadaDAO.insert(nfceProcessada);

            NodeList detList = NFe.getElementsByTagName("det");
            for (int i = 0; i < detList.getLength(); i++) {
                Element det = (Element) detList.item(i);
                NFCeProdutoModel produto = extractProdutoData(det, nfceCodigo);
                nfceProdutoDAO.insert(produto);
            }

            NodeList detPagList = NFe.getElementsByTagName("detPag");
            for (int i = 0; i < detPagList.getLength(); i++) {
                Element detPag = (Element) detPagList.item(i);
                PagamentoModel pagamento = extractPagamentoData(detPag, nfceCodigo);
                pagamento.setNumNFe(nfce.getNumeroFaixa());
                pagamentoDAO.insert(pagamento);
            }

        } catch (SQLException e) {
            LogUtil.logException(e);
            throw new Exception("Erro ao processar XML: " + xmlFile.getName(), e);
        } catch (Exception e) {
            LogUtil.logException(e);
            throw new Exception("Erro desconhecido ao processar XML: " + xmlFile.getName(), e);
        }
    }

    private Document parseXML(File xmlFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder().parse(xmlFile);
    }

    private String convertNodeToString(Element element) throws TransformerException {
        try {
            // Cria um StringWriter para armazenar a saída em String
            StringWriter writer = new StringWriter();

            // Cria uma instância de Transformer para transformar o Element em String
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            // Transforma o Element em uma String
            transformer.transform(new DOMSource(element), new StreamResult(writer));

            // Retorna o conteúdo do StringWriter como String
            return writer.toString();
        } catch (TransformerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private NFCeModel extractNFCEData(Element infNFe) {
        NFCeModel nfce = new NFCeModel();

        // Inicializando valores padrão
        nfce.setTipoOperacao("SAIDA");
        nfce.setCodigoCliente(0);
        nfce.setCfopDaNota(getElementValue(infNFe, "CFOP"));
        nfce.setTipoDeCobranca("A_VISTA");
        nfce.setTotalLiquido(parseDoubleOrDefault(getElementValue(infNFe, "vNF"), 0.0));
        nfce.setQuantidadeDuplicatas(0);
        nfce.setIntervaloEntreDuplicatas(0);
        nfce.setAPartirDe("null");
        nfce.setEmissao("NORMAL");
        nfce.setChaveDaNotaOriginal("");
        nfce.setChaveDaNotaDeEntrada("");
        nfce.setInformacoesComplementares("");
        nfce.setStatus("PROCESSADA");
        nfce.setUltimoProcessamento(getElementValue(infNFe, "dhEmi"));

        Element total = (Element) infNFe.getElementsByTagName("total").item(0);
        if (total != null) {
            nfce.setDesconto(parseDoubleOrDefault(getElementValue(total, "vDesc"), 0.0));
            nfce.setVOutro((int) parseDoubleOrDefault(getElementValue(total, "vOutro"), 0.0));
        } else {
            nfce.setDesconto(0.0);
            nfce.setVOutro(0);
        }

        nfce.setCnpj(getElementValue(infNFe, "CNPJ"));
        nfce.setAmbiente(1);
        nfce.setAtendente(1);
        nfce.setNumeroFaixa(parseIntOrDefault(getElementValue(infNFe, "nNF"), 0));
        nfce.setImportouTudoOs(false);
        nfce.setCodigoOperador(1);
        nfce.setTerminal("DESKTOP-2B94APF");
        nfce.setCodigoOs(-1);

        return nfce;
    }

    private NFCeProdutoModel extractProdutoData(Element det, int nfceCodigo) {
        NFCeProdutoModel produto = new NFCeProdutoModel();
        // Implement extraction logic and set NFCEProduto properties
        produto.setCodigoProduto(Integer.parseInt(getElementValue(det, "cProd")));
        produto.setCodigoNfe(nfceCodigo);
        produto.setQuantidade(Double.parseDouble(getElementValue(det, "qTrib")));
        produto.setValor(Double.parseDouble(getElementValue(det, "vProd")));
        produto.setUnidade(getElementValue(det, "uCom"));
        produto.setCfop(Integer.parseInt(getElementValue(det, "CFOP")));
        produto.setDescricao(getElementValue(det, "xProd"));
        String gtin = getElementValue(det, "cEAN");
        if (gtin.equals("SEM GTIN")) {
            produto.setCodBarras("");
        } else {
            produto.setCodBarras(gtin);
        }

        produto.setValorCusto(Double.parseDouble(getElementValue(det, "vProd"))); //verificar
        produto.setRefGrade(""); //verificar
        produto.setResponsavel(0);
        produto.setMarca(""); //verificar
        produto.setCodigoEdital(""); //verificar
        produto.setItemNoPedido(-1);
        produto.setObservacao("");
        produto.setItem(-1);
        return produto;
    }

    private PagamentoModel extractPagamentoData(Element detPag, int nfceCodigo) {
        PagamentoModel pagamento = new PagamentoModel();
        // pagamento.setNumNFe(nfceCodigo);
        pagamento.setTipoDePagamento("DINHEIRO"); //VERIFICAR
        pagamento.setValor(Double.parseDouble(getElementValue(detPag, "vPag")));
        pagamento.setTroco(0.0); //verificar
        pagamento.setCnpj(getElementValue(detPag, "CNPJ"));
        pagamento.setTipoDeCartao("");
        pagamento.setBandeiraDoCartao(getElementValue(detPag, "tpIntegra"));
        pagamento.setNumAutorizacao(getElementValue(detPag, "cAut"));
        pagamento.setCodigoNFCeOrigem(nfceCodigo);
        pagamento.setParcelas(-1); //verificar
        pagamento.setCodigoCFeOrigem(0);
        pagamento.setCodigoTipoPagOutros(0);
        pagamento.setTipoPix(""); //verificar

        // Implement extraction logic and set Pagamento properties
        return pagamento;
    }

    private NFCeProcessadaModel extractNFCEProcessadaData(Element infNFe, int nfceCodigo, String idValue, String numProtocolo) {
        NFCeProcessadaModel nfceProcessada = new NFCeProcessadaModel();
        nfceProcessada.setCodigoNFe(nfceCodigo);
        nfceProcessada.setChaveNFe(idValue);
        nfceProcessada.setNumNFe(Integer.parseInt(getElementValue(infNFe, "nNF")));
        nfceProcessada.setNumProtocolo(numProtocolo);
        nfceProcessada.setXml("xml");

        return nfceProcessada;
    }

    // Método utilitário para obter o valor de um elemento XML
    private String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    // Método utilitário para obter o valor de um atributo de um elemento XML
    private String getAttributeValue(Element parentElement, String tagName, String namespaceURI, String attributeName) {
        NodeList nodeList = parentElement.getElementsByTagNameNS(namespaceURI, tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            return element.getAttribute(attributeName);
        }
        return "";
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private Element createBaseElement(String cabecalho) throws ParserConfigurationException {

        DocumentBuilderFactory fabricaDeConstrutorDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder construtorDeDocumentos = fabricaDeConstrutorDeDocumentos.newDocumentBuilder();

        //elemento da base
        Document xmlNFE = construtorDeDocumentos.newDocument();

        Element enviNFe = xmlNFE.createElement("enviNFe");
        enviNFe.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
        enviNFe.setAttribute("versao", "4.00");
        xmlNFE.appendChild(enviNFe);

        Element idLote = xmlNFE.createElement("idLote");
        idLote.appendChild(xmlNFE.createTextNode("0" + cabecalho));
        enviNFe.appendChild(idLote);

        Element indSinc = xmlNFE.createElement("indSinc");
        indSinc.appendChild(xmlNFE.createTextNode("1"));

        enviNFe.appendChild(indSinc);

        return enviNFe;
    }

}
