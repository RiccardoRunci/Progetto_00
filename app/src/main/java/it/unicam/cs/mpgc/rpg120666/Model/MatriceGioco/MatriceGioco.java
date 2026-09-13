package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.Difficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.EnumListaNemiciGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.InventoreNomiNemici;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.EnumListaOggettiGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.InventoreNomiOggetti;
import java.util.Random;
public class MatriceGioco
{
    private Random generatoreRandom;
    private int numeroNemici, numeroOggetti;
    Difficolta diffGioco;
    private static final int CELLE_MATRICE_FACILE = 8; // 16
    private static final int CELLE_MATRICE_MEDIA = 16; //32
    private static final int CELLE_MATRICE_DIFFICILE = 32; //64
    private ElementiPerMatrice[][] matrice;
    //private HashMap<CellaMatrice, ElementiPerMatrice> hashMapNemici_Oggetti; /* = new HashMap<>();*/

    //Metodo set:
    public void setOggettoNellaMatrice(ElementiPerMatrice oggetto, CellaMatrice cella)
    {
        if (this.controllaConfiniMatrice(cella) && oggetto!=null /*&& oggetto instanceof ElementiPerMatrice*/ )
        {   //true se sto dentro i confini:
            this.matrice[cella.getX()][cella.getY()] = oggetto;
            //this.hashMapNemici_Oggetti.put(cella, oggetto);
        }
    }
    //Metodo get:
    public ElementiPerMatrice getOggettoDellaMatrice(CellaMatrice cella)
    {
        if(this.controllaConfiniMatrice(cella)) //se è true sto dentro la matrice!
            return this.matrice[cella.getX()][cella.getY()];
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
    public int getNumeroNemici(){ return this.numeroNemici; }
    public int getNumeroOggetti(){ return this.numeroOggetti; }
    // Costruttore vuoto
    public MatriceGioco() {}
    // Costruttore con difficoltà
    public MatriceGioco(EnumDifficolta difficolta)
    {
        this.generatoreRandom = new Random();
        this.matrice = creaMatriceInBaseAllaDifficolta(difficolta); //Crea la dimensione della matrice!
        this.diffGioco=new Difficolta(difficolta); //Crea il numero di nemici e oggetti nella matrice!
        settaNumeroOggettiMatrice(difficolta);  //Crea il numero di nemici e oggetti nella matrice!
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
    /**Inserisce nemici, oggetti, ecc. in base alla difficoltà.*/
    private void settaNumeroOggettiMatrice(EnumDifficolta difficolta)
    {
        switch (difficolta)
        {
            case FACILE , MEDIA, DIFFICILE-> //con questo modo ho tolto 17 righe di codice :)
            {
                this.numeroNemici = this.diffGioco.getNumNemici();
                this.numeroOggetti = this.diffGioco.getNumOggetti();
                this.popolaMatrice();
            }
            default -> { /*Non dovrebbe mai accadere*/ }
        }
    }
    private void popolaMatrice()
    {
        ElementiPerMatrice pers;
        for(int i=0;i<this.numeroNemici;i++)
        {
            //todo SECONDO ME NON FUNZIONA! (il fatto che un oggetto Nemico faccia i metodi di mago e di guerriero)!
            if(i%2==0)
            {
                try
                {
                    char lettera = (char) ('A' + i); //Mi serve per fare "_Lettera" nel nome, non i (perchè ho un controllo sulle cifre nel nome).
                    pers = new Guerriero(InventoreNomiNemici.ritornaNomeACaso(EnumListaNemiciGioco.GUERRIERO)+"_"+lettera,
                            generatoreRandom.nextInt(40-5)+1,
                            generatoreRandom.nextInt(40-5)+1);

                    this.setOggettoNellaMatrice(pers, this.trovaCellaVuota());
                }
                catch (Exception e) { throw new RuntimeException(e); }
            }
            else
            {
                try
                {
                    char lettera = (char) ('A' + i);
                    pers = new Mago(InventoreNomiNemici.ritornaNomeACaso(EnumListaNemiciGioco.MAGO)+"_"+lettera,
                        generatoreRandom.nextInt(40-5)+1,
                        generatoreRandom.nextInt(40-5)+1);

                this.setOggettoNellaMatrice(pers,this.trovaCellaVuota());
                }
                catch (Exception e) { throw new RuntimeException(e); }
            }
        }

         for(int j=0;j<this.numeroOggetti;j++)
         {
                //todo devo ancora fare le classi che estendono Oggetto!
             if(j%2==0)
             {
                 try
                 {
                     char lettera = (char) ('A' + j); //Mi serve per fare "_Lettera" nel nome, non i (perchè ho un controllo sulle cifre nel nome).
                     pers = new Medikit(InventoreNomiOggetti.ritornaNomeACaso(EnumListaOggettiGioco.MEDIKIT)+"_"+lettera,
                             generatoreRandom.nextInt(40-5)+1);
                     this.setOggettoNellaMatrice(pers, this.trovaCellaVuota());
                 }
                 catch (Exception e) { throw new RuntimeException(e); }
             }
             else
             {
                 char lettera = (char) ('A' + j); //Mi serve per fare "_Lettera" nel nome, non i (perchè ho un controllo sulle cifre nel nome).
                 pers = new Arma(InventoreNomiOggetti.ritornaNomeACaso(EnumListaOggettiGioco.ARMA)+"_"+lettera,
                         generatoreRandom.nextInt(40-5)+1);
                 this.setOggettoNellaMatrice(pers, this.trovaCellaVuota());
             }
         }

    }
    private CellaMatrice trovaCellaVuota()
    {
        int x = this.generatoreRandom.nextInt(this.matrice.length);
        int y = this.generatoreRandom.nextInt(this.matrice.length);
        if(this.matrice[x][y]==null)
            return new CellaMatrice(x,y);
        else
        {
            do
            {
                x = this.generatoreRandom.nextInt(this.matrice.length);
                y = this.generatoreRandom.nextInt(this.matrice.length);

                // Escludo l'ultima cella in basso a destra. CI METTO L'EROE!
                if (x == this.matrice.length - 1 && y == this.matrice.length - 1)
                {
                    continue; // ripeti
                }
            }
            while(this.matrice[x][y]!=null);
            return new CellaMatrice(x,y);
        }
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
