package it.unicam.cs.mpgc.rpg120666.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class SpostamentoEroeView
{
    /**Componenti grafici per far muovere tra le celle il nostro eroe!*/
    private VBox layoutTotale = new VBox(10);
    private HBox riga1 = new HBox(10);
    private HBox riga2 = new HBox(10);
    private HBox riga3 = new HBox(10);
    private Label lblCoordinateRiga=new Label("Vai a RIGA:   ");
    private TextField txtRigaMatrice= new TextField("");
    private Label lblCoordinateColonna=new Label("Vai a COLONNA:");
    private TextField txtColonnaMatrice= new TextField("");
    private Button btnMandaEroeAllaCasella = new Button("VAI!");

    public SpostamentoEroeView()
    {
        //Componenti per far muovere il nostro eroe dentro 3 Hbox (riga1,riga2,riga3).
        this.riga1.setAlignment(Pos.CENTER_LEFT);
        this.riga1.getChildren().addAll(lblCoordinateRiga, txtRigaMatrice);
        this.riga2.setAlignment(Pos.CENTER_LEFT);
        this.riga2.getChildren().addAll(lblCoordinateColonna, txtColonnaMatrice);
        this.riga3.setAlignment(Pos.CENTER_LEFT);
        this.riga3.getChildren().add(btnMandaEroeAllaCasella);
        this.layoutTotale.getChildren().addAll(riga1,riga2,riga3);
    }
    public VBox  getLayoutTotale() { return this.layoutTotale; }

    public void setTxtRigaMatrice(TextField txtRigaMatrice) { this.txtRigaMatrice=txtRigaMatrice; }
    public void setTxtColonnaMatrice(TextField txtColonnaMatrice) {this.txtColonnaMatrice=txtColonnaMatrice; }
    public TextField getTxtRigaMatrice(){ return this.txtRigaMatrice;}
    public TextField getTxtColonnaMatrice(){ return this.txtColonnaMatrice; }
    public Button getBtnMandaEroeAllaCasella(){ return this.btnMandaEroeAllaCasella; }
}