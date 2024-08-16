package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ProductToUpdate;

/**
 *
 * @author artur
 */
public class ProdutoDAO {

    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public ProductToUpdate findByBarcode(String barcode) throws SQLException {
        String sql = "select p.codigo, p.quantidade "
                + "from produtos p "
                + "where p.codigo_barras =  ?";

        ProductToUpdate updateQtdProduct = new ProductToUpdate();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Configura o valor do parâmetro da query
            stmt.setString(1, barcode);

            // Executa a consulta e obtém o resultado
            try (ResultSet rs = stmt.executeQuery()) {
                // Se um produto for encontrado, preenche o objeto ProdutoModel
                if (rs.next()) {
                    updateQtdProduct.setCode(rs.getInt("codigo"));
                    updateQtdProduct.setQuantity(rs.getBigDecimal("quantidade"));
                    return updateQtdProduct;
                } else {
                    return null;
                }
            }
        }
    }

    public void updateQtdProduct(int code, BigDecimal quantity) throws SQLException {
        String sql = "UPDATE produtos SET quantidade = ? WHERE codigo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, quantity);
            stmt.setInt(2, code);

            stmt.executeUpdate(); // Corrigido para executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throwing the exception if needed
        }
    }
}
