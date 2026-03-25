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
            System.out.println("Ya existe una celda");
            return;
        }

        Celda actual = inicio;
        Celda anterior = null;

        while (actual != null && actual.fila < fila) {
            anterior = actual;
            actual = actual.abajo;
        }

        if (anterior == null) {
            nueva.abajo = inicio;
            inicio = nueva;
        } else {
            nueva.abajo = actual;
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

    public void modificarCelda(int fila, int columna, String nuevoValor) {
        Celda celda = buscarCelda(fila, columna);

        if (celda != null) {
            celda.valor = nuevoValor;
            System.out.println("Celda modificada correctamente ");
        } else {
            System.out.println("La celda no existe ");
        }
    }

    public void eliminarCelda(int fila, int columna) {

        Celda actualFila = inicio;
        Celda anteriorFila = null;

        while (actualFila != null) {

            Celda actual = actualFila;
            Celda anterior = null;

            while (actual != null) {

                if (actual.fila == fila && actual.columna == columna) {

                    if (anterior == null) {
                        if (anteriorFila == null) {
                            inicio = actual.derecha;
                        } else {
                            anteriorFila.abajo = actual.derecha;
                        }
                    } else {
                        anterior.derecha = actual.derecha;
                    }

                    if (actual.abajo != null) {
                    }

                    System.out.println("Celda eliminada correctamente");
                    return;
                }

                anterior = actual;
                actual = actual.derecha;
            }

            anteriorFila = actualFila;
            actualFila = actualFila.abajo;
        }

        System.out.println("Celda no encontrada");
    }
    public int sumarCeldas(int fila1, int columna1, int fila2, int columna2) {

    Celda c1 = buscarCelda(fila1, columna1);
    Celda c2 = buscarCelda(fila2, columna2);

    if (c1 == null || c2 == null) {
        System.out.println("Una de las celdas no existe");
        return 0;
    }

    int valor1 = Integer.parseInt(c1.valor);
    int valor2 = Integer.parseInt(c2.valor);

    return valor1 + valor2;
}
   public int sumarRango(int filaInicio, int filaFin, int columna) {

    int suma = 0;

    for (int i = filaInicio; i <= filaFin; i++) {
        Celda celda = buscarCelda(i, columna);

        if (celda != null) {
            suma += Integer.parseInt(celda.valor);
        }
    }

    return suma;
}
   public int restarCeldas(int fila1, int columna1, int fila2, int columna2) {

    Celda c1 = buscarCelda(fila1, columna1);
    Celda c2 = buscarCelda(fila2, columna2);

    if (c1 == null || c2 == null) {
        System.out.println("Una de las celdas no existe");
        return 0;
    }

    int valor1 = Integer.parseInt(c1.valor);
    int valor2 = Integer.parseInt(c2.valor);

    return valor1 - valor2;
}

public int multiplicarCeldas(int fila1, int columna1, int fila2, int columna2) {

    Celda c1 = buscarCelda(fila1, columna1);
    Celda c2 = buscarCelda(fila2, columna2);

    if (c1 == null || c2 == null) {
        System.out.println("Una de las celdas no existe");
        return 0;
    }

    int valor1 = Integer.parseInt(c1.valor);
    int valor2 = Integer.parseInt(c2.valor);

    return valor1 * valor2;
}

public double dividirCeldas(int fila1, int columna1, int fila2, int columna2) {

    Celda c1 = buscarCelda(fila1, columna1);
    Celda c2 = buscarCelda(fila2, columna2);

    if (c1 == null || c2 == null) {
        System.out.println("Una de las celdas no existe");
        return 0;
    }

    double valor1 = Double.parseDouble(c1.valor);
    double valor2 = Double.parseDouble(c2.valor);

    if (valor2 == 0) {
        System.out.println("No se puede dividir entre cero");
        return 0;
    }

    return valor1 / valor2;
}
}
