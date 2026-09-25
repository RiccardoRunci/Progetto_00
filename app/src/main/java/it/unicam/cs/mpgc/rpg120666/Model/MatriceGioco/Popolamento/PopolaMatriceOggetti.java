package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.Difficolta;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.FactoryOggetti;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;

public class PopolaMatriceOggetti
{
    private MatriceGioco matriceGioco;
    private Difficolta diffGioco;
    public PopolaMatriceOggetti(MatriceGioco matriceGioco)
    {
        this.matriceGioco=matriceGioco;
        this.diffGioco= matriceGioco.getDifficoltaGioco();
        this.collacaOggettiInMatriceConFactory();
        //this.collocaOggettiInMatriceNONSOLID();
    }
    /**Metodo Non solid per popolare la matrice!
     * Difficilmente espandibile perché, per ogni nuova classe che implementa ElementiPerMatrice che ci inventiamo,
     * tocca ogni volta ad aggiungere un nuovo pezzo di codice del tipo:
     * this.matriceGioco.setOggettoNellaMatrice(new Classe("nomeOggetto", numIntero), this.matriceGioco.trovaCellaVuota());
     * Da quello che ho capito, bisogna utilizzare una classe FACTORY però non le abbiamo mai viste.
     * Dovrebbe violare il principio O (Open/Closed Principle).
     * Una classe dovrebbe essere aperta alle estensioni ma chiusa alle modifiche.*/
    @Deprecated
    private void collocaOggettiInMatriceNONSOLID()
    {
        for (int i=0;i<this.diffGioco.getNumOggetti();i++)
        {
            if(i%2==0)
                this.matriceGioco.setOggettoNellaMatrice(new Medikit("Medikit",100),this.matriceGioco.trovaCellaVuota());
            else
                this.matriceGioco.setOggettoNellaMatrice(new Arma("Arma",100),this.matriceGioco.trovaCellaVuota());
        }
    }
    /**In questo metodo stò utilizzando la classe Factory fatta da Copilot.*/
    private void collacaOggettiInMatriceConFactory()
    {
        FactoryOggetti genOggetti = new FactoryOggetti();
        for (int i=0;i<this.diffGioco.getNumOggetti();i++)
        {
            this.matriceGioco.setOggettoNellaMatrice(genOggetti.restituisciOggettoRandom(),this.matriceGioco.trovaCellaVuota());
        }
    }
}