/**Classe PopolaMatriceNemici:
 * Questa classe serve per caricare nella matrice gli antagonisti dell'eroe.
 * "Antagonisti" perché questa classe carica sia Maghi che Guerrieri.
 * Grazie alla classe FactoryNemici.
 * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.FactoryNemici
 * @Author Runci Riccardo, copilot per avermi mostrato un esempio delle classi Factory.
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.Difficolta;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.FactoryNemici;

public class PopolaMatriceNemici
{
    private MatriceGioco matriceGioco;
    private Difficolta diffGioco; //contiene il numero dei nemici!
    /**Metodo costruttore:
     * @param matriceGioco = la matrice del gioco!*/
    public PopolaMatriceNemici(MatriceGioco matriceGioco)
    {
        this.matriceGioco = matriceGioco;
        this.diffGioco = matriceGioco.getDifficoltaGioco();
        this.collocaNemiciInMatrice();
    }
    /**Metodo collocaNemiciInMatrice:
     * Questo metodo serve per riempire la matrice di Nemici.
     * I Nemici vengono presi dal metodo restituisciNemicoRandom dell'oggetto genNemici di tipo FactoryNemici.
     * @Author Runci Riccardo, copilot per avermi mostrato un esempio delle classi Factory.
     * @since versione 1.0.*/
    private void collocaNemiciInMatrice()
    {
        FactoryNemici genNemici = new FactoryNemici();
        for (int i=0;i<this.diffGioco.getNumNemici();i++)
        {
            this.matriceGioco.setOggettoNellaMatrice(genNemici.restituisciNemicoRandom(),this.matriceGioco.trovaCellaVuota());
        }
    }
}
