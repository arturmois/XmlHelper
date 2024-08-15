package controller;

import DAO.NFCeDAO;
import DAO.NFCeProdutoDAO;
import DAO.PagamentoDAO;
import database.FirebirdConnection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import model.NFCeModel;
import model.NFCeProdutoModel;
import model.PagamentoModel;

/**
 *
 * @author artur moisés
 */
public class XMLProcessor {

    public void processXML(File xmlFile) throws Exception {
        Document document = parseXML(xmlFile);
        Element root = document.getDocumentElement();

        Element infNFe = (Element) root.getElementsByTagName("infNFe").item(0);
        NFCeModel nfce = extractNFCEData(infNFe);
        nfce.setXml(infNFe.toString());

        try (Connection conn = FirebirdConnection.getConnection()) {
            NFCeDAO nfceDAO = new NFCeDAO(conn);
            int nfceCodigo = nfceDAO.insert(nfce);

            NodeList detList = infNFe.getElementsByTagName("det");
            for (int i = 0; i < detList.getLength(); i++) {
                Element det = (Element) detList.item(i);
                NFCeProdutoModel produto = extractProdutoData(det, nfceCodigo);
                NFCeProdutoDAO nfceProdutoDAO = new NFCeProdutoDAO(conn);
                nfceProdutoDAO.insert(produto);
            }

            NodeList detPagList = infNFe.getElementsByTagName("detPag");
            for (int i = 0; i < detPagList.getLength(); i++) {
                Element detPag = (Element) detPagList.item(i);
                PagamentoModel pagamento = extractPagamentoData(detPag, nfceCodigo);
                PagamentoDAO pagamentoDAO = new PagamentoDAO(conn);
                
                pagamento.setNumNFe(nfce.getNumeroFaixa()); // verificar
                pagamentoDAO.insert(pagamento);
            }
        }
    }

    private Document parseXML(File xmlFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder().parse(xmlFile);
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
        produto.setCodBarras(getElementValue(det, "cEAN"));
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

    // Método utilitário para obter o valor de um elemento XML
    private String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
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

}
