package it.unicam.cs.mpgc.rpg120666.View;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class EroeView
{
    private Label vitaEroe = new Label("Vita Eroe: ");
    private TextField txtVitaEroe = new TextField("");
    private HBox layotVistaEroe = new HBox(10);

    public EroeView()
    {
        layotVistaEroe.getChildren().addAll(vitaEroe,txtVitaEroe);
    }
    public TextField getTxtVitaEroe() { return txtVitaEroe; }
    public void setTxtVitaEroe(TextField txtVitaEroe) { this.txtVitaEroe = txtVitaEroe; }

    /*Provo a prendermi tutto il LayoutManager*/
    public HBox getLayotVistaEroe() { return this.layotVistaEroe; }
}