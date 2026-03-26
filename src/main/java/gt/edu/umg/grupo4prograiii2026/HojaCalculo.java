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

    /*public void eliminarCelda(int fila, int columna) {

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
                        anterior.derecha = actual.derecha;
                        
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
    }*/
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
                        if (anterior != null) {
                            anterior.abajo = actual.abajo;
                        } else if (anteriorFila != null) {
                            anteriorFila.abajo = actual.abajo;
                        } else {
                            inicio = actual.abajo;
                        }
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

    public void imprimirHoja() {

        if (inicio == null) {
            System.out.println("La hoja esta vacia");
            return;
        }

        int maxFila = 0;
        int maxColumna = 0;

        Celda f = inicio;
        while (f != null) {
            Celda c = f;
            while (c != null) {
                if (c.fila > maxFila) {
                    maxFila = c.fila;
                }
                if (c.columna > maxColumna) {
                    maxColumna = c.columna;
                }
                c = c.derecha;
            }
            f = f.abajo;
        }

        int[] anchos = new int[maxColumna + 1];

        f = inicio;
        while (f != null) {
            Celda c = f;
            while (c != null) {
                int len = c.valor.length();
                if (len > anchos[c.columna]) {
                    anchos[c.columna] = len;
                }
                c = c.derecha;
            }
            f = f.abajo;
        }

        for (int i = 1; i <= maxColumna; i++) {
            if (anchos[i] < 5) {
                anchos[i] = 5;
            }
        }

        String separador = construirSeparador(anchos, maxColumna);

        System.out.println(separador);
        System.out.print("|     |");
        for (int col = 1; col <= maxColumna; col++) {
            System.out.print(centrar("C" + col, anchos[col]) + "|");
        }
        System.out.println();
        System.out.println(separador);

        for (int fila = 1; fila <= maxFila; fila++) {
            System.out.print("| F" + fila + " |");
            for (int col = 1; col <= maxColumna; col++) {
                Celda celda = buscarCelda(fila, col);
                String texto = (celda != null) ? celda.valor : "---";
                System.out.print(centrar(texto, anchos[col]) + "|");
            }
            System.out.println();
            System.out.println(separador);
        }
    }

    private String construirSeparador(int[] anchos, int maxColumna) {
        StringBuilder sb = new StringBuilder("+-----+");
        for (int i = 1; i <= maxColumna; i++) {
            for (int j = 0; j < anchos[i] + 2; j++) {
                sb.append('-');
            }
            sb.append('+');
        }
        return sb.toString();
    }

    private String centrar(String texto, int ancho) {
        int total = ancho + 2;
        int izq = (total - texto.length()) / 2;
        int der = total - texto.length() - izq;
        return " ".repeat(izq) + texto + " ".repeat(der);
    }
}
