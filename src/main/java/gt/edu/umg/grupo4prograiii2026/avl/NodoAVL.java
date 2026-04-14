package gt.edu.umg.grupo4prograiii2026.avl;

import gt.edu.umg.grupo4prograiii2026.modelo.Libro;

public class NodoAVL {

    Libro libro;
    NodoAVL izquierdo;
    NodoAVL derecho;
    int altura;

    public NodoAVL(Libro libro) {
        this.libro = libro;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1;
    }
}