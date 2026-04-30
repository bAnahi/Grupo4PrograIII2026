package gt.edu.umg.grupo4prograiii2026;

import gt.edu.umg.grupo4prograiii2026.arbolb.ArbolB;
import gt.edu.umg.grupo4prograiii2026.avl.ArbolAVL;
import gt.edu.umg.grupo4prograiii2026.modelo.Libro;
import gt.edu.umg.grupo4prograiii2026.controladores.bibliotecaDigital;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            salir = mostrarMenuPrincipal(s);
        }

        s.close();
        System.out.println("\n✓ Programa finalizado correctamente");
    }

    public static boolean mostrarMenuPrincipal(Scanner scanner) {
        System.out.println("\n-------------------------------------------");
        System.out.println("|   MENÚ PRINCIPAL - UMG PROGRAMACIÓN III    |");
        System.out.println("----------------------------------------------");
        System.out.println("\n1. Hoja de Cálculo (Entregable 1)");
        System.out.println("2. Biblioteca Digital - AVL y Árbol B");
        System.out.println("3. Salir");
        System.out.print("\nSeleccione una opción: ");

        String opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1":
                ejecutarHojaCalculo(scanner);
                return false;

            case "2":
                bibliotecaDigital bd = new bibliotecaDigital();
                bd.mostrarMenu(scanner);
                return false;

            case "3":
                return true;

            default:
                System.out.println(" Opción inválida");
                return false;
        }
    }

    public static void ejecutarHojaCalculo(Scanner scanner) {
        HojaCalculo hoja = new HojaCalculo();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("MÓDULO: HOJA DE CÁLCULO");
        System.out.println("=".repeat(60));

        insercionEjemplo(hoja);

        boolean salir;
        do {
            salir = menu(hoja, scanner);
        } while (!salir);
    }

    public static void insercionEjemplo(HojaCalculo hoja) {
        System.out.println("***************** Excel - Hoja de Calculo *****************");
        hoja.insertarPrimeraCelda(1, 1, "10");
        hoja.insertarEnColumna(2, 1, "52");
        hoja.insertarEnColumna(3, 1, "70");

        System.out.println("Se han ingresado datos de ejemplo...\n");
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
            System.out.println("9. Probar AVL y Arbol B");
            System.out.println("10. Salir");
            System.out.println();
            System.out.print("Seleccione una opcion: ");
            op = s.nextInt();
        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
            s.nextLine();
            return false;
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
                probarAVL();
                probarB();
                break;
            case 10:
                salir = true;
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion incorrecta");
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
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
            s.nextLine();
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
            s.nextLine();
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
            s.nextLine();
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
            s.nextLine();
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
            System.out.println("\nResultado de la multiplicacion: " + multiplicacion);
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
            s.nextLine();
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
            System.out.println("\nResultado de la division: " + division);
            System.out.println("");
            hoja.imprimirHoja();

        } catch (Exception e) {
            System.out.println("Error con el ingreso de los datos.");
            s.nextLine();
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
            s.nextLine();
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
            s.nextLine();
        }
    }

    public static void probarAVL() {
        ArbolAVL avl = new ArbolAVL();

        long inicio = System.nanoTime();

        avl.insertar(new Libro(30, "ISBN30", "Libro 30", "Autor A", 2020, "Historia"));
        avl.insertar(new Libro(20, "ISBN20", "Libro 20", "Autor B", 2021, "Ciencia"));
        avl.insertar(new Libro(10, "ISBN10", "Libro 10", "Autor C", 2022, "Novela"));

        long fin = System.nanoTime();

        System.out.println("\n********** PRUEBA AVL **********");
        System.out.println("Tiempo de insercion AVL: " + (fin - inicio));
        System.out.println("Altura del arbol AVL: " + avl.getAlturaArbol());
        System.out.println("Rotaciones realizadas: " + avl.getRotaciones());

        if (avl.buscar(20) != null) {
            System.out.println("Libro encontrado en el AVL");
        } else {
            System.out.println("Libro no encontrado en el AVL");
        }

        avl.eliminar(20);

        if (avl.buscar(20) == null) {
            System.out.println("Libro eliminado correctamente del AVL");
        } else {
            System.out.println("No se elimino el libro del AVL");
        }

        System.out.println("Altura del arbol AVL despues de eliminar: " + avl.getAlturaArbol());
        System.out.println("Rotaciones realizadas despues de eliminar: " + avl.getRotaciones());
    }

    public static void probarB() {
        ArbolB arbolB = new ArbolB();
        long inicio = System.nanoTime();
        arbolB.insertar(new Libro(30, "ISBN30", "Libro 30", "Autor A", 2020, "Historia"));
        arbolB.insertar(new Libro(20, "ISBN20", "Libro 20", "Autor B", 2021, "Ciencia"));
        arbolB.insertar(new Libro(10, "ISBN10", "Libro 10", "Autor C", 2022, "Novela"));
        long fin = System.nanoTime();

        System.out.println("\n********** PRUEBA Arbol B **********");
        System.out.println("Tiempo de Insercion Arbol B: " + (fin - inicio));
        System.out.println("Altura del Arbol B: " + arbolB.altura());
        System.out.println("Divisiones realizadas: " + arbolB.getDivisiones());

        if (arbolB.buscar(20) != null) {
            System.out.println("Libro encontrado en el Arbol B");
        } else {
            System.out.println("Libro no encontrado en el Arbol B");
        }

        arbolB.eliminar(20);

        if (arbolB.buscar(20) == null) {
            System.out.println("Libro eliminado correctamente del Arbol B");
        } else {
            System.out.println("No se elimino el libro del Arbol B");
        }

        System.out.println("Redistribuciones al eliminar: " + arbolB.getRedistribuciones());
        System.out.println("Divisiones realizadas despues de eliminar: " + arbolB.getDivisiones());
    }
}
