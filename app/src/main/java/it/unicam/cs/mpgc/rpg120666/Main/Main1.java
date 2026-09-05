package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Model.Attori.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.EnumDifficoltaGioco;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;

public class Main1
{
    public String saluta() {return "Benvenuto nel gioco RPG!"; }

    public static void main(String[] args)
    {
        MatriceGioco gioco = new MatriceGioco(EnumDifficoltaGioco.DIFFICILE);
        System.out.println(gioco.toString());

    }
}
