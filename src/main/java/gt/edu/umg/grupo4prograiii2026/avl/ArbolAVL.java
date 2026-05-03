package gt.edu.umg.grupo4prograiii2026.avl;

import gt.edu.umg.grupo4prograiii2026.modelo.Libro;

public class ArbolAVL {

    private NodoAVL raiz;
    private int rotaciones;

    public ArbolAVL() {
        this.raiz = null;
        this.rotaciones = 0;
    }

    public NodoAVL getRaiz() {
        return raiz;
    }

    public int getRotaciones() {
        return rotaciones;
    }

    public int getAlturaArbol() {
        return altura(raiz);
    }

    private int altura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    private int maximo(int a, int b) {
        return Math.max(a, b);
    }

    private int getBalance(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL t2 = x.derecho;

        x.derecho = y;
        y.izquierdo = t2;

        y.altura = maximo(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = maximo(altura(x.izquierdo), altura(x.derecho)) + 1;

        rotaciones++;
        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL t2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = t2;

        x.altura = maximo(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = maximo(altura(y.izquierdo), altura(y.derecho)) + 1;

        rotaciones++;
        return y;
    }

    public void insertar(Libro libro) {
        raiz = insertarRecursivo(raiz, libro);
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, Libro libro) {

        if (nodo == null) {
            return new NodoAVL(libro);
        }

        if (libro.getCodigoLibro() < nodo.getLibro().getCodigoLibro()) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, libro);
        } else if (libro.getCodigoLibro() > nodo.getLibro().getCodigoLibro()) {
            nodo.derecho = insertarRecursivo(nodo.derecho, libro);
        } else {
            return nodo;
        }

        nodo.altura = 1 + maximo(altura(nodo.izquierdo), altura(nodo.derecho));

        int balance = getBalance(nodo);

        if (balance > 1 && libro.getCodigoLibro() < nodo.izquierdo.getLibro().getCodigoLibro()) {
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && libro.getCodigoLibro() > nodo.derecho.getLibro().getCodigoLibro()) {
            return rotacionIzquierda(nodo);
        }

        if (balance > 1 && libro.getCodigoLibro() > nodo.izquierdo.getLibro().getCodigoLibro()) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && libro.getCodigoLibro() < nodo.derecho.getLibro().getCodigoLibro()) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public NodoAVL buscar(int codigoLibro) {
        return buscarRecursivo(raiz, codigoLibro);
    }

    private NodoAVL buscarRecursivo(NodoAVL nodo, int codigoLibro) {

        if (nodo == null || nodo.getLibro().getCodigoLibro() == codigoLibro) {
            return nodo;
        }

        if (codigoLibro < nodo.getLibro().getCodigoLibro()) {
            return buscarRecursivo(nodo.izquierdo, codigoLibro);
        } else {
            return buscarRecursivo(nodo.derecho, codigoLibro);
        }
    }

    public void eliminar(int codigoLibro) {
        raiz = eliminarRecursivo(raiz, codigoLibro);
    }

    private NodoAVL eliminarRecursivo(NodoAVL nodo, int codigoLibro) {

        if (nodo == null) {
            return nodo;
        }

        if (codigoLibro < nodo.getLibro().getCodigoLibro()) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, codigoLibro);
        } else if (codigoLibro > nodo.getLibro().getCodigoLibro()) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, codigoLibro);
        } else {

            if ((nodo.izquierdo == null) || (nodo.derecho == null)) {
                NodoAVL temp = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;

                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }

            } else {
                NodoAVL temp = nodoMinimo(nodo.derecho);
                nodo.setLibro(temp.getLibro());
                nodo.derecho = eliminarRecursivo(nodo.derecho, temp.getLibro().getCodigoLibro());
            }
        }

        if (nodo == null) {
            return nodo;
        }

        nodo.altura = 1 + maximo(altura(nodo.izquierdo), altura(nodo.derecho));

        int balance = getBalance(nodo);

        if (balance > 1 && getBalance(nodo.izquierdo) >= 0) {
            return rotacionDerecha(nodo);
        }

        if (balance > 1 && getBalance(nodo.izquierdo) < 0) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && getBalance(nodo.derecho) <= 0) {
            return rotacionIzquierda(nodo);
        }

        if (balance < -1 && getBalance(nodo.derecho) > 0) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL nodoMinimo(NodoAVL nodo) {
        NodoAVL actual = nodo;

        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }

        return actual;
    }
}