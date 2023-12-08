/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3.publicar;

import java.util.ArrayList;

/**
 *
 * @author Juanjo
 */
public class Document {

    //Variables
    private int id; //identificador
    private String category; //categoria del documento
    private ArrayList<Card> general;   //palabras que aparecen en un documento con su frecuencia

    //Métodos
    /**
     * Método constructor
     *
     * @param id: identificador
     * @param category: categoria del documento
     */
    public Document(int id, String category) {
        this.id = id;
        this.category = category;
    }

    /**
     * Método para devolver la card con mayor frecuencia
     *
     * @return la card con mayor frecuencia del array general.
     */
    public Card moreFrequently() {
        if (general.isEmpty()) {
            return null; // Si el array está vacío, devuelve null
        }

        Card mostFrequentCard = general.get(0); // Inicializa con la primera carta del array
        int maxFrequency = mostFrequentCard.getFrequency(); //Toma la frecuencia de la card

        // Busca la carta con la frecuencia más alta
        for (int i = 1; i < general.size(); i++) {
            Card aux = general.get(i);
            int auxFrequency = aux.getFrequency();

            //Compara la frecuencia del aux con la frecuencia maxima
            //Si es mayor, la mayor será ahora la del auxiliar
            if (auxFrequency > maxFrequency) {
                mostFrequentCard = aux;
                maxFrequency = auxFrequency;
            }
        }

        return mostFrequentCard;
    }

    /**
     * Método para añadir una card al final del array general si no existe la
     * cadena
     *
     * @param cadena
     */
    public void addCard(String cadena) {
        Card tarjeta = new Card(cadena);   //Creo el objeto tarjeta
        boolean encontrado = false; //Comprueba si está ya en el array
        for (Card card : general) { //Para recorrer todas las card en general
            if (card.getWord().equals(tarjeta.getWord())) {   //Compara las word dentro de general con la nueva
                encontrado = true;
                card.increment();    //Incrementa la tarjeta (duda de si incremento la card en lugar de tarjeta)
                break;
            }
        }
        if (!encontrado) {
            general.add(tarjeta); // Agrega la tarjeta al final del ArrayList general
        }
    }

    /**
     * Método getter para el Id
     *
     * @return id del documento
     */
    public int getId() {
        return this.id;
    }

    /**
     * Método getter para la categoria
     *
     * @return la categoria del documento
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Método devuelve true si la cadena pasada como parámetro coincide,
     * ignodando mayus y minus. con alguna palabra de las card contenidas en
     * general del documento
     *
     * @param cadena palabra que se buscará en general
     * @return true si la palabra está en general, false en caso contrario
     */
    public boolean search(String cadena) {
        cadena = cadena.toLowerCase();
        for (Card card : general) {
            if (card.getWord().equals(cadena)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método getter para devolver el array general
     *
     * @return copia del vector general
     */
    public ArrayList<Card> getGeneral() {
        return this.general;
    }

    /**
     * Método para mostrar los datos de un documento
     *
     * @return string con información del documento REVISAR
     */
    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder(String.valueOf(id) + "-" + category + "\n");

        for (Card card : general) {
            retorno.append(card.toString()).append("\n");
        }

        return retorno.toString();
    }

}
