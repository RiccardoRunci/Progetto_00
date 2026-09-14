package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.Difficolta;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.EnumListaNemiciGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.InventoreNomiNemici;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.EnumListaOggettiGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.InventoreNomiOggetti;
import java.util.Random;

public class PopolaMatriceNemiciOggetti
{
    private Difficolta diffGioco;
    private MatriceGioco matGioco;
    private Random generatoreRandom;
    private int numeroNemici;
    private int numeroOggetti;
    public PopolaMatriceNemiciOggetti(){}
    public PopolaMatriceNemiciOggetti(MatriceGioco matGioco,Difficolta diffGioco)
    {
        this.generatoreRandom = new Random();
        this.diffGioco=diffGioco;
        this.matGioco=matGioco;
        this.numeroNemici=diffGioco.getNumNemici();
        this.numeroOggetti=diffGioco.getNumOggetti();
        this.riempiMatriceDiOggettiNemici();
    }
    private void riempiMatriceDiOggettiNemici()
    {
        ElementiPerMatrice pers;
        for(int i=0;i<this.numeroNemici;i++)
        {
            //todo SECONDO ME NON FUNZIONA! (il fatto che un oggetto Nemico faccia i metodi di mago e di guerriero)!
            if(i%2==0)
            {
                try
                {
                    char lettera = (char) ('A' + i); //Mi serve per fare "_Lettera" nel nome, non i (perché ho un controllo sulle cifre nel nome).
                    pers = new Guerriero(InventoreNomiNemici.ritornaNomeACaso(EnumListaNemiciGioco.GUERRIERO)+"_"+lettera,
                            generatoreRandom.nextInt(40-5)+1,
                            generatoreRandom.nextInt(40-5)+1);

                    this.matGioco.setOggettoNellaMatrice(pers, this.matGioco.trovaCellaVuota());
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

                    this.matGioco.setOggettoNellaMatrice(pers, this.matGioco.trovaCellaVuota());
                }
                catch (Exception e) { throw new RuntimeException(e); }
            }
        }

        for(int j=0;j<this.numeroOggetti;j++)
        {
            if(j%2==0)
            {
                try
                {
                    char lettera = (char) ('A' + j); //Mi serve per fare "_Lettera" nel nome, non i (perché ho un controllo sulle cifre nel nome).
                    pers = new Medikit(InventoreNomiOggetti.ritornaNomeACaso(EnumListaOggettiGioco.MEDIKIT)+"_"+lettera,
                            generatoreRandom.nextInt(40-5)+1);
                    this.matGioco.setOggettoNellaMatrice(pers,  this.matGioco.trovaCellaVuota());
                }
                catch (Exception e) { throw new RuntimeException(e); }
            }
            else
            {
                char lettera = (char) ('A' + j); //Mi serve per fare "_Lettera" nel nome, non i (perché ho un controllo sulle cifre nel nome).
                pers = new Arma(InventoreNomiOggetti.ritornaNomeACaso(EnumListaOggettiGioco.ARMA)+"_"+lettera,
                        generatoreRandom.nextInt(40-5)+1);
                this.matGioco.setOggettoNellaMatrice(pers,  this.matGioco.trovaCellaVuota());
            }
        }
    }
}