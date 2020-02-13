package interpreteMepa;
import java.util.*;
import java.io.*;
import excepciones.ejecucion.EjecucionException;
import analizadorLexico.*;

public class Interprete {

	/** Variables de instancia */
	Vector P; /* Región de programa */ 
	Vector M; /* Región de pila de datos */
	Vector D; /* Display */
	HashMap TL; /* Tabla de labels */
	int i; /* Contador de programa */
	int s; /* Tope de pila */

	/** Comandos */

	public void ejecutarPrograma() throws EjecucionException {
		Instruccion ins ;
		do {
			ins = elemento_P(i);
			ejecutar(ins);
		} while (!(ins.para()));
	}

	public void ejecutar (Instruccion ins) throws EjecucionException {
		int op1, op2, result;
		switch (ins.cod) {
		case Token.APCT: 
			s++;
			insertar_M(s, ins.arg1);
			i++;
			break;
		case Token.SUMA: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			result = op1 + op2;
			controlarRango(result);
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.SUST: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			result = op1 - op2;
			controlarRango(result);
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.MULT: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			result = op1 * op2;
			controlarRango(result);
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.DIVI: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			controlarDivisor(op2);
			result = (int) (op1 / op2);
			controlarRango(result);
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.MODU: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			controlarDivisor(op2);
			result = op1 % op2;
			controlarRango(result);
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.UMEN: 
			op2 = elemento_M(s);
			result = - op2;
			controlarRango(result);
			insertar_M(s, result);
			i++;
			break;

		case Token.CONJ: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			controlarBooleano (op1);
			controlarBooleano (op2);
			result = op1 & op2;
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.DISJ: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			controlarBooleano (op1);
			controlarBooleano (op2);
			result = op1 | op2 ;
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.NEGA: 
			op2 = elemento_M(s);
			controlarBooleano(op2);
			result = 1 - op2;
			insertar_M(s, result);
			i++;
			break;

		case Token.CMME: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			result = (op1 < op2)? 1 : 0;
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.CMMA:
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			result = (op1 > op2)? 1 : 0;
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.CMIG: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			result = (op1 == op2)? 1 : 0;
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.CMDG: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			result = (op1 != op2)? 1 : 0;
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.CMNI: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			result = (op1 <= op2)? 1 : 0;
			insertar_M(s-1, result);
			s --;
			i++;
			break;

		case Token.CMYI: 
			op1 = elemento_M(s-1);
			op2 = elemento_M(s);
			result = (op1 >= op2)? 1 : 0;
			insertar_M(s-1, result);
			s --;
			i++;
			break;		
		case Token.DSVS: 
			i = recuperarLabel(ins.label);
			break;
		case Token.DSVF: 
			if (elemento_M(s) == 0)
				i = recuperarLabel(ins.label);
			else
				i++;
			s--;
			break;
		case Token.NADA: 
			i++;
			break;
		case Token.LEER:
			s++;
			insertar_M(s,  leer());
			i++;
			break;
		case Token.LELN:
			s++;
			insertar_M(s,  leln());
			i++;
			break;
		case Token.IMPR: 
			System.out.print(elemento_M(s));
			s--;
			i++;
			break;
		case Token.IMLN: 
			System.out.println(elemento_M(s));
			s--;
			i++;
			break;
                 case Token.LECH:
                        s++;
			insertar_M(s, leerCaracter());
			i++;
			break;
                case Token.IMCH:
                        System.out.print((char)elemento_M(s));                        
			s--;
			i++;
			break;
                case Token.LECN:
                        s++;
			insertar_M(s, lecn());
			i++;
			break;
                case Token.IMCN: 
                        System.out.println((char)elemento_M(s));
			s--;
			i++;
			break;        
		case Token.APVL: 
			s++;
			insertar_M(s, elemento_M(elemento_D(ins.arg1)+ins.arg2));
			i++;
			break;

		case Token.ALVL: 
			insertar_M(elemento_D(ins.arg1)+ins.arg2, elemento_M(s));
			s --;
			i++;
			break;
		case Token.INPP: 
			s = -1;
			insertar_D(0, 0);
			i++;
			break;
		case Token.ENPR: 
			s++;  
			insertar_M(s, elemento_D(ins.arg1));
			insertar_D(ins.arg1, s+1);
			i++;
			break;
		case Token.LLPR: 
			s++;
			insertar_M(s, i+1);
			i = recuperarLabel(ins.label);
			break;

		case Token.RMEM: 
			s+=ins.arg1;
			i++;
			break;

		case Token.LMEM:
			s-=ins.arg1;
			i++;
			break;
		case Token.RTPR: 
			insertar_D(ins.arg1, elemento_M(s));
			i = elemento_M(s-1);
			s = s - (ins.arg2 + 2);
			break;
		case Token.PARA:
			i++;
			break;
		case Token.APDR: 
			s++;
			insertar_M(s, elemento_D(ins.arg1) + ins.arg2);
			i++;
			break;

		case Token.APVI: 
			s++;
			insertar_M(s, elemento_M(elemento_M(elemento_D(ins.arg1) + ins.arg2)));
			i++;
			break;

		case Token.ALVI: 
			insertar_M(elemento_M(elemento_D(ins.arg1) + ins.arg2) , elemento_M(s));
			s = s-1;
			i++;
			break;
		case Token.APAR: 
			insertar_M(s , elemento_M(elemento_D(ins.arg1)+ins.arg2+elemento_M(s)));
			i++;
			break;
		case Token.ALAR: 
			insertar_M(elemento_D(ins.arg1)+ins.arg2+elemento_M(s-1), elemento_M(s));
			s-=2;
			i++;
			break;
		case Token.PUAR: 
                        int aux = elemento_M(s);
                        s--;                         
			for (int indice = aux; indice<=(ins.arg3 - 1 + aux); indice++){
				s++;
				insertar_M(s, elemento_M(elemento_D(ins.arg1)+ins.arg2+indice));
			}
			i++;
			break;                       
		case Token.POAR:
                        aux = elemento_M(s - 1);
			for (int indice=(ins.arg3 - 1 + aux);indice >= aux;indice--){
				insertar_M(elemento_D(ins.arg1)+ins.arg2+indice, elemento_M(s));
				s--;
			}
			i++;
			break;                        
		case Token.APAI:
			insertar_M(s,elemento_M(elemento_M(elemento_D(ins.arg1)+ins.arg2)+elemento_M(s)));
			i++;
			break;
		case Token.ALAI:
			insertar_M(elemento_M(elemento_D(ins.arg1)+ins.arg2)+elemento_M(s-1),elemento_M(s));
			i++;
			s-=2;
			break;
		case Token.PUAI:
                        aux = elemento_M(s);
                        s--;
			for (int campo=aux;campo <= (ins.arg3 - 1 + aux);campo++){
				s++;
				insertar_M(s,elemento_M(elemento_M(elemento_D(ins.arg1)+ins.arg2)+campo));
			}
			i++;
			break;                
		case Token.POAI:
                        aux = elemento_M(s - 1);
			for (int campo= (ins.arg3 - 1 + aux); campo>= aux ;campo--){
				insertar_M(elemento_M(elemento_D(ins.arg1)+ins.arg2)+campo,elemento_M(s));
				s--;
			}
			i++;
			break;                       
		case Token.APDC:
			insertar_M(s,elemento_D(ins.arg1)+ins.arg2+elemento_M(s));
			i++;
			break;
		case Token.CONT:
			if (!((ins.arg1<=elemento_M(s))&&(elemento_M(s)<=ins.arg2)))
				throw new EjecucionException(i,"Valor fuera de rango válido");
			i++;
			break;
		case Token.DIVC:
			if (elemento_M(s)==0)
				throw new EjecucionException(i,"División por cero");
			i++;
			break;               
		}
	}

