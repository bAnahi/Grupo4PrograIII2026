/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.grupo4prograiii2026.conexion;

import gt.edu.umg.grupo4prograiii2026.modelo.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class conexionSQL {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private Connection conexion;

    private String servidor;
    private String puerto;
    private String baseDatos;
    private String usuario;
    private String contrasena;

    public conexionSQL(String servidor, String puerto, String baseDatos, String usuario, String contrasena) {
        this.servidor = servidor;
        this.puerto = puerto;
        this.baseDatos = baseDatos;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public boolean conectar() {
        try {
            Class.forName(DRIVER);

            String url = "jdbc:sqlserver://" + servidor + ":" + puerto +
                    ";databaseName=" + baseDatos +
                    ";encrypt=true;trustServerCertificate=true";

            conexion = DriverManager.getConnection(url, usuario, contrasena);

            System.out.println("Conectado a SQL Server");
            return true;

        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }

    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> lista = new ArrayList<>();

        if (conexion == null) {
            System.out.println("No hay conexión activa");
            return lista;
        }

        String sql = "SELECT codigoLibro, isbn, titulo, autor, anio, categoria FROM libro";

        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("codigoLibro"),
                        rs.getString("isbn"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio"),
                        rs.getString("categoria")
                );

                lista.add(libro);
            }

            System.out.println("Libros cargados: " + lista.size());

        } catch (Exception e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return lista;
    }

    // CERRAR CONEXIÓN
    public void desconectar() {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar conexión");
        }
    }
}