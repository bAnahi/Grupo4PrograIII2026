package gt.edu.umg.grupo4prograiii2026.arbolb;

import gt.edu.umg.grupo4prograiii2026.modelo.Libro;

public class ArbolB {

    private NodoB raiz;
    private int divisiones = 0;

    public ArbolB() {
        raiz = new NodoB(true);
    }

    public int getDivisiones() {
        return divisiones;
    }

    public Libro buscar(int codigo) {
        return buscarRec(raiz, codigo);
    }

    private Libro buscarRec(NodoB nodo, int codigo) {

        int i = 0;

        while (i < nodo.numClaves && codigo > nodo.claves[i].getCodigoLibro()) {
            i++;
        }

        if (i < nodo.numClaves && nodo.claves[i].getCodigoLibro() == codigo) {
            return nodo.claves[i];
        }

        if (nodo.hoja) {
            return null;
        }

        return buscarRec(nodo.hijos[i], codigo);
    }

    public void insertar(Libro libro) {

        NodoB r = raiz;

        if (r.numClaves == NodoB.M - 1) {

            NodoB nuevaRaiz = new NodoB(false);

            nuevaRaiz.hijos[0] = r;

            dividirNodo(nuevaRaiz, 0);

            raiz = nuevaRaiz;

            insertarNoLleno(nuevaRaiz, libro);

        } else {
            insertarNoLleno(r, libro);
        }
    }

    private void insertarNoLleno(NodoB nodo, Libro libro) {

        int i = nodo.numClaves - 1;

        if (nodo.hoja) {

            while (i >= 0 && libro.getCodigoLibro() < nodo.claves[i].getCodigoLibro()) {
                nodo.claves[i + 1] = nodo.claves[i];
                i--;
            }

            nodo.claves[i + 1] = libro;
            nodo.numClaves++;

        } else {

            while (i >= 0 && libro.getCodigoLibro() < nodo.claves[i].getCodigoLibro()) {
                i--;
            }

            i++;

            if (nodo.hijos[i].numClaves == NodoB.M - 1) {

                dividirNodo(nodo, i);

                if (libro.getCodigoLibro() > nodo.claves[i].getCodigoLibro()) {
                    i++;
                }
            }

            insertarNoLleno(nodo.hijos[i], libro);
        }
    }

    private void dividirNodo(NodoB padre, int indice) {

        NodoB lleno = padre.hijos[indice];
        NodoB nuevo = new NodoB(lleno.hoja);

        int mitad = (NodoB.M - 1) / 2;

        nuevo.numClaves = mitad;

        for (int j = 0; j < mitad; j++) {
            nuevo.claves[j] = lleno.claves[j + mitad + 1];
        }

        if (!lleno.hoja) {
            for (int j = 0; j < mitad + 1; j++) {
                nuevo.hijos[j] = lleno.hijos[j + mitad + 1];
            }
        }

        lleno.numClaves = mitad;

        for (int j = padre.numClaves; j >= indice + 1; j--) {
            padre.hijos[j + 1] = padre.hijos[j];
        }

        padre.hijos[indice + 1] = nuevo;

        for (int j = padre.numClaves - 1; j >= indice; j--) {
            padre.claves[j + 1] = padre.claves[j];
        }

        padre.claves[indice] = lleno.claves[mitad];

        padre.numClaves++;

        divisiones++;
    }

    public int altura() {

        int altura = 0;

        NodoB actual = raiz;

        while (!actual.hoja) {
            actual = actual.hijos[0];
            altura++;
        }

        return altura + 1;
    }

}
