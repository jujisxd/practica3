/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package practica3.publicar;

/**
 *
 * @author Juanjo
 */
public class Card {

    //Variables
    private String word;
    private int frequency;

    /**
     * Método constructor: Se le pasa una cadena que se almacena en word
     *
     * @param cadena = palabra
     */
    public Card(String cadena) {
        if (cadena == null || cadena.equals("")) {
            frequency = 0;
            word = "";
        } else {
            cadena = cadena.toLowerCase();
            this.word = cadena;
            frequency = 1;
        }

    }

    /**
     * Método que devuelve true si la palabra (word) del objeto pasado por
     * parametro es igual a la del propio objeto sin tener en cuenta su
     * frecuencia; si no, false
     *
     * @param tarjeta objeto pasado por parametro
     * @return
     */
    public boolean equals(Card tarjeta) {
        return tarjeta.word.equals(this.word);
    }

    /**
     * Incrementa en una unidad la frecuencia
     */
    public void increment() {
        this.frequency++;
    }

    /**
     * Método para tomar la frecuencia
     *
     * @return la frecuencia de una tarjeta
     */
    public int getFrequency() {
        return this.frequency;
    }

    /**
     * Método que devuelve la palabra de una tarjeta
     *
     * @return la palabra de una tarjeta
     */
    public String getWord() {
        return this.word;
    }

    @Override
    public String toString() {
        return word + " " + frequency;
    }
}
