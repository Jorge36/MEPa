package interpreteMepa;
import java.io.FileNotFoundException;
import java.io.IOException;
import excepciones.ejecucion.EjecucionException;
import excepciones.lexico.LexicoException;
import excepciones.sintactico.SintacticoException;
import analizadorSintactico.*;

public class Mepa {
    
    /**
     * Creates a new instance of Mepa 
     */
    public Mepa() {
    }
    
    public static void main(String[] args) {
        AnalizadorSintMepa AS;
        Interprete I;
        
        try {
	        if (args.length != 1) {
	            System.out.println("Error: Cantidad invalida de argumentos.");
	            System.exit(0);
	        }
	        else { 
	              AS = new AnalizadorSintMepa(args[0]);
	              I = new Interprete (AS.P, AS.TL);
	              I.ejecutarPrograma();
	              System.out.println("\n\nEjecucion exitosa!");
	        }
        }//end try
        catch (EjecucionException e){
        	System.out.println(e);
        } // main
        catch (FileNotFoundException e) {
        	System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (LexicoException e) {
			System.out.println(e);
		} catch (SintacticoException e) {
			System.out.println(e);
		}
    }
}
