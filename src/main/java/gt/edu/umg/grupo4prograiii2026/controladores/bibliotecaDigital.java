/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.grupo4prograiii2026.controladores;

import gt.edu.umg.grupo4prograiii2026.avl.ArbolAVL;
import gt.edu.umg.grupo4prograiii2026.arbolb.ArbolB;
import gt.edu.umg.grupo4prograiii2026.avl.NodoAVL;
import gt.edu.umg.grupo4prograiii2026.conexion.conexionSQL;
import gt.edu.umg.grupo4prograiii2026.modelo.Libro;
import java.util.List;
import java.util.Scanner;

public class bibliotecaDigital {

    private conexionSQL conexion;
    private List<Libro> libros;
    private ArbolAVL avl;
    private ArbolB arbolB;

    public void mostrarMenu(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n1.Conectar a SQL Server");
            System.out.println("2. Cargar datos");
            System.out.println("3. Ejecutar análisis");
            System.out.println("4. Operaciones manuales");
            System.out.println("5. Volver");

            System.out.print("\nSeleccione opción: ");
            String op = scanner.nextLine();

            switch (op) {
                case "1":
                    conectar();
                    break;
                case "2":
                    cargarDatos();
                    break;
                case "3":
                    ejecutarAnalisis();
                    break;
                case "4":
                    operacionesManual(scanner);
                    break;
                case "5":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void conectar() {
        conexion = new conexionSQL("localhost", "1433", "BibliotecaBD", "ADMIN", "ADMIN1234");

        if (conexion.conectar()) {
            System.out.println("\nConectado correctamente");
        } else {
            System.out.println("Error al conectar");
        }
    }

    private void cargarDatos() {

        if (conexion == null) {
            System.out.println("Primero conecte a la base de datos");
            return;
        }

        libros = conexion.obtenerTodosLosLibros();

        if (libros == null || libros.isEmpty()) {
            System.out.println("No hay datos");
        } else {
            System.out.println("Libros cargados: " + libros.size());
        }
    }

    private void ejecutarAnalisis() {

        if (conexion == null) {
            System.out.println("Primero conecte a la base de datos");
            return;
        }

        if (libros == null || libros.isEmpty()) {
            System.out.println("Primero cargue datos");
            return;
        }

        avl = new ArbolAVL();
        arbolB = new ArbolB();

        long inicioAVL = System.nanoTime();
        for (Libro l : libros) {
            avl.insertar(l);
        }
        long finAVL = System.nanoTime();

        long inicioB = System.nanoTime();
        for (Libro l : libros) {
            arbolB.insertar(l);
        }
        long finB = System.nanoTime();

        System.out.println("\n--- RESULTADOS ---");

        System.out.println("\nINSERCIÓN:");
        System.out.println("AVL Tiempo: " + (finAVL - inicioAVL));
        System.out.println("AVL Altura: " + avl.getAlturaArbol());
        System.out.println("AVL Rotaciones: " + avl.getRotaciones());

        System.out.println("\n B Tiempo: " + (finB - inicioB));
        System.out.println("B Altura: " + arbolB.altura());
        System.out.println("B Divisiones: " + arbolB.getDivisiones());
        System.out.println("B Fusiones: " + arbolB.getFusiones());
        System.out.println("B Redistribuciones: " + arbolB.getRedistribuciones());

        long inicioBusquedaAVL = System.nanoTime();
        for (int i = 0; i < libros.size(); i++) {
            avl.buscar(libros.get(i).getCodigoLibro());
        }
        long finBusquedaAVL = System.nanoTime();

        long inicioBusquedaB = System.nanoTime();
        for (int i = 0; i < libros.size(); i++) {
            arbolB.buscar(libros.get(i).getCodigoLibro());
        }
        long finBusquedaB = System.nanoTime();

        System.out.println("\nBÚSQUEDA :");
        System.out.println("AVL Tiempo: " + (finBusquedaAVL - inicioBusquedaAVL));
        System.out.println("B Tiempo: " + (finBusquedaB - inicioBusquedaB));

       long inicioElimAVL = System.nanoTime();
        for (int i = 0; i < libros.size() / 2; i++) {
            avl.eliminar(libros.get(i).getCodigoLibro());
        }
        long finElimAVL = System.nanoTime();

        long inicioElimB = System.nanoTime();
        for (int i = 0; i < libros.size() / 2; i++) {
            arbolB.eliminar(libros.get(i).getCodigoLibro());
        }
        long finElimB = System.nanoTime();

        System.out.println("\nELIMINACIÓN :");
        System.out.println("AVL Tiempo: " + (finElimAVL - inicioElimAVL));
        System.out.println("B Tiempo: " + (finElimB - inicioElimB));
        avl=new ArbolAVL();
        arbolB = new ArbolB();
        for(Libro l: libros)    {
            avl.insertar(l);
            arbolB.insertar(l);
        
        }
    }
   
    private void operacionesManual(Scanner scanner) {

    if (avl == null || arbolB == null) {
        System.out.println("Primero ejecute el análisis");
        return;
    }

    System.out.println("\n1. Buscar");
    System.out.println("2. Insertar");
    System.out.println("3. Eliminar");
    System.out.print("Seleccione: ");
    String op = scanner.nextLine();

    System.out.print("Ingrese código: ");
    int codigo = Integer.parseInt(scanner.nextLine());

    switch (op) {

        case "1":

            NodoAVL nodoAVL = avl.buscar(codigo);
            Libro libroB = arbolB.buscar(codigo);

            System.out.println("\n--- RESULTADO AVL ---");
            if (nodoAVL != null) {
                Libro l = nodoAVL.getLibro();
                System.out.println("Código: " + l.getCodigoLibro());
                System.out.println("ISBN: " + l.getIsbn());
                System.out.println("Título: " + l.getTitulo());
                System.out.println("Autor: " + l.getAutor());
                System.out.println("Año: " + l.getAnio());
                System.out.println("Categoría: " + l.getCategoria());
            } else {
                System.out.println("No encontrado");
            }

            System.out.println("\n--- RESULTADO ÁRBOL B ---");
            if (libroB != null) {
                System.out.println("Código: " + libroB.getCodigoLibro());
                System.out.println("ISBN: " + libroB.getIsbn());
                System.out.println("Título: " + libroB.getTitulo());
                System.out.println("Autor: " + libroB.getAutor());
                System.out.println("Año: " + libroB.getAnio());
                System.out.println("Categoría: " + libroB.getCategoria());
            } else {
                System.out.println("No encontrado");
            }

            break;

        case "2":

            System.out.print("Ingrese ISBN: ");
            String isbn = scanner.nextLine();

            System.out.print("Ingrese título: ");
            String titulo = scanner.nextLine();

            System.out.print("Ingrese autor: ");
            String autor = scanner.nextLine();

            System.out.print("Ingrese año: ");
            int anio = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese categoría: ");
            String categoria = scanner.nextLine();

            Libro nuevo = new Libro(codigo, isbn, titulo, autor, anio, categoria);

            avl.insertar(nuevo);
            arbolB.insertar(nuevo);

            System.out.println("Insertado correctamente");
            break;

        case "3":

            avl.eliminar(codigo);
            arbolB.eliminar(codigo);

            System.out.println("Eliminado correctamente");
            break;

        default:
            System.out.println("Opción inválida");
    }
}}