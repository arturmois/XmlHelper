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
public enum Tributacao {

    /**
     *
     */
    _00,

    /**
     *
     */
    _10,

    /**
     *
     */
    _20,

    /**
     *
     */
    _30,

    /**
     *
     */
    _40,

    /**
     *
     */
    _41,

    /**
     *
     */
    _50,

    /**
     *
     */
    _51,

    /**
     *
     */
    _60,
    
    /**
     *
     */
    _61,

    /**
     *
     */
    _70,

    /**
     *
     */
    _90,

    /**
     *
     */
    _P10,

    /**
     *
     */
    _P90,

    /**
     *
     */
    ST,

    /**
     *
     */
    _541,

    /**
     *
     */
    _101,

    /**
     *
     */
    _102,

    /**
     *
     */
    _103,

    /**
     *
     */
    _201,

    /**
     *
     */
    _202,

    /**
     *
     */
    _203,

    /**
     *
     */
    _300,

    /**
     *
     */
    _400,

    /**
     *
     */
    _500,

    /**
     *
     */
    _900;
    
    /**
     *
     * @param codigo
     * @return
     */
    public Tributacao getTributacao(String codigo){
        if (codigo.equals("_00")){
            return Tributacao._00;
        }
        else if (codigo.equals("_10")){
            return Tributacao._10;
        }
        else if (codigo.equals("_20")){
            return Tributacao._20;
        }
        else if (codigo.equals("_30")){
            return Tributacao._30;
        }
        else if (codigo.equals("_40")){
            return Tributacao._40;
        }
        else if (codigo.equals("_41")){
            return Tributacao._41;
        }
        else if (codigo.equals("_50")){
            return Tributacao._50;
        }
        else if (codigo.equals("_51")){
            return Tributacao._51;
        }
        else if (codigo.equals("_60")){
            return Tributacao._60;
        }
        else if (codigo.equals("_61")){
            return Tributacao._61;
        }
        else if (codigo.equals("_70")){
            return Tributacao._70;
        }
        else if (codigo.equals("_90")){
            return Tributacao._90;
        }
        else if (codigo.equals("_P10")){
            return Tributacao._P10;
        }
        else if (codigo.equals("_P90")){
            return Tributacao._P90;
        }
        else if (codigo.equals("_541")){
            return Tributacao._541;
        }
        else if (codigo.equals("_101")){
            return Tributacao._101;
        }
        else if (codigo.equals("_102")){
            return Tributacao._102;
        }
        else if (codigo.equals("_103")){
            return Tributacao._103;
        }
        else if (codigo.equals("_201")){
            return Tributacao._201;
        }
        else if (codigo.equals("_202")){
            return Tributacao._202;
        }
        else if (codigo.equals("_203")){
            return Tributacao._203;
        }
        else if (codigo.equals("_300")){
            return Tributacao._300;
        }
        else if (codigo.equals("_400")){
            return Tributacao._400;
        }
        else if (codigo.equals("_500")){
            return Tributacao._500;
        }
        else if (codigo.equals("_900")){
            return Tributacao._900;
        }
        else if (codigo.equals("ST")){
            return Tributacao.ST;
        }
        else{
            return null;
        }
    }
    
    /**
     *
     * @param tributacao
     * @return
     */
    public String getString(Tributacao tributacao){
        if (null == tributacao){
            return "Erro";
        }
        else switch (tributacao) {
            case _00:
                return "00 - Tributada Integralmente";
            case _10:
                return "10 - Tributada e com cobrança de ICMS por substituição tributária";
            case _20:
                return "20 - Com redução de base de cálculo";
            case _30:
                return "30 - Isenta e não tributada de ICMS por substituição tributária";
            case _40:
                return "40 - Isenta";
            case _41:
                return "41 - Não tributada";
            case _50:
                return "50 - Suspensão";
            case _51:
                return "51 - Deferimento";
            case _60:
                return "60 - ICMS Cobrado anteriormente por substituição tributária";
            case _61:
                return "61 - Tributação monofásica sobre combustíveiscobrada anteriormente";
            case _70:
                return "70 - Com redução de base de cálculo e cobrança de ICMS por substituição tributária";
            case _90:
                return "90 - Outras";
            case _P10:
                return "P10 - ICMSPart com CST = 10";
            case _P90:
                return "P90 - ICMSPart com CST = 90";
            case _541:
                return "541 - ICMSST";
            case _101:
                return "101 - Tributada pelo Simples Nacional com permissão de crédito(ECF=T)";
            case _102:
                return "102 - Tributada pelo Simples Nacional sem permissão de crédito(ECF=T)";
            case _103:
                return "103 - Isenção do ICMS no Simples Nacional para faixa de receita bruta";
            case _201:
                return "201 - Tributada pelo Simples Nacional com Permissão de crédito e com cobrança de ICMS por substituição tributária";
            case _202:
                return "202 - Tributada pelo Simples Nacional sem Permissão de crédito e com cobrança de ICMS por substituição tributária";
            case _203:
                return "203 - Isenção do ICMS no Simples Nacional para faixa de receita bruta e com cobrança de ICMS por substituição tributária(ECF=I)";
            case _300:
                return "300 - Imune(ECF=I)";
            case _400:
                return "400 - Não tributada pelo Simples Nacional(ECF=N)";
            case _500:
                return "500 - ICMS cobrado anteriormente por substituição tributária(substituído) ou por antecipação(ECF=F)";
            case _900:
                return "900 - Outros";
            case ST:
                return "541 - ICMSST";
            default:
                return "Erro";
        }
    }
}