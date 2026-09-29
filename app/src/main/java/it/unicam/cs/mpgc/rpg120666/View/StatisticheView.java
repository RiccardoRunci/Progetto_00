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
    private Label lblNumMaghi=new Label("NUMERO MAGHI:");
    private Label lblNumGuerrieri=new Label("NUMERO GUERRIERI:");
    private Label lblNumArmi=new Label("NUMERO ARMI:");
    private Label lblNumMedikit=new Label("NUMERO MEDIKIT:");


    private TextField txtNumMaghi= new TextField();
    private TextField txtNumGuerrieri= new TextField();
    private TextField txtNumArmi= new TextField();
    private TextField txtNumMedikit= new TextField();

    private HBox layotTotVistaStatistiche = new HBox(10);

    public StatisticheView()
    {
        layotTotVistaStatistiche.getChildren().addAll(
                lblNumMaghi,txtNumMaghi,
                lblNumGuerrieri,txtNumGuerrieri,
                lblNumArmi,txtNumArmi,
                lblNumMedikit,txtNumMedikit
        );
    }
    public Label getLblNumMaghi() { return this.lblNumMaghi; }
    public Label getLblNumGuerrieri() { return this.lblNumGuerrieri; }
    public Label getLblNumArmi() { return this.lblNumArmi; }
    public Label getLblNumMedikit() { return this.lblNumMedikit; }

    public void setLblNumMaghi(Label lblNumMaghi){ this.lblNumMaghi=lblNumMaghi; }
    public void setLblNumGuerrieri(Label lblNumGuerrieri){ this.lblNumGuerrieri=lblNumGuerrieri; }
    public void setLblNumArmi(Label lblNumArmi){ this.lblNumArmi=lblNumArmi; }
    public void setLblNumMedikit(Label lblNumMedikit){ this.lblNumMedikit=lblNumMedikit; }


    public TextField getTxtNumMaghi() {return this.txtNumMaghi;}
    public TextField getTxtNumGuerrieri() {return this.txtNumGuerrieri;}
    public TextField getTxtNumArmi() {return this.txtNumArmi;}
    public TextField getTxtNumMedikit() {return this.txtNumMedikit;}

    public void setTxtNumMaghi(TextField txtNumMaghi){ this.txtNumMaghi=txtNumMaghi; }
    public void setTxtNumGuerrieri(TextField txtNumGuerrieri){ this.txtNumGuerrieri=txtNumGuerrieri; }
    public void setTxtNumArmi(TextField txtNumArmi){ this.txtNumArmi=txtNumArmi; }
    public void setTxtNumMedikit(TextField txtNumMedikit){ this.txtNumMedikit=txtNumMedikit; }

    //VECCHIO METODO:
//    public void scorriMatriceContaNemiciOggetti(ElementiPerMatrice[][] matriceElementi)
//    {
//        ElementiPerMatrice ogg;
//        int contatoreNemici=0,contatoreOggetti=0;
//        for(int righe=0; righe<matriceElementi.length; righe++)
//        {
//            for (int colonne = 0; colonne < matriceElementi.length; colonne++)
//            {
//                ogg = matriceElementi[righe][colonne];
//                if (ogg instanceof Nemico)
//                    contatoreNemici++;
//                else if (ogg instanceof Oggetto)
//                    contatoreOggetti++;
//            }
//        }
//        this.txtNumNemici.setText(""+contatoreNemici);
//        this.txtNumOggetti.setText(""+contatoreOggetti);
//    }

    public Node getLayoutTotale() {
        return this.layotTotVistaStatistiche;}

    public void aggiornaStatistiche(int numArmi, int numMedikit, int numMaghi, int numGuerrieri)
    {
        this.txtNumArmi.setText(Integer.toString(numArmi));
        this.txtNumMedikit.setText(Integer.toString(numMedikit));
        this.txtNumMaghi.setText(Integer.toString(numMaghi));
        this.txtNumGuerrieri.setText(Integer.toString(numGuerrieri));
    }
}
