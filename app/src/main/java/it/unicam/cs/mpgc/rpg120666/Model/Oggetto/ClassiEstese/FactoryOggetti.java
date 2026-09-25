/**Per risolvere il problema della classe PopolaMatriceOggetti devo creare una classe
 * che mi restituisce in modo randomico un oggetto già creato che sia appartenente alla
 * sottoclasse Oggetto.
 * Per fare questo esistono delle classi fatte apposta che si chiamano classi Factory.
 * Per farle mi sono aiutato con copilot, ma non sono molto difficili.
 * @author copilot.*/
package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;

import java.util.Random;
public class FactoryOggetti
{
    private Random generatoreRandom;
    private Random generatoreRandom2;
    public ElementiPerMatrice restituisciOggettoRandom()
    {
        this.generatoreRandom=new Random();
        this.generatoreRandom2=new Random();

        return switch(generatoreRandom.nextInt(2))
        {
            case 0-> new Medikit("Medikit",generatoreRandom2.nextInt(100)+1);
            case 1-> new Arma("Arma",generatoreRandom2.nextInt(200)+1);
            default -> throw new IllegalArgumentException("Qualcosa non è andato bene " +
                    "nella classe FactoryOggetti");
        };
    }
}
