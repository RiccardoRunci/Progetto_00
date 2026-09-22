package it.unicam.cs.mpgc.rpg120666.View;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Medikit;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class InizioGiocoView
{
    private VBox ManagerLayoutTotale;
    private Label lblNumNemici=new Label("NUMERO NEMICI:");
    private Label lblNumOggetti=new Label("NUMERO OGGETTI:");
    private TextField txtNumNemici= new TextField();
    private TextField txtNumOggetti= new TextField();
    private Button btnIndietro = new Button("<-INDIETRO");
    private Button btnCancellaCampi = new Button("CANCELLA CAMPI");
    private int dimensioneMatrice; //Me la potevo risparmiare!?!?!

    private Label[][] matriceDiLabel;
    private GridPane ManagerGridPaneMatrice;

    /**Per 'snellire' il codice di questa classe, ho creato altre classi view che si 'appoggiano'  su di qusta.
     * In modo tale di avere (ad esempio):
     * la classe vistaEroe con solo i componenti grafici che servono a visualizzare la vita dell'Eroe.
     * Oppure, ho creato, vistaSpostamentoEroe la quale ha solo i componenti (bottoni, label, ecc...).
     * I quali servono per Muovere il nostro eroe all'interno della matrice!*/
    private EroeView vistaEroe;
    private SpostamentoEroeView vistaSpostamentoEroe;


    /**@Autor Copilot, creo una variabile di tipo interfaccia CellaClickListener,
     * per far funzionare i click del mouse della matrice di Label,
     * senza passare il controller a questa classe view!*/
    private CellaClickListener cellaClickListener;

    /**Metodo Costruttore:*/
    public InizioGiocoView(int dimensioneMatrice)
    {
        /**Le 2 classi 'snellenti'(vedi sopra) di gui per i vari pezzi di questa Finestra.
         * Nel controller però bisogna prenderli con i metodi set da questa classe.*/
        this.vistaEroe = new EroeView();
        this.vistaSpostamentoEroe= new SpostamentoEroeView();

        this.ManagerLayoutTotale = new VBox(10);
        this.ManagerGridPaneMatrice = new GridPane();

        if (dimensioneMatrice > 0)
        {
            this.matriceDiLabel = new Label[dimensioneMatrice][dimensioneMatrice]; //la matrice di label
            this.dimensioneMatrice=dimensioneMatrice;
            this.costruisciMatrice();
        }

          //Aggiungo tutti i componenti al manager totale (ossia il Vbox)!
          ManagerLayoutTotale.getChildren().addAll(
                ManagerGridPaneMatrice,
                lblNumNemici, txtNumNemici,
                lblNumOggetti, txtNumOggetti,
                  btnCancellaCampi,
                btnIndietro,
                  this.vistaSpostamentoEroe.getLayoutTotale(), //la classe di componenti gui per spostare l'eroe.
                  this.vistaEroe.getLayotVistaEroe() //la classe di componenti gui per vedere la vita dell'eroe.
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
                /**@documented Metodo: 'cella.setOnMouseClicked'.
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
        this.ManagerGridPaneMatrice.getChildren().clear();

        int dim = this.matriceDiLabel.length;

        for (int rig = 0; rig < dim; rig++)
            for (int col = 0; col < dim; col++)
            {
                ElementiPerMatrice ogg = matriceElementi[rig][col];

                Label lbl = matriceDiLabel[rig][col];

                if (ogg == null)
                {
                    lbl.setText("Vuota");
                    lbl.setStyle("-fx-background-color: lightgray; -fx-border-color: black;");
                }
                else
                {
                    lbl.setText(ogg.toString());

                    if (ogg instanceof Guerriero) lbl.setStyle("-fx-background-color: red; -fx-border-color: black;");
                    else if (ogg instanceof Mago) lbl.setStyle("-fx-background-color: blue; -fx-border-color: black;");
                    else if (ogg instanceof Arma) lbl.setStyle("-fx-background-color: yellow; -fx-border-color: red;");
                    else if (ogg instanceof Medikit) lbl.setStyle("-fx-background-color: green; -fx-border-color: black;");
                    else if (ogg instanceof Eroe) lbl.setStyle("-fx-background-color: purple; -fx-border-color: black;");
                }

                this.ManagerGridPaneMatrice.add(lbl, col, rig);
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
    public Button getBtnCancellaCampi(){ return this.btnCancellaCampi; }
    public void setBtnCancellaCampi(Button btnCancellaCampi) { this.btnCancellaCampi = btnCancellaCampi; }
    public int getDimensioneMatrice() { return this.dimensioneMatrice; }
    public Label[][] getMatriceDiLabel() { return this.matriceDiLabel; }
    public EroeView getEroeView() { return this.vistaEroe; }
    public SpostamentoEroeView getSpostamentoEroeView () {return this.vistaSpostamentoEroe; }

    //public void setTextVitaEroeInLayoutEroe(int txtVitaEroe){ this.vistaEroe.getTxtVitaEroe().setText(String.valueOf(txtVitaEroe)); }

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
}
