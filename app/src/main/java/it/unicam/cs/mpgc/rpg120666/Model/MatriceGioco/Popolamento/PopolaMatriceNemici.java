package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.Difficolta;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.FactoryNemici;

public class PopolaMatriceNemici
{
    private MatriceGioco matriceGioco;
    private Difficolta diffGioco;
    public PopolaMatriceNemici(MatriceGioco matriceGioco)
    {
        this.matriceGioco = matriceGioco;
        this.diffGioco = matriceGioco.getDifficoltaGioco();
        this.collocaNemiciInMatrice();
    }
    /**Faccio direttamente il metodo con la classe Factory!*/
    private void collocaNemiciInMatrice()
    {
        FactoryNemici genNemici = new FactoryNemici();
        for (int i=0;i<this.diffGioco.getNumNemici();i++)
        {
            this.matriceGioco.setOggettoNellaMatrice(genNemici.restituisciNemicoRandom(),this.matriceGioco.trovaCellaVuota());
        }
    }
}
