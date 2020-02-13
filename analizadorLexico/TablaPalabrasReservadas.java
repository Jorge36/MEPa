package analizadorLexico;
import java.util.*;

/*
 * Posee un arreglo de Simbolos Especiales y otro de Lexemas.
 * La operacion getTipo(String p) retorna el Lexema asociado
 * al simbolo p.
 */ 

public class TablaPalabrasReservadas {
	private static HashMap tablaSE; 
    static{
    	tablaSE = new HashMap(47);
    	tablaSE.put("apct",new Integer(Token.APCT));
    	tablaSE.put("suma",new Integer(Token.SUMA));
    	tablaSE.put("sust",new Integer(Token.SUST));
    	tablaSE.put("mult",new Integer(Token.MULT));
    	tablaSE.put("divi",new Integer(Token.DIVI));
    	tablaSE.put("modu",new Integer(Token.MODU));
    	tablaSE.put("umen",new Integer(Token.UMEN));
    	tablaSE.put("conj",new Integer(Token.CONJ));
    	tablaSE.put("disj",new Integer(Token.DISJ));
    	tablaSE.put("nega",new Integer(Token.NEGA));
    	tablaSE.put("cmme",new Integer(Token.CMME));
    	tablaSE.put("cmma",new Integer(Token.CMMA));
    	tablaSE.put("cmig",new Integer(Token.CMIG));
    	tablaSE.put("cmdg",new Integer(Token.CMDG));
    	tablaSE.put("cmni",new Integer(Token.CMNI));
    	tablaSE.put("cmyi",new Integer(Token.CMYI));    	
    	tablaSE.put("dsvs",new Integer(Token.DSVS));
    	tablaSE.put("dsvf",new Integer(Token.DSVF));
    	tablaSE.put("nada",new Integer(Token.NADA));
     	tablaSE.put("leer",new Integer(Token.LEER));
     	tablaSE.put("leln",new Integer(Token.LELN));
     	tablaSE.put("impr",new Integer(Token.IMPR));
     	tablaSE.put("imln",new Integer(Token.IMLN));
     	tablaSE.put("apvl",new Integer(Token.APVL));
     	tablaSE.put("alvl",new Integer(Token.ALVL));
     	tablaSE.put("inpp",new Integer(Token.INPP));
     	tablaSE.put("enpr",new Integer(Token.ENPR));
     	tablaSE.put("llpr",new Integer(Token.LLPR));
     	tablaSE.put("rmem",new Integer(Token.RMEM));
     	tablaSE.put("lmem",new Integer(Token.LMEM));
     	tablaSE.put("rtpr",new Integer(Token.RTPR));
     	tablaSE.put("para",new Integer(Token.PARA));
     	tablaSE.put("apdr",new Integer(Token.APDR));
     	tablaSE.put("apvi",new Integer(Token.APVI));
     	tablaSE.put("alvi",new Integer(Token.ALVI));
     	tablaSE.put("apar",new Integer(Token.APAR));
     	tablaSE.put("alar",new Integer(Token.ALAR));
     	tablaSE.put("puar",new Integer(Token.PUAR));
     	tablaSE.put("poar",new Integer(Token.POAR));
     	tablaSE.put("apai",new Integer(Token.APAI));
     	tablaSE.put("alai",new Integer(Token.ALAI));
     	tablaSE.put("puai",new Integer(Token.PUAI));
     	tablaSE.put("poai",new Integer(Token.POAI));
     	tablaSE.put("apdc",new Integer(Token.APDC));
     	tablaSE.put("cont",new Integer(Token.CONT));
     	tablaSE.put("divc",new Integer(Token.DIVC));
        tablaSE.put("lech",new Integer (Token.LECH));
        tablaSE.put("imch",new Integer (Token.IMCH));
        tablaSE.put("lecn",new Integer (Token.LECN));
        tablaSE.put("imcn",new Integer (Token.IMCN));	  
    }
    
    public static Integer getTipo(String id)
    {
		Integer tipoToken = ((Integer)tablaSE.get(id.toLowerCase()));
		return tipoToken;
    }	
}
