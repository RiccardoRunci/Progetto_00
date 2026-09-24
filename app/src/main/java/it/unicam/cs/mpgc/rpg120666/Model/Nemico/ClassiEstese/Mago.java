package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Interfacce.InterfacciaMago;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;

public class Mago extends Personaggio implements InterfacciaMago
{
    private int numeroMagie;

    /**
     * Metodo costruttore:
     *
     * @param nomePersonaggio
     * @param puntiVitaPersonaggio
     */
    public Mago(String nomePersonaggio, int puntiVitaPersonaggio) {
        super(nomePersonaggio, puntiVitaPersonaggio);
    }

    @Override
    public boolean eseguiAttaccoMagico(Eroe e) {
        return false;
    }

    @Override
    public boolean eseguiCuraMagica(Eroe e) {
        return false;
    }
}
