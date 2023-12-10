/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//package practica3.publicar;
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
            if (args[1].equals("W")) {
                normalizar = false;
            } else if (args[1].equals("N")) {
                normalizar = true;
            } else {
                System.out.println("ERROR EN EL ARG1");
            }

            //Asignación de nombres de ficheros
            String nomFicheroDatos = args[2];
            String nomFicheroTexto = args[3];
            
            String [] datos = leerArchivo(nomFicheroDatos);
            String [] texto = leerArchivo(nomFicheroTexto);
                        
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
