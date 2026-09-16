package it.unicam.cs.mpgc.rpg120666.View;

import it.unicam.cs.mpgc.rpg120666.Controller.MatriceController;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Medikit;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MatriceView
{
    private VBox ManagerLayoutTotale;
    private Label lblNumNemici=new Label("NUMERO NEMICI:");
    private Label lblNumOggetti=new Label("NUMERO OGGETTI:");
    private TextField txtNumNemici= new TextField();
    private TextField txtNumOggetti= new TextField();
    private Button btnIndietro = new Button("<-INDIETRO");
    private Button btnReset = new Button("RESET");
    private int dimensioneMatrice;

    private MatriceController matriceController;

    private GridPane ManagerGridPaneMatrice;
    private Label[][] matriceDiLabel;


    public MatriceView(int dimensioneMatrice/*, MatriceController matriceController*/)
    {
        /*this.matriceController = matriceController;*/
        this.ManagerLayoutTotale = new VBox(10);


        this.ManagerGridPaneMatrice = new GridPane();

        if (dimensioneMatrice > 0)
        {
            this.matriceDiLabel = new Label[dimensioneMatrice][dimensioneMatrice]; //la matrice di label
            this.dimensioneMatrice=dimensioneMatrice;
            this.costruisciMatrice();
        }

        //Aggiungo gli altri componenti al manager totale!
          ManagerLayoutTotale.getChildren().addAll(
                ManagerGridPaneMatrice,
                lblNumNemici, txtNumNemici,
                lblNumOggetti, txtNumOggetti,
                btnReset,
                btnIndietro
       );
        //ManagerLayoutTotale.getChildren().add(ManagerGridPaneMatrice); //Aggiungo la matrice al manager totale!
    }
    private void costruisciMatrice()
    {
        for (int col = 0; col < this.dimensioneMatrice; col++)
        {
            for (int row  = 0; row  < this.dimensioneMatrice; row++)
            {

                Label cella = new Label();
                cella.setMinSize(20, 20);
                cella.setMaxSize(20, 20);
                cella.setStyle("-fx-border-color: black; -fx-alignment: center;");

                final int fColonna = col;
                final int fRiga = row;
                cella.setOnMouseClicked(e -> this.matriceController.cellaCliccata(fRiga, fColonna));
                /**@Documented L'IA mi ha detto che il metodo .add di GridPane prende prima l'elemento da mettere,
                 * poi (differentemente da come siamo abituati a scorrere le matrici) la colonna e dopo la riga!*/
                this.matriceDiLabel[col][row] = cella;
                this.ManagerGridPaneMatrice.add(cella, col, row);
            }
        }
    }
    public void aggiorna(MatriceGioco matriceModel)
    {
        /**todo i rinominala in COLONNA
         * j rinominala in RIGA*/
        int dim = this.matriceDiLabel.length;
        ElementiPerMatrice ogg;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++)
            {
                ogg = matriceModel.getOggettoDellaMatrice(new CellaMatrice(i, j));

                if (ogg == null)
                {
                    matriceDiLabel[i][j].setText("Vuota");
                    matriceDiLabel[i][j].setStyle("-fx-background-color: lightgray; -fx-border-color: black;");
                }
                else
                {
                    matriceDiLabel[i][j].setText(ogg.toString());
                    if (ogg instanceof Guerriero) { matriceDiLabel[i][j].setStyle("-fx-background-color: red; -fx-border-color: black;"); }
                    else if (ogg instanceof Mago) { matriceDiLabel[i][j].setStyle("-fx-background-color: blue; -fx-border-color: black;"); }
                    else if(ogg instanceof Arma) { matriceDiLabel[i][j].setStyle("-fx-background-color: yellow; -fx-border-color: red;"); }
                    else if (ogg instanceof Medikit) { matriceDiLabel[i][j].setStyle("-fx-background-color: green; -fx-border-color: black;");}
                    else if (ogg instanceof Eroe) { matriceDiLabel[i][j].setStyle("-fx-background-color: purple; -fx-border-color: black;"); }
                }
            }
    }
    /**Metodi set e get di tutti i componenti grafici!*/
    public Label getLblNumNemici() { return this.lblNumNemici; }
    public Label getLblNumOggetti() { return this.lblNumOggetti; }
    public void setLblNumNemici(Label lblNumNemici){ this.lblNumNemici=lblNumNemici; }
    public void setLblNumOggetti(Label lblNumOggetti){ this.lblNumOggetti=lblNumOggetti; }
    public TextField getTxtNumNemici() {return this.txtNumNemici;}
    public TextField getTxtNumOggetti(){return this.txtNumOggetti;}
    public void setTxtNumNemici(TextField txtNumNemici){ this.txtNumNemici=txtNumNemici; }
    public void setTxtNumOggetti(TextField txtNumOggetti){ this.txtNumOggetti=txtNumOggetti; }
    public Button getBtnIndietro(){ return this.btnIndietro; }
    public void setBtnIndietro(Button btnIndietro) { this.btnIndietro=btnIndietro; }
    public Button getBtnReset(){ return this.btnReset; }
    public void setBtnReset(Button btnReset) { this.btnReset=btnReset; }
    public int getDimensioneMatrice() { return this.dimensioneMatrice; }
    public Label[][] getMatriceDiLabel() { return this.matriceDiLabel; }
    public void setMatriceDiLabel(Label[][] matriceDiLabel)
    {
        if (matriceDiLabel!=null)
            this.matriceDiLabel=matriceDiLabel;
    }
    public void setMatriceController(MatriceController matriceController)
    {
        this.matriceController=matriceController;
    }


    /**Questo metodo serve alle classi che
     **devono visualizzare questa View**
     * a ottenere il suo layout manager.*/
    public Parent getLayoutManagerTotaleView()
    {
        return this.ManagerLayoutTotale;
    }

}
