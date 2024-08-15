package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author artur moisés
 */
public class FirebirdConnection {

    private static final String URL = "jdbc:firebirdsql://localhost:3050/C:\\Users\\artur\\java\\ServeSoft-v1\\banco.fdb?lc_ctype=NONE";
    private static final String USER = "sysdba";
    private static final String PASSWORD = "masterkey";
    private Connection connection;

    public FirebirdConnection() {
        try {
            // Testa a conexão antes de inicializar
            if (testConnection()) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.err.println("Falha ao conectar ao banco de dados.");
                // Lançar uma exceção ou sair da aplicação, conforme necessário
                throw new SQLException("Não foi possível conectar ao banco de dados.");
            }
            // Inicializa a conexão
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            // Trate o erro conforme necessário
        }
    }

    private boolean testConnection() {
        try (Connection testConnection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            return testConnection != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
