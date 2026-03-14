
package gt.edu.umg.grupo4prograiii2026;


public class HojaCalculo {
    
    
    Celda inicio;

    public HojaCalculo() {
        inicio = null;
    }

    public void insertarPrimeraCelda(int fila, int columna, String valor) {
        if (inicio == null) {
            inicio = new Celda(fila, columna, valor);
        } else {
            System.out.println("La hoja ya tiene una celda inicial");
        }
    }

    public void insertarEnFila(int fila, int columna, String valor) {

        Celda nueva = new Celda(fila, columna, valor);

        if (inicio == null) {
            inicio = nueva;
            return;
        }

        if (buscarCelda(fila, columna) != null) {
            System.out.println("Ya existe un celda en esa posicion");
            return;
        }

        Celda actual = inicio;
        Celda anterior = null;

        while (actual != null && actual.fila == fila) {
            anterior = actual;
            actual = actual.derecha;
        }

        if (anterior != null) {
            anterior.derecha = nueva;
        }
    }

    public void insertarEnColumna(int fila, int columna, String valor) {

        Celda nueva = new Celda(fila, columna, valor);

        if (inicio == null) {
            inicio = nueva;
            return;
        }

        if (buscarCelda(fila, columna) != null) {
            System.out.println("Ya existe una celda en esa posicion");
            return;
        }

        Celda actual = inicio;
        Celda anterior = null;

        while (actual != null && actual.columna == columna) {
            anterior = actual;
            actual = actual.abajo;
        }

        if (anterior != null) {
            anterior.abajo = nueva;
        }
    }

    public Celda buscarCelda(int fila, int columna) {

        Celda actualFila = inicio;

        while (actualFila != null) {

            Celda actual = actualFila;

            while (actual != null) {

                if (actual.fila == fila && actual.columna == columna) {
                    return actual;
                }

                actual = actual.derecha;
            }

            actualFila = actualFila.abajo;
        }

        return null;
    }

    public void mostrarFila() {
        Celda actual = inicio;

        while (actual != null) {
            System.out.print("[" + actual.fila + "," + actual.columna + "] = " + actual.valor + "   ");
            actual = actual.derecha;
        }

        System.out.println();
    }

    public void mostrarColumna() {
        Celda actual = inicio;

        while (actual != null) {
            System.out.println("[" + actual.fila + "," + actual.columna + "] = " + actual.valor);
            actual = actual.abajo;
        }
    }
}