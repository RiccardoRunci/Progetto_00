package it.unicam.cs.mpgc.rpg120666.Controller;

import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.OggettiPerMatrice.OggettiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.View.MatriceGiocoView;

public class MatriceGiocoController
{
    private MatriceGioco model;
    private MatriceGiocoView view;

    public MatriceGiocoController(MatriceGioco model, MatriceGiocoView view)
    {
        this.model = model;
        this.view = view;

        // inizializza la view
        this.view.aggiorna(model);
    }

    public void aggiornaView()
    {
        this.view.aggiorna(model);
    }

    public void cellaCliccata(int x, int y)
    {
        System.out.println("Hai cliccato la cella: " + x + "," + y);

        OggettiPerMatrice ogg = model.getOggettoDellaMatrice(new CellaMatrice(x, y));

        if (ogg == null)
        {
            System.out.println("Cella vuota.");
        }
        else
        {
            System.out.println("Contenuto della cella: \n" + ogg.toString());
        }

        this.view.aggiorna(model);
    }
}
