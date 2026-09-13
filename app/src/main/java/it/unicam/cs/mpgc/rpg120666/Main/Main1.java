package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Controller.ManagerMovimento;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.EnumDifficoltaGioco;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;

public class Main1
{
    public String saluta() {return "Benvenuto nel gioco RPG!"; }

    public static void main(String[] args)
    {
        MatriceGioco gioco = new MatriceGioco(EnumDifficolta.DIFFICILE);
        ManagerMovimento manager = new ManagerMovimento(gioco);
        System.out.println(gioco.toString());

        Nemico p = new Guerriero("Achille", 10, 10);
        CellaMatrice destinazione = new CellaMatrice(3, 5);
        boolean mosso = manager.muovi(p, destinazione);

        System.out.println("Movimento riuscito: " + mosso);
        System.out.println(gioco.toString());
    }
}
