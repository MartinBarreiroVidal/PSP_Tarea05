import java.io.IOException;

public class Lanzador {
    public int ejecutarFactor(String numero) {
        //processbuilder sirve para configurar el comando del SO que queramos ejecutar (factor, en linux)
        ProcessBuilder pb = new ProcessBuilder("factor", numero);
        //conecta la entrada, salida y errores del proceso hijo con los del padre (consola actual)
        pb.inheritIO();

        try {
            //ejecutamos el proceso
            Process proceso = pb.start();

            //detiene la ejecucion y espera hasta que el comando externo se ejecute
            int exitCode = proceso.waitFor();

            return exitCode;
        }
        //2 errores: IOException por si no existe el comando factor
        catch (IOException | InterruptedException e) {
            System.out.println("Error al ejecutar el proceso: " + e.getMessage());
            return -1;
        }
    }

}