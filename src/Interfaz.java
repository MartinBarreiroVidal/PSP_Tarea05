import java.util.Scanner;

public class Interfaz {

    public static void main(String[] args) {
        //creams objeto scanner
        Scanner scanner = new Scanner(System.in);
        //creamos objeto lanzador
        Lanzador lanzador = new Lanzador();
        //preguntamos el nivel al arrancar el programa
        System.out.println("¿Qué nivel quieres usar? (1, 2, 3 o 4):");
        System.out.print("> ");
        String nivelEscrito = scanner.nextLine();
        int nivel = 1; //nivel 1 por defecto

        //intentamos pasar lo que escribio a numero
        try {
            nivel = Integer.parseInt(nivelEscrito);
        } catch (NumberFormatException e) {
            System.out.println("Nivel no válido. Usaremos el 1.");
        }
        //entrada a lo que escriba el usuario
        String entrada = "";

        //bucle mientras sea true...
        while(true) {
            System.out.println("Introduzca un número (o salir para terminar): ");
            entrada = scanner.nextLine();

            if (entrada.equals("salir")) {
                System.out.println("Saliendo del programa");
                //salimos del bucle si el usuario indica salir
                break;
            }

            int numSalida = 0;

            //dependiendo del nivel que escribieramos al principio usamos un metodo u otro
            if (nivel == 1) {
                numSalida = lanzador.ejecutarFactor(entrada);
            } else if (nivel == 2) {
                numSalida = lanzador.ejecutarFactorNivel2(entrada);
            } else {
                //si no es 1 ni 2, el máximo será el 3
                numSalida = lanzador.ejecutarFactorNivel3(entrada);
            }
            System.out.println("Operación completada. Código de salida: " + numSalida);
        }
        scanner.close();
    }
}

