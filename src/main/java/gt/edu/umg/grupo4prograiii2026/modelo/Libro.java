
package gt.edu.umg.grupo4prograiii2026.modelo;

public class Libro {

    private int codigoLibro;
    private String isbn;
    private String titulo;
    private String autor;
    private int anio;
    private String categoria;

    public Libro(int codigoLibro, String isbn, String titulo, String autor, int anio, String categoria) {
        this.codigoLibro = codigoLibro;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.categoria = categoria;
    }

    public int getCodigoLibro() {
        return codigoLibro;
    }

    public String getTitulo() {
        return titulo;
    }
}