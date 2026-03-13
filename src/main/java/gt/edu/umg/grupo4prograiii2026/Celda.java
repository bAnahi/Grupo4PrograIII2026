
package gt.edu.umg.grupo4prograiii2026;


public class Celda {
    
    int fila;
    int columna;
    String valor;

    Celda derecha;
    Celda abajo;

    public Celda(int fila, int columna, String valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.derecha = null;
        this.abajo = null;
    }
}
