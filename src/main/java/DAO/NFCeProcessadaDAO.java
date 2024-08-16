package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.NFCeProcessadaModel;

/**
 *
 * @author artur
 */
public class NFCeProcessadaDAO {

    private final Connection connection;

    public NFCeProcessadaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(NFCeProcessadaModel produto) throws SQLException {
        String sql = "INSERT INTO NFCE_PROCESSADA (CODIGO_NFE, CHAVE_NFE, NUM_NFE, NUM_PROTOCOLO, XML) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, produto.getCodigoNFe());
            stmt.setString(2, produto.getChaveNFe());
            stmt.setInt(3, produto.getNumNFe());
            stmt.setString(4, produto.getNumProtocolo());
            stmt.setString(5, produto.getXml());
            stmt.executeUpdate();
        }
    }

}
