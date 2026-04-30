/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.grupo4prograiii2026.controladores;

import gt.edu.umg.grupo4prograiii2026.avl.ArbolAVL;
import gt.edu.umg.grupo4prograiii2026.arbolb.ArbolB;
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
            System.out.println("\n===== BIBLIOTECA DIGITAL =====");
            System.out.println("1. Conectar a SQL Server");
            System.out.println("2. Cargar datos");
            System.out.println("3. Ejecutar análisis");
            System.out.println("4. Volver");

            System.out.print("Seleccione opción: ");
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
            System.out.println("Conectado correctamente");
        } else {
            System.out.println("Error al conectar");
        }
    }

    private void cargarDatos() {
        libros = conexion.obtenerTodosLosLibros();

        if (libros == null || libros.isEmpty()) {
            System.out.println("No hay datos");
        } else {
            System.out.println("Libros cargados: " + libros.size());
        }
    }

    private void ejecutarAnalisis() {
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

        System.out.println("\nAVL:");
        System.out.println("Tiempo: " + (finAVL - inicioAVL));
        System.out.println("Altura: " + avl.getAlturaArbol());
        System.out.println("Rotaciones: " + avl.getRotaciones());

        System.out.println("\nÁRBOL B:");
        System.out.println("Tiempo: " + (finB - inicioB));
        System.out.println("Altura: " + arbolB.altura());
        System.out.println("Divisiones: " + arbolB.getDivisiones());
        System.out.println("Fusiones: " + arbolB.getFusiones());
        System.out.println("Redistribuciones: " + arbolB.getRedistribuciones());

        // PRUEBA DE BÚSQUEDA
        int codigo = libros.get(0).getCodigoLibro();

        System.out.println("\nBúsqueda código: " + codigo);
        System.out.println("AVL: " + (avl.buscar(codigo) != null));
        System.out.println("B: " + (arbolB.buscar(codigo) != null));

        // ELIMINACIÓN
        avl.eliminar(codigo);
        arbolB.eliminar(codigo);

        System.out.println("\nEliminado código: " + codigo);
    }
}