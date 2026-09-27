package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;

import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerCombattimentoEroeNemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.InterfacciaPerPersonaggi;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;

public class Guerriero extends Nemico implements InterfacciaPerPersonaggi, ElementiPerMatrice
{
    private int aumentoDannoCausato;
    /**Metodo costruttore:
     *@param nomePersonaggio
     *@param puntiVitaPersonaggio*/
    public Guerriero(String nomePersonaggio, int puntiVitaPersonaggio,int dannoCausato)
    {
        super(nomePersonaggio, puntiVitaPersonaggio, dannoCausato);
    }





    /**Metodo che viene preso dall'interfaccia ElementiPerMatrice.
     * Serve per evitare i wildcards in MatriceGioco.
     * Quando scorro la matrice, per capire se ci sono ancora Oggetti, Armi, Eroe, ecc...
     * Ogni volta che si estende il codice devo mettere questo metodo su una nuova classe che creo.
     * Ne parlo meglio nella classe MatriceContaElementi. L'ho fatto solo per non darla vinta all' IA.*/
    @Override
    public String restituisciTipoOggetto() {
        return "Guerriero";
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
     * @throws IllegalArgumentException (se e ==null).*/
    @Override
    public boolean eseguiAttacco(Personaggio e)
    {
        if(e!=null) //Controllo se l'eroe passato è un parametro != null.
            /*Se il guerriero è ancora vivo, allora può attaccare l'eroe!*/
            if(this.controllaSeVivo())
            {
                //Attacca l'eroe:
                e.setPuntiVitaPersonaggio(e.getPuntiVitaPersonaggio() - this.getDannoCausato());
                return true; //L'attacco del Guerriero (ai danni dell'eroe) Ha avuto successo!
            }
            else
                return false; //L'attacco del Guerriero ai danni dell'eroe NON è andato a buon fine.
        else
            throw new IllegalArgumentException("Eroe passato al metodo eseguiAttacco di Guerriero: invalido!");
    }
}
