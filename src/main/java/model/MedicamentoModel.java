/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * @author Matheus Silva Medeiros
 */
public class MedicamentoModel {
    
    private String tipo;
    private String codigo;
    private String dataValidade;
    private String idLote;
    
    public MedicamentoModel() {
        this.tipo = "";
        this.codigo = "";
        this.dataValidade = "";
        this.idLote = "";
    }

    public MedicamentoModel(String tipo, String codigo, String dataValidade, String idLote) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.dataValidade = dataValidade;
        this.idLote = idLote;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }
    
    public String getNuProduto() {
        return tipo + codigo;
    }

    @Override
    public String toString() {
        return "Medicamento{" + "tipo=" + tipo + ", codigo=" + codigo + ", dataValidade=" + dataValidade + ", idLote=" + idLote + '}';
    }
}