package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Attori.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Attori.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.OggettiPerMatrice.OggettiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;
import java.util.Random;

public class MatriceGioco
{
    private Random generatoreRandom;
    private int numeroNemici, numeroOggetti;
    private static final int CELLE_MATRICE_FACILE = 16;
    private static final int CELLE_MATRICE_MEDIA = 32;
    private static final int CELLE_MATRICE_DIFFICILE = 64;
    private OggettiPerMatrice[][] matrice;
    //private HashMap<CellaMatrice, OggettiPerMatrice> hashMapNemici_Oggetti; /* = new HashMap<>();*/

    //Metodo set:
    public void setOggettoNellaMatrice(OggettiPerMatrice oggetto,CellaMatrice cella)
    {
        if (this.controllaConfiniMatrice(cella) && oggetto!=null /*&& oggetto instanceof OggettiPerMatrice*/ )
        {   //true se sto dentro i confini:
            this.matrice[cella.getX()][cella.getY()] = oggetto;
            //this.hashMapNemici_Oggetti.put(cella, oggetto);
        }
    }
    //Metodo get:
    public OggettiPerMatrice getOggettoDellaMatrice(CellaMatrice cella)
    {
        if(this.controllaConfiniMatrice(cella)) //se è true sto dentro la matrice!
            return this.matrice[cella.getX()][cella.getY()];
        else
        {
            System.out.println("Errore del metodo get della classe MatriceGioco\nStai fuori dalla matrice!\n"); //todo ricordati di levare questo printf quando funziona tutto!
            throw new ArrayIndexOutOfBoundsException("Errore del metodo get della classe MatriceGioco\nStai fuori dalla matrice!\n");
        }
    }
    // Costruttore vuoto
    public MatriceGioco() {}
    // Costruttore con difficoltà
    public MatriceGioco(EnumDifficoltaGioco difficolta)
    {
        //this.hashMapNemici_Oggetti = new HashMap<>();
        this.generatoreRandom = new Random();
        this.matrice = creaMatriceInBaseAllaDifficolta(difficolta);
        settaNumeroOggettiMatrice(difficolta);
    }

    /**Crea la matrice in base alla difficoltà.*/
    private OggettiPerMatrice[][] creaMatriceInBaseAllaDifficolta(EnumDifficoltaGioco difficolta)
    {
        return switch (difficolta)
        {
            case FACILE -> new OggettiPerMatrice[CELLE_MATRICE_FACILE][CELLE_MATRICE_FACILE];
            case MEDIA -> new OggettiPerMatrice[CELLE_MATRICE_MEDIA][CELLE_MATRICE_MEDIA];
            case DIFFICILE -> new OggettiPerMatrice[CELLE_MATRICE_DIFFICILE][CELLE_MATRICE_DIFFICILE];
            default -> throw new IllegalStateException("Eccezione nella classe MatriceGioco: difficoltà non valida!");
        };
    }
    /**Inserisce nemici, oggetti, ecc. in base alla difficoltà.*/
    private void settaNumeroOggettiMatrice(EnumDifficoltaGioco difficolta)
    {
        switch (difficolta)
        {
            case FACILE ->
            {
                // Inserisci pochi nemici
                this.numeroNemici = this.generatoreRandom.nextInt(CELLE_MATRICE_FACILE/4)+1; //[1,...,4]
                // Inserisci tanti oggetti
                this.numeroOggetti = this.generatoreRandom.nextInt(CELLE_MATRICE_FACILE/2)+1; //[1,...,8]
                this.popolaMatrice();
            }
            case MEDIA ->
            {
                // Inserisci medi nemici
                this.numeroNemici = this.generatoreRandom.nextInt(CELLE_MATRICE_MEDIA/3)+1; //[1,...,10 ]
                // Inserisci medi oggetti
                this.numeroOggetti=this.numeroNemici; // mi faccio andare bene [1,...,10] anche per gli oggetti!
                this.popolaMatrice();
            }
            case DIFFICILE ->
            {
                // Inserisci molti nemici
                this.numeroNemici = this.generatoreRandom.nextInt(CELLE_MATRICE_DIFFICILE/2)+1; //[1,...,32]
                // Inserisci pochi oggetti
                this.numeroOggetti = this.generatoreRandom.nextInt(CELLE_MATRICE_FACILE/4)+1; //[1,...,16]
                this.popolaMatrice();
            }
            default -> { /*Non dovrebbe mai accadere*/ }
        }
    }
    private void popolaMatrice()
    {
        OggettiPerMatrice pers;
        Oggetto ogg;
        for(int i=0;i<this.numeroNemici;i++)
        {
            //todo SECONDO ME NON FUNZIONA! (il fatto che un oggetto Personaggio faccia i metodi di mago e di guerriero)!
            if(i%2==0)                                               //this.numeroNemici perché mi servivano 2 int (senza che li ri-dichiaravo).
            {
                pers = new Guerriero("Agamennone_num:" + i, this.numeroNemici, this.numeroNemici);
                //todo fai il codice per inserirli dentro una cella a caso della matrice!
                this.setOggettoNellaMatrice(pers,this.trovaCellaVuota());
            }
            else
            {
                pers = new Mago("Merlino_num:" + i, this.numeroNemici, this.numeroNemici);
                //todo fai il codice per inserirli dentro una cella a caso della matrice!
                this.setOggettoNellaMatrice(pers,this.trovaCellaVuota());
            }
        }

         for(int j=0;j<this.numeroOggetti;j++)
         {
                //todo devo ancora fare le classi che estendono Oggetto!
         }

    }
    private CellaMatrice trovaCellaVuota()
    {
        for (int i = 0; i < this.matrice.length; i++)
        {
            for (int j = 0; j < this.matrice.length; j++)
            {
                if (matrice[i][j] == null)
                {
                    return new CellaMatrice(i, j);
                }
            }
        }
        return null; // nessuna cella vuota
    }

//    private boolean controllaSeLaCellaELibera(CellaMatrice cella)
//    {
//        return (this.matrice[cella.getX()][cella.getY()] == null); /*True = cella libera, scritto come vuole Intelli j!*/
//    }
    public boolean controllaConfiniMatrice(CellaMatrice cella)
    {
        if(cella.getX()<0 || cella.getX()>this.matrice.length-1 || cella.getY()<0 || cella.getY()>this.matrice.length-1)
        {
            System.out.print("Fuori dai confini della matrice!"); //todo eliminare questo system.out appena vedo che è corretto!
            return false; //false = fuori dai confini della matrice!
        }
        else return true; //true = stai dentro la matrice!
    }
    @Override
    public String toString()
    {
        StringBuilder Mat = new StringBuilder();

        for(int i=0;i<this.matrice.length;i++)
        {
            for (int j = 0; j < this.matrice[i].length; j++)
            {
                if(this.matrice[i][j]!=null)
                Mat.append(this.matrice[i][j].toString()+"\t");
                else
                    Mat.append("VUOTA\t");
            }
            Mat.append("\n");

        }
        System.out.println("IN QUESTA MAPPA CI SONO:");
        System.out.println("Num Oggetti: "+this.numeroOggetti);
        System.out.println("Num Nemici: "+this.numeroNemici);
        return Mat.toString();
    }
}
