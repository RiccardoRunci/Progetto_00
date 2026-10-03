/**Classe astratta VerificaParametri.
 * Questa classe permette una verifica di stringhe (grazie al suo metodo statico) verificaStringhe,
 * e una verifica di numeri interi (grazie al suo metodo statico verificaInteri).
 * Ho creato questa classe per evitare di scrivere sempre lo stesso codice ripetitivo.
 * @autor Runci Riccardo & Copilot (per il metodo con l'enumerazione verificaInteri,
 *        poi appreso e riutilizzato in molti altri casi in questo progetto.).
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Utility;
public abstract class VerificaParametri
{
    /**Metodo verificaStringhe:
     * @return boolean = true se la stringa va bene. Nel senso che:
     *                   non è una stringa null, non è una stringa vuota, non è una stringa formata da soli numeri.
     *                   False, altrimenti.
     *@param stringaPassata = la stringa da controllare.
     *@note è un metodo statico, per comodità. Invece di stare a creare l'oggetto VerificaParametri .....
     *      Faccio come Math.Sqrt(parametro), VerificaParametri.metodo(parametro).
     *@since verisone 1.0*/
    public static boolean verificaStringhe(String stringaPassata)
    {
        /*Controllo che la stringa non sia vuota o nulla!*/
        if(stringaPassata==null || stringaPassata.isEmpty())
            return false;
        /*Controllo che la stringa non sia formata da soli numeri!*/
        if(stringaPassata.chars().allMatch(Character::isDigit)) return false;
        /*Altrimenti adesso non mi vengono in mente altri controlli:*/
        return true;
    }
    /**Metodo VerificaInteri:
     * @param interoPassato = il numero intero da verificare.
     * @param enumCA ={@link CreazioneAggiornamento} la "modalità" di come lo devo verificare
     *                (questo parametro è di tipo enumerazione: Model.Utility.CreazioneAggiornamento).
     *@see CreazioneAggiornamento
     *@return true le il numero è >0 per i costruttori (tramite l'enumerazione CREAZIONE),
     *        true se il numero è qualsiasi numero per i metodi set dei vari oggetti.
     *@since verisone 1.0.
     *@author Runci Riccardo & Copilot.*/
    public static boolean verificaInteri(int interoPassato,CreazioneAggiornamento enumCA)
    {
        switch(enumCA)
        {
            case CREAZIONE ->
            {
                if(interoPassato<=0)
                {
                    System.out.println("NON PUOI CREARE UN PERSONAGGIO CON VITA <=0\n");
                    return false;
                }
                else return true;
            }
            case AGGIORNAMENTO ->
            {
                if(interoPassato<=0 || interoPassato>0) //sempre vero???
                {
                    /* puoi farlo perché se un personaggio viene colpito può abbassare la sua vita a <=0
                      Oppure se prende un medikit può aumentare la sua vita! */
                    return true;
                }
            }
            default -> System.out.println("Qui non dovrebbe mai passare\n");
        }
        return false;
    }
}
