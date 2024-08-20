package model;

import java.sql.Timestamp;
import lombok.Data;

/**
 *
 * @author artur
 */
@Data
public class EntradaNotaModel {

    String chaveNFe;
    String xml;
    Timestamp dataHoraEntrada;
    Timestamp dataEntradaProdutos;
    String cfop;
    String status;
    String statausManifestacao;
    String xml2;
}
