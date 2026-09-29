package it.unicam.cs.mpgc.rpg120666.View;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.TableView;
/*https://docs.oracle.com/javafx/2/ui_controls/table-view.htm*/
public class ZainoView
{
    private final TableView tabellaZaino;
    private final TableColumn intestazione1Colonna = new TableColumn("Tipo:");
    private final TableColumn intestazione2Colonna = new TableColumn("Chiave:");
    private final  TableColumn intestazione3Colonna = new TableColumn("Quantità:");
    private final Label label = new Label("Zaino eroe");
    private final VBox layoutTotZainoView;
    //Metodo costruttore:
    public ZainoView()
    {
        this.tabellaZaino = new TableView();
        this.label.setFont(new Font("Arial", 20));

        this.tabellaZaino.setEditable(true);
        this.tabellaZaino.getColumns().addAll(intestazione1Colonna, intestazione2Colonna,
                intestazione3Colonna);

        this.layoutTotZainoView = new VBox();
        this.layoutTotZainoView.setSpacing(5);
        this.layoutTotZainoView.setPadding(new Insets(10, 0, 0, 10));
        this.layoutTotZainoView.getChildren().addAll(label, tabellaZaino);
    }
    public Node getLayoutTotale() {
        return this.layoutTotZainoView;
    }
}
