package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento.PopolaMatriceEroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento.PopolaMatriceNemici;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Popolamento.PopolaMatriceOggetti;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ClassiEstese.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;

public class MainCli
{
    public String saluta() {return "Benvenuto nel gioco RPG!"; }

    public static void main(String[] args)
    {
        //0 Creo l'EROE!
        Eroe IlNostroEroe = new Eroe("RICCARDO",50);
        // 1) MODEL
        MatriceGioco model = new MatriceGioco(EnumDifficolta.MEDIA,IlNostroEroe);

        PopolaMatriceEroe popEroeInMatrice = new PopolaMatriceEroe(model,IlNostroEroe);
        model.stampaMatriceCLI();
        PopolaMatriceOggetti popolaOggettiInMatrice= new PopolaMatriceOggetti(model);
        PopolaMatriceNemici popolaNemiciInMatrice = new PopolaMatriceNemici(model);

        System.out.println("LA MATRICE POPOLATA SARA':\n");
        model.stampaMatriceCLI();

    }
}
