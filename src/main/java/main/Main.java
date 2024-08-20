package main;

import controller.EntradaNotaController;
import controller.NFCeController;
import java.io.File;

/**
 *
 * @author artur moisés
 */
public class Main {

    public static void main(String[] args) throws Exception {
        File xmlDirectory = new File("C:\\Users\\artur\\Downloads\\B333\\NFCE_XML_ZYKHJBUSXC");

        File xmlEntradaNota = new File("C:\\Users\\artur\\Downloads\\B333\\NFE_XML_404032_20240723090629");

        NFCeController nfceController = new NFCeController();
        EntradaNotaController entradaNotaController = new EntradaNotaController();

        if (xmlDirectory.isDirectory()) {
            File[] xmlFiles = xmlDirectory.listFiles((dir, name) -> name.endsWith(".xml"));
            File[] EntradaxmlFiles = xmlEntradaNota.listFiles((dir, name) -> name.endsWith(".xml"));
            if (EntradaxmlFiles != null) {
                for (File xmlFile : EntradaxmlFiles) {
                    entradaNotaController.processXML(xmlFile);
                }
            } else {
                System.out.println("No XML files found in the directory.");
            }
        } else {
            System.out.println("The provided path is not a directory.");
        }
    }
}
