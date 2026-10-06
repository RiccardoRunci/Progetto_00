/**
 * Questa classe l'ho creata per contare gli elementi rimanenti in "ElementiPerMatrice[][] matrice".
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
 * Quindi, oltre che aggiungere un nuovo pezzo di case quà sotto, bisogna anche aggiungere nell'enumerazione "EnumOggNem" il nuovo tipo di elemento di gioco.
 *
 * Tranne nel cercare il numero di Nemici generali, in quel caso dovrebbe funzionare sempre.
 * Perchè per quella ricerca, viene richiamato il metodo veroVolto definito nell'interfaccia di ElementiPerMatrice.
 * Il Quale è un metodo final scritto solo sulla classe Abstract Nemico che ritorna una stringa "Nemico".
 * Utile per contare TUTTI i nemici rimanenti nella matrice.
 * @author Runci Riccardo.
 * @since Versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;

public class MatriceContaElementi
{
    private  final ElementiPerMatrice[][] matrice;
    /**Metodo costruttore:
     * @param matrice = la matrice di gioco di tipo ElementiPerMatrice
     * @throws IllegalArgumentException se la matrice passata è = null!*/
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
     * agli argomenti svolti in classe (non vorrei andare fuori tema).*/
    public int calcolaNumOggettiNemiciRimanenti(EnumOggNem oggNem)
    {
        /**@Documented questa "manovra" dovrà rendere il codice non estendibile, nel senso che:
         * Posso creare nuovi Oggetti e/o nuovi Nemici, Ma non posso creare altri tipi di classi.
         * Se le creassi dovrei aggiungere un case a questo metodo. VIOLAZIONE OPENCLOSE*/
        int somma=0;
        switch(oggNem)
        {
            case EROE -> {
                somma=this.calcola("Eroe");
            }
            case MAGO ->{
                somma=this.calcola("Mago");
            }
            case GUERRIERO ->{
                somma=this.calcola("Guerriero");
            }
            case MEDIKIT ->{
                somma=this.calcola("Medikit");
            }
            case ARMA ->{
                somma=this.calcola("Arma");
            }
            case NEMICO->{
                somma=this.trovaNemici("Nemico");
            }
            default -> throw new IllegalArgumentException("Il valore non valido");
        }
        //System.out.println("Num trovato di: "+oggNem+" = "+somma);
        return somma;
    }
    /**Metodo che scorre la matrice e richiama su i suoi elementi non null il metodo restituisciTipoOggetto
     * descritto nell'interfaccia ElementiPerMatrice. Questo è implementato dentro ogni oggetto che estende quell'interfaccia.
     * Ma nel caso di Eroe dovrei aver commesso una violazione ISP perché i due metodi (restituisciTipoOggetto e veroVolto)
     * fanno quasi la stessa cosa. Eroe ce ne è solo 1 (non si incontra mai, quindi i 2 metodi non gli servono).
     * @author Runci Riccardo.
     * @since Versione 1.0.*/
    private int calcola(String s)
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
    /**Metodo trovaNemici:
     * Serve per controllare quanti NEMICI generici ci sono ancora attivi in matrice.
     * Questo metodo richiama il metodo veroVolto (descritto nell'interfaccia ElementiPerMatrice)
     * su tutti gli elementi (non null) della matriceGioco. Controlla se il metodo veroVolto restituisce una
     * Stringa "Nemico" (passata dal metodo calcolaNumOggettiNemiciRimanenti), fà la somma della loro quantità
     * e li restituisce.
     * @author Runci Riccardo.
     * @since Versione 1.0.*/
    private int trovaNemici(String s)
    {
        ElementiPerMatrice forseNemico;
        int somma=0;

        for (int riga=0; riga<this.matrice.length; riga++)
        {
            for (int colonna=0;colonna < this.matrice.length; colonna++)
            {
                if(this.matrice[riga][colonna]!=null &&
                        this.matrice[riga][colonna].veroVolto().equals(s))
                {
                    //forseNemico=this.matrice[riga][colonna];
                    //forseNemico.veroVolto().equals(s); //s="Nemico".
                    somma++;
                }
            }
        }
        System.out.println("il numero di nemici trovati : "+somma);
        return somma;
    }
}
