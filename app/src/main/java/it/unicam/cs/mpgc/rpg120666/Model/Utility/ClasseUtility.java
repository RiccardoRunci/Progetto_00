/**ClasseUtility consente di fare dei controlli sulle stringhe (che non siano null o vuote).
 * Consente anche di evitare che un Nemico, un Oggetto, ecc... Sia creato con 0 punti vita (o <=0).
 * Consente anche (in caso che un personaggio venga colpito, o un oggetto usato, ecc...) Di arrivare allo 0 qual'ora
 * ce ne sia bisogno (in caso di morte di un Nemico o in caso di un termine di un oggetto).
 * É una classe astratta, quindi non si possono creare oggetti di questa classe.
 * @author Runci Riccardo.
 * @since versione 0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Utility;
public abstract class ClasseUtility
{
    /**Metodo statico che viene richiamato da molte classi (Nemico, Oggetto, ecc...).
     * Serve per controllare la stringa passata (nome del personaggio, nome dell'oggetto, ecc... ) si un nome corretto
     * senza numeri.
     * @param stringaPassata (String) è la stringa (nome del personaggio, nome dell'oggetto,ecc..) da controllare.
     * @param NomeClasse (String) è il nome della classe per riferire dove è accaduto l'errore.
     * @throws IllegalArgumentException se la stringaPassata è null oppure vuota.
     * @return (boolean) true se la stringaPassata va bene (non null & non empty).*/
    public static boolean controllaStringa (String stringaPassata, String NomeClasse)
    {
        if (stringaPassata.chars().anyMatch(Character::isDigit)) return false; // nella è presente almeno un numero!
        if(NomeClasse==null || NomeClasse.isEmpty())
            throw new IllegalArgumentException("Attenzione, errore nella classe: " +NomeClasse+"\n"+
                    "la stringa passata: "+NomeClasse+" è null, oppure vuoto;\n");

        if (stringaPassata==null || stringaPassata.isEmpty())
            throw new IllegalArgumentException("Attenzione, errore nella classe: " +NomeClasse+"\n"+
                    "la stringa passata: "+stringaPassata+" è null, oppure vuoto;\n");
        else
            return true;
    }

    /**Metodo statico che consente (alla classe Nemico, a chi la estenderà, o ad altre classi)
     * un controllo sui punti rappresentati con degli int.
     * Se il personaggio (l'Oggetto o ecc...) Viene creato allora, i suoi punti "int" non possono essere <=0.
     * Se il personaggio prende un colpo, oppure muore, oppure un oggetto viene usato la sua vita (la sua quantità)
     * può arrivare a essere = 0 (ma non <0).
     * @param NumeriInteri (int).
     * @param fase (enum) è una enumerazione con le opzioni CREAZIONE e AGGIORNAMENTO.
     * @param NomeClasse (String) è una stringa che contiene il nome della classe che richiama il metodo. Serve
     *                   per capire (nei messaggi d'errore) dove viene generato l'eventuale errore.
     * @throws IllegalArgumentException se in entrambi casi và sotto la soglia.
     * @return true se il controllo va a buon fine.*/
    public static boolean verificaNumeriInteri (int NumeriInteri, EnumControlloCreazioneAggiornamento fase, String NomeClasse)
    {
        /*String s = String.valueOf(NumeriInteri);
        if(s.chars().anyMatch(Character::isLetter) || NumeriInteri < 0) return false;*/

        if(NomeClasse==null || NomeClasse.isEmpty())
            throw new IllegalArgumentException("Attenzione, errore nella classe: " +NomeClasse+"\n"+
                    "la stringa passata: "+NomeClasse+" è null, oppure vuoto;\n");
        switch(fase)
        {
            case CREAZIONE:
                if (NumeriInteri <= 0)
                    throw new IllegalArgumentException("Attenzione, errore nella classe: " +NomeClasse+"\n"+
                            " i suoi valori: " + NumeriInteri + " sono <=0.\"n");
                break;
            case AGGIORNAMENTO:
                if (NumeriInteri < 0)
                    throw new IllegalArgumentException("Attenzione, errore nella classe: " +NomeClasse+"\n"+
                            "I suoi valori: " + NumeriInteri + " sono <0.\n");
                break;
        }
        return true;
    }
}
