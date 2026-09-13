package it.unicam.cs.mpgc.rpg120666.View;

import it.unicam.cs.mpgc.rpg120666.Controller.MatriceGiocoController;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Medikit;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MatriceGiocoView
{
    private GridPane gridPane;
    private final Label[][] matriceDiLabel;
    private MatriceGiocoController controller;

    private VBox ManagerLayoutVBox;
    private Label lblNumNemici = new Label("NUMERO NEMICI: ");
    private Label lblNumOggetti = new Label("NUMERO OGGETTI: ");;
    private TextField txtNumOggetti= new TextField();
    private TextField txtNumNemici= new TextField();

    /*Metodo get del contenitore GridPane!*/
    public GridPane getGridPane() { return gridPane; }
    /**Costruttore della classe View.*/
    public MatriceGiocoView(int dimensioneMatrice)
    {
        this.gridPane = new GridPane(); //è il contenitore per la matrice di label
        this.matriceDiLabel = new Label[dimensioneMatrice][dimensioneMatrice]; //la matrice di label
        if (dimensioneMatrice > 0)
            this.inizializzazioneGraficaMatrice(dimensioneMatrice);
    }

    public void setController(MatriceGiocoController controller) {
        this.controller = controller;
    }

    /**Metodo che serve per 'inizializzare' la matrice. Questo metodo colora solo tutte le label della matrice
     * in grigio (tipo colore che rappresenta l'inattività). Per farlo crea delle nuove label (tante quante le celle
     * della matrice), le 'dimensiona', le colora in grigio.
     * POI mette la nuova label appena creata DENTO LA MATRICE!
     * E DICE AL GRIDPANE (il contenitore che rappresenta visivamente una specie di matrice) "METTI QUESTA cella/label
     * nelle coordinate x e y" come se fosse una cella della matrice! */
    private void inizializzazioneGraficaMatrice(int dim)
    {
        for (int x = 0; x < dim; x++)
        {
            for (int y = 0; y < dim; y++)
            {
                Label cella = new Label();
                cella.setMinSize(30, 30);      // dimensione fissa
                cella.setMaxSize(30, 30);
                cella.setStyle("-fx-border-color: gray; -fx-alignment: center;");

                // AGGIUNTA: click sulla cella-----------------------------------------------------------------------
                final int fx = x; //variabili per l'espressione lambda, l'espressione.
                final int fy = y; //la lambda vede solo variabili finali!
                cella.setOnMouseClicked(event ->
                {
                    if (controller != null)
                    {
                        controller.cellaCliccata(fx, fy);
                        controller.apriFinestraDettagli(fx, fy);
                    }

                });
                //---------------------------------------------------------------------------------------------------

                this.matriceDiLabel[x][y] = cella;
                this.gridPane.add(cella, x, y);
            }
        }
    }

    public void aggiorna(MatriceGioco matriceGiocoPassata)
    {
        int dim = this.matriceDiLabel.length;
        ElementiPerMatrice ogg;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++)
            {
                ogg = matriceGiocoPassata.getOggettoDellaMatrice(new CellaMatrice(i, j));

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
                    else if (ogg instanceof Medikit) { matriceDiLabel[i][j].setStyle("-fx-background-color: green; -fx-border-color: black;"); }
                }
            }
    }

    public void evidenziaCella(int x, int y)
    {
       // evidenzia la cella selezionata
        matriceDiLabel[x][y].setStyle("-fx-border-color: yellow; -fx-background-color: orange;");
    }
}