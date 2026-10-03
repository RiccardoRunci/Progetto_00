/**Classe Arma:
 * Questa classe serve per creare Oggetti specifici di tipo Arma.
 * Estende Oggetto.
 * @see it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto
 *
 * Un Arma può essere presa e usata dall'eroe. Anche perché l'eroe è l'unico che si muove.
 * Serve per aumentare i suoi (dell'eroe) puntiDanno quando sferra un attacco. NON è intesa come arma vera e propria.
 *
 * Implementa l'interfaccia Arma, così come ogni nuovo Oggetto specifico avrà una sua interfaccia specifica.
 * @see it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce.InterfacciaArma
 * Implementa ElementiPerMatrice perché anche lei deve entrare dentro una casella della matrice,
 * deve essere calpestata da un eroe (per essere presa), deve ritornare il suo tipo quando scorro la matrice e vedere
 * cosa le caselle contengono.
 * @see it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice
 * @author Runci Riccardo.
 * @since Versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Interfacce.InterfacciaArma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;
public class Arma extends Oggetto implements InterfacciaArma, ElementiPerMatrice
{
    private int puntiDannoPiu;
    /**Metodo costruttore:
     * @param nomeOggetto che va al costruttore di Oggetto tramite super,
     * @param puntiDannoPiu che va ad inserirsi nell'unico attributo presente in questa classe
     * @throws IllegalArgumentException se i controlli da parte di VerificaParametri non sono soddisfatti!
     * @author Runci Riccardo
     * @since versione 1.0*/
    public Arma(String nomeOggetto, int puntiDannoPiu)
    {
        if(!(VerificaParametri.verificaStringhe(nomeOggetto)) ||  !(VerificaParametri.verificaInteri(puntiDannoPiu,CREAZIONE)))
            throw new IllegalArgumentException("Parametri passati al costruttore di Arma invalidi|");
        super(nomeOggetto);
        this.puntiDannoPiu = puntiDannoPiu;
    }

    /**Metodo toString:
     * override del metodo toString di Oggetto.
     * @return una stringa che descrive l'istanza di Arma.*/
    @Override
    public String toString()
    { return "CLASSE ARMA!\n"+
            "NOME= "+super.getNomeOggetto()+"\n"+
            "+DANNO= "+this.puntiDannoPiu;
    }
    /**Metodo hashCode:
     *@override del metodo hashCode della classe Oggetto (non Java.Object).
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'Arma.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this.puntiDannoPiu);
    }
    /**Metodo equals:
     *@override del metodo equals della classe Oggetto.
     *@return (boolean) True, se le due armi confrontate sono uguali.
     *                  False, altrimenti.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Arma)) return false;
        if(!super.equals(obj)) return false; //riga da aggiungere in un equals di una sottoclasse!
        Arma other = (Arma)obj;
        return this.puntiDannoPiu==other.puntiDannoPiu;
    }
    /**Metodo usaOggetto:
     * @param e Eroe che userà l'arma (se è vivo o se non è null).
     * @return il risultato del metodo sotto (potenziaAttacco) é il metodo presente nell'Interfaccia Arma.
     *         Ogni oggetto ha come metodo usaOggetto (derivato dall'interfaccia usaOggetto) e
     *         il metodo usaOggetto richiama i metodi delle interfacce specifiche (tipo potenzia attacco, scritto in
     *         interfacciaArma).
     * Metodo abstract della super classe Oggetto, che a sua volta viene ereditato dall' interfaccia UsaOggetto.
     * Così da permettere a ogni sotto classe che estende Oggetto di utilizzare l'oggetto con scopi diversi!
     * Questo dovrebbe impedire la scrittura di 'instance of' nella futura matrice gioco! Speriamo!
     * @throws IllegalArgumentException se l'eroe passato è morto o è null!
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public boolean usaOggetto(Eroe e)
    {
        /*Se l'eroe è null, allora lancia eccezione!*/
        if (e==null || !e.controllaSeVivo())
            throw new IllegalArgumentException("Eroe passato al metodo usaOggetto di Arma = null!\n" +
                    "Oppure l'eroe è morto!\n");
        else
        {
            //System.out.println("Prima i punti attacco di eroe erano: "+e.getDannoCausato()+"\n");
            return this.potenziaAttacco(e);
        }
    }
    /**Questo è il metodo che viene dall' interfacciaArma!
     * @param e = eroe che dovrà ottenere il potenziamento dato da questa Arma.
     * @return true se è riuscito a ottenerlo, false altrimenti.
     * @throws IllegalArgumentException se l'eroe passato è morto o null.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @Override
    public boolean potenziaAttacco(Eroe e)
    {
        if(e==null || !e.controllaSeVivo())
        {
            throw new  IllegalArgumentException("Eroe non valido o morto!");
        }
        else
        {
            e.setDannoCausato(e.getDannoCausato() + this.puntiDannoPiu);
            //System.out.println("Ora il danno causato da eroe= "+e.getDannoCausato()+"\n");
            /*Mi devo ricordare di eliminare l'oggetto dallo zaino!*/
            e.getZaino().eliminaOggettoDalloZaino(this);
            return true;
        }
    }


    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i wildcards in MatriceGioco.
     * Quando scorro la matrice, per capire se ci sono ancora Oggetti, Armi, Eroe, ecc...
     * Ogni volta che si estende il codice devo mettere questo metodo su una nuova classe che creo.
     * Ne parlo meglio nella classe MatriceContaElementi. L'ho fatto solo per non darla vinta all' IA.
     * @see it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti.MatriceContaElementi
     * @return String "Arma".
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public String restituisciTipoOggetto() {
        return "Arma";
    }

    /**@Note avrei dovuto implementare l'interfaccia ElementiPerMatrice in Oggetto e non nelle
     * sue sottoclassi. Questo metodo mi serviva (non in questa classe ma in Nemico) per contare il
     * numero di Nemici generali ancora in vita dentro la matrice.
     * Può essere utile anche per contare gli oggetti, ma oramai l'ho messo qui e quando aggiungo
     * (in futuro) un nuovo Oggetto, dovrò sovrascrivere anche questo metodo scrivendoci "Oggetto".
     * Non era qui che era meglio metterlo, ma anche qui (per la conta degli oggetti quando scansiono la matrice)
     * dovrebbe andare bene.
     * Ne parlo meglio dentro l'interfaccia ElementiPerMatrice e dentro la classe ManagerFineGioco.
     *@see it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerFineGioco
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @Override
    public String veroVolto() {
        return "Oggetto";
    }

    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i vari istanceof dentro la classe ManagerIncontroNemiciOggetti.
     * @see it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerIncontroNemiciOggetti
     * Perchè la matriceGioco passata alla classe ManagerIncontroNemiciOggetti è di tipo interfaccia ElementiPerMatrice.
     * Essendo di quel tipo interfaccia ElementiPerMatrice, deve avere anche questo metodo implementato.
     * Così da riuscire a fare una cosa tipo ElementoDellaMatrice (che sia oggetto, nemico, arma, ecc...).calpestatoDaEroe(eroe che calpesta l'oggetto).
     * Così da usare le stesse righe di codice, ma ogni oggetto le implementa in modo diverso!
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public boolean calpestataDaEroe(Eroe e)
    {
        System.out.println("L'EROE HA TROVATO UN ARMA!");
        if ((e.controllaSeVivo()) && e!=null)
        {
            //System.out.println("L'eroe:"+e.toString()+" ha raccolto l'oggetto: "+this.toString());
            e.prendiOggetto(this);
            //System.out.println("CONTENUTO ZAINO EROE:\n"+e.getZaino().toString());
            return true;
        }
        else
        {
            System.out.println("Accaduto qualche problema, l'eroe non è riuscito a prendere l'oggetto:\n"+this.toString());
            return false;
        }
         /*Dovrebbe funzionare ugualmente sostituendo
            tutto il codice sopra con: "return e.prendiOggetto(this);"
            ...Ma adesso funziona.*/

    }
}