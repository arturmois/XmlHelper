package model;

import lombok.Data;

/**
 *
 * @author artur moisés
 */
@Data
public class PagamentoModel {

    private int numNFe;
    private String tipoDePagamento;
    private double valor;
    private double troco;
    private String cnpj;
    private String tipoDeCartao;
    private String bandeiraDoCartao;
    private String numAutorizacao;
    private int codigoNFCeOrigem;
    private int parcelas;
    private int codigoCFeOrigem;
    private int codigoTipoPagOutros;
    private String tipoPix;

    // Getters and setters
}
