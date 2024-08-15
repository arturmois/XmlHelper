package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PagamentoModel;

/**
 *
 * @author artur moisés
 */
public class PagamentoDAO {

    private final Connection connection;

    public PagamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(PagamentoModel pagamento) throws SQLException {
        String sql = "INSERT INTO PAGAMENTOS (NUM_NFE, TIPODEPAGAMENTO, VALOR, TROCO, CNPJ, TIPODECARTAO, BANDEIRADOCARTAO, NUMAUTORIZACAO, CODIGO_NFCE_ORIGEM, PARCELAS, CODIGO_CFE_ORIGEM, CODIGO_TIPOPAGOUTROS, TIPO_PIX) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set parameters based on Pagamento object fields
            stmt.setInt(1, pagamento.getNumNFe());
            stmt.setString(2, pagamento.getTipoDePagamento());
            stmt.setDouble(3, pagamento.getValor());
            stmt.setDouble(4, pagamento.getTroco());
            stmt.setString(5, pagamento.getCnpj());
            stmt.setString(6, pagamento.getTipoDeCartao());
            stmt.setString(7, pagamento.getBandeiraDoCartao());
            stmt.setString(8, pagamento.getNumAutorizacao());
            stmt.setInt(9, pagamento.getCodigoNFCeOrigem());
            stmt.setInt(10, pagamento.getParcelas());
            stmt.setInt(11, pagamento.getCodigoCFeOrigem());
            stmt.setInt(12, pagamento.getCodigoTipoPagOutros());
            stmt.setString(13, pagamento.getTipoPix());
            stmt.executeUpdate();
        }
    }
}
