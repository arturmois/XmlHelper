package main;

import controller.XMLProcessor;
import java.io.File;

/**
 *
 * @author artur moisés
 */
public class Main {

    public static void main(String[] args) throws Exception {
        File xmlDirectory = new File("C:\\Users\\artur\\Downloads\\B333\\NFCE_XML_ZYKHJBUSXC");
        XMLProcessor controller = new XMLProcessor();

        if (xmlDirectory.isDirectory()) {
            File[] xmlFiles = xmlDirectory.listFiles((dir, name) -> name.endsWith(".xml"));
            if (xmlFiles != null) {
                for (File xmlFile : xmlFiles) {
                    controller.processXML(xmlFile);
                }
            } else {
                System.out.println("No XML files found in the directory.");
            }
        } else {
            System.out.println("The provided path is not a directory.");
        }
    }
}