package it.unicam.cs.mpgc.rpg120666.View;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private int dimensioneMatrice; //Me la potevo risparmiare!?!?!
    /**Componenti grafici per far muovere tra le celle il nostro eroe!*/
    private HBox riga1 = new HBox(10);
    private HBox riga2 = new HBox(10);
    private HBox riga3 = new HBox(10);
    private Label lblCoordinateRiga=new Label("Vai a RIGA:   ");
    private TextField txtRigaMatrice= new TextField("");
    private Label lblCoordinateColonna=new Label("Vai a COLONNA:");
    private TextField txtColonnaMatrice= new TextField("");
    private Button btnMandaEroeAllaCasella = new Button("VAI!");
    private Label[][] matriceDiLabel;
    private GridPane ManagerGridPaneMatrice;


    /**@Autor Copilot, creo una variabile di tipo interfaccia CellaClickListener,
     * per far funzionare i click del mouse della matrice di Label,
     * senza passare il controller a questa classe view!*/
    private CellaClickListener cellaClickListener;

    /**Metodo Costruttore:*/
    public MatriceView(int dimensioneMatrice)
    {
        /*this.matriceController = matriceController; GRANDE ERROE!!!!*/
        this.ManagerLayoutTotale = new VBox(10);
        this.ManagerGridPaneMatrice = new GridPane();

        if (dimensioneMatrice > 0)
        {
            this.matriceDiLabel = new Label[dimensioneMatrice][dimensioneMatrice]; //la matrice di label
            this.dimensioneMatrice=dimensioneMatrice;
            this.costruisciMatrice();
        }

        //Componenti per far muovere il nostro eroe dentro 3 Hbox (riga1,riga2,riga3).
        this.riga1.setAlignment(Pos.CENTER_LEFT);
        this.riga1.getChildren().addAll(lblCoordinateRiga, txtRigaMatrice);
        this.riga2.setAlignment(Pos.CENTER_LEFT);
        this.riga2.getChildren().addAll(lblCoordinateColonna, txtColonnaMatrice);
        this.riga3.setAlignment(Pos.CENTER_LEFT);
        this.riga3.getChildren().add(btnMandaEroeAllaCasella);

        //Aggiungo tutti i componenti al manager totale (ossia il Vbox)!
          ManagerLayoutTotale.getChildren().addAll(
                ManagerGridPaneMatrice,
                lblNumNemici, txtNumNemici,
                lblNumOggetti, txtNumOggetti,
                btnReset,
                btnIndietro,
                  riga1,
                  riga2,
                  riga3
       );
    }
    private void costruisciMatrice()
    {
        for (int row = 0; row < this.dimensioneMatrice; row++)
        {
            for (int col  = 0; col  < this.dimensioneMatrice; col++)
            {
                final int fRiga = row;
                final int fColonna = col;

                Label cella=new Label();
                cella.setMinSize(20, 20);
                cella.setMaxSize(20, 20);
                cella.setStyle("-fx-border-color: black; -fx-alignment: center;");
                /**@documented
                 * @Author copilot: */
                cella.setOnMouseClicked(e -> {
                    if (cellaClickListener != null) {
                        cellaClickListener.onCellaClick(fRiga, fColonna);
                    }
                });

                /**@Documented L'IA mi ha detto che il metodo .add di GridPane prende prima l'elemento da mettere,
                 * poi (differentemente da come siamo abituati a scorrere le matrici) la colonna e dopo la riga!*/
                this.matriceDiLabel[row][col] = cella; //Questa è una Matrice "normale" [riga][colonna]
                this.ManagerGridPaneMatrice.add(cella, col, row); //Questo è il metodo add di GridPane!
            }
        }
    }
    public void aggiorna(ElementiPerMatrice[][] matriceElementi)
    {
       /**Questa è una matrice "classica" ma di Label.
        * Matrice[riga][colonna]*/
        int dim = this.matriceDiLabel.length;
        ElementiPerMatrice ogg;
        for (int rig = 0; rig < dim; rig++)
            for (int col = 0; col < dim; col++)
            {
                ogg = matriceElementi[rig][col];

                if (ogg == null)
                {
                    matriceDiLabel[rig][col].setText("Vuota");
                    matriceDiLabel[rig][col].setStyle("-fx-background-color: lightgray; -fx-border-color: black;");
                }
                else
                {
                    matriceDiLabel[rig][col].setText(ogg.toString());
                    if (ogg instanceof Guerriero) { matriceDiLabel[rig][col].setStyle("-fx-background-color: red; -fx-border-color: black;"); }
                    else if (ogg instanceof Mago) { matriceDiLabel[rig][col].setStyle("-fx-background-color: blue; -fx-border-color: black;"); }
                    else if(ogg instanceof Arma) { matriceDiLabel[rig][col].setStyle("-fx-background-color: yellow; -fx-border-color: red;"); }
                    else if (ogg instanceof Medikit) { matriceDiLabel[rig][col].setStyle("-fx-background-color: green; -fx-border-color: black;");}
                    else if (ogg instanceof Eroe) { matriceDiLabel[rig][col].setStyle("-fx-background-color: purple; -fx-border-color: black;"); }
                }
            }
        this.scorriMatriceContaNemiciOggetti(matriceElementi);
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
    public void setTxtRigaMatrice(TextField txtRigaMatrice) { this.txtRigaMatrice=txtRigaMatrice; }
    public void setTxtColonnaMatrice(TextField txtColonnaMatrice) {this.txtColonnaMatrice=txtColonnaMatrice; }
    public TextField getTxtRigaMatrice(){ return this.txtRigaMatrice;}
    public TextField getTxtColonnaMatrice(){ return this.txtColonnaMatrice; }
    public Button getBtnMandaEroeAllaCasella(){ return this.btnMandaEroeAllaCasella; }
    /**@autor copilot, metodo set dell'attributo cellaClickListener.*/
    public void setCellaClickListener(CellaClickListener listener)
    {
        this.cellaClickListener = listener;
    }
    public void setMatriceDiLabel(Label[][] matriceDiLabel)
    {
        if (matriceDiLabel!=null)
            this.matriceDiLabel=matriceDiLabel;
    }

//    public void setMatriceController(MatriceController matriceController)
//    { LA VIEW NON DEVE CONOSCERE NULLA (ne il model ne il controller)!!!!
//        this.matriceController=matriceController;
//    } QUESTO ERA UN ALTRO ERRORE GRAVE!!!!!!

    /**Questo metodo serve alle classi che devono visualizzare questa View a ottenere il suo layout manager.*/
    public Parent getLayoutManagerTotaleView()
    {
        return this.ManagerLayoutTotale;
    }
    private void scorriMatriceContaNemiciOggetti(ElementiPerMatrice[][] matriceElementi)
    {
        ElementiPerMatrice ogg;
        int contatoreNemici=0,contatoreOggetti=0;
        for(int righe=0; righe<this.matriceDiLabel.length; righe++)
        {
            for (int colonne = 0; colonne < this.matriceDiLabel.length; colonne++)
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
//    public void rimuoviElemento(int riga,int colonna)
//    {
//        //this.matriceDiLabel[riga][colonna]=null;
//        this.ManagerGridPaneMatrice.add(null, colonna, riga);
//    }
}
