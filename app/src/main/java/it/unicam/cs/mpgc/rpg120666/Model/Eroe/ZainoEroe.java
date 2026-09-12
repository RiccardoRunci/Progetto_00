
package it.unicam.cs.mpgc.rpg120666.Model.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import java.util.Map;
import java.util.HashMap;
public class ZainoEroe
{
    private Eroe eroeProprietarioDelloZaino;
    //Oggetto è la chiave, Integer = quantità dell'oggetto!
    private Map<Oggetto,Integer> oggettiPosseduti;
    public ZainoEroe(){}
    public ZainoEroe(Eroe eroeProprietarioDelloZaino)
    {
        if(eroeProprietarioDelloZaino==null)
            throw new NullPointerException("eroeProprietarioDelloZaino == null");
        this.eroeProprietarioDelloZaino=eroeProprietarioDelloZaino;
        this.oggettiPosseduti = new HashMap<Oggetto,Integer>();
    }

    public void aggiungiOggettoNelloZaino(Oggetto oggetto)
    {

    }

}
