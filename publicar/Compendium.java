/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package practica3.publicar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Juanjo
 */
public class Compendium {

    //Variables
    private ArrayList<Document> documents;
    private ArrayList<String> dictionary;

    //Métodos
    /**
     * Método constructor, inicializa valores
     */
public Compendium() {
    documents = new ArrayList<>();
    dictionary = new ArrayList<>();
}


    /**
     * Método para mostrar los datos de compendium REVISAR
     *
     * @return string con los datos
     */
    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder("DICTIONARY:\n");

        for (String cadenas : dictionary) {
            retorno.append(cadenas).append(" ");
        }
        retorno.append("\n");

        for (Document doc : documents) {
            retorno.append(doc.toString());
        }

        return retorno.toString();
    }

    /**
     * Método que lee el fichero de texto de manera que: Si encuentra una
     * etiqueta DOC: - Crea un objeto tipo Document que se guarda al final del
     * array documents - Cada nuevo objeto se crea pasando su id (posición en el
     * fichero de texto iniciando en 1), su categoría (primera linea despues de
     * DOC y el texto que son las lineas posteriores (cada vez que se lee una
     * palabra, se le pasa al objeto Document para que la agregue a su vector
     * general de Card La primera vez que aparece la palabra su frecuencia es 1,
     * y si ya ha aparecido antes se incrementa su frecuencia en 1. Tener en
     * cuenta que la palabra se debe guardar siempre en minuscula Si encuentra
     * una etiqueta DIC: - Comeinza un diccionario por tanto lee por líneas
     * hasta el final del fichero, guardando cada linea en minúsculas al final
     * del diccionario
     *
     * @param cadena : nombre del fichero
     */
    public void readFile(String cadena) {
        try (BufferedReader br = new BufferedReader(new FileReader(cadena))) {
            String linea;
            boolean esDoc = false;
            int id = 0;

            while ((linea = br.readLine()) != null) {
                Document docActual;
                if (linea.equals("<DOC>")) {
                    id++;
                    linea = br.readLine(); // Leemos la categoría
                    docActual = new Document(id, linea); // Creamos el objeto
                    documents.add(docActual);
                    esDoc = true;
                } else if (linea.equals("<DIC>")) {
                    esDoc = false;
                } else {
                    StringTokenizer st = new StringTokenizer(linea);
                    while (st.hasMoreTokens()) {
                        String word = st.nextToken();
                        // Elimina los espacios en blanco alrededor de la palabra
                        word = word.trim();
                        // Elimina los signos de puntuación
                        word = word.replaceAll("[,:.()?\\[\\];]", "");
                        if (esDoc) {
                            int ultPos = documents.size() - 1;
                            documents.get(ultPos).addCard(word);
                        } else {
                            dictionary.add(word);
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    /**
     * Método que devuelve un array con los ids de los documentos que contengan
     * entre sus cards alguna palabra que coincida ignorando mayus, con la
     * pasada por parametro. Si no coincide con ninguno, devuelve null
     *
     * @param palabra string que se busca
     * @return array con los ids
     */
    public ArrayList<Integer> search(String palabra) {
        ArrayList<Integer> retorno = new ArrayList();
        int id;
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).search(palabra)) {
                id = documents.get(i).getId();
                retorno.add(id);
            }
        }
        return retorno;
    }

    //Getters
    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
    }
}
