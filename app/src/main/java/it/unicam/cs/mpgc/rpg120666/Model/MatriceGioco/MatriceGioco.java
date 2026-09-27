package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;

import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.Difficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti.MatriceContaElementi;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento.PopolaMatriceEroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento.PopolaMatriceNemici;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento.PopolaMatriceOggetti;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ClassiEstese.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;

import java.util.Random;
public class MatriceGioco
{
    private Difficolta diffGioco;
    private Random generatoreRandom;

    private PopolaMatriceNemici popolaMatriceNemici;
    private PopolaMatriceOggetti popolaMatriceOggetti;
    private PopolaMatriceEroe popolaMatriceEroe;
    private final MatriceContaElementi matriceContaElementi; /*Classe che serve per contare gli elementi rimanenti in matrice.*/
    private Eroe ilNostroEroe;
    private static final int CELLE_MATRICE_FACILE = 8; // 16
    private static final int CELLE_MATRICE_MEDIA = 16; //32
    private static final int CELLE_MATRICE_DIFFICILE = 32; //64
    private ElementiPerMatrice[][] matrice;

    // Costruttore con difficoltà
    public MatriceGioco(EnumDifficolta difficolta, Eroe IlNostroEroe)
    {
        this.generatoreRandom = new Random();
        this.matrice = creaMatriceInBaseAllaDifficolta(difficolta); //Crea la dimensione della matrice!
        this.diffGioco=new Difficolta(difficolta); //Crea il numero di nemici e oggetti nella matrice!


        this.ilNostroEroe=IlNostroEroe;
        this.popolaMatriceEroe = new PopolaMatriceEroe(this,this.ilNostroEroe); //todo
        this.popolaMatriceNemici = new PopolaMatriceNemici(this);
        this.popolaMatriceOggetti = new PopolaMatriceOggetti(this);

        /*Creo un oggetto che serve per contare gli elementi rimanenti in matrice (Oggetti, Nemici, ecc..)*/
        this.matriceContaElementi =new MatriceContaElementi(this.matrice);
    }
    /**Crea la matrice in base alla difficoltà.*/
    private ElementiPerMatrice[][] creaMatriceInBaseAllaDifficolta(EnumDifficolta difficolta)
    {
        return switch (difficolta)
        {
            case FACILE -> new ElementiPerMatrice[CELLE_MATRICE_FACILE][CELLE_MATRICE_FACILE];
            case MEDIA -> new ElementiPerMatrice[CELLE_MATRICE_MEDIA][CELLE_MATRICE_MEDIA];
            case DIFFICILE -> new ElementiPerMatrice[CELLE_MATRICE_DIFFICILE][CELLE_MATRICE_DIFFICILE];
        };
    }
    //Metodo set:
    public void setOggettoNellaMatrice(ElementiPerMatrice oggetto, CellaMatrice cella)
    {
        /*Ho dovuto levare il controllo di "oggetto!=null" perchè quando vado a spostare l'eroe in una nuova
         * cella, per forza devo passare null a questo metodo, per far mettere null nella vecchia cella dove stava
         * l'eroe, prima che va nella nuova posizione!*/
        if (this.controllaConfiniMatrice(cella) /*&& oggetto!=null*/ /*&& oggetto instanceof ElementiPerMatrice*/ )
        {   //true se sto dentro i confini:
            this.matrice[cella.getRiga()][cella.getColonna()] = oggetto;
            //this.hashMapNemici_Oggetti.put(cella, oggetto);
        }
    }
    //Metodo get:
    public ElementiPerMatrice getOggettoDellaMatrice(CellaMatrice cella)
    {
        if(this.controllaConfiniMatrice(cella)) //se è true sto dentro la matrice!
            return this.matrice[cella.getRiga()][cella.getColonna()];
        else
        {
            //System.out.println("Errore del metodo get della classe MatriceGioco\nStai fuori dalla matrice!\n");
            throw new ArrayIndexOutOfBoundsException("Errore del metodo get della classe MatriceGioco\nStai fuori dalla matrice!\n");
        }
    }
    public MatriceContaElementi getMatriceContaElementi() {return this.matriceContaElementi;}
    //Metodo get:
    public int getDimensione()
    {
        return this.matrice.length;
    }
    public Difficolta getDifficoltaGioco(){ return this.diffGioco; }
    public int getNumeroNemiciIniziali(){ return this.diffGioco.getNumNemici(); }
    public int getNumeroOggettiIniziali(){ return this.diffGioco.getNumOggetti(); }
    public Eroe getIlNostroEroe(){ return this.ilNostroEroe; }
    public ElementiPerMatrice[][] getMatriceElementi() { return this.matrice; }

    public CellaMatrice trovaCellaVuota()
    {
        int x, y;

        while (true)
        {
            x = generatoreRandom.nextInt(matrice.length);
            y = generatoreRandom.nextInt(matrice.length);

            //Escludi SEMPRE la cella in basso a destra, ci devo mettere il nostro EROE!
            if (x == matrice.length - 1 && y == matrice.length - 1)
                continue;

            //Escludi celle occupate
            if (matrice[x][y] != null)
                continue;

            //Cella valida
            return new CellaMatrice(x, y);
        }
    }

    public boolean controllaConfiniMatrice(CellaMatrice cella)
    {
        if(cella.getRiga()<0 || cella.getColonna()>this.matrice.length-1 || cella.getColonna()<0 || cella.getColonna()>this.matrice.length-1)
        {
            System.out.print("Fuori dai confini della matrice!"); //todo eliminare questo system.out appena vedo che è corretto!
            return false; //false = fuori dai confini della matrice!
        }
        else return true; //true = stai dentro la matrice!
    }
    /**@Deprecated Metodo per "debug". Mi serviva per vedere come si comportava la matrice della classe MatriceGioco.
     * Avevo un "bug" nel metodo setOggettoNellaMatrice(ElementiPerMatrice oggetto, CellaMatrice cella).
     * Facevo un controllo (oggetto!=null) -> NON SI POTEVA!
     * Invece la vecchia posizione dell'eroe (quella che doveva essere cancellata), veniva passata come null e quindi
     * non si cancellava.
     * Dato che sono riuscito a risolvere un bug che copilot non riusciva a risolvermi, sono rimasto affezionato e ho
     * tenuto il metodo.*/
    @Deprecated
    public void stampaMatriceCLI()
    {
        ElementiPerMatrice element;

        for(int riga=0; riga<this.matrice.length; riga++)
        {
            for (int colonna = 0; colonna < this.matrice.length; colonna++)
            {
                element= this.matrice[riga][colonna];
                if (element==null)
                {
                    //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Vuota\t");
                    System.out.print("-\t");
                }
                else
                {
                    if(element instanceof Mago)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Nemico\t");
                        System.out.print("M\t");
                    if(element instanceof Guerriero)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Nemico\t");
                        System.out.print("G\t");
                    else if(element instanceof Arma)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Oggetto\t");
                        System.out.print("A\t");
                    else if(element instanceof Medikit)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Oggetto\t");
                        System.out.print("+\t");
                    else if (element instanceof Eroe)
                        //System.out.print("[R:"+riga+"]"+"[C:"+colonna+"] = Eroe\t");
                        System.out.print("E\t");
                }
            }
            System.out.print("\n");
        }
    }

}
