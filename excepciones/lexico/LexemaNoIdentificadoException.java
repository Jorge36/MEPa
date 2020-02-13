package excepciones.lexico;

public class LexemaNoIdentificadoException extends LexicoException 
{
	public String lexema;
	public int linea;
	
	public LexemaNoIdentificadoException(String lexema,int linea)
	{
		this.lexema=lexema;
		this.linea=linea;
	}
	
	public String toString()
	{
		return "Lexema no identificado: ( '"+lexema+"' en linea "+linea+" )";
	}
}
