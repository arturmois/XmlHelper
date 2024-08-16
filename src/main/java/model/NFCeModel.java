package model;

import lombok.Data;

/**
 *
 * @author artur moisés
 */
@Data
public class NFCeModel {

    private String tipoOperacao;
    private int codigoCliente;
    private String cfopDaNota;
    private String tipoDeCobranca;
    private double totalLiquido;
    private int quantidadeDuplicatas;
    private int intervaloEntreDuplicatas;
    private String aPartirDe;
    private String emissao;
    private String chaveDaNotaOriginal;
    private String chaveDaNotaDeEntrada;
    private String informacoesComplementares;
    private String status;
    private String ultimoProcessamento;
    private double desconto;
    private String xml;
    private String cnpj;
    private int ambiente;
    private int atendente;
    private int numeroFaixa;
    private boolean importouTudoOs;
    private int codigoOperador;
    private int vOutro;
    private String terminal;
    private int codigoOs;

}
