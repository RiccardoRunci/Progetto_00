/**Questa enumerazione serve nella classe MatriceContaElementi.
 * Rappresenta una soluzione "casalinga" che serve per evitare i wildcards (< ?).
 * Purtroppo implica che il codice non è più estendibile perché bisogna mettere qui il nuovo componente di gioco.
 * Poi modificare la classe MatriceContaElementi con un nuovo case nello switch.
 * Ma per non darla vinta all' AI che mi aveva proposto di copiare e incollare i wilds cards, ho optato per questa
 * soluzione più macchinosa.
 * Così dovrei violare l'open/close principle (il 2° principio solid) perché devo modificare questa enumerazione e
 * la classe MatriceContaElementi.
 * @author Runci Riccardo
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti;
public enum EnumOggNem
{
    GUERRIERO,
    MAGO,
    ARMA,
    MEDIKIT,
    EROE,
    NEMICO
}
