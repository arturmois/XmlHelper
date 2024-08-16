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
public enum FinalidadeDoProduto {

    /**
     *
     */
    VENDA {
        public String toString() {
            return "VENDA";
        }
    },

    /**
     *
     */
    MATERIA_PRIMA {
       public String toString() {
            return "MATERIA_PRIMA";
        } 
    },

    /**
     *
     */
    SERVICO {
        public String toString() {
            return "SERVICO";
        }
    };
}
