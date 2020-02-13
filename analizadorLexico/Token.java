package analizadorLexico;
import java.util.HashMap;

public class Token 
{
    
    //  CONSTANTES PARA REPRESENTAR TOKENS
    public static final int EOF = 0;
    public static final int NUMERO = 1;
    public static final int LABEL = 2;
    public static final int COMA = 3;
    public static final int MENOS = 4;

    // INSTRUCCIONES CON 0 ARGUMENTOS
    public static final int INPP = 5;
    public static final int PARA = 6;
    public static final int MULT = 7;
    public static final int SUMA = 8;
    public static final int SUST = 9;
    public static final int DIVI = 10;
    public static final int MODU = 11;
    public static final int UMEN = 12;
    public static final int CONJ = 13;
    public static final int DISJ = 14;
    public static final int NEGA = 15;
    public static final int CMME = 16;
    public static final int CMMA = 17;
    public static final int CMIG = 18;
    public static final int CMDG = 19;
    public static final int CMNI = 20;
    public static final int CMYI = 21;
    public static final int NADA = 22;
    public static final int LEER = 23;
    public static final int LELN = 24;
    public static final int IMPR = 25;
    public static final int IMLN = 26;
    public static final int DIVC = 27;
    public static final int LECH = 28;
    public static final int IMCH = 29;
    public static final int LECN = 30;
    public static final int IMCN = 31;
    
    
    // INSTRUCCIONES CON 1 ARGUMENTO LABEL
    public static final int DSVS = 32;
    public static final int DSVF = 33;
    public static final int LLPR = 34;
    
    // INSTRUCCIONES CON 1 ARGUMENTO ENTERO
    public static final int APCT = 35;
    public static final int ENPR = 36;
    public static final int RMEM = 37;
    public static final int LMEM = 38;
    
    // INSTRUCCIONES CON 2 ARGUMENTOS
    public static final int APVL = 39;
    public static final int ALVL = 40;
    public static final int RTPR = 41;
    public static final int APDR = 42;
    public static final int APVI = 43;
    public static final int ALVI = 44;
    public static final int APAR = 45;
    public static final int ALAR = 46;
    public static final int APAI = 47;
    public static final int ALAI = 48;
    public static final int APDC = 49;
    public static final int CONT = 50;
    
    // INSTRUCCIONES CON 3 ARGUMENTOS
    public static final int PUAR = 51;
    public static final int POAR = 52;
    public static final int PUAI = 53;
    public static final int POAI = 54;
    
