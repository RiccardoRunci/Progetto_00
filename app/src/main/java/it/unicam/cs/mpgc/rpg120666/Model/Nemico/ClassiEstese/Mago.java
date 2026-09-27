package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerCombattimentoEroeNemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;

import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public class Mago extends Nemico implements InterfacciaPerPersonaggi, ElementiPerMatrice
{
    private int numeroMagie;

    /**Metodo costruttore:
     *@param nomePersonaggio
     * @param puntiVitaPersonaggio*/
    public Mago(String nomePersonaggio, int puntiVitaPersonaggio,int dannoCausato,int numeroMagie)
    {
        if(!(VerificaParametri.verificaInteri(numeroMagie,CREAZIONE)))
            throw new IllegalArgumentException("Parametro numero magie passato al costruttore di Mago non valido");
        this.numeroMagie=numeroMagie;
        super(nomePersonaggio, puntiVitaPersonaggio,dannoCausato);
    }





    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i wildcards in MatriceGioco.
     * Quando scorro la matrice, per capire se ci sono ancora Oggetti, Armi, Eroe, ecc...
     * Ogni volta che si estende il codice devo mettere questo metodo su una nuova classe che creo.
     * Ne parlo meglio nella classe MatriceContaElementi. L'ho fatto solo per non darla vinta all' IA.*/
    @Override
    public String restituisciTipoOggetto() {
        return "Mago";
    }
    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i vari istanceof dentro la classe ManagerIncontroNemiciOggetti.
     * Perchè la matriceGioco passata al metodo ManagerIncontroNemiciOggetti è di tipo interfaccia ElementiPerMatrice.
     * Essendo di quel tipo interfaccia ElementiPerMatrice, deve avere anche questo metodo implementato.
     * Così da riuscire a fare una cosa tipo ElementoDellaMatrice (che sia oggetto, nemico, arma, ecc...).calpestatoDaEroe(eroe che calpesta l'oggetto).
     * Così da usare le stesse righe di codice, ma ogni oggetto le implementa in modo diverso!*/
    @Override
    public boolean calpestataDaEroe(Eroe e)
    {
        System.out.println("L'EROE HA INCONTRATO UN MAGO!");
        ManagerCombattimentoEroeNemico manCombat= new ManagerCombattimentoEroeNemico(e,this);
        /* manCombat.Combattimento deve ritornare:
        * True se il combattimento è finito e ha vinto l'eroe, così (l'eroe) si può muovere.
        * False se il combattimento è stato vinto dal Nemico!*/
        return manCombat.combattimento();
    }

    /**Metodo che deriva dall'interfaccia Personaggio.InterfacciaPerPersonaggi:
     * @param e = Eroe a cui sferrare l'attacco.
     * @return true = se ha effettuato con successo l'attacco a eroe!
     * @return false = se non è riuscito ad attaccare l'eroe!
     * @throws IllegalArgumentException (se e ==null).*/
    @Override
    public boolean eseguiAttacco(Personaggio e)
    {
        if(e!=null) //Controllo se l'eroe passato è un parametro != null.
            /*se il mago è ancora vivo e ha le magie, allora il mago può attaccare l'eroe!*/
            if(this.numeroMagie>0 && this.controllaSeVivo())
            {
               //Attacca l'eroe:
            e.setPuntiVitaPersonaggio(e.getPuntiVitaPersonaggio() - this.getDannoCausato());
            //è un mago e si auto-cura!
            this.eseguiCuraMagica();
            return true; //L'attacco del mago (ai danni dell'eroe) Ha avuto successo!
            }
            else if(this.numeroMagie<=0) //se il numero magie del mago scende a 0, il mago auto-muore.
            {
                this.setPuntiVitaPersonaggio(0);
                return false;
            }
            else
                return false; //L'attacco del mago ai danni dell'eroe NON è andato a buon fine.
        else
            throw new IllegalArgumentException("Eroe passato al metodo eseguiAttacco di Mago: invalido!");

    }
    /**Metodo privato che ho implementato per differenziare il mago dall'altro Nemico (guerriero).
     * Consiste nel far recuperare un pò di salute al mago.
     * Viene richiamato sopra (nel metodo implementato dall'interfaccia InterfacciaPerNemici),
     * così da permettere al mago di auto rigenerarsi a ogni attacco che sferra.*/
    private void eseguiCuraMagica()
    {
        /*Per attaccare e per autocurarsi c'è bisogno di magie disponibili.
         *Il numero delle magie lo controllo sopra, insieme a se il mago è ancora in vita!
         * Essendo il metodo privato non dovrebbe essere visibile da un istanza mago*/
        this.setPuntiVitaPersonaggio(this.getPuntiVitaPersonaggio()+5);
    }
}
