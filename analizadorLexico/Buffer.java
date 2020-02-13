 /*
 * Buffer.java
 * 
 * Maneja un buffer dividido en dos mitades de N = 4096 bytes (4KB)
 * N = numero de caracteres por bloque
 * No lee un archivo caracter a caracter, almacena en el buffer N caracteres.
 *
 */
package analizadorLexico;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Buffer {
    
    /** Variables de Clase */
	
    public static final char eof = (char)0;
    
    
    /** Variables de Instancia */
    
    /* 4 KB = 4096 Bytes (lecturas de 4KB, segun el sistema de archivos
     * pueden ser 1, 2 o 4 bloques de sistema) */
    /* tamaño de Buffer: (2*4KB + 2) (2 espacios para los eof adicionales */
    final private int tamLectBuffer = 4096;
    final private int maxBuffer = (2*tamLectBuffer)+2;
    final private int mitBuffer = (maxBuffer/2)-1; /* primer mitad desde 0 a 4096*/
    final private int finBuffer = maxBuffer-1; /* segunda mitad desde 4097 a 8193*/
    
    private char[] buffer = new char[maxBuffer];
    
    /* Manejadores del Archivo y del Buffer */
    private FileReader lectorArchivo;
    private BufferedReader lectorBuffer;
    
    /* apuntadores del buffer inicialmente en primera posicion */
    private int iniLex = 0, delan = -1; 
    
    private boolean cargaBuffer = true;
    
    /** Fin de declaracion de Variables */
    
    // esEof() agrega eof en el buffer en el lugar 
    // correspondiente al fin de archivo.
    private void esEof(int mitad, int fin) {
        if (mitad == 1){ 
            if (fin < tamLectBuffer)  
                if (fin == -1) buffer[0] = eof;
                else buffer[fin] = eof;
        }
        else /* mitad == 2 */{
            if (fin < tamLectBuffer) 
                if (fin == -1) buffer[mitBuffer + 1] = eof;
                else buffer[mitBuffer + 1 + fin] = eof;
        }
    }
            
    
    public void abrirArchivo (String f) throws FileNotFoundException {
    	lectorArchivo = new FileReader(f);
        lectorBuffer = new BufferedReader(lectorArchivo, tamLectBuffer);
        buffer[mitBuffer] = eof; /* agrega centinelas eof */
        buffer[finBuffer] = eof; /* agrega centinelas eof */
    }
    
    
    public void cerrarArchivo () throws IOException {        
    	lectorBuffer.close();
        lectorArchivo.close();
    }
    
    // avanzar() retorna la posicion siguiente a pos, si es el fin
    // de una mitad, sigue con el comienzo de la proxima mitad
    public int avanzar(int pos) {
        pos++;
        if (buffer[pos] == eof){
            if (pos == mitBuffer)
                pos++; // Sigue leyendo la segunda mitad
            else if (pos == finBuffer)
                     pos = 0; // Vuelve al comienzo de la primer mitad
        }
        return pos;
    }
    
    // retroceder() coloca el apuntador delan en una posicion menos 
    // de la actual. Esto implica que si delant es -1, debe retroceder
    // a la ultima posicion de la segunda mitad. 
    // Si retrocede a la ultima posicion del buffer anterior, el proximo 
    // buffer ya fue cargado
    public void retroceder() {
        delan--;
        if (delan == -1){ // debe apuntar al anterior del fin de la segunda mitad
            delan = finBuffer - 1;
            cargaBuffer = false;
        }
        else if (delan == mitBuffer){ //
                delan--;
                cargaBuffer = false;
             }
    }
    
    // proxCaracter() retorna el caracter que apuntara avanzar(delan), cargando
    // la nueva porcion del buffer si es necesario.
    public char proxCaracter() throws IOException { 
        int fin;
        delan = avanzar(delan);
        if ((delan == 0) && (cargaBuffer)){ /* cargo primera mitad del buffer */
            fin = lectorBuffer.read(buffer, delan, tamLectBuffer);
            esEof(1,fin);
            }
        else if ((delan == mitBuffer+1) && (cargaBuffer)){ /* cargo segunda mitad del buffer */
        		fin = lectorBuffer.read(buffer, delan, tamLectBuffer);
                esEof(2,fin);
            }
        cargaBuffer = true; // actualizo cargaBuffer       
        return buffer[delan];
    }
    
    // lexema() retorna el lexema del token identificado entre los apuntadores iniLex y delan
    public String lexema() {
        String lex = new String("");
        while (iniLex != delan) {
            lex = lex + buffer[iniLex];
            iniLex = avanzar(iniLex);
        }
        lex = lex + buffer[delan]; // ultimo caracter del token
        iniLex = avanzar(iniLex); //
        return lex;
    }
    
    /** Constructor */
    
    public Buffer() {
    }
    
}
