package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Controller.MatriceController;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.View.MatriceView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFx2 extends Application
{
    @Override
    public void start(Stage palcoscenico) throws Exception
    {
        // 1) MODEL
        MatriceGioco model = new MatriceGioco(EnumDifficolta.DIFFICILE);

        // 2) VIEW
        int dimensione = model.getDimensione();
        /*Prima avevo che view prendeva (dimensione, controller)!
        * solo che controller l'ho dichiarato sotto!
        * e non potevo passarlo quando inizializzavo view!*/
        MatriceView view = new MatriceView(dimensione); //Allora ho risolto con view.setMatriceController! (4)

        // 3) CONTROLLER
        MatriceController controller = new MatriceController(view, model);

        // 4) COLLEGO IL CONTROLLER ALLA VIEW
        view.setMatriceController(controller);
        view.aggiorna(model); //questo fa comparire subito la matrice "colorata", senza bisogna prima cliccarci dentro.

        // 5) SCENA
        Scene scena = new Scene(view.getLayoutManagerTotaleView());

        // 6) STAGE = palcoscenico (su cui si basa la scena)
        palcoscenico.setScene(scena);
        palcoscenico.setTitle("Matrice Gioco");
        palcoscenico.show();
    }
    public static void main(String[] args)
    {
        launch();
    }
}