	public int elemento_M (int index) throws EjecucionException  {
		int elem;
		try {
			elem = (M.get(index)==null)? 0 : ((Integer) M.get(index)).intValue();
		}
		catch (ArrayIndexOutOfBoundsException e) {
			//throw new EjecucionException(i, "Indice fuera de rango en la pila de datos");
			elem=0;
		}

		return elem;

	}

	public int elemento_D (int index) throws EjecucionException {
		int elem=0;
		try {
			elem = (D.get(index)==null)?0:((Integer)D.get(index)).intValue();
		}
		catch (ArrayIndexOutOfBoundsException e)        {
			//throw new EjecucionException(i, "Indice fuera de rango en el display");
			/*cuando se llama a un procedimiento, se apila el valor del display 
			  del procedimiento del mismo nivel que el llamado. Si el llamador
			  esta en un nivel mas bajo, no va a existir el display de ese nivel
			  lexico. Por lo tanto, se asume que si el indice esta fuera de
			  rango, se debe a que fue llamado desde un nivel lexico inferior,
			  y no importa el valor que se devuelva porque para volver desde
			  el procedimiento se va a usar el valor de display de el nivel
			  lexico inferior y no el que se apilo.
			 * 
			 */
		}
		return elem;              
	}

	public Instruccion elemento_P (int index) throws EjecucionException {
		Instruccion elem;
		try {
			elem = (Instruccion)P.get(index);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			throw new EjecucionException(i, "Salto fuera del area del programa");
		}
		return elem;

	}

