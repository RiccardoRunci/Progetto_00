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
//        //0 Creo l'EROE!
//        Eroe IlNostroEroe = new Eroe("RICCARDO",50);
//        // 1) MODEL
//        MatriceGioco model = new MatriceGioco(EnumDifficolta.MEDIA,IlNostroEroe);
//
//        PopolaMatriceEroe popEroeInMatrice = new PopolaMatriceEroe(model,IlNostroEroe);
//        model.stampaMatriceCLI();
//        PopolaMatriceOggetti popolaOggettiInMatrice= new PopolaMatriceOggetti(model);
//        PopolaMatriceNemici popolaNemiciInMatrice = new PopolaMatriceNemici(model);
//
//        System.out.println("LA MATRICE POPOLATA SARA':\n");
//        model.stampaMatriceCLI();

        Eroe eroe1 = new Eroe("Riccardo",100);
        System.out.println("Eroe vivo: "+eroe1.controllaSeVivo());
        Oggetto medikit1 = new Medikit("PFZIER",100);
        System.out.println("Alllora??? "+medikit1);
        //System.out.println("Alllora??? "+medikit1 == null);
        eroe1.prendiOggetto(medikit1);
        System.out.println("Ora si dovrebbe vedere che in zaino si trova un medikit:\n");
        System.out.println(eroe1.getZaino().toString());
        medikit1.usaOggetto(eroe1);
        System.out.println("Ora si dovrebbe vedere che in zaino NON C'E' + Il medikit:\n");
        System.out.println(eroe1.getZaino().toString());


        Oggetto arma1 = new Arma("Spada",800);
        eroe1.prendiOggetto(arma1);
        eroe1.usaOggetto(arma1);

    }
}
