package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
/**Questa interfaccia viene utilizzata dalla classe Medikit che estende la classe Oggetto!
 * @see it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit
 * @since versione 1.0.
 * @autor Runci Riccardo.*/
@FunctionalInterface
public interface InterfacciaMedikit
{
    /**Metodo curaEroe:
     * @param e = eroe da curare (eroe a cui verranno aumentati i puntiVita).
     * @return true se l'eroe è riuscito a prendere il medikit, false altrimenti.
     * @since versione 1.0.
     * @author Runci Riccardo*/
    public boolean curaEroe(Eroe e);
}
