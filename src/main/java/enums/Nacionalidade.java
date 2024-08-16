/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author Servetech1
 */
public enum Nacionalidade {

    /**
     *
     */
    _0,

    /**
     *
     */
    _1,

    /**
     *
     */
    _2,

    /**
     *
     */
    _3,

    /**
     *
     */
    _4,

    /**
     *
     */
    _5,

    /**
     *
     */
    _6,

    /**
     *
     */
    _7,

    /**
     *
     */
    _8;
    
    /**
     *
     * @param nacionalidade
     * @return
     */
    public String getString(Nacionalidade nacionalidade){
        if (null == nacionalidade){
            return "Erro";
        }
        else switch (nacionalidade) {
            case _0:
                return "0 - Nacional, exceto as indicadas nos códigos 3, 4, 5 e 8";
            case _1:
                return "1 - Estrangeira - Importação direta, exceto a indicada no campo 6";
            case _2:
                return "2 - Estrangeira - Adquirida no mercado interno, exceto a indicada no código 7";
            case _3:
                return "3 - Nacional, mercadoria ou bem com Conteúdo de Importação superior a 40%(quarenta por cento) e inferior ou igual a 70%(setenta por cento)";
            case _4:
                return "4 - Nacional, cuja produção tenha sido feita em conformidade com os processos produtivos básicos de que tratam o Decreto-Lei nº 288/67, e as Leis nºs 8.248/91, 8.387/91, 10.176/01 e 11.484/07";
            case _5:
                return "5 - Nacional, mercadoria ou bem com Conteúdo de Importação inferior ou igual a 40%(quarenta por cento)";
            case _6:
                return "6 - Estrangeira - Importação direta, sem similar nacional, constante em lista de Resolução CAMEX";
            case _7:
                return "7 - Estrangeira - Adquirida no mercado interno, sem similar nacional, constante em lista de Resoluçao CAMEX";
            case _8:
                return "8 - Nacional, mercadoria ou bem com Conteúdo de Importação superior a 70%(setenta por cento), (Ajuste Sinief 15/2013 vig. 01/08/2013)";
            default:
                return "Erro";
        }
    }
}
