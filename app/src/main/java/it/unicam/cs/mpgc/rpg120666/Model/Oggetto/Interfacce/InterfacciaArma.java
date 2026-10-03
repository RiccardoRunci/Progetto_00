/**Questa interfaccia viene utilizzata dalla classe Arma che estende la classe Oggetto!
 * @see it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma
 * @since versione 1.0.
 * @autor Runci Riccardo.*/
package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
@FunctionalInterface
public interface InterfacciaArma
{
    /**Metodo potenziaAttacco:
     * @param e = eroe a cui verrà potenziato l'attributo dannoCausato (a cui verrà potenziato l'attacco).
     * @return true se l'eroe è riuscito a usare il potenziamento.
     * @since versione 1.0.
     * @author Runci Riccardo*/
    public boolean potenziaAttacco(Eroe e);
}
