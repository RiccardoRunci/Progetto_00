/**Classe factory che ritorna oggetti random di tipo sotto classe di Nemico
 * Viene usata nella classe: PopolaMatriceNemici per creare degli oggetti di tipo sottoclasse di Nemico(Guerriero e Mago).
 * Se si vuole aggiungere un nemico, prima si deve creare la sottoclasse di quel nemico, estendere InterfacciaPerPersonaggi, ElementiPerMatrice.
 * Poi bisogna aggiungere un nuovo case qui sotto, e aumentare l'intero generato dal generatoreRandom.
 *
 * Personaggi.InterfacciaPersonaggi = è l'interfaccia che evita la violazione di ISP (segregazione interfacce)
 *                                    da parte della ManagerCombattimento (richiamando il metodo eseguiAttacco, sugli
 *                                    Nemici che devono eseguire l'attacco. Gli oggetti non hanno quel metodo.)
 *
 * ElementiPerMatrice.ElementiPerMatrice = è l'interfaccia di tipo della matriceGioco. Serve per creare la matriceGioco di diversi tipi di oggetti
 * (ma tutti questi oggetti implementano ElementiPerMatrice). Questa interfaccia ha un metodo "calpestatoDaEroe(Eroe)" che permette di
 * chiamare lo stesso nome di metodo in tutte le classi (Arma, Medikit, Guerriero, Mago), così da evitare istanceof.
 * "calpestatoDaEroe(Eroe)" viene utilizzato nella classe Manager.ManagerIncontroNemiciOggetti.
 * L'interfaccia ElementiPerMatrice ha il metodo restituisciTipoOggetto che restituisce "Arma,Medikit,ecc...".
 * L'interfaccia ElementiPerMatrice ha il metodo veroVolto che restituisce "Oggetto, Nemico".
 * @Author Runci Riccardo (Ricopiata da quella generata da copilot precedentemente).
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiJsonGuerrieri;

import java.util.ArrayList;
import java.util.Random;
public class FactoryNemici
{
    private Random generatoreRandom;

    /**Metodo: restituisciNemicoRandom:
     * @return ElementiPerMatrice un oggetto di tipo ElementiPerMatrice creato dal nuovo switch/case di java.
     * @throws IllegalArgumentException ma in teoria non ci dovrebbe arrivare mai.*/
    public ElementiPerMatrice restituisciNemicoRandom()
    {
        //Creo un oggetto della classe LeggiJsonGuerrieri!
        LeggiJsonGuerrieri nomiGuerrieri=new LeggiJsonGuerrieri();
        //Creo un arrayList che mi conterrà il set restituito dalla classe LeggiJsonGuerrieri!
        ArrayList<Guerriero> arrayGuerr = new ArrayList<>();
        //Inserisco dentro l' ArrayList tutto il Set con addAll!
        //Così posso avere l'indicizzazione (dentro l'array)!
        arrayGuerr.addAll(nomiGuerrieri.getInsiemeGuerrieri());
        //creo un nuovo generatore random per prendere un numero da caso dall'ArrayList Guerrieri
        Random genRandomGuerr=new Random();
        int numG = genRandomGuerr.nextInt(arrayGuerr.size()-1);


        this.generatoreRandom=new Random();

        return switch(generatoreRandom.nextInt(2))
        {
            case 0-> new Mago("Mago",
                    40,
                    5,
                    15);
            case 1-> new Guerriero(arrayGuerr.get(numG).getNomePersonaggio(),
                    arrayGuerr.get(numG).getPuntiVitaPersonaggio(),
                    arrayGuerr.get(numG).getDannoCausato());
           default -> throw new IllegalArgumentException("Qualcosa non è andato bene " +
                    "nella classe FactoryOggetti");

        };
    }
}
