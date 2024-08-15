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
import java.sql.SQLException;
import model.NFCeModel;

/**
 *
 * @author artur
 */
public class NFCeController {

    private final Connection connection;
    private final NFCeDAO nfceDAO;
    private final NFCeProdutoDAO nfceProdutoDAO;
    private final PagamentoDAO pagamentoDAO;

    public NFCeController() throws SQLException {
        this.connection = FirebirdConnection.getConnection();
        nfceDAO = new NFCeDAO(connection);
        nfceProdutoDAO = new NFCeProdutoDAO(connection);
        pagamentoDAO = new PagamentoDAO(connection);
    }

    public void processarXML(File xmlFile) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nfceList = doc.getElementsByTagName("NFCe");
//            for (int i = 0; i < nfceList.getLength(); i++) {
//                Element nfceElement = (Element) nfceList.item(i);
//                NFCeModel nfce = new NFCeModel();
//                nfce.setChave(nfceElement.getElementsByTagName("chave").item(0).getTextContent());
//                nfce.setDataEmissao(nfceElement.getElementsByTagName("data_emissao").item(0).getTextContent());
//
//                nfceDAO.insert(nfce);
//                int nfceId = nfceDAO.getNFCe(nfce.getChave()).getId();
//
//                NodeList produtosList = nfceElement.getElementsByTagName("produto");
//                for (int j = 0; j < produtosList.getLength(); j++) {
//                    Element produtoElement = (Element) produtosList.item(j);
//                    NFCeProduto produto = new NFCeProduto();
//                    produto.setIdNfce(nfceId);
//                    produto.setCodigoProduto(produtoElement.getElementsByTagName("codigo").item(0).getTextContent());
//                    produto.setQuantidade(Integer.parseInt(produtoElement.getElementsByTagName("quantidade").item(0).getTextContent()));
//                    produto.setValor(Double.parseDouble(produtoElement.getElementsByTagName("valor").item(0).getTextContent()));
//
//                    nfceProdutoDAO.insertNFCeProduto(produto);
//                }
//
//                NodeList pagamentosList = nfceElement.getElementsByTagName("pagamento");
//                for (int k = 0; k < pagamentosList.getLength(); k++) {
//                    Element pagamentoElement = (Element) pagamentosList.item(k);
//                    Pagamento pagamento = new Pagamento();
//                    pagamento.setIdNfce(nfceId);
//                    pagamento.setTipo(pagamentoElement.getElementsByTagName("tipo").item(0).getTextContent());
//                    pagamento.setValor(Double.parseDouble(pagamentoElement.getElementsByTagName("valor").item(0).getTextContent()));
//                    pagamento.setDataPagamento(pagamentoElement.getElementsByTagName("data_pagamento").item(0).getTextContent());
//
//                    pagamentoDAO.insertPagamento(pagamento);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
