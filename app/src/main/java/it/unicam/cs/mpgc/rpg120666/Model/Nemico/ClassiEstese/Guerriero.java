package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Interfacce.InterfacciaGuerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;

public class Guerriero extends Personaggio implements InterfacciaGuerriero
{
    private int aumentoDannoCausato;
    /**
     * Metodo costruttore:
     *
     * @param nomePersonaggio
     * @param puntiVitaPersonaggio
     */
    public Guerriero(String nomePersonaggio, int puntiVitaPersonaggio) {
        super(nomePersonaggio, puntiVitaPersonaggio);
    }

    @Override
    public boolean eseguiAttaccoFisico(Eroe e) {
        return false;
    }
}
