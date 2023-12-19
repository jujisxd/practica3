/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package practica3.publicar;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Juanjo
 */
public class IndexTree {

    // Variables
    private TreeMap<String, TreeSet<Integer>> tree;

    /**
     * Método constructor: Inicializa la variable de entrada
     */
    public IndexTree() {
        tree = new TreeMap<>();
    }

    /**
     * Método que verifica si el arbol está vacío
     *
     * @return true si está vacio, false si no
     */
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * Método que verifica si la cadena no está vacía, ni null y no está en el
     * árbol. Inserta un nodo en el árbol con la cadena en minúscula (con un
     * TreeSet vacío como valor asociado) y devuelve true. En caso contrario,
     * devuelve false.
     *
     * @param cadena La cadena a insertar en el árbol.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insert(String cadena) {
        // Verifica si la cadena no está vacía ni es null
        if (cadena != null && !cadena.isEmpty()) {
            // Convierte la cadena a minúsculas
            String lowerCaseCadena = cadena.toLowerCase();

            // Verifica si la cadena no está en el árbol
            if (!tree.containsKey(lowerCaseCadena)) {
                // Inserta la cadena en el árbol con un TreeSet vacío como valor asociado
                tree.put(lowerCaseCadena, new TreeSet<>());
                return true; // Indica que la inserción fue exitosa
            }
        }
        return false; // Indica que la inserción no fue exitosa
    }

    /**
     * Método que añade un nuevo identificador de documento al árbol asociado al
     * nodo etiquetado con la cadena que coincide con la cadena pasada como
     * parámetro.
     *
     * @param cadena La cadena asociada al nodo del árbol.
     * @param id     El identificador de documento a agregar.
     * @return true si lo agrega, falso en caso contrario.
     */
    public boolean insertId(String cadena, int id) {
        // Verifica si la cadena no es nula ni está vacía
        if (cadena != null && !cadena.isEmpty()) {
            // Convierte la cadena a minúsculas
            String lowerCaseCadena = cadena.toLowerCase();

            // Verifica si la cadena está presente en el árbol
            if (tree.containsKey(lowerCaseCadena)) {
                // Obtiene el TreeSet asociado a la cadena
                TreeSet<Integer> idSet = tree.get(lowerCaseCadena);

                // Verifica si el id no está presente en el TreeSet
                if (!idSet.contains(id)) {
                    // Agrega el id al TreeSet
                    idSet.add(id);
                    return true; // Indica que se agregó correctamente
                }
            }
        }
        return false; // Indica que no se pudo agregar el id
    }

    /**
     * Método que recorre el diccionario del compendium pasado por parametro.
     * Para cada palabra del diccionario pueden ocurrir dos cosas
     * 
     * - si la palabra ya existe en el árbol, hay que añadir al nodo correspondiente
     * los
     * documentos del objeto pasado por parámetro en los que aparece (sin
     * repetición de identificadores de documentos).
     * 
     * - si la palabra no estaba en el árbol, se añade un nuevo nodo al árbol con la
     * palabra
     * y todos los documentos del objeto pasado por parámetro en los que aparece (si
     * no
     * aparece en ningún documento, se añade con un valor asociado vacío).
     *
     * @param com
     */
    public void insertCompendium(Compendium com) {
        ArrayList<String> dictionary = com.getDictionary(); // Copia del diccionario

        // Recorro el array de dictionary
        for (String palabra : dictionary) {

            ArrayList<Integer> ids = com.search(palabra);
            // El error está en esta comparación , que nunca entra aqui
            // Creo que toca añadir algo para ver si el arbol está vacío)
            if (tree.containsKey(palabra)) { // Verifico si la palabra está en el árbol
                // Si la palabra ya existe en el árbol, obtenemos el TreeSet asociado a esa
                // palabra
                TreeSet<Integer> idSet = tree.get(palabra);

                // Agregamos los identificadores de documentos al TreeSet asociado a esa palabra

                if (!idSet.contains(ids)) {
                    idSet.addAll(ids);
                }

                System.out.println("Llegué aqui");
                tree.put(palabra, idSet); // recien lo añadi
            } else {
                // Si la palabra no está en el árbol, creamos un nuevo nodo con la palabra
                // y añadimos los documentos del compendium si existen
                TreeSet<Integer> newIdSet = new TreeSet<>();

                newIdSet.addAll(ids); // Agregar el identificador del documento

                tree.put(palabra, newIdSet); // Agregar la palabra y su conjunto de IDs al árbol
            }
        }
    }

    /**
     * elimina del árbol el nodo que contiene la palabra que coincide con la
     * pasada como parámetro, devolviendo el TreeSet de identificadores de
     * documentos asociados a esa cadena. Si en el árbol no hay ningún nodo con
     * esa cadena, el método devuelve una referencia vacía (null).
     *
     * @param palabra cadena que se va a eliminar
     * @return identificadores asociados a esa cadena
     */
    public TreeSet<Integer> erase(String palabra) {
        if (!tree.containsKey(palabra)) {
            return null;
        } else {
            TreeSet<Integer> retorno = tree.get(palabra);
            tree.remove(palabra);
            return retorno;
        }
    }

