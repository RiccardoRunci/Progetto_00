package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Controller.ManagerMovimento;
import it.unicam.cs.mpgc.rpg120666.Model.Attori.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.EnumDifficoltaGioco;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Personaggio.Personaggio;

public class Main1
{
    public String saluta() {return "Benvenuto nel gioco RPG!"; }

    public static void main(String[] args)
    {
        MatriceGioco gioco = new MatriceGioco(EnumDifficoltaGioco.FACILE);
        ManagerMovimento manager = new ManagerMovimento(gioco);
        System.out.println(gioco.toString());

        Personaggio p = new Guerriero("Achille", 10, 10);
        CellaMatrice destinazione = new CellaMatrice(3, 5);
        boolean mosso = manager.muovi(p, destinazione);

        System.out.println("Movimento riuscito: " + mosso);
        System.out.println(gioco.toString());
    }
}
