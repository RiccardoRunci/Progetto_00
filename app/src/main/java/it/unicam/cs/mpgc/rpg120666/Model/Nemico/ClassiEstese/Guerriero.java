/**Classe Guerriero estende Nemico.
 * Serve per creare un Guerriero (un nemico specifico).
 * NON ha nessun campo, non me ne sono mai accorto fino adesso.
 * Anche questa classe Estende Nemico, e implementa InterfacciaPerPersonaggi & ElementiPerMatrice.
 * @see it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice
 * @see it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi
 * @author Runci Riccardo.
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerCombattimentoEroeNemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.MieAnnotazioni;

public class Guerriero extends Nemico implements InterfacciaPerPersonaggi, ElementiPerMatrice
{
    /**Metodo costruttore:
     *@param nomePersonaggio va al costruttore di Nemico,
     *@param puntiVitaPersonaggio va al costruttore di Nemico
     *@param dannoCausato va al costruttore di Nemico
     *@note i controlli (della solita classe VerificaParametri) sono fatti dalla super-classe Nemico.
     *Non aver aggiunto campi nuovi è stata una svista, mi sono accorto solo ora. Sono stato troppo preso
     *         dal funzionamento in generale.
     * @author Runci Riccardo.
     * @since Versione 1.0*/
    @MieAnnotazioni(
            domanda="è un grave errore quello di non mettere nessun nuovo campo ad una classe?",
            osservazione=" Funziona ugualmente, è stata una svista (il fatto di non aver messo nessun campo) " +
                         " Ma credo che non abbia pregiudicato il funzionamento del programma.")
    public Guerriero(String nomePersonaggio, int puntiVitaPersonaggio,int dannoCausato)
    {
        super(nomePersonaggio, puntiVitaPersonaggio, dannoCausato);
    }

    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i wildcards in MatriceGioco.
     * Quando scorro la matrice, per capire se ci sono ancora Oggetti, Armi, Eroe, ecc...
     * Ogni volta che si estende il codice devo mettere questo metodo su una nuova classe che creo.
     * Ne parlo meglio nella classe MatriceContaElementi. L'ho fatto solo per non darla vinta all' IA.
     *@see ElementiPerMatrice
     *@author Runci Riccardo.
     *@since versione 1.0.*/
    @Override
    public String restituisciTipoOggetto() {
        return "Guerriero";
    }
    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i vari istanceof dentro la classe ManagerIncontroNemiciOggetti.
     * Perchè la matriceGioco passata al metodo ManagerIncontroNemiciOggetti è di tipo interfaccia ElementiPerMatrice.
     * Essendo di quel tipo interfaccia ElementiPerMatrice, deve avere anche questo metodo implementato.
     * Così da riuscire a fare una cosa tipo ElementoDellaMatrice (che sia oggetto, nemico, arma, ecc...).calpestatoDaEroe(eroe che calpesta l'oggetto).
     * Così da usare le stesse righe di codice, ma ogni oggetto le implementa in modo diverso!
     * @see ElementiPerMatrice
     * @author Runci Riccardo.
     * @since versione 1.0.*/
    @Override
    public boolean calpestataDaEroe(Eroe e)
    {
        System.out.println("L'EROE HA INCONTRATO UN GUERRIERO!");
        ManagerCombattimentoEroeNemico manCombat= new ManagerCombattimentoEroeNemico(e,this);
        /* manCombat.Combattimento deve ritornare:
         * True se il combattimento è finito e ha vinto l'eroe, così (l'eroe) si può muovere.
         * False se il combattimento è stato vinto dal Nemico!*/
        return manCombat.combattimento();
    }
    /**Metodo che deriva dall'interfaccia Personaggio.InterfacciaPerPersonaggi:
     * @param e = Eroe a cui sferrare l'attacco.
     * @return true = se il nemico ha effettuato con successo l'attacco ai danni dell' eroe!
     * @return false = se il nemico non è riuscito ad attaccare l'eroe!
     * @throws IllegalArgumentException (se e ==null).
     * @see InterfacciaPerPersonaggi
     * @author Runci Riccardo.
     * @since versione 1.0.*/
    @Override
    public boolean eseguiAttacco(Personaggio e)
    {
        if(e!=null) //Controllo se l'eroe passato è un parametro != null.
            /*Se il guerriero è ancora vivo, allora può attaccare l'eroe!*/
            if(this.controllaSeVivo())
            {
                //System.out.println("PRIMA IL L'eroe HA VITA: "+e.getPuntiVitaPersonaggio());
                //Attacca l'eroe:
                e.setPuntiVitaPersonaggio(e.getPuntiVitaPersonaggio() - this.getDannoCausato());
                //System.out.println("DOPO IL L'eroe HA VITA: "+e.getPuntiVitaPersonaggio());
                return true; //L'attacco del Guerriero (ai danni dell'eroe) Ha avuto successo!
            }
            else
                return false; //L'attacco del Guerriero ai danni dell'eroe NON è andato a buon fine.
        else
            throw new IllegalArgumentException("Eroe passato al metodo eseguiAttacco di Guerriero: invalido!");
    }
    /**Metodo toString:
     * @override del metodo toString della classe Nemico.
     * @return String = una stringa che descrive un istanza di Oggetto Guerriero.
     *                  Questa descrizione è formata da "Classe Personaggio"+ il nome e i punti vita del Personaggio,
     *                  il danno causato(prese da super).*
     * @since versione 1.0.
     * @autor Runci Riccardo.*/
    @Override
    public String toString(){
        return "CLASSE Guerriero!\n"+
                "NOME: "+super.getNomePersonaggio()+"\n"+
                "VITA:"+super.getPuntiVitaPersonaggio()+"\n"+
                "DANNO CAUSATO: "+super.getDannoCausato()+"\n";
    }

    /**Metodo hashCode:
     *@override del metodo hashCode della classe Nemico.
     *@return (int) che specifica in quale bucket andrà a cacciarsi l'oggetto.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @MieAnnotazioni(
            domanda="Mi sono accorto solo ora che non ho messo nessun campo nuovo in questa classe " +
                    "(che potesse distinguerla dalla sua superClasse Nemico). " +
                    "Dato che funziona tutto, e non so cosa altro far fare al" +
                    "guerriero, posso lasciare tutto così facendo l'override dei metodi di Nemico anche se li ricopio?",
            osservazione=" vale anche per il toString, hashCode e equals")
    @Override
    public int hashCode(){
        return super.hashCode();
    }
    /**Metodo equals:
     *@override del metodo equals della classe Nemico.
     *@return (boolean) True, se i due oggetti confrontati sono uguali.
     *                  False, altrimenti.
     *@since versione 1.0.
     *@autor Runci Riccardo.*/
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(!(obj instanceof Nemico)) return false;
        if(!super.equals(obj)) return false; //riga da aggiungere in un equals di una sottoclasse!
        Guerriero other = (Guerriero)obj;
        return super.equals(other);
    }
}