    /**
     * Método que permite eliiminar los valores asociados a las palabras del
     * arbol dicho id.
     *
     * @param id identificador que se va a buscar
     * @return un ArrayList con las palabras de las cuales se ha eliminado el id
     *         Si en el arbol no aparece el id pasado, devuelve null
     */
    public ArrayList<String> erase(int id) {
        ArrayList<String> palabrasEliminadas = new ArrayList<>();

        // Iterar sobre cada palabra en el árbol
        for (String palabra : tree.keySet()) {
            TreeSet<Integer> idSet = tree.get(palabra);

            // Verificar si el id está presente en el conjunto de identificadores
            if (idSet.contains(id)) {
                idSet.remove(id); // Eliminar el id del conjunto

                palabrasEliminadas.add(palabra); // Agregar la palabra a la lista de eliminadas
            }
        }

        // Verificar si se eliminaron palabras asociadas al id
        if (palabrasEliminadas.isEmpty()) {
            return null; // Devolver null si no se encontró el id en el árbol
        } else {
            return palabrasEliminadas; // Devolver la lista de palabras eliminadas
        }
    }

    /**
     *
     * @param caracter inicial de palabra
     * @return devuelve una copia del subconjunto del árbol formado por las
     *         palabras que comiencen por el carácter pasado por parámetro.
     */
    public TreeMap<String, TreeSet<Integer>> search(char caracter) {
        TreeMap<String, TreeSet<Integer>> retorno = new TreeMap<>();

        // Iterar sobre cada palabra en el árbol
        for (String palabra : tree.keySet()) {
            if (palabra.charAt(0) == caracter) {
                // Verificar si la palabra comienza con el carácter especificado

                // Obtener el conjunto de identificadores asociados a esa palabra y agregarlo al
                // subconjunto
                retorno.put(palabra, tree.get(palabra));
            }
        }
        return retorno;
    }

    /**
     *
     * @param id identificador
     * @return palabras cuyos valores asociados se encuentren en id, si no
     *         aparece el id asociado, devuevle null
     */
    public TreeSet<String> search(int id) {
        TreeSet<String> palabrasEnId = new TreeSet<>();

        // Iterar sobre cada palabra en el árbol
        for (String palabra : tree.keySet()) {
            TreeSet<Integer> idSet = tree.get(palabra);

            // Verificar si el conjunto de identificadores contiene el ID proporcionado
            if (idSet.contains(id)) {
                palabrasEnId.add(palabra); // Agregar la palabra al conjunto
            }
        }

        // Verificar si se encontraron palabras asociadas al id
        if (palabrasEnId.isEmpty()) {
            return null; // Devolver null si no se encontraron palabras
        } else {
            return palabrasEnId; // Devolver el conjunto de palabras encontradas
        }
    }

    /**
     *
     * @return conjunto de palabras clave contenidas en el TreeMap
     */
    public Set<String> getWords() {
        return tree.keySet();
    }

    /**
     *
     * @param palabra que se va a buscar.
     * @return el treeset con los id de documentos en los que aparece la palabra
     *         por parámetro, en cualquiero otro caso, devuelve null
     */
    public TreeSet<Integer> getDocuments(String palabra) {
        if (tree.containsKey(palabra)) {
            return tree.get(palabra); // Devuelve el conjunto de identificadores asociados a la palabra
        } else {
            return null; // Devuelve null si la palabra no está en el árbol
        }
    }

    /**
     *
     * @return el contenido del arbol en una cadena que debe tener el siguiente
     *         formato: • para cada nodo del árbol: clave del nodo, espacio en
     *         blanco,
     *         asterisco; • si el nodo tiene identificadores de documentos
     *         asociados:
     *         espacio en blanco, identificador. Si hay más de uno, para cada
     *         identificador, espacio en blanco, guión, identificador. Termina con
     *         cambio de línea sin espacio en blanco;
     *
     *         Ejemplo: war * 1 - 2 - 5 - 8 - 11 - 22 dog * 7 - 9 - 14 - 17 law *
     *         house
     *         * 1 - 2 - 7 - 11 - 13 ... music * 22
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Iterar sobre cada palabra en el árbol
        int totalKeys = tree.keySet().size();
        int currentKeyIndex = 0;

        for (String palabra : tree.keySet()) {
            currentKeyIndex++;

            sb.append(palabra).append(" * "); // Agregar la clave seguida de un asterisco

            TreeSet<Integer> idSet = tree.get(palabra);

            if (idSet != null && !idSet.isEmpty()) {
                // Si el conjunto de identificadores no está vacío, agregar los identificadores
                for (int id : idSet) {
                    sb.append(id).append(" - ");
                }
                // Eliminar el último separador ("-") y el espacio en blanco
                sb.delete(sb.length() - 3, sb.length());
            }

            // Verificar si es la última palabra en el árbol
            if (currentKeyIndex < totalKeys) {
                sb.append("\n"); // Agregar un cambio de línea al final de cada entrada excepto la última
            }
        }

        return sb.toString();
    }

}
