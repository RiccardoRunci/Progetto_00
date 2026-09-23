package it.unicam.cs.mpgc.rpg120666.View;

import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class StatisticheView
{
    private Label lblNumNemici=new Label("NUMERO NEMICI:");
    private Label lblNumOggetti=new Label("NUMERO OGGETTI:");
    private TextField txtNumNemici= new TextField();
    private TextField txtNumOggetti= new TextField();
    private HBox layotTotVistaStatistiche = new HBox(10);
    public StatisticheView()
    {
        layotTotVistaStatistiche.getChildren().addAll(lblNumNemici,txtNumNemici,
                lblNumOggetti,txtNumOggetti);
    }
    public Label getLblNumNemici() { return this.lblNumNemici; }
    public Label getLblNumOggetti() { return this.lblNumOggetti; }
    public void setLblNumNemici(Label lblNumNemici){ this.lblNumNemici=lblNumNemici; }
    public void setLblNumOggetti(Label lblNumOggetti){ this.lblNumOggetti=lblNumOggetti; }
    public TextField getTxtNumNemici() {return this.txtNumNemici;}
    public TextField getTxtNumOggetti(){return this.txtNumOggetti;}
    public void setTxtNumNemici(TextField txtNumNemici){ this.txtNumNemici=txtNumNemici; }
    public void setTxtNumOggetti(TextField txtNumOggetti){ this.txtNumOggetti=txtNumOggetti; }
    public void scorriMatriceContaNemiciOggetti(ElementiPerMatrice[][] matriceElementi)
    {
        ElementiPerMatrice ogg;
        int contatoreNemici=0,contatoreOggetti=0;
        for(int righe=0; righe<matriceElementi.length; righe++)
        {
            for (int colonne = 0; colonne < matriceElementi.length; colonne++)
            {
                ogg = matriceElementi[righe][colonne];
                if (ogg instanceof Nemico)
                    contatoreNemici++;
                else if (ogg instanceof Oggetto)
                    contatoreOggetti++;
            }
        }
        this.txtNumNemici.setText(""+contatoreNemici);
        this.txtNumOggetti.setText(""+contatoreOggetti);
    }

    public Node getLayoutTotale() {
        return this.layotTotVistaStatistiche;}
}
