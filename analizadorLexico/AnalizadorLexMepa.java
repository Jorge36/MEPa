package analizadorLexico;
import java.io.FileNotFoundException;
import java.io.IOException;
import excepciones.lexico.FormatoLabelErroneoException;
import excepciones.lexico.LexemaNoIdentificadoException;
import excepciones.lexico.NumeroFueraDeRangoException;

public class AnalizadorLexMepa {

	int estado;
	int numeroLinea = 1;
	Buffer buf;
	public static final int MAXINT = 32768;
	
	public AnalizadorLexMepa(String path) throws FileNotFoundException 
        {
        buf = new Buffer();
        buf.abrirArchivo(path);
        }   
        
	public Token getToken()	throws IOException, 
	LexemaNoIdentificadoException,  
	FormatoLabelErroneoException,
	NumeroFueraDeRangoException
        {
        char c;
        estado=0;                       
        while (true){
            switch (estado) {
                case 0: /* CADENA VACÍA */ 
                    c = buf.proxCaracter();
                    if (c == '-')
                    	return new Token (Token.MENOS,buf.lexema(),numeroLinea);
                    else if (c == ',')
                    	return new Token (Token.COMA,buf.lexema(),numeroLinea);
                    else if (c == 'L' || c == 'l') estado = 1;
                    else if (Character.isLetter (c)) estado = 2;
                    else if (Character.isDigit(c)) estado = 3;
                    else if (c == 10) 
                    {
                    	numeroLinea++;
                        buf.lexema(); 
                        estado=0;
                    }
                    else if (Character.isWhitespace(c)) {buf.lexema();estado=0;} 
                    else if (c == Buffer.eof) {buf.cerrarArchivo(); return new Token(Token.EOF,"fin de archivo",numeroLinea);} // finaliza ejecucion
                    else throw new LexemaNoIdentificadoException(String.valueOf(c),numeroLinea);
                    break;
            /****************************/
                case 1: // label
                	c = buf.proxCaracter();
                	if (Character.isDigit(c))
                		estado = 1;
                	else if (Character.isLetter(c))
                		estado=2;
                	else 
                        {
                    	 buf.retroceder();
                    	 String lexema=buf.lexema();
                    	 return new Token (Token.LABEL,lexema,numeroLinea);
                	}
                break;
                case 2: /* letra(letra|digito)* */ 
                    if (Character.isLetterOrDigit(buf.proxCaracter())) 
                    	estado = 2;
                    else 
                    {
                    	buf.retroceder();
                    	String lexema=buf.lexema();
                    	if (TablaPalabrasReservadas.getTipo(lexema)==null)
                    		throw new LexemaNoIdentificadoException(lexema,numeroLinea);
                    	else return new Token (TablaPalabrasReservadas.getTipo(lexema).intValue(),lexema,numeroLinea);
                    }
                    break;
                case 3: /* digito (digito)* */ 
                    c = buf.proxCaracter();
                    if (Character.isDigit(c)) 
                        estado = 3;
                    else if (Character.isLetter(c))
                    	estado=4;
                    else {
                    	buf.retroceder(); 
                    	int numero;
                    	String lexema=buf.lexema();
                        try {
                        	numero = Integer.parseInt(lexema);}  
                        catch (NumberFormatException e) {
                            throw new NumeroFueraDeRangoException(lexema,numeroLinea);
                        }
                        if (numero <= MAXINT) return new Token (Token.NUMERO, lexema,numeroLinea);
                        else /* Error: número fuera de rango */
                        	throw new NumeroFueraDeRangoException(lexema,numeroLinea);
                    }
                    break;
                case 4: //  leyendo identificador no valido 
                	if (Character.isLetterOrDigit(buf.proxCaracter())) 
                    	estado = 4;
                    else {
                    	buf.retroceder();
                    	String lexema=buf.lexema();
                    	throw new FormatoLabelErroneoException(lexema,numeroLinea);
                    }
                    break;
            } // switch (estado)
        } // while (true)
            
    } // proxToken()
	public int lineaActual() {
		
		return numeroLinea;
	}
    
}
