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
 *
 * Questa classe prende Guerrieri e Maghi (da ritornare) da 2 classi che utilizzano la persistenza.
 * Ho optato per 2 classi di persistenza diverse per poter mettere più argomenti visti in classe.
 * Per la classe dei Maghi ho usato un file xml parsato tramite SAX:
 * @see it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiXmlMaghi_SAX
 * Per la classe dei Guerrieri ho utilizzato un file json parsato tramite GSON:
 * @see it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiJsonGuerrieri
 *
 * @Author Runci Riccardo (Ricopiata da quella generata da copilot precedentemente).
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiJsonGuerrieri;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiXmlMaghi_SAX;

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
        //-------------------------------------------------- PRENDO I GUERRIERI DAL FILE: Guerrieri.json (di resource).
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
        //-------------------------------------------------- ORA PRENDO I MAGHI DAL FILE: Maghi_ver2.xml (di resource).
        LeggiXmlMaghi_SAX leggiMaghi=new LeggiXmlMaghi_SAX();
        ArrayList<Mago>  arrayMaghi = new ArrayList<>();
        arrayMaghi= leggiMaghi.vaiConIlSAX();
        Random genRandomMaghi=new Random();
        int numM = genRandomMaghi.nextInt(arrayMaghi.size()-1);
        //---------------------------------------------------

        this.generatoreRandom=new Random();// serve per lo switch (seleziona o maghi o guerrieri).

        return switch(generatoreRandom.nextInt(2))
        {
            //QUI USO L'ARRAY RITORNATO DALLA CLASSE CHE FA IL PARSING DEL FILE XML dei maghi CON SAX:
            case 0-> new Mago(arrayMaghi.get(numM).getNomePersonaggio(),
                    arrayMaghi.get(numM).getPuntiVitaPersonaggio(),
                    arrayMaghi.get(numM).getDannoCausato(),
                    arrayMaghi.get(numM).getNumeroMagie());

            //QUI USO GSON CHE PARSA UN FILE JSON PIENO DI GUERRIERI:
            case 1-> new Guerriero(arrayGuerr.get(numG).getNomePersonaggio(),
                    arrayGuerr.get(numG).getPuntiVitaPersonaggio(),
                    arrayGuerr.get(numG).getDannoCausato());

            //QUI NIENTE!
           default -> throw new IllegalArgumentException("Qualcosa non è andato bene " +
                    "nella classe FactoryOggetti");

        };
    }
}
