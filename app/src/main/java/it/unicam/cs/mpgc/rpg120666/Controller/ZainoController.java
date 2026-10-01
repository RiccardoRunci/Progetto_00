package it.unicam.cs.mpgc.rpg120666.Controller;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Oggetto.Oggetto;
import it.unicam.cs.mpgc.rpg120666.View.EroeView;
import it.unicam.cs.mpgc.rpg120666.View.ZainoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.Map;

public class ZainoController
{
    private Eroe eroeProprietarioZaino;
    private ZainoView zainoView;
    /*Importo anche l'EroeView per poter modificare (nel metodo aggiornaComponentiZainoeEroe)
     *i suoi TextBox: txtVitaEroe e PotereAttaccoEroe.*/
    private EroeView eroeView;

    /*Attributo che contiene la tableview della classe ZainoView, serve per rendere il codice più leggibile,
    * altrimenti avrei dovuto utilizzare (al posto di this.tabellaZaino) "this.zainoView.getTabellaZaino()". */
    private final TableView<Map.Entry<Oggetto,Integer>> tabellaZaino;

    public ZainoController(Eroe eroeProprietarioZaino, ZainoView zainoView, EroeView eroeView)
    {
        this.eroeProprietarioZaino = eroeProprietarioZaino;
        this.zainoView=zainoView;
        this.eroeView=eroeView;
        /*Mi prendo in una variabile tabellaZaino, la TableView della classe ZainoView, per
        * collegarci (nel metodo collegaClickAllaTabella()) i click del mouse. Faccio così solo per
        * avere una lettura del codice più fluida.*/
        this.tabellaZaino =  this.zainoView.getTabellaZaino();

        this.aggiornaComponentiZainoeEroe();
        /*Richiamo nel costruttore il metodo collegaClickAllaTabella(), così da eseguirlo alla creazione
        * dell'oggetto ZainoController.*/
        this.collegaClickAllaTabella();
    }
    public void aggiornaComponentiZainoeEroe()
    {
        /*All'inizio del metodo cancello il contenuto vecchio della tableview (se presente):*/
        this.tabellaZaino.getItems().clear();
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
        /*Qui ho bisogno anche di modificare i vari TextBox che contengono la vita e il potere di attacco dell'eroe:
         * Purtroppo devo farlo passando a questa classe (nel costruttore) anche la classe EroeView (quella dove
         * questi oggetti javaFx sono contenuti).*/
        this.eroeView.getTxtVitaEroe().setText(String.valueOf((this.eroeProprietarioZaino.getPuntiVitaPersonaggio())));
        this.eroeView.getTxtPuntiAttaccoEroe().setText(String.valueOf(this.eroeProprietarioZaino.getDannoCausato()));
    }
    public void collegaClickAllaTabella()
    {
        /*@Note Per ".getSelectionModel().getSelectedItem();" mi ha aiutato copilot.
        * Per collegare il click listener alla table view di ZainoView, non serve creare un interfaccia (come fatto
        * per la tabella di MatriceView), basta solo collegare alla TableView (presa attraverso il getSelectionModel()),
        * il metodo getSelecetedItem. Il quale prende una entry della mappa di TableView.*/
        this.tabellaZaino.setOnMouseClicked(e ->
        {
            Map.Entry<Oggetto,Integer> selezionato =
                    this.tabellaZaino.getSelectionModel().getSelectedItem();

            if(selezionato != null)
            {
                System.out.println("OGGETTO CLICCATO NELLO ZAINO: ");
                System.out.println("CHIAVE: "+selezionato.getKey()+" VALORE: "+ selezionato.getValue());
                System.out.println("");
                this.eroeProprietarioZaino.usaOggetto(selezionato.getKey());

                /*Quando l'oggetto viene usato, richiamo il metodo sopra per aggiornare la tableview.*/
                this.aggiornaComponentiZainoeEroe();
            }
        });

    }

}
