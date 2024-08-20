package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.EntradaNotaModel;

/**
 *
 * @author artur
 */
public class EntradaNotaDAO {

    private final Connection connection;

    public EntradaNotaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(EntradaNotaModel entradaNota) throws SQLException {
        String sql = "INSERT INTO NOTAENTRADA (CHAVE_NFE, XML, DATA_E_HORA_ENTRADA, DATA_ENTRADA_DE_PRODUTOS, CFOP, STATUS, STATUS_MANIFESTACAO, XML_2) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entradaNota.getChaveNFe());
            stmt.setString(2, entradaNota.getXml());
            stmt.setTimestamp(3, entradaNota.getDataHoraEntrada());
            stmt.setTimestamp(4, entradaNota.getDataEntradaProdutos());
            stmt.setString(5, entradaNota.getCfop());
            stmt.setString(6, entradaNota.getStatus());
            stmt.setString(7, entradaNota.getStatausManifestacao());
            stmt.setString(8, entradaNota.getXml2());

            stmt.executeUpdate();
        }
    }
}
