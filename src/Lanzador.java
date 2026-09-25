import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
    //metodo que ejecuta factor, lee la salida linea a linea añadiendo [OK] o [ERROR] y devuelve el codigo de salida
    public int ejecutarFactorNivel2(String numero) {
        ProcessBuilder pb = new ProcessBuilder("factor", numero);

        try {
            //ejecutamos el proceso
            Process proceso = pb.start();

            //leemos la salida normal del proceso (cuando funciona bien)
            BufferedReader readerOk = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = readerOk.readLine()) != null) {
                System.out.println("[OK] " + linea);
            }

            //leemos la salida de error (cuando le pasamos letras por ejemplo)
            BufferedReader readerError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            while ((linea = readerError.readLine()) != null) {
                System.out.println("[ERROR] " + linea);
            }

            //detiene la ejecucion y espera
            int exitCode = proceso.waitFor();

            return exitCode;

        } catch (IOException | InterruptedException e) {
            System.out.println("[ERROR] Error al ejecutar el proceso: " + e.getMessage());
            return -1;
        }
    }//metodo que ejecuta factor, redirige la salida y los errores a ficheros .log conservando lo anterior, y devuelve el codigo
    public int ejecutarFactorNivel3(String numero) {
        ProcessBuilder pb = new ProcessBuilder("factor", numero);

        //creamos los objetos File con los nombres de los ficheros de log que pide el documento
        File logOutput = new File("factor_output.log");
        File logError = new File("factor_error.log");

        //redirigimos indicando que añada al final del archivo (appendTo)
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(logOutput));
        pb.redirectError(ProcessBuilder.Redirect.appendTo(logError));

        try {
            //ejecutamos el proceso
            Process proceso = pb.start();

            //detiene la ejecucion y espera. No sale nada por consola porque se fue a los ficheros.
            int exitCode = proceso.waitFor();

            return exitCode;

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al ejecutar el proceso: " + e.getMessage());
            return -1;
        }
    }
}
