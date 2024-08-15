package model;

import lombok.Data;

/**
 *
 * @author artur moisés
 */
@Data
public class NFCeProdutoModel {

    private int codigoProduto;
    private int codigoNfe;
    private double quantidade;
    private double valor;
    private String unidade;
    private int cfop;
    private String descricao;
    private String codBarras;
    private double valorCusto;
    private String refGrade;
    private int responsavel;
    private String marca;
    private String codigoEdital;
    private int itemNoPedido;
    private String observacao;
    private int item;
}
