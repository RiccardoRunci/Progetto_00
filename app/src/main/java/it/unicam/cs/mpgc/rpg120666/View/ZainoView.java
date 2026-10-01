package it.unicam.cs.mpgc.rpg120666.View;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.TableView;
import java.util.Map;

/*https://docs.oracle.com/javafx/2/ui_controls/table-view.htm*/
public class ZainoView
{
    /*La TableView deve essere di tipo "riga" della Map, ne parlo meglio nella classe ZainoController:*/
    private final TableView<Map.Entry<Oggetto,Integer>> tabellaZaino;
    /*Anche nell'intestazione delle colonne della TableView devo dichiarare il tipo di "riga" (entry) della Map,
    * seguito da un tipo (come ad esempio String e Integer) che servono per rappresentare i valori nelle rispettive colonne:
    * String rappresenterà la chiave dell' entry di Map<Oggetto, Integer> "in forma String";
    * Integer rappresenterà il valore dell' entry di Map<Oggetto, Integer> sotto forma di numero intero.*/
    private final TableColumn<Map.Entry<Oggetto,Integer>, String> intestazione1Colonna = new TableColumn<>("Tipo:");
    private final TableColumn<Map.Entry<Oggetto,Integer>, Integer> intestazione2Colonna = new TableColumn<>("Chiave:");

    private final Label label = new Label("Zaino eroe");

    private final VBox layoutTotZainoView;
    //Metodo costruttore:
    public ZainoView()
    {
        this.tabellaZaino = new TableView<>();
        this.label.setFont(new Font("Arial", 20));

        this.tabellaZaino.setEditable(true);
        //Metto nella TableView l'intestazione delle colonne:
        this.tabellaZaino.getColumns().addAll(intestazione1Colonna, intestazione2Colonna);

        this.layoutTotZainoView = new VBox();
        this.layoutTotZainoView.setSpacing(5);
        this.layoutTotZainoView.setPadding(new Insets(10, 0, 0, 10));
        this.layoutTotZainoView.getChildren().addAll(label, tabellaZaino);

        /*Devo inserire questi 2 metodi che servono PER VEDERE i DATI dentro la tableview: */
        this.intestazione1Colonna.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getKey().getNomeOggetto()
                )
        );

        this.intestazione2Colonna.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(
                        cellData.getValue().getValue()
                ).asObject()
        );
    }

    /*Anche il metodo get deve ritornare la TableView tipata con riga della Map (vedere classe ZainoController):*/
    public TableView<Map.Entry<Oggetto,Integer>> getTabellaZaino() {return this.tabellaZaino;}
    public Node getLayoutTotale() {
        return this.layoutTotZainoView;
    }
}
