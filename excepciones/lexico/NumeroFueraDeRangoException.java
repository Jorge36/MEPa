package excepciones.lexico;

public class NumeroFueraDeRangoException extends LexicoException 
{
	public String lexema;
	public int linea;
	
	public NumeroFueraDeRangoException(String lexema,int linea)
	{
		this.lexema=lexema;
		this.linea=linea;
	}
	
	public String toString()
	{
		return "N�mero fuera de rango (-32768..32767) ( "+lexema+" en linea "+linea+" )";
	}
}
