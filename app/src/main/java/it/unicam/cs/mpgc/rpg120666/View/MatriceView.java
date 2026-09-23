package it.unicam.cs.mpgc.rpg120666.View;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Arma;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.ElencoClassiEsteseOggetto.Medikit;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MatriceView
{
    private int dimensioneMatrice; //Me la potevo risparmiare!?!?!
    private Label[][] matriceDiLabel;
    private GridPane ManagerGridPaneMatrice;
    /**@Autor Copilot, creo una variabile di tipo interfaccia CellaClickListener,
     * per far funzionare i click del mouse della matrice di Label,
     * senza passare il controller a questa classe view!*/
    private CellaClickListener cellaClickListener;

    public MatriceView(int dimensioneMatrice)
    {
        this.ManagerGridPaneMatrice = new GridPane();

        if (dimensioneMatrice > 0)
        {
            this.matriceDiLabel = new Label[dimensioneMatrice][dimensioneMatrice]; //la matrice di label
            this.dimensioneMatrice=dimensioneMatrice;
            this.costruisciMatrice();
        }
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
    }
    /**@autor copilot, metodo set dell'attributo cellaClickListener.*/
    public void setCellaClickListener(CellaClickListener listener)
    {
        this.cellaClickListener = listener;
    }
    public int getDimensioneMatrice() { return this.dimensioneMatrice; }
    public Label[][] getMatriceDiLabel() { return this.matriceDiLabel; }
    public void setMatriceDiLabel(Label[][] matriceDiLabel)
    {
        if (matriceDiLabel!=null)
            this.matriceDiLabel=matriceDiLabel;
    }
    public Node getLayoutTotale() {
        return this.ManagerGridPaneMatrice;
    }
}
