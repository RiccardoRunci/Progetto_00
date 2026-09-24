package it.unicam.cs.mpgc.rpg120666.Model.Utility;

public abstract class VerificaParametri
{
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
                if(interoPassato<=0)
                {
                    //puoi farlo perché se un personaggio viene colpito può abbassare la sua vita a <=0
                    return true;
                }
            }
            default -> System.out.println("Qui non dovrebbe mai passare\n");
        }
        return false;
    }
}
