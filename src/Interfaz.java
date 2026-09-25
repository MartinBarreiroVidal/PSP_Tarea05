import java.util.Scanner;

public class Interfaz {

    public static void main(String[] args) {
        //creams objeto scanner
        Scanner scanner = new Scanner(System.in);
        //creamos objeto lanzador
        Lanzador lanzador = new Lanzador();
        //entrada a lo que escriba el usuario
        String entrada = "";

        //bucle mientras sea true...
        while(true) {
            System.out.println("Introduzca un número entre (o salir para terminar): ");
            entrada = scanner.nextLine();

            if (entrada.equals("salir")) {
                System.out.println("Saliendo del programa");
                //salimos del bucle si el usuario indica salir
                break;
            }
            int numSalida = lanzador.ejecutarFactor(entrada);
            System.out.println("Operación completada. Código de salida: " + numSalida);
        }
        scanner.close();

    }
}

