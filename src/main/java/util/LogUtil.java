package util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author artur
 */
public class LogUtil {

    // Obtém uma instância do Logger
    private static final Logger logger = Logger.getLogger(LogUtil.class.getName());

    // Configuração inicial (pode ser expandida para adicionar FileHandler, etc.)
    static {
        logger.setLevel(Level.ALL); // Define o nível de log para ALL (pode ser alterado conforme necessário)
    }

    // Método para logar mensagens de INFO
    public static void info(String message) {
        logger.info(message);
    }

    // Método para logar mensagens de WARN (WARNING)
    public static void warn(String message) {
        logger.warning(message);
    }

    // Método para logar mensagens de ERROR (SEVERE)
    public static void error(String message) {
        logger.severe(message);
    }

    // Método para logar mensagens de DEBUG (FINE)
    public static void debug(String message) {
        logger.fine(message);
    }

    // Método para logar uma exceção
    public static void logException(Exception e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
    }
}