    private static HashMap tablaTokens; 
    static{
	    tablaTokens = new HashMap (55);
	    tablaTokens.put(new Integer (APCT),"APCT");
            tablaTokens.put(new Integer (SUMA),"SUMA");
            tablaTokens.put(new Integer (SUST),"SUST");
            tablaTokens.put(new Integer (MULT),"MULT");
	    tablaTokens.put(new Integer (DIVI),"DIVI");
	    tablaTokens.put(new Integer (MODU),"MODU");
	    tablaTokens.put(new Integer (UMEN),"UMEN");
	    tablaTokens.put(new Integer (CONJ),"CONJ");
	    tablaTokens.put(new Integer (DISJ),"DISJ");
	    tablaTokens.put(new Integer (NEGA),"NEGA");
	    tablaTokens.put(new Integer (CMME),"CMME");
	    tablaTokens.put(new Integer (CMMA),"CMMA");
	    tablaTokens.put(new Integer (CMIG),"CMIG");
	    tablaTokens.put(new Integer (CMDG),"CMDG");
	    tablaTokens.put(new Integer (CMNI),"CMNI");
	    tablaTokens.put(new Integer (CMYI),"CMYI");	    
	    tablaTokens.put(new Integer (DSVS),"DSVS");
	    tablaTokens.put(new Integer (DSVF),"DSVF");
	    tablaTokens.put(new Integer (NADA),"NADA");
	    tablaTokens.put(new Integer (LEER),"LEER");
	    tablaTokens.put(new Integer (LELN),"LELN");
	    tablaTokens.put(new Integer (IMPR),"IMPR");
	    tablaTokens.put(new Integer (IMLN),"IMLN");
	    tablaTokens.put(new Integer (APVL),"APVL");
	    tablaTokens.put(new Integer (COMA),"COMA");
	    tablaTokens.put(new Integer (ALVL),"ALVL");
	    tablaTokens.put(new Integer (INPP),"INPP");
	    tablaTokens.put(new Integer (ENPR),"MEN");
	    tablaTokens.put(new Integer (LLPR),"LLPR");
	    tablaTokens.put(new Integer (RMEM),"RMEM");
	    tablaTokens.put(new Integer (LMEM),"LMEM");
	    tablaTokens.put(new Integer (RTPR),"RTPR");
	    tablaTokens.put(new Integer (PARA),"PARA");
	    tablaTokens.put(new Integer (APDR),"APDR");
	    tablaTokens.put(new Integer (APVI),"APVI");
	    tablaTokens.put(new Integer (ALVI),"ALVI");
	    tablaTokens.put(new Integer (MENOS),"MENOS");
	    tablaTokens.put(new Integer (APAR),"APAR");
	    tablaTokens.put(new Integer (ALAR),"ALAR");
	    tablaTokens.put(new Integer (PUAR),"PUAR");
	    tablaTokens.put(new Integer (POAR),"POAR");
	    tablaTokens.put(new Integer (APAI),"APAI");
	    tablaTokens.put(new Integer (ALAI),"ALAI");
	    tablaTokens.put(new Integer (PUAI),"PUAI");
	    tablaTokens.put(new Integer (POAI),"POAI");
	    tablaTokens.put(new Integer (APDC),"APDC");
	    tablaTokens.put(new Integer (CONT),"CONT");
	    tablaTokens.put(new Integer (DIVC),"DIVC");
	    tablaTokens.put(new Integer (NUMERO),"NUMERO");
	    tablaTokens.put(new Integer (LABEL),"LABEL");
	    tablaTokens.put(new Integer (EOF),"EOF");
            tablaTokens.put(new Integer (LECH),"LECH");
            tablaTokens.put(new Integer (IMCH),"IMCH");
            tablaTokens.put(new Integer (LECN),"LECN");
            tablaTokens.put(new Integer (IMCN),"IMCN");	  
    }
    
    public int tipoToken;
    public String lexema;   
    public int linea; 
    
    public static String nombreToken(int cod)
    {
    	return ((String) tablaTokens.get(new Integer(cod)));
    }
    public String toString()
    {
    	String nombreToken = ((String) tablaTokens.get(new Integer(tipoToken)));
    	return ("token: '"+nombreToken+"' lexema: '"+lexema.toString()+"' en linea: '"+String.valueOf(linea)+"'");
    }
    
    public boolean esInstruccion()
    {
    	return (tipoToken > 6 && tipoToken < 55);
    }
    
    public boolean esInstruccionCon0Args()
    {
    	return(tipoToken > 6 && tipoToken < 32);
    }
    
    public boolean esInstruccionCon1ArgLabel()
    {
    	return(tipoToken > 31 && tipoToken < 35);
    }
    
    public boolean esInstruccionCon1ArgEntero()
    {
    	return(tipoToken > 34 && tipoToken < 39);
    }  
    
    public boolean esInstruccionCon2Args()
    {
    	return(tipoToken > 38 && tipoToken < 51);
    }
    
    public boolean esInstruccionCon3Args()
    {
    	return(tipoToken > 50 && tipoToken < 55);
    }
    
    public Token(int tipoToken, String lexema, int linea) 
    {
     this.tipoToken=tipoToken;
     this.lexema=lexema;
     this.linea=linea;    
    }            
}
