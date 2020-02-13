package excepciones.sintactico;

import analizadorLexico.Token;

public class SintacticoException extends Exception {
    private String mensaje;
    private Token token;
	public SintacticoException(String mensaje,Token token){
		this.mensaje=mensaje;
		this.token=token;
	}
	public String toString(){
		return "Se esperaba '"+mensaje+"' y se leyó "+token;
	}
}
