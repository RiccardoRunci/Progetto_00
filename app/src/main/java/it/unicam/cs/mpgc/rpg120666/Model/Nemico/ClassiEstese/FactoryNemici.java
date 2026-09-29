/**Classe factory che ritorna oggetti random di tipo sotto classe di Nemico
 * Viene usata nella classe: PopolaMatriceNemici per creare degli oggetti di tipo sottoclasse di Nemico(Guerriero e Mago).
 * Se si vuole aggiungere un nemico, prima si deve creare la sottoclasse di quel nemico, estendere InterfacciaPerPersonaggi, ElementiPerMatrice.
 * Poi bisogna aggiungere un nuovo case qui sotto, e aumentare l'intero generato dal generatoreRandom.
 *
 * Personaggi.InterfacciaPersonaggi = è l'interfaccia che evita la violazione di ISP (segregazione interfacce) da parte della ManagerCombattimento.
 *
 * ElementiPerMatrice.ElementiPerMatrice = è l'interfaccia di tipo della matriceGioco. Serve per creare l'interfaccia di diversi tipi di oggetti
 * (ma tutti questi oggetti implementano ElementiPerMatrice). Questa interfaccia ha un metodo "calpestatoDaEroe(Eroe)" che permette di
 * chiamare lo stesso nome di metodo in tutte le classi (Arma, Medikit, Guerriero, Mago), così da evitare istanceof.
 * "calpestatoDaEroe(Eroe)" viene utilizzato nella classe Manager.ManagerIncontroNemiciOggetti.*/
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
