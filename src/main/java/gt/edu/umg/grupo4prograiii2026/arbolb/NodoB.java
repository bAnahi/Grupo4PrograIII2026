
package gt.edu.umg.grupo4prograiii2026.arbolb;

import gt.edu.umg.grupo4prograiii2026.modelo.Libro;


public class NodoB {
    
    public static final int M = 5;
    int numClaves;
    Libro[] claves;
    NodoB[] hijos;
    boolean hoja;
    
    public NodoB(boolean hoja){
        this.hoja = hoja;
        claves = new Libro[M - 1];
        hijos = new NodoB[M];
        numClaves = 0;
    }
    
}
