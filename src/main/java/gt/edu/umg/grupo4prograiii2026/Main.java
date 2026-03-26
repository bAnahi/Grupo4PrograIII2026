package gt.edu.umg.grupo4prograiii2026;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HojaCalculo hoja = new HojaCalculo();
        Scanner s = new Scanner(System.in);

        insercionEjemplo(hoja);
        boolean op;
        do {
            op = menu(hoja, s);
        } while (!op);


        /*hoja.insertarPrimeraCelda(1, 1, "10");
        hoja.insertarEnColumna(2, 1, "52");
        hoja.insertarEnColumna(3, 1, "70");
        
        System.out.println("Columna original: ");
        hoja.mostrarColumna();*/
 /* hoja.modificarCelda(2, 1, "100");
        System.out.println("Despues de modificar: ");
        hoja.mostrarColumna();

        Celda encontrada = hoja.buscarCelda(2, 1);*/
 /*hoja.eliminarCelda(1,1);
        System.out.println("Eliminado correctamente ");
        hoja.mostrarColumna();*/

 /*  if (encontrada != null) {
            System.out.println("Celda ya encontrada: " + encontrada.valor);
        } else {
            System.out.println("Celda no encontrada");
        }*/
    }

    public static void insercionEjemplo(HojaCalculo hoja) {
        System.out.println("***************** Excel - Hoja de Calculo *****************");
        hoja.insertarPrimeraCelda(1, 1, "10");
        hoja.insertarEnColumna(2, 1, "52");
        hoja.insertarEnColumna(3, 1, "70");

        System.out.println("Se han ingresado datos de Ejemplo...\n");
        hoja.mostrarColumna();
        System.out.println("");
        hoja.imprimirHoja();
    }

    public static boolean menu(HojaCalculo hoja, Scanner s) {
        boolean salir = false;
        int op = 0;
        try {

            System.out.println("\nOpciones en Hoja de Calculo:");
            System.out.println();
            System.out.println("1. Insertar Valores En Celda");
            System.out.println("2. Sumar Celdas");
            System.out.println("3. Sumar Rango");
            System.out.println("4. Restar Celdas");
            System.out.println("5. Multiplicar Celdas");
            System.out.println("6. Division de Celdas");
            System.out.println("7. Eliminar Celda");
            System.out.println("8. Modificar Celda");
            System.out.println("9. Salir");
            System.out.println();
            System.out.print("Seleccione una Opcion: ");
            op = s.nextInt();
        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
        }

        switch (op) {
            case 1:
                MenuInsertar(hoja, s);
                break;
            case 2:
                sumarCeldas(hoja, s);
                break;
            case 3:
                sumarRango(hoja, s);
                break;
            case 4:
                restarCeldas(hoja, s);
                break;
            case 5:
                multiplicarCeldas(hoja, s);
                break;
            case 6:
                divCeldas(hoja, s);
                break;
            case 7:
                eliminarCelda(hoja, s);
                break;
            case 8:
                modCelda(hoja, s);
                break;
            case 9:
                salir = true;
                System.out.println("Saliendo....");
                break;
            default:
                System.out.println("Opcion Incorrecta");
                break;
        }
        s.nextLine();
        return salir;
    }

    public static void MenuInsertar(HojaCalculo hoja, Scanner s) {
        try {
            System.out.print("\nNumero de Fila: ");
            int fila = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna = s.nextInt();
            s.nextLine();
            System.out.print("Ingrese el Valor a Insertar: ");
            String valor = s.next();
            hoja.insertarEnColumna(fila, columna, valor);
            //System.out.print("Columna original: ");
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
        }

    }

    public static void sumarCeldas(HojaCalculo hoja, Scanner s) {
        try {
            System.out.println("Celda 1: ");
            System.out.print("Numero de Fila: ");
            int fila1 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna1 = s.nextInt();

            System.out.println("Celda 2: ");
            System.out.print("Numero de Fila: ");
            int fila2 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna2 = s.nextInt();

            int resultado = hoja.sumarCeldas(fila1, columna1, fila2, columna2);
            System.out.println("\nResultado de la suma: " + resultado);
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
        }

    }

    public static void sumarRango(HojaCalculo hoja, Scanner s) {
        try {
            System.out.print("Fila Inicial de Celda: ");
            int fila1 = s.nextInt();
            System.out.print("Fila Final de Celda: ");
            int fila2 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna1 = s.nextInt();

            int resultadoRango = hoja.sumarRango(fila1, fila2, columna1);
            System.out.println("\nResultado de la suma por rango: " + resultadoRango);
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
        }

    }

    public static void restarCeldas(HojaCalculo hoja, Scanner s) {
        try {
            System.out.println("Celda 1: ");
            System.out.print("Numero de Fila: ");
            int fila1 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna1 = s.nextInt();

            System.out.println("Celda 2: ");
            System.out.print("Numero de Fila: ");
            int fila2 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna2 = s.nextInt();

            int resta = hoja.restarCeldas(fila1, columna1, fila2, columna2);
            System.out.println("\nResultado de la resta: " + resta);
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
        }

    }

    public static void multiplicarCeldas(HojaCalculo hoja, Scanner s) {
        try {
            System.out.println("Celda 1: ");
            System.out.print("Numero de Fila: ");
            int fila1 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna1 = s.nextInt();

            System.out.println("Celda 2: ");
            System.out.print("Numero de Fila: ");
            int fila2 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna2 = s.nextInt();

            int multiplicacion = hoja.multiplicarCeldas(fila1, columna1, fila2, columna2);
            System.out.println("\nResultado de la multiplicación: " + multiplicacion);
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
        }

    }

    public static void divCeldas(HojaCalculo hoja, Scanner s) {
        try {
            System.out.println("Celda 1: ");
            System.out.print("Numero de Fila: ");
            int fila1 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna1 = s.nextInt();

            System.out.println("Celda 2: ");
            System.out.print("Numero de Fila: ");
            int fila2 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna2 = s.nextInt();

            double division = hoja.dividirCeldas(fila1, columna1, fila2, columna2);
            System.out.println("\nResultado de la división: " + division);
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
        }

    }

    public static void eliminarCelda(HojaCalculo hoja, Scanner s) {
        try {
            System.out.print("Numero de Fila: ");
            int fila1 = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna1 = s.nextInt();
            hoja.eliminarCelda(fila1, columna1);
            System.out.println("");
            hoja.imprimirHoja();
        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
        }

    }

    public static void modCelda(HojaCalculo hoja, Scanner s) {
        try {
            System.out.print("\nNumero de Fila: ");
            int fila = s.nextInt();
            System.out.print("Numero de Columna: ");
            int columna = s.nextInt();
            s.nextLine();
            System.out.print("Ingrese el Nuevo Valor: ");
            String nuevoValor = s.next();
            hoja.modificarCelda(fila, columna, nuevoValor);
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
        }

    }

}
