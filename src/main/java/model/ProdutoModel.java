package model;

import enums.CSTIPIDeCompra;
import enums.CSTIPIDeVenda;
import enums.FinalidadeDoProduto;
import enums.Nacionalidade;
import enums.Tributacao;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author artur
 */
@Data
public class ProdutoModel {

    private int codigo;
    private String codigoBarras;
    private String referencia;
    private String descricao;
    private String fornecedor;
    private Double custo;
    private Double lucro;
    private Double venda;
    private Double quantidade;
    private String unidade;
    private String marca;
    private String grupo;
    private String NCM;
    private String CEST;
    private String CFOP;
    private Double IPIDeCompra;
    private Double IPIDeVenda;
    private CSTIPIDeCompra CSTIpiDeCompra;
    private CSTIPIDeVenda CSTIpiDeVenda;
    private Tributacao tributacao;
    private Nacionalidade nacionalidade;
    private FinalidadeDoProduto finalidade;
    private boolean inativo;
    private BigDecimal pisEntrada;
    private BigDecimal pisSaida;
    private BigDecimal cofinsEntrada;
    private BigDecimal cofinsSaida;
    private String CSTPisCofinsEntrada;
    private String CSTPisCofinsSaida;
    private BigDecimal FCPAliquota;
    private String codigoFornecedor;
    private String impressoraServidor;
    private String caminhoDaImagem;
    private Double vendaAtacado;
    private boolean abateEstoque;
    private boolean comissaoIndividual;
    private BigDecimal porcentagemComissaoIndividual;
    private boolean avisaEstoqueMinimo;
    private BigDecimal quantidadeMinima;
    private MedicamentoModel medicamento;
    private boolean pis_cofins_custom;
    private boolean considerarQtdsGrades;
    private boolean considerarQtdMinimaGrade;
    private String referenciaGrade;
    private BigDecimal ICMSFronteira;
    private String impressoraTerminal;
    private boolean isTipoBateria;
    private double peso;
}
