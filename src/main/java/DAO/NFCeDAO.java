package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.NFCeModel;

/**
 *
 * @author artur moisés
 */
public class NFCeDAO {

    private final Connection connection;

    public NFCeDAO(Connection connection) {
        this.connection = connection;
    }

    public int insert(NFCeModel nfce) throws SQLException {
        String sql = "INSERT INTO NFCE (TIPO_OPERACAO, CODIGO_CLIENTE, CFOP_DA_NOTA, TIPO_DE_COBRANCA, TOTAL_LIQUIDO, QUANTIDADE_DUPLICATAS, INTEVALO_ENTRE_DUPLICATAS, A_PARTIR_DE, EMISSAO, CHAVE_DA_NOTA_ORIGINAL, CHAVE_DA_NOTA_DE_ENTRADA, INFORMACOES_COMPLEMENTARES, STATUS, ULTIMO_PROCESSAMENTO, DESCONTO, XML, CNPJ, AMBIENTE, ATENDENTE, NUMERO_FAIXA, IMPORTOU_TUDO_OS, CODIGO_OPERADOR, VOUTRO, TERMINAL, CODIGO_OS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Set parameters based on NFCE object fields
            stmt.setString(1, nfce.getTipoOperacao());
            stmt.setInt(2, nfce.getCodigoCliente());
            stmt.setString(3, nfce.getCfopDaNota());
            stmt.setString(4, nfce.getTipoDeCobranca());
            stmt.setDouble(5, nfce.getTotalLiquido());
            stmt.setInt(6, nfce.getQuantidadeDuplicatas());
            stmt.setInt(7, nfce.getIntervaloEntreDuplicatas());
            stmt.setString(8, nfce.getAPartirDe());
            stmt.setString(9, nfce.getEmissao());
            stmt.setString(10, nfce.getChaveDaNotaOriginal());
            stmt.setString(11, nfce.getChaveDaNotaDeEntrada());
            stmt.setString(12, nfce.getInformacoesComplementares());
            stmt.setString(13, nfce.getStatus());
            stmt.setString(14, nfce.getUltimoProcessamento());
            stmt.setDouble(15, nfce.getDesconto());
            stmt.setString(16, nfce.getXml());
            stmt.setString(17, nfce.getCnpj());
            stmt.setInt(18, nfce.getAmbiente());
            stmt.setInt(19, nfce.getAtendente());
            stmt.setInt(20, nfce.getNumeroFaixa());
            stmt.setBoolean(21, nfce.isImportouTudoOs());
            stmt.setInt(22, nfce.getCodigoOperador());
            stmt.setInt(23, nfce.getVOutro());
            stmt.setString(24, nfce.getTerminal());
            stmt.setInt(25, nfce.getCodigoOs());

            // Execute the insertion
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the generated ID
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Return the generated ID
                    }
                }
            }

            return -1; // Return a value indicating that no ID was generated
        } catch (SQLException e) {
            // Log the exception and rethrow or handle accordingly
            System.err.println("Error inserting NFCe: " + e.getMessage());
            throw e;
        }
    }

    public int getCodigoByFaixa(int faixa) throws SQLException {
        String sql = "SELECT n.codigo FROM nfce n WHERE n.numero_faixa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, faixa);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("codigo"); // Retorna o código encontrado
                } else {
                    return -1; // Nenhum código encontrado
                }
            }
        } catch (SQLException e) {
            // Log da exceção e rethrow ou tratamento adequado
            System.err.println("Error retrieving NFCe code: " + e.getMessage());
            throw e;
        }
    }

}
