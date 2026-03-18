

package gt.edu.umg.grupo4prograiii2026;


public class Main {

    public static void main(String[] args) {
        
        
      HojaCalculo hoja = new HojaCalculo();

        hoja.insertarPrimeraCelda(1, 1, "10");
        hoja.insertarEnColumna(2, 1, "52");
        hoja.insertarEnColumna(3, 1, "70");
        
        System.out.println("Columna original: ");
        hoja.mostrarColumna();
        
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
}


