package it.unicam.cs.mpgc.rpg120666.Main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainFX extends Application
{
    @Override
    public void start(Stage palcoscenico) throws Exception
    {
        /*Sul palcoscenico (oggetto di classe Stage) ci monto la scena1 (che è un oggetto di classe Scene), dentro la scena1 ci metto *una label con scritto "Hello JAVAFX" (che è un nodo). Poi infine mostro il palcoscenico. */
        Text l = new Text("Hello JAVAFX");
        /*LA REGISTRAZIONE DI UN handler va messo nel palcoscenico!*/
        EventHandler<? super MouseEvent> handlerMio = new EventHandler<MouseEvent>()
        {
            @Override public void handle(MouseEvent event)
            {
                System.out.println("Coordinate x:"+event.getX()+"Coordinate y:"+event.getY());
            }
        };
        l.setOnMouseClicked(handlerMio); //associo la registrazione sopra del handler al componente Text
        /*Faccio "la stessa cosa" (scrivo il codice dell'handler + l'associzione a un componente) con un unica riga di codice grazie alla lambda expression:*/
        l.setOnMouseReleased(e->{
            System.out.println("hai fatto il release del mouse!");
        });
        Scene scena1 = new Scene(new StackPane(l),640,480);
        palcoscenico.setScene(scena1);
        palcoscenico.show();
    }
        public static void main(String[] args)
        {
            /*Il metodo main contiene il metodo lauch() che serve per avviare la classe Javafx.*/
            launch();
        }
}