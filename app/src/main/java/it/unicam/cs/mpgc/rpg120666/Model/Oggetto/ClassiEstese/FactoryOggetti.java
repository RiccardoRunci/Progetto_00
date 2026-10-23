/**Per risolvere il problema della classe PopolaMatriceOggetti:
 * @see it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento.PopolaMatriceOggetti
 * devo creare una classe che mi restituisce in modo random un oggetto già creato che sia appartenente alla
 * sottoclasse Oggetto.
 * Per fare questo esistono delle classi fatte apposta che si chiamano classi Factory.
 * Per farle mi sono aiutato con copilot, ma non sono molto difficili.
 * @author copilot, Runci Riccardo.
 * @note Capito il procedimento usato nella classe FactoryNemici, ho creato una classe Factory anche per Oggetti.
 * @see it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.FactoryNemici
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.LeggiXMLOggetti_DOM;
import java.util.ArrayList;
import java.util.Random;
public class FactoryOggetti
{
    /*Campo che prenderà l'array restituito dal parser dom del file xml contente tutti gli oggetti.
    * @see Model/Serializzazione/LeggiXMLOggetti_DOM.java*/
    private ArrayList<Oggetto> arrayOggetti;
    private ArrayList<Arma> arrayArmi;
    private ArrayList<Medikit> arrayMedikit;

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
        /*Richiamo la classe: LeggiXMLOggetti_DOM*/
        LeggiXMLOggetti_DOM Ogg=new LeggiXMLOggetti_DOM();
        /*Mi prendo l' array riempito dal parser DOM:*/
        this.arrayOggetti= Ogg.getArrayOggetti();
        /*Adesso questo array sopra contiene mischiati sia Armi che Medikit, quindi dovrei separarli!*/
        this.dividiArrayRestituitoDalDom();

        this.generatoreRandom=new Random();

        /*Sono 2 array che possono avere 2 dimensioni diverse, dipende dal file xml!*/
        Random randomArma=new Random();
        Random randomMedikit=new Random();
        int numArmi = randomArma.nextInt(this.arrayArmi.size()-1);
        int numMedikit = randomMedikit.nextInt(this.arrayMedikit.size()-1);

        return switch(generatoreRandom.nextInt(2))
        {
            case 0-> new Medikit(this.arrayMedikit.get(numMedikit).getNomeOggetto(),
                    this.arrayMedikit.get(numMedikit).getPuntiVitaRistabiliti());


            case 1-> new Arma(this.arrayArmi.get(numArmi).getNomeOggetto(),
                    this.arrayArmi.get(numArmi).getPuntiDannoPiu());


            default -> throw new IllegalArgumentException("Qualcosa non è andato bene " +
                    "nella classe FactoryOggetti");
        };
    }
    private void dividiArrayRestituitoDalDom()
    {
        this.arrayArmi=new ArrayList<>();
        this.arrayMedikit=new ArrayList<>();
        for (int i=0;i<this.arrayOggetti.size();i++)
        {
            if (this.arrayOggetti.get(i) instanceof Medikit)
                this.arrayMedikit.add((Medikit)this.arrayOggetti.get(i));
            else if(this.arrayOggetti.get(i) instanceof Arma)
                this.arrayArmi.add((Arma)this.arrayOggetti.get(i));
        }
    }

}
