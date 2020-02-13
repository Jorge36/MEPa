package excepciones.lexico;

public class FormatoLabelErroneoException extends LexicoException 
{
	public String id;
	public int linea;
	
	public FormatoLabelErroneoException(String id,int linea)
	{
		this.id=id;
		this.linea=linea;	
	}
	
	public String toString(){
		return "Formato de identificador erroneo: ( "+id+" en linea "+linea+" )";
	}
}
