package it.unicam.cs.mpgc.rpg120666.Controller;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.View.ZainoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class ZainoController
{
    private Eroe eroeProprietarioZaino;
    private ZainoView zainoView;

    public ZainoController(Eroe eroeProprietarioZaino, ZainoView zainoView)
    {
        this.eroeProprietarioZaino = eroeProprietarioZaino;
        this.zainoView=zainoView;
        this.aggiornaComponentiZaino();
    }
    public void aggiornaComponentiZaino()
    {
        /*All'inizio del metodo cancello il contenuto vecchio della tableview (se presente):*/
        this.zainoView.getTabellaZaino().getItems().clear();
        /*Un oggetto javaFx TableView non sà leggere direttamente dalla nostra Map<Oggetto, Integer>.
         * Devo trasformare in qualche modo ogni "riga" di quella mappa in un altro oggetto.*/
        /*.getZaino().getOggettiPosseduti() mi ritorna l'intera interfaccia Map<Oggetto, Integer> che rappresenta
        * il contenuto di tutto lo zaino. Ho creato una nuova variabile contenutoZaino solo per favorire
        * la leggibilità del codice (a discapito dell'utilizzo della memoria).*/
        Map<Oggetto,Integer> contenutoZaino = this.eroeProprietarioZaino.getZaino().getOggettiPosseduti();

        /*ObservableList< vuole un tipo, non una variabile>.
        * (SCORRETTO) Passando Map<Oggetto, Integer>, stò passando una mappa, significa che per ogni riga dell'TableView vi è una
        *   Map piena, quindi n righe di TableView = n Map<> COMPLETE!
        * (CORRETTO) Devo passare una riga (una Entry) di Map<Oggetto, Integer>.*/
        ObservableList< Map.Entry<Oggetto,Integer> > dati = FXCollections.observableArrayList();

        /*Scorro la mappa (salvata sopra, che sarebbe una copia di quella ritornata dalla classe Zaino) con un for each.
        * Poi tramite .add aggiungo gli oggetti (della Map copia di Zaino) dentro dati (la ObservableList).*/
        for (Map.Entry<Oggetto, Integer> entry : contenutoZaino.entrySet())
        {
            dati.add(entry); //Metto tutta la "riga" di Map (copia fornita da Zaino) dentro dati (l' ObservableList).
        }                    //questo copia sia chiave che valore della Map.
        /*Prima di inserire dentro l'oggetto javafx TableView, devo controllare se quella TableView (che ho in
        * ZainoView è di tipo ---> TableView<Map.Entry<Oggetto,Integer>> */
        this.zainoView.getTabellaZaino().setItems(dati); //tableView.setItems(dati);
    }

}
