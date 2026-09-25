package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Interfacce.InterfacciaMago;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;

import static it.unicam.cs.mpgc.rpg120666.Model.Utility.CreazioneAggiornamento.CREAZIONE;

public class Mago extends Nemico implements InterfacciaMago, ElementiPerMatrice
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

    @Override
    public boolean combattiControEroe(Eroe e)
    {
        return false;
    }
    @Override
    public boolean eseguiAttaccoMagico(Eroe e)
    {
        return false;
    }

    @Override
    public boolean eseguiCuraMagica(Eroe e)
    {
        return false;
    }
}
