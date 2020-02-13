package analizadorSintactico;
import analizadorLexico.*;
import interpreteMepa.Instruccion; 
import java.io.*;
import java.util.*;
import excepciones.sintactico.*;
import excepciones.lexico.*;

public class AnalizadorSintMepa {
    
    /** Variables de Instancia */
    AnalizadorLexMepa AL;
    Token t;
    int ip; /* instruccion pointer comienza en 0 */
    public HashMap TL; /* tabla de labels */ 
    public Vector P; /* vector de instrucciones */ 
    
    /** Constructor */
    public AnalizadorSintMepa(String pathIn)throws FileNotFoundException,IOException,LexicoException,SintacticoException
    {
		AL=new AnalizadorLexMepa(pathIn);
		TL=new HashMap();
		P =new Vector();
                t = AL.getToken();
                programa();
    }
    
    /** Comandos */
    public void programa() throws SintacticoException, LexicoException, IOException{
        ppio(); 
        lista_instr(); 
        fin();
    }

    public void ppio() throws SintacticoException, LexicoException, IOException  
    {
        ip = 0; //acción semántica
        if (t.tipoToken == Token.LABEL){
                insertarLabel(t.lexema); //acción semántica
                t = AL.getToken();
                if (t.tipoToken == Token.INPP)
                    t = AL.getToken();
                else
                    throw new SintacticoException("INPP",t);
        }//end if label
        else if (t.tipoToken == Token.INPP)
                t = AL.getToken();
        else
                throw new SintacticoException("INPP o label",t);
        insertarInstr(Token.INPP, 0, 0, 0, null); //acción semántica
    }
    
    public void fin () throws SintacticoException, LexicoException, IOException  {
        ip ++; //acción semántica
        if (t.tipoToken == Token.PARA)
                t = AL.getToken();
        else throw new SintacticoException("PARA",t);
        insertarInstr(Token.PARA, 0, 0, 0, null); //acción semántica
   }
    
    public void lista_instr () throws SintacticoException, LexicoException, IOException  {
        if (t.tipoToken == Token.LABEL){
                ip ++; //acción semantica
                insertarLabel(t.lexema); //acción semántica
                t = AL.getToken();
                if (t.esInstruccion()){
                    instruccion();
                    lista_instr();
                }
                else if (t.tipoToken==Token.PARA) {ip --;}
                else throw new SintacticoException("instrucción",t);
        }//end if label
        else if (t.esInstruccion()){
                ip ++; //acción semántica
                instruccion();
                lista_instr();
        }
        //else empty 
    }

    public void instruccion() throws SintacticoException, LexicoException, IOException  {
        int instr, arg1, arg2, arg3; //atts semánticos
        
        if (t.esInstruccionCon0Args()) {
                insertarInstr(t.tipoToken, 0, 0, 0, null); //acción semántica
                t = AL.getToken();
        }
        else if (t.esInstruccionCon1ArgEntero()){
                instr = t.tipoToken; //acción semántica
                t = AL.getToken();
                arg1 = constante(); //la excepción la tira constante()
                insertarInstr(instr, arg1, 0, 0, null); //acción semántica
        }
        else if (t.esInstruccionCon1ArgLabel()){
                instr = t.tipoToken; //acción semántica
                t = AL.getToken();
                if (t.tipoToken == Token.LABEL){
                    insertarInstr(instr, 0, 0, 0, t.lexema); //acción semántica
                    t = AL.getToken();
                }                  
                else throw new SintacticoException("label",t);
        }
        else if (t.esInstruccionCon2Args()){
                instr = t.tipoToken; //acción semántica
                t = AL.getToken();
                arg1 = constante();
                if (t.tipoToken == Token.COMA)
                    t = AL.getToken();
                else throw new SintacticoException(",",t);
                arg2 = constante();
                insertarInstr(instr, arg1, arg2, 0, null); //acción semántica
         }
        else if (t.esInstruccionCon3Args()){
                instr = t.tipoToken; //acción semántica
                t = AL.getToken();
                arg1 = constante();
                if (t.tipoToken == Token.COMA)
                    t = AL.getToken();
                else throw new SintacticoException(",",t);
                arg2 = constante();
                if (t.tipoToken == Token.COMA)
                    t = AL.getToken();
                else throw new SintacticoException(",",t);
                arg3 = constante();
                insertarInstr(instr, arg1, arg2, arg3, null); //acción semántica
        }
        else throw new SintacticoException("instrucción",t);
   }
    
    public int constante () throws SintacticoException, LexicoException, IOException  {
       int cte=0;
       
       if (t.tipoToken == Token.MENOS){
                t = AL.getToken();
                if (t.tipoToken == Token.NUMERO){
                    try {cte = -Integer.parseInt(t.lexema);}  
                    catch (NumberFormatException e) {}
                    t = AL.getToken();
                }
                else throw new SintacticoException("numero",t);
       }
       else if (t.tipoToken == Token.NUMERO){
    	   try {cte = Integer.parseInt(t.lexema);}  
           catch (NumberFormatException e) {}
           t = AL.getToken();
       }
       else throw new SintacticoException("constante",t);
       return cte; //acción semántica
    }
    
    public void insertarLabel(String label) throws SintacticoException{ //ver si no es error semantico
        if (TL.get(label) == null)
            TL.put(label, new Integer(ip));
        else throw new SintacticoException("una etiqueta que no este repetida",t);
    }
    
    public void insertarInstr(int instr, int arg1, int arg2, int arg3, String l){
        P.add(new Instruccion(instr, arg1, arg2, arg3, l)); 
    }
}
   

