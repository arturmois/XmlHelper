package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.NFCeProdutoModel;

/**
 *
 * @author artur moisés
 */
public class NFCeProdutoDAO {

    private final Connection connection;

    public NFCeProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(NFCeProdutoModel produto) throws SQLException {
        String sql = "INSERT INTO NFCE_PRODUTO (CODIGO_PRODUTO, CODIGO_NFE, QUANTIDADE, VALOR, UNIDADE, CFOP, DESCRICAO, COD_BARRAS, VALOR_CUSTO, REF_GRADE, RESPONSAVEL, MARCA, CODIGO_EDITAL, ITEM_NO_PEDIDO, OBSERVACAO, ITEM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set parameters based on NFCEProduto object fields
            stmt.setInt(1, produto.getCodigoProduto());
            stmt.setInt(2, produto.getCodigoNfe());
            stmt.setDouble(3, produto.getQuantidade());
            stmt.setDouble(4, produto.getValor());
            stmt.setString(5, produto.getUnidade());
            stmt.setInt(6, produto.getCfop());
            stmt.setString(7, produto.getDescricao());
            stmt.setString(8, produto.getCodBarras());
            stmt.setDouble(9, produto.getValorCusto());
            stmt.setString(10, produto.getRefGrade());
            stmt.setInt(11, produto.getResponsavel());
            stmt.setString(12, produto.getMarca());
            stmt.setString(13, produto.getCodigoEdital());
            stmt.setInt(14, produto.getItemNoPedido());
            stmt.setString(15, produto.getObservacao());
            stmt.setInt(16, produto.getItem());
            stmt.executeUpdate();
        }
    }

    public NFCeProdutoModel getById(int codigoProduto) throws SQLException {
        String sql = "SELECT * FROM NFCE_PRODUTO WHERE CODIGO_PRODUTO = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NFCeProdutoModel produto = new NFCeProdutoModel();
                    produto.setCodigoProduto(rs.getInt("CODIGO_PRODUTO"));
                    produto.setCodigoNfe(rs.getInt("CODIGO_NFE"));
                    produto.setQuantidade(rs.getInt("QUANTIDADE"));
                    produto.setValor(rs.getDouble("VALOR"));
                    produto.setUnidade(rs.getString("UNIDADE"));
                    produto.setCfop(rs.getInt("CFOP"));
                    produto.setDescricao(rs.getString("DESCRICAO"));
                    produto.setCodBarras(rs.getString("COD_BARRAS"));
                    produto.setValorCusto(rs.getDouble("VALOR_CUSTO"));
                    produto.setRefGrade(rs.getString("REF_GRADE"));
                    produto.setResponsavel(rs.getInt("RESPONSAVEL"));
                    produto.setMarca(rs.getString("MARCA"));
                    produto.setCodigoEdital(rs.getString("CODIGO_EDITAL"));
                    produto.setItemNoPedido(rs.getInt("ITEM_NO_PEDIDO"));
                    produto.setItem(rs.getInt("ITEM"));
                    return produto;
                }
            }
        }
        return null;
    }

    public void update(NFCeProdutoModel produto) throws SQLException {
        String sql = "UPDATE NFCE_PRODUTO SET CODIGO_NFE = ?, QUANTIDADE = ?, VALOR = ?, UNIDADE = ?, CFOP = ?, DESCRICAO = ?, COD_BARRAS = ?, VALOR_CUSTO = ?, REF_GRADE = ?, RESPONSAVEL = ?, MARCA = ?, CODIGO_EDITAL = ?, ITEM_NO_PEDIDO = ?, ITEM = ? WHERE CODIGO_PRODUTO = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set parameters based on NFCEProduto object fields
            stmt.setInt(1, produto.getCodigoNfe());
            stmt.setDouble(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getValor());
            stmt.setString(4, produto.getUnidade());
            stmt.setInt(5, produto.getCfop());
            stmt.setString(6, produto.getDescricao());
            stmt.setString(7, produto.getCodBarras());
            stmt.setDouble(8, produto.getValorCusto());
            stmt.setString(9, produto.getRefGrade());
            stmt.setInt(10, produto.getResponsavel());
            stmt.setString(11, produto.getMarca());
            stmt.setString(12, produto.getCodigoEdital());
            stmt.setInt(13, produto.getItemNoPedido());
            stmt.setInt(14, produto.getItem());
            stmt.setInt(15, produto.getCodigoProduto());
            stmt.executeUpdate();
        }
    }

    public void delete(int codigoProduto) throws SQLException {
        String sql = "DELETE FROM NFCE_PRODUTO WHERE CODIGO_PRODUTO = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoProduto);
            stmt.executeUpdate();
        }
    }
}
