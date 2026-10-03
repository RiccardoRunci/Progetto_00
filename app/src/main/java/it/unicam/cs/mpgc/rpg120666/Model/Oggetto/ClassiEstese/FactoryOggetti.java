/**Per risolvere il problema della classe PopolaMatriceOggetti:
 * @see it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento.PopolaMatriceOggetti
 * devo creare una classe che mi restituisce in modo random un oggetto già creato che sia appartenente alla
 * sottoclasse Oggetto.
 * Per fare questo esistono delle classi fatte apposta che si chiamano classi Factory.
 * Per farle mi sono aiutato con copilot, ma non sono molto difficili.
 * @author copilot, Runci Riccardo.
 * @note Capito il procedimento ho creato una classe Factory anche per Oggetti.
 * @see it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.FactoryOggetti
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import java.util.Random;
public class FactoryOggetti
{
    private Random generatoreRandom;
    /**Metodo restituisciOggettoRandom:
     * @return un oggetto a caso di tipo interfaccia ElementiPerMatrice.
     * @see ElementiPerMatrice* = è un interfaccia che mi consente di creare la matriceGioco di diversi tipologie di Oggetti.
     *                           Un pò come fatto in classe con l'interafaccia Valutatore. (la quale creava "insiemi" di oggetti
     *                           diversi che avevano, tra altri metodi, quelli appartenenti all'interfaccia Valutatore).
     * Ritorna un oggetto ElementiPerMatrice tramite il nuovo switch/case di java.
     * @throws IllegalArgumentException ma in teoria non succederà mai.
     * @author principalmente Copilot.*/
    public ElementiPerMatrice restituisciOggettoRandom()
    {
        this.generatoreRandom=new Random();

        return switch(generatoreRandom.nextInt(2))
        {
            case 0-> new Medikit("Medikit",30);
            case 1-> new Arma("Arma",20);
            default -> throw new IllegalArgumentException("Qualcosa non è andato bene " +
                    "nella classe FactoryOggetti");
        };
    }

}
