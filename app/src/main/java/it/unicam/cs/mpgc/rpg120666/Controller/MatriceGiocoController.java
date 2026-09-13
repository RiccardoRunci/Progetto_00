package it.unicam.cs.mpgc.rpg120666.Controller;

import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.View.MatriceGiocoView;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MatriceGiocoController
{
    private MatriceGioco model;
    private MatriceGiocoView view;
    private int indiceCellaXAttuale = 0;
    private int indiceCellaYAttuale = 0;

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
        ElementiPerMatrice ogg = model.getOggettoDellaMatrice(new CellaMatrice(x, y));
        if (ogg == null) { System.out.println("Cella vuota."); }
        else { System.out.println("Contenuto della cella: \n" + ogg.toString()); }
        this.view.aggiorna(model);
    }

    public void apriFinestraDettagli(int x, int y)
    {
        ElementiPerMatrice ogg = model.getOggettoDellaMatrice(new CellaMatrice(x, y));
        if(ogg!=null)
        {
            VBox root = new VBox(20);
            root.setStyle("-fx-padding: 20; -fx-alignment: center;");

            Label titolo = new Label("Dettagli dell'oggetto");
            titolo.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

            Label contenuto = new Label(ogg.toString());
            contenuto.setStyle("-fx-font-size: 16;");

            root.getChildren().addAll(titolo, contenuto);

            Stage finestra = new Stage();
            finestra.setTitle("Dettagli cella");
            finestra.setScene(new Scene(root, 400, 200));
            finestra.show();
        }
    }

    public void muoviSu()
    {
        if (indiceCellaXAttuale > 0)
            indiceCellaXAttuale--;
        view.evidenziaCella(indiceCellaXAttuale, indiceCellaYAttuale);
    }

    public void muoviGiu()
    {
        if (indiceCellaXAttuale < model.getDimensione() - 1)
            indiceCellaXAttuale++;
        view.evidenziaCella(indiceCellaXAttuale, indiceCellaYAttuale);
    }

    public void muoviSinistra()
    {
        if (indiceCellaYAttuale > 0)
            indiceCellaYAttuale--;
        view.evidenziaCella(indiceCellaXAttuale, indiceCellaYAttuale);
    }

    public void muoviDestra()
    {
        if (indiceCellaYAttuale < model.getDimensione() - 1)
            indiceCellaYAttuale++;
        view.evidenziaCella(indiceCellaXAttuale, indiceCellaYAttuale);
    }
}
