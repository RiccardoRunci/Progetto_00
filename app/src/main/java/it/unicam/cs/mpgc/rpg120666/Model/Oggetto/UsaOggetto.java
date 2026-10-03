/*Classe interfaccia Funzionale (con un solo metodo dentro) UsaOggetto:
 * Questa interfaccia viene "implementata" dalla classe abstract Oggetto (super classe di Arma e Medikit).
 * Perchè ogni Oggetto ha un modo diverso di essere utilizzato! Solo la classe Oggetto "usa" questa interfaccia,
 * ma (in quella classe Oggetto che usa questa interfaccia) questo metodo è abstract!
 * Così da essere riscritto nelle sottoclassi di Oggetto!
 * In questo modo posso dare un utilizzo diverso a ogni sottoclasse di Oggetto!
 * @autor Runci Riccardo.
 * @version 1.0.
 * @since version 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
@FunctionalInterface
public interface UsaOggetto
{
    /**Metodo usaOggetto:
     * @param e = l'eroe che userà l'oggetto (solo gli eroi possono usare gli oggetti).
     * @return true = se l'eroe è riuscito a usare l'oggetto.
     *         False = altrimenti.
     *@since versione 1.0
     *@author Runci Riccardo.*/
    public boolean usaOggetto(Eroe e);
}
