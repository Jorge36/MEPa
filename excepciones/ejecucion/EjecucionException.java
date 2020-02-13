package excepciones.ejecucion;

public class EjecucionException extends Exception {

int instr;
String tipo;

    public EjecucionException(int instr,String tipo) {
        this.instr=instr+1;
        this.tipo=tipo;
    }
    public String toString(){
		return "Error de ejecucion en instruccion "+instr+":"+tipo+"\n";
	}
}

