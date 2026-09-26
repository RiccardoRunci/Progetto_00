/**Questa classe l'ho creata per contare gli elementi rimanenti in "ElementiPerMatrice[][] matrice".
 * @documented In questo modo dovrei aver violato il principio solid open/close.
 * ElementiPerMatrice[][] matrice è la matrice dove si poggiano tutti gli attori del gioco (Eroe, Nemici, Oggetti, ecc...).
 * Inizialmente avevo 2 metodi diversi in MatriceGioco, uno chiamato all'incirca "contaNumNemiciRimasti" e uno chiamato "contaNumOggettiRimasti".
 * Poi siccome che i 2 metodi sopra facevano più o meno la stessa cosa (avevano le stesse righe di codice) li ho uniti con uno switch.
 * L'unione di questi 2 metodi si può vedere ancora quà sotto.
 * Poi, però, per fare il lavoro di questa classe fatto bene, avevo bisogno dei wild cards (quelle cose "< ?").
 * Dato che non sono stati trattati a lezione, e dato che già ho aggiunto le "classi Factory" (che anche loro non sono state argomento di lezione),
 * Non volevo darla vinta all' AI (che mi aveva consigliato di mettere questi wild cards) e mi sono inventato questa soluzione "casalinga".
 * Purtroppo (con questa cosa) ho eliminato la possibilità di estendere il codice. Perchè, per aggiungere un'altra categoria di elementi della matrice, devo
 * aggiungere uno switch quà sotto con la nuova categoria di "elemento della matrice" che ho inserito.
 * Questa classe si basa su un metodo dell'interfaccia ElementiPerMatrice(String restituisciTipoOggetto();) che restituisce una stringa che indica il tipo di oggetto.
 * Quindi, oltre che aggiungere un nuovo pezzo di case quà sotto, bisogna anche aggiungere nell'enumerazione "EnumOggNem" il nuovo tipo di elemento di gioco.*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;

public class MatriceContaElementi
{
    private  final ElementiPerMatrice[][] matrice;
    public MatriceContaElementi(ElementiPerMatrice[][] matrice)
    {
        if(matrice!=null)
        {
            this.matrice=matrice;
        }
        else throw new IllegalArgumentException("Matrice passata al costruttore della classe MatriceContaElementi null!");
    }
    /**@note Qui sarebbe stato meglio usare i wild cards ( quelle cose < ?> ). Però non le sò usare!
     * Dato che ho già messo le classi factory (fuori programma), voglio attenermi il più possibile
     * agli argomenti svolti in classe (che ne sono molti, non vorrei andare fuori tema).*/
    public int calcolaNumOggettiNemiciRimanenti(EnumOggNem oggNem)
    {
        /**@Documented questa "manovra" dovrà rendere il codice non estendibile, nel senso che:
         * Posso creare nuovi Oggetti e/o nuovi Nemici, Ma non posso creare altri tipi di classi.
         * Se le creassi dovrei aggiungere un case a questo metodo.*/
        int somma=0;
        switch(oggNem)
        {
            case EROE -> {
                somma=this.calcolaNumNemiciOggetti("Eroe"); // mi segnala errore qui in (Oggetto.class)
            }
            case MAGO ->{
                somma=this.calcolaNumNemiciOggetti("Mago"); // mi segnala errore qui in (Nemico.class)
            }
            case GUERRIERO ->{
                somma=this.calcolaNumNemiciOggetti("Guerriero"); // mi segnala errore qui in (Nemico.class)
            }
            case MEDIKIT ->{
                somma=this.calcolaNumNemiciOggetti("Medikit"); // mi segnala errore qui in (Nemico.class)
            }
            case ARMA ->{
                somma=this.calcolaNumNemiciOggetti("Arma"); // mi segnala errore qui in (Nemico.class)
            }
            default -> throw new IllegalArgumentException("Il valore non valido");
        }
        System.out.println("Num trovato di: "+oggNem+" = "+somma);
        return somma;
    }
    private int calcolaNumNemiciOggetti(String s) //Gli errori sopra, mi dice che sono riconducibili a (ElementiPerMatrice e)
    {
        int somma=0;
        for (int riga=0; riga<this.matrice.length; riga++)
        {
            for (int colonna=0;colonna < this.matrice.length; colonna++)
            {
                if(this.matrice[riga][colonna]!=null &&
                        this.matrice[riga][colonna].restituisciTipoOggetto().equals(s))
                    somma++;
            }
        }
        return somma;
    }
}
