package it.unicam.cs.mpgc.rpg120666.Main;

import it.unicam.cs.mpgc.rpg120666.Controller.ManagerTastiera;
import it.unicam.cs.mpgc.rpg120666.Controller.MatriceGiocoController;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.View.MatriceGiocoView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application
{
    public static void main(String[] args)
    {
        /*Il metodo main contiene il metodo lauch() che serve per avviare la classe Javafx.*/
        launch();
    }
    @Override
    public void start(Stage palcoscenico) throws Exception
    {
        //0)Creo L'eroe
        Eroe ilNostroEroe = new  Eroe("Riccardo",100);
        // 1) MODEL
        MatriceGioco model = new MatriceGioco(EnumDifficolta.MEDIA,ilNostroEroe);

        // 2) VIEW
        int dimensione = model.getDimensione();
        MatriceGiocoView view = new MatriceGiocoView(dimensione);

        // 3) CONTROLLER
        MatriceGiocoController controller = new MatriceGiocoController(model, view);

        // 4) COLLEGO IL CONTROLLER ALLA VIEW
        view.setController(controller);

        // 5) SCENA
        Scene scena = new Scene(view.getGridPane());

        //5.1)PER I CONTROLLI DA TASTIERA
        ManagerTastiera managerTastiera = new ManagerTastiera(controller);
        scena.setOnKeyPressed(event -> {
            managerTastiera.gestisciTasto(event.getCode());
        });

        // 6) STAGE = palcoscenico (su cui si basa la scena)
        palcoscenico.setScene(scena);
        palcoscenico.setTitle("Matrice Gioco");
        palcoscenico.show();
    }
}