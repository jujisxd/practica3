/*
 * TODO: debo hacer que el método de lectura tome como separador 
 * los signos de puntuación que pide la práctica.
 */
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
/**
 *
 * @author Juanjo
 */
public class ErrorCorrector {

    /**
     * Método principal
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 4) {
            System.out.println("Error: wrong arguments");
        } else {
            boolean normalizar = false;

            //Evaluación del primer argumento
            if (args[0].equals("W")) {
                normalizar = false;
            } else if (args[0].equals("N")) {
                normalizar = true;
            } else {
                System.out.println("Error: wrong arguments2");
            }

            //Asignación de nombres de ficheros
            String nomFicheroDatos = args[1];
            String nomFicheroTexto = args[2];
            
            //HACER UN COMPENDIUM Y EL READFILE CON EL PRIMER ARCHIVO
            Compendium com = new Compendium();
            com.readFile(nomFicheroDatos);
            //LEERARCHIVO CON EL SEGUNDO      
            String [] datos = leerArchivo(nomFicheroTexto);
            

            //Hacer un insertCompendium a los datos
            IndexTree it = new IndexTree();
            it.insertCompendium(com);
        }

    }
    
    // Método para leer un archivo de texto y retornar sus palabras como un array de strings
    public static String[] leerArchivo(String nombreArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + nombreArchivo);
        }
        return contenido.toString().split("\\s+"); // Separar por espacios, tabulaciones, saltos de línea, etc.
    }
}
