/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ProdutoDAO;
import database.FirebirdConnection;
import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.ProductToUpdate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author artur
 */
public class EntradaNotaController {

    public void processXML(File xmlFile) throws Exception {
        Connection conn = FirebirdConnection.getConnection();
        ProdutoDAO produtoDAO = new ProdutoDAO(conn);

        // Parseia o arquivo XML e obtém o documento
        Document document = parseXML(xmlFile);

        // Obtém o elemento raiz do documento (nfeProc)
        Element nfeProc = document.getDocumentElement();

        // Obtém o primeiro elemento "NFe" dentro de "nfeProc"
        Element NFe = (Element) nfeProc.getElementsByTagName("NFe").item(0);

        // Obtém a lista de todos os elementos "det" dentro do elemento "NFe"
        NodeList detList = NFe.getElementsByTagName("det");

        // Cria um mapa para armazenar os elementos "det" associados a seus códigos
        Map<Integer, Element> nfeMap = new HashMap<>();

        // Loop através de cada elemento "det"
        for (int i = 0; i < detList.getLength(); i++) {
            // Converte o nó em um Elemento
            Element det = (Element) detList.item(i);
            String codigoBarras = getElementValue(det, "cEAN");
            ProductToUpdate product = produtoDAO.findByBarcode(codigoBarras);
            if (product != null) {
                processProduct(det, product, produtoDAO);
            }
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

    private void processProduct(Element det, ProductToUpdate product, ProdutoDAO produtoDAO) throws SQLException {
        BigDecimal newQuantity = new BigDecimal(getElementValue(det, "qCom"));
        if (newQuantity.compareTo(BigDecimal.ZERO) >= 0) {
            produtoDAO.updateQtdProduct(product.getCode(), product.getQuantity().add(newQuantity));
            System.out.println(product.getCode() + ":" + product.getQuantity().add(newQuantity));
            System.out.println("quantidade anterior: " + product.getQuantity());
            System.out.println("nova quantidade: " + product.getQuantity().add(newQuantity));
        } else {
            System.out.println("none");
        }

        System.out.println();
    }

    // Método utilitário para obter o valor de um elemento XML
    private String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}
