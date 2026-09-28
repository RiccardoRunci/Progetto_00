/**Classe factory che ritorna oggetti random di tipo sotto classe di Nemico*/
package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import java.util.Random;
public class FactoryNemici
{
    private Random generatoreRandom;
    private Random generatoreRandom2;
    public ElementiPerMatrice restituisciNemicoRandom()
    {
        this.generatoreRandom=new Random();
        this.generatoreRandom2=new Random();

        return switch(generatoreRandom.nextInt(2))
        {
            case 0-> new Mago("Mago",
                    generatoreRandom2.nextInt(30)+1,
                    generatoreRandom2.nextInt(7)+1,
                    generatoreRandom2.nextInt(5)+1);
            case 1-> new Guerriero("Guerriero",
                    generatoreRandom2.nextInt(50)+1,
                    generatoreRandom2.nextInt(8)+1);
            default -> throw new IllegalArgumentException("Qualcosa non è andato bene " +
                    "nella classe FactoryOggetti");
        };
    }
}
