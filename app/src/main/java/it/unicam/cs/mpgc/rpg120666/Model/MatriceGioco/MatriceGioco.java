package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.Difficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;

import java.util.Random;
public class MatriceGioco
{
    private Difficolta diffGioco;
    private Random generatoreRandom;
    private PopolaMatriceNemiciOggetti popolaNemiciOggetti;
    private PopolaMatriceEroe popolaMatriceEroe; //todo
    private Eroe ilNostroEroe; //todo
    private static final int CELLE_MATRICE_FACILE = 8; // 16
    private static final int CELLE_MATRICE_MEDIA = 16; //32
    private static final int CELLE_MATRICE_DIFFICILE = 32; //64
    private ElementiPerMatrice[][] matrice;
    //private HashMap<CellaMatrice, ElementiPerMatrice> hashMapNemici_Oggetti; /* = new HashMap<>();*/
    // Costruttore vuoto
    public MatriceGioco() {}
    // Costruttore con difficoltà
    public MatriceGioco(EnumDifficolta difficolta, Eroe IlNostroEroe) //todo
    {
        this.generatoreRandom = new Random();
        this.matrice = creaMatriceInBaseAllaDifficolta(difficolta); //Crea la dimensione della matrice!
        this.diffGioco=new Difficolta(difficolta); //Crea il numero di nemici e oggetti nella matrice!
        this.popolaNemiciOggetti = new PopolaMatriceNemiciOggetti(this,this.diffGioco);
        this.ilNostroEroe=IlNostroEroe; //todo
        this.popolaMatriceEroe = new PopolaMatriceEroe(this,this.ilNostroEroe); //todo

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
        if (this.controllaConfiniMatrice(cella) && oggetto!=null /*&& oggetto instanceof ElementiPerMatrice*/ )
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
    //Metodo get:
    public int getDimensione()
    {
        return this.matrice.length;
    }
    public int getNumeroNemici(){ return this.diffGioco.getNumNemici(); }
    public int getNumeroOggetti(){ return this.diffGioco.getNumOggetti(); }


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

}
