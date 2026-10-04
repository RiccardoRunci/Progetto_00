/**Classe PopolaMatriceOggetti:
 * Questa classe serve per riempire la matriceGioco di Oggetti (generati dalla classe FactoryOgetti).
 * @note ci sono 2 metodi sotto, uno è deprecato perché non era espandibile. Infatti se in futuro vado ad aggiungere
 *        una nuova categoria di Nemici, dovrò (tra le altre cose) aggiungere delle righe di codice per inserire anche
 *        quel nuovo tipo di nemico.
 * Mentre invece (nel metodo collacaOggettiInMatriceConFactory) non ho questo problema perché basterà inserire
 * la nuova tipologia di Nemico direttamente nella classe FactoryNemici.
 * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.FactoryNemici
 * @author Runci Riccardo, copilot per la parte delle classe Factory.
 * @since versione 1.0.*/
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
    /**Metodo Costruttore:
     * @param matriceGioco = la matrice di gioco, da cui ricavo anche la difficoltà di gioco.*/
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
    /**Metodo collacaOggettiInMatriceConFactory:
     * In questo metodo stò utilizzando la classe Factory fatta da Copilot.
     * @author Runci Riccardo & Copilot.
     * @since versione 1.0.*/
    private void collacaOggettiInMatriceConFactory()
    {
        FactoryOggetti genOggetti = new FactoryOggetti();
        for (int i=0;i<this.diffGioco.getNumOggetti();i++)
        {
            this.matriceGioco.setOggettoNellaMatrice(genOggetti.restituisciOggettoRandom(),this.matriceGioco.trovaCellaVuota());
        }
    }
}