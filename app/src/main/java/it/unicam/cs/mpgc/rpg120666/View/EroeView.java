package it.unicam.cs.mpgc.rpg120666.View;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EroeView
{
    private Label vitaEroe = new Label("VITA EROE: ");
    private Label puntiAttaccoEroe = new Label("PUNTI ATTACCO EROE: ");

    private TextField txtVitaEroe = new TextField("");
    private TextField txtPuntiAttaccoEroe = new TextField("");

    private VBox layotVistaEroe = new VBox(10);

    public EroeView()
    {
        layotVistaEroe.getChildren().addAll(vitaEroe,txtVitaEroe,puntiAttaccoEroe,txtPuntiAttaccoEroe);
        layotVistaEroe.setStyle("-fx-background-color: mediumpurple;");
    }
    public TextField getTxtVitaEroe() { return this.txtVitaEroe; }
    public TextField getTxtPuntiAttaccoEroe() { return this.txtPuntiAttaccoEroe; }
    /*Commento questi metodi set, non li ho mai usati nel codice:
     *public void setTxtVitaEroe(TextField txtVitaEroe) { this.txtVitaEroe = txtVitaEroe; }
     *public void setTxtPuntiAttaccoEroe(TextField txtPuntiAttaccoEroe) {this.txtPuntiAttaccoEroe=txtPuntiAttaccoEroe;}
     * Per settare il testo dentro questi TextField, (nelle classi ZainoController e MatriceController) ho utilizzato
     * la catena di metodi: .getTxt___Eroe().setText(String.valueOf((Eroe.getIntero____)));*/

    /*Provo a prendermi tutto il LayoutManager
    * Qui sarebbe stato meglio inserire come valore di ritorno Node. Perché se in futuro decidessi di cambiare il layout
    * manager della classe, non dovrei cambiare anche questo valore di ritorno. Lo tengo come un appunto.
    * Perchè in javaFx tutti i componenti sono nodi dell'albero.*/
    public VBox getLayotVistaEroe() { return this.layotVistaEroe; }
}