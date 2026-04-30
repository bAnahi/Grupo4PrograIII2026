package gt.edu.umg.grupo4prograiii2026.arbolb;

import gt.edu.umg.grupo4prograiii2026.modelo.Libro;

public class ArbolB {

    private NodoB raiz;
    private int divisiones = 0;
    private int fusiones = 0;
    private int redistribuciones = 0;

    public ArbolB() {
        raiz = new NodoB(true);
    }

    public int getDivisiones() {
        return divisiones;
    }

    public int getFusiones() {
        return fusiones;
    }

    public int getRedistribuciones() {
        return redistribuciones;
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

    public void eliminar(int codigoLibro) {

        eliminarRecursivo(raiz, codigoLibro);

        if (raiz.numClaves == 0) {

            if (raiz.hoja) {
                raiz = null;
            } else {
                raiz = raiz.hijos[0];
            }
        }
    }

    private void eliminarRecursivo(NodoB nodo, int codigo) {

        int idx = encontrarClave(nodo, codigo);

        if (idx < nodo.numClaves && nodo.claves[idx].getCodigoLibro() == codigo) {

            if (nodo.hoja) {
                eliminarDeHoja(nodo, idx);
            } else {
                eliminarDeNoHoja(nodo, idx);
            }

        } else {

            if (nodo.hoja) {
                return;
            }

            boolean bandera = (idx == nodo.numClaves);

            if (nodo.hijos[idx].numClaves < 2) {
                llenar(nodo, idx);
            }

            if (bandera && idx > nodo.numClaves) {
                eliminarRecursivo(nodo.hijos[idx - 1], codigo);
            } else {
                eliminarRecursivo(nodo.hijos[idx], codigo);
            }
        }
    }

    private int encontrarClave(NodoB nodo, int codigo) {

        int idx = 0;

        while (idx < nodo.numClaves
                && nodo.claves[idx].getCodigoLibro() < codigo) {
            idx++;
        }

        return idx;
    }

    private void eliminarDeHoja(NodoB nodo, int idx) {

        for (int i = idx + 1; i < nodo.numClaves; i++) {
            nodo.claves[i - 1] = nodo.claves[i];
        }

        nodo.numClaves--;
    }

    
    private void eliminarDeNoHoja(NodoB nodo, int idx) {

        Libro clave = nodo.claves[idx];

        if (nodo.hijos[idx].numClaves >= 2) {

            Libro pred = obtenerPredecesor(nodo, idx);
            nodo.claves[idx] = pred;

            eliminarRecursivo(nodo.hijos[idx], pred.getCodigoLibro());

        } else if (nodo.hijos[idx + 1].numClaves >= 2) {

            Libro succ = obtenerSucesor(nodo, idx);
            nodo.claves[idx] = succ;

            eliminarRecursivo(nodo.hijos[idx + 1], succ.getCodigoLibro());

        } else {

            fusionar(nodo, idx);
            eliminarRecursivo(nodo.hijos[idx], clave.getCodigoLibro());
        }
    }

    private Libro obtenerPredecesor(NodoB nodo, int idx) {

        NodoB actual = nodo.hijos[idx];

        while (!actual.hoja) {
            actual = actual.hijos[actual.numClaves];
        }

        return actual.claves[actual.numClaves - 1];
    }

    private Libro obtenerSucesor(NodoB nodo, int idx) {

        NodoB actual = nodo.hijos[idx + 1];

        while (!actual.hoja) {
            actual = actual.hijos[0];
        }

        return actual.claves[0];
    }

    private void llenar(NodoB nodo, int idx) {

        if (idx != 0 && nodo.hijos[idx - 1].numClaves >= 2) {

            tomarDeAnterior(nodo, idx);

        } else if (idx != nodo.numClaves
                && nodo.hijos[idx + 1].numClaves >= 2) {

            tomarDeSiguiente(nodo, idx);

        } else {

            if (idx != nodo.numClaves) {
                fusionar(nodo, idx);
            } else {
                fusionar(nodo, idx - 1);
            }
        }
    }

    private void tomarDeAnterior(NodoB nodo, int idx) {

        NodoB hijo = nodo.hijos[idx];
        NodoB hermano = nodo.hijos[idx - 1];

        for (int i = hijo.numClaves - 1; i >= 0; i--) {
            hijo.claves[i + 1] = hijo.claves[i];
        }

        hijo.claves[0] = nodo.claves[idx - 1];

        nodo.claves[idx - 1] = hermano.claves[hermano.numClaves - 1];

        hijo.numClaves++;
        hermano.numClaves--;

        redistribuciones++;
    }

    private void tomarDeSiguiente(NodoB nodo, int idx) {

        NodoB hijo = nodo.hijos[idx];
        NodoB hermano = nodo.hijos[idx + 1];

        hijo.claves[hijo.numClaves] = nodo.claves[idx];

        nodo.claves[idx] = hermano.claves[0];

        for (int i = 1; i < hermano.numClaves; i++) {
            hermano.claves[i - 1] = hermano.claves[i];
        }

        hijo.numClaves++;
        hermano.numClaves--;

        redistribuciones++;
    }

    private void fusionar(NodoB nodo, int idx) {

        NodoB hijo = nodo.hijos[idx];
        NodoB hermano = nodo.hijos[idx + 1];

        hijo.claves[1] = nodo.claves[idx];

        for (int i = 0; i < hermano.numClaves; i++) {
            hijo.claves[i + 2] = hermano.claves[i];
        }

        hijo.numClaves += hermano.numClaves + 1;

        for (int i = idx + 1; i < nodo.numClaves; i++) {
            nodo.claves[i - 1] = nodo.claves[i];
        }

        nodo.numClaves--;

        fusiones++;
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
