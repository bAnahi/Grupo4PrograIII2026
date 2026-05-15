
package gt.edu.umg.grupo4prograiii2026.memoria;

import java.util.Scanner;

public class PrimerAjuste {

    public void ejecutar() {

        Scanner scanner = new Scanner(System.in);

        int[] bloques = {100, 500, 200, 300, 600};
        String continuar = "s";

        System.out.println("\n=== PRIMER AJUSTE ===");

        while (continuar.equalsIgnoreCase("s")) {

            System.out.println("\nANTES:");
            mostrarBloques(bloques);

            System.out.print("\nIngrese tamaño del proceso: ");
            int proceso = scanner.nextInt();
            scanner.nextLine();

            boolean asignado = false;

            for (int i = 0; i < bloques.length; i++) {

                if (bloques[i] >= proceso) {

                    System.out.println("\nProceso de " + proceso + " asignado al Bloque " + (i + 1));
                    System.out.println("Espacio anterior del bloque: " + bloques[i]);

                    bloques[i] = bloques[i] - proceso;

                    System.out.println("Espacio restante del bloque: " + bloques[i]);

                    asignado = true;
                    break;
                }
            }

            if (!asignado) {
                System.out.println("\nNo se encontro espacio suficiente para el proceso de " + proceso);
            }

            System.out.println("\nDESPUÉS:");
            mostrarBloques(bloques);

            System.out.print("\n¿Desea ingresar otro proceso? (s/n): ");
            continuar = scanner.nextLine();
        }

        System.out.println("\nFinalizando simulacion de Primer Ajuste...");
    }

    private void mostrarBloques(int[] bloques) {

        for (int i = 0; i < bloques.length; i++) {
            System.out.println("Bloque " + (i + 1) + ": " + bloques[i] + " libres");
        }
    }
}