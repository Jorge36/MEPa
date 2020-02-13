package interpreteMepa;

import analizadorLexico.Token;

public class Instruccion {
    
    /** Variables de Instancia */
    public int cod; /* corresponde a la constante definida en la clase Token */
    public String label;
    public int arg1; 
    public int arg2; 
    public int arg3;
    
    /** Consultas */
    
    public boolean para(){
        return (cod == Token.PARA);
    }
    
    /** Comandos */
    
    /* metodo auxiliar de prueba */
    public void imprimir(){
        System.out.println(Token.nombreToken(cod)+" " + label + " " + arg1 + " " + arg2);
    }
           
    /** Constructores */
    
    public Instruccion(int codigo, int argu1, int argu2,int argu3, String l) {
        cod = codigo; 
        arg1 = argu1; 
        arg2 = argu2; 
        arg3 = argu3;
        label = l;
    }
    
}
