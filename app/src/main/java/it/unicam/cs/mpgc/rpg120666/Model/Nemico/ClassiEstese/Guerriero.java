package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;

import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Interfacce.InterfacciaGuerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;

public class Guerriero extends Nemico implements InterfacciaGuerriero, ElementiPerMatrice
{
    private int aumentoDannoCausato;
    /**Metodo costruttore:
     *@param nomePersonaggio
     *@param puntiVitaPersonaggio*/
    public Guerriero(String nomePersonaggio, int puntiVitaPersonaggio,int dannoCausato)
    {
        super(nomePersonaggio, puntiVitaPersonaggio, dannoCausato);
    }

    /**Metodo abstract della super classe Nemico
     * (ereditato dall'interfaccia "combattiControEroe")*/
    @Override
    public boolean combattiControEroe(Eroe e)
    {
        return false;
    }
    /**Metodo che deriva dall'interfaccia InterfacciaGuerriero:*/
    @Override
    public boolean eseguiAttaccoFisico(Eroe e)
    {
        return false;
    }

    @Override
    public boolean calpestataDaEroe(Eroe e)
    {
        System.out.println("L'EROE è incappato in un guerriero!");
        return false;
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
}
