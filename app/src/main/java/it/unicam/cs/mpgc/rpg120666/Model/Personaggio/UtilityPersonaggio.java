/**Classe astratta che raccoglie una serie di controlli utili alla classe Personaggio.
 * @author Runci Riccardo.
 * @since versione 0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Personaggio;

public abstract class UtilityPersonaggio
{
    /**Metodo statico che viene richiamato dalla classe Personaggio.
     * Serve per controllare che il nome passato (del personaggio) si un nome corretto.
     * @param nomePersonaggio (String) è il nome del personaggio da controllare.
     * @throws IllegalArgumentException se il nomePersonaggio è null oppure vuoto.
     * @return (boolean) true se il nomePersonaggio va bene (non null & non empty).*/
    public static boolean controllaNomePersonaggio (String nomePersonaggio)
    {
        if (nomePersonaggio==null || nomePersonaggio.isEmpty())
            throw new IllegalArgumentException("Attenzione, errore nella classe astratta Personaggio:\n " +
                    "Il nome del personaggio passato: "+nomePersonaggio+" è null, oppure vuoto;\n");
        else
            return true;
    }
    /**Metodo statico che consente (alla classe Personaggio, a chi la estenderà) un controllo sui punti vita dei personaggi.
     * Se il personaggio viene creato allora, i suoi punti vita non possono essere <=0.
     * Se il personaggio prende un colpo, oppure muore, la sua vita può arrivare ad essere = 0 (ma non <0).
     * @param puntiVitaPersonaggio (int).
     * @param faseDellaVita (enum) è una enumerazione con le opzioni CREAZIONE e AGGIORNAMENTO.
     * @throws IllegalArgumentException se in entrambi casi và sotto la soglia.
     * @return true se il controllo va a buon fine.*/
    public static boolean controlloPuntiVitaPersonaggio (int puntiVitaPersonaggio, EnumFaseVitaPersonaggio faseDellaVita)
    {
        switch(faseDellaVita)
        {
            case CREAZIONE:
                if (puntiVitaPersonaggio <= 0)
                    throw new IllegalArgumentException("Attenzione, errore nella classe astratta Personaggio:\n " +
                            " i suoi punti vita: " + puntiVitaPersonaggio + " sono <=0.\"n");
                break;
            case AGGIORNAMENTO:
            if (puntiVitaPersonaggio < 0)
                throw new IllegalArgumentException("Attenzione, errore nella classe astratta Personaggio:\n " +
                        "I suoi punti vita: " + puntiVitaPersonaggio + " sono <0.\"n"+
                        "Il personaggio dovrebbe essere già morto!");
            break;
        }
        return true;
    }
}