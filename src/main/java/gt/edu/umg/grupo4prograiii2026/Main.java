

package gt.edu.umg.grupo4prograiii2026;


public class Main {

    public static void main(String[] args) {
        
        
      HojaCalculo hoja = new HojaCalculo();

        hoja.insertarEnColumna(1, 1, "10");
        hoja.insertarEnColumna(2, 1, "40");
        hoja.insertarEnColumna(3, 1, "70");

        hoja.mostrarColumna();

        Celda encontrada = hoja.buscarCelda(2, 1);

        if (encontrada != null) {
            System.out.println("Celda ya encontrada: " + encontrada.valor);
        } else {
            System.out.println("Celda no encontrada");
        }
    }
}