	public void insertar_M (int index, int elem) throws EjecucionException {
		try {
			M.set(index, new Integer (elem));
		}
		catch (ArrayIndexOutOfBoundsException e1){
			try {
				M.setSize(index);
				M.add(new Integer(elem));
			}
			catch (Exception e2) {
				throw new EjecucionException(i, "Overflow de pila de datos");
			}
		}

	}

	public void insertar_D (int index, int elem) throws EjecucionException {
		try {
			D.set(index, new Integer(elem));
		}
		catch (ArrayIndexOutOfBoundsException e1) {
			try {
				D.setSize(index);
				D.add(new Integer (elem));
			}
			catch (Exception e2) {
				throw new EjecucionException(i, "Overflow de display");
			}
		}

	}

	public int recuperarLabel(String label) throws EjecucionException {
		Integer valor;
		valor = (Integer) TL.get(label);
		if (valor == null) {
			throw new EjecucionException(i, "Etiqueta indefinida: " + label);
		}
		else
			return valor.intValue();
	}

	public void controlarRango (int num) throws EjecucionException
        {
        if (num < - AnalizadorLexMepa.MAXINT || num >= AnalizadorLexMepa.MAXINT)
        {
            throw new EjecucionException(i, "Numero fuera de rango: " + num);
        }
        }

    public void controlarDivisor (int div) throws EjecucionException {
    	if (div == 0) {
    		throw new EjecucionException(i, "Division por cero");
    	}
    }

    public void controlarBooleano (int bool) throws EjecucionException  {
    	if (bool != 0 && bool != 1){
    		throw new EjecucionException(i, "Los argumentos no son booleanos");
    	}
    }

    public char saltarEspacios () throws EjecucionException {
    	char c;
    	try {
    		c = (char)System.in.read();
    		while (Character.isWhitespace(c))
    			c = (char)System.in.read();
    	} // try
    	catch (IOException ioe) {
    		throw new EjecucionException(i, "Error al leer de la entrada estandar");
    	}
    	return c;
    }

    public int leln () throws EjecucionException{
    	int num;
    	String s = "";

    	try {
    		char c = saltarEspacios();
    		while (!Character.isWhitespace(c)){
    			s = s + c;
    			c = (char)System.in.read();
    		}
    		num = Integer.parseInt(s);
    	} // try
    	catch (IOException ioe)  {
    		throw new EjecucionException(i, "Error al leer de la entrada estandar");
    	}
    	catch (NumberFormatException  e1) {
    		throw new EjecucionException(i, "Formato de numero invalido");
    	}
    	controlarRango (num);
    	try{ System.in.skip(System.in.available());} catch (Exception e){}
        
    	return num;
    }//leln

    public int leer() throws EjecucionException {
    	int num;
    	String s = "";
    	try {
    		char c = saltarEspacios();
    		while (!Character.isWhitespace(c)) {
    			s = s + c;
    			c = (char)System.in.read();
    		}
    		num = Integer.parseInt(s);
    	} // try
    	catch (IOException ioe) {
    		throw new EjecucionException(i, "Error al leer de la entrada estandar");
    	}
    	catch (NumberFormatException  e1) {
    		throw new EjecucionException(i, "Formato de numero invalido");
    	}
    	controlarRango (num);
    	return num;
    }//leer

    public int leerCaracter() throws EjecucionException {
    	int num;
    	String s = "";
    	try {
    		do {
                    num =  System.in.read();
                } while((num == 10) || (num == 13)); 
    	} // try
    	catch (IOException ioe) {
    		throw new EjecucionException(i, "Error al leer de la entrada estandar");
    	}
    	catch (NumberFormatException  e1) {
    		throw new EjecucionException(i, "Formato de numero invalido");
    	}    
    	return num;
    }//leerCaracter
    
    public int lecn () throws EjecucionException{
    	int num;
    	String s = "";

    	try {
    		do {
                    num =  System.in.read();
                } while((num == 10) || (num == 13)); 
    	} // try
    	catch (IOException ioe)  {
    		throw new EjecucionException(i, "Error al leer de la entrada estandar");
    	}
    	catch (NumberFormatException  e1) {
    		throw new EjecucionException(i, "Formato de numero invalido");
    	}    
    	try{ System.in.skip(System.in.available());} catch (Exception e){}        
    	return num;
    }//lecn


    public Interprete(Vector Programa, HashMap TablaLabels) 
    {
    	P = Programa; 
    	TL = TablaLabels;
    	M = new Vector();
    	D = new Vector();
    	i = 0;
    }
}
