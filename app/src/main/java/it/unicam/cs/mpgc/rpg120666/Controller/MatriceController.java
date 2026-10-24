package it.unicam.cs.mpgc.rpg120666.Controller;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti.EnumOggNem;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerFineGioco;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerSpostamentoEroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate.Punteggi;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate.UsaPunteggi;
import it.unicam.cs.mpgc.rpg120666.View.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class MatriceController
{
    /*La view principale, dai cui posso recuperare tutte le altre view (o gli oggetti delle varie classi view che
     * si poggiano sopra a essa, le altre classi view sono posizionate nel layout di InizioGiocoView). Vedere nel
     * costruttore: */
    private InizioGiocoView inizioGiocoView;
    /*IL MODEL:*/
    private MatriceGioco matriceModel;
    /*Classe per spostare l'eroe*/
    private ManagerSpostamentoEroe managerSpostamentoEroe;
    /*La view dello spostamento dell'eroe:*/
    private SpostamentoEroeView  spostamentoEroeView;
    /*La view dell'eroe:*/
    private EroeView eroeView;
    /*La view della matrice di gioco:*/
    private MatriceView matriceView;
    /*La classe dell'Eroe:*/
    private Eroe ilNostroEroe;
    //Il controller e la vista della "TableView" Zaino:
    private ZainoController controllerZaino;
    private ZainoView zainoView;
    //la classe per vedere se hai finito la partita o no!
    private ManagerFineGioco managerFineGioco;

    public MatriceController(InizioGiocoView inizioGiocoView, MatriceGioco matriceModel)
    {
         /*ERRORE! DEVO PRENDERE gli oggetti spostamentoEroeView e eroeView
         * che sono contenuti all'interno di matriceView!
         * NON DEVO FARNE 2 NUOVI!
         * NO ---> this.spostamentoEroeView = new SpostamentoEroeView();
         * NO ---> this.eroeView = new EroeView(); */
        this.spostamentoEroeView = inizioGiocoView.getSpostamentoEroeView(); //SI
        this.eroeView = inizioGiocoView.getEroeView(); //SI
        this.matriceView =inizioGiocoView.getMatriceView();


        this.inizioGiocoView = inizioGiocoView;
        this.matriceModel = matriceModel;
        this.ilNostroEroe = this.matriceModel.getIlNostroEroe();
        this.managerSpostamentoEroe=new ManagerSpostamentoEroe(this.matriceModel,this.ilNostroEroe);

        //Da una view principale (InizioGiocoView), mi prendo la view dello zaino:
        this.zainoView = this.inizioGiocoView.getZainoView(); // la view dello zaino
        //Inizializzo il controller dello zaino:
        this.controllerZaino=new ZainoController(this.ilNostroEroe,this.zainoView,this.eroeView);
        //inizializzo la classe per vedere se il gioco è finito o no!
        this.managerFineGioco=new ManagerFineGioco(this.matriceModel.getMatriceElementi(),this.ilNostroEroe);


        inizioGiocoView.getBtnCancellaCampi().setOnAction(e -> pulisciTxtDellaView());
        inizioGiocoView.getBtnPunteggi().setOnAction(e -> vediPunteggi());
        inizioGiocoView.getBtnCancellaTuttiPunteggi().setOnAction(e -> cancellaPunteggi());
        inizioGiocoView.getStatisticheView().getTxtNumArmi().setText(""+matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.ARMA));
        inizioGiocoView.getStatisticheView().getTxtNumMedikit().setText(""+matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MEDIKIT));
        inizioGiocoView.getStatisticheView().getTxtNumMaghi().setText(""+matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MAGO));
        inizioGiocoView.getStatisticheView().getTxtNumGuerrieri().setText(""+matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.GUERRIERO));
        spostamentoEroeView.getBtnMandaEroeAllaCasella().setOnAction(
           e ->spostaEroe(spostamentoEroeView.getTxtRigaMatrice().getText(), spostamentoEroeView.getTxtColonnaMatrice().getText())
        );

        // @Author copilot REGISTRAZIONE EVENTO (per far funzionare il click alla label del view e non importare il controller nella view).
        inizioGiocoView.getMatriceView().setCellaClickListener((riga, colonna) -> {
            cellaCliccata(riga, colonna);
        });
    }
    public void cellaCliccata(int riga,int colonna)
    {
        //System.out.println("\nHAI CLICCATO LA CELLA: " + riga + "," +colonna);
        ElementiPerMatrice ogg = this.matriceModel.getOggettoDellaMatrice(new CellaMatrice(riga, colonna));

        if (ogg == null)
        {
            Alert messaggio = new Alert(Alert.AlertType.INFORMATION,"HAI CLICCATO LA CELLA: " + riga + "," +colonna+"\n"+
                    "CELLA VUOTA!\n"); //crea un nuovo oggetto Alert (sarebbero i nuovi JOptionPane di swing).
            messaggio.setTitle("Dettagli Cella"); //Il titolo nella barra del titolo.
            messaggio.setHeaderText(null); //rimuove la scritta "Messaggio" sotto al titolo.
            messaggio.showAndWait(); //la fà comparire.
            //System.out.println("CELLA VUOTA.");
        }
        else
        {
            Alert messaggio = new Alert(Alert.AlertType.INFORMATION,"HAI CLICCATO LA CELLA: " + riga + "," +colonna+"\n"+
                    "CONTIENE: \n"+ ogg.toString());
            messaggio.setTitle("Dettagli Cella");
            messaggio.setHeaderText(null);
            messaggio.showAndWait();
           //System.out.println("CONTENUTO CELLA: \n" + ogg.toString());
        }

       /**Passo la matrice di elementi (dati grezzi) alla view della matrice, così può aggiornare */
        this.inizioGiocoView.getMatriceView().aggiorna(this.matriceModel.getMatriceElementi());
        /**Aggiorno le statistiche (oggetti e nemici rimanenti)!*/
        this.inizioGiocoView.getStatisticheView().aggiornaStatistiche(
                matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.ARMA),
                matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MEDIKIT),
                matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MAGO),
                matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.GUERRIERO));
    }

    private void pulisciTxtDellaView()
    {
        this.inizioGiocoView.getStatisticheView().getTxtNumArmi().clear();
        this.inizioGiocoView.getStatisticheView().getTxtNumMedikit().clear();
        this.inizioGiocoView.getStatisticheView().getTxtNumGuerrieri().clear();
        this.inizioGiocoView.getStatisticheView().getTxtNumMaghi().clear();

        this.spostamentoEroeView.getTxtColonnaMatrice().clear();
        this.spostamentoEroeView.getTxtRigaMatrice().clear();

        this.eroeView.getTxtVitaEroe().clear();
        this.eroeView.getTxtPuntiAttaccoEroe().clear();
    }
    /*Più che statistiche avrei dovuto chiamarli punteggi!*/
    private void vediPunteggi()
    {
        ArrayList<Punteggi> arrayStatistiche = new ArrayList<Punteggi>(UsaPunteggi.ritornaTutteRighe());
        StringBuilder infoDaVisualizzare=new StringBuilder();
        for(int i=0;i<arrayStatistiche.size();i++)
        {
            infoDaVisualizzare.append("Id Partita: "+arrayStatistiche.get(i).getId());
            infoDaVisualizzare.append("\n");
            infoDaVisualizzare.append("Nome Giocatore: "+arrayStatistiche.get(i).getNomeGiocatore());
            infoDaVisualizzare.append("\n");
            infoDaVisualizzare.append("Nemici Rimasti: "+arrayStatistiche.get(i).getNemiciRimasti());
            infoDaVisualizzare.append("\n");
            infoDaVisualizzare.append("Oggetti Rimasti: "+arrayStatistiche.get(i).getOggettiRimasti());
            infoDaVisualizzare.append("\n");
            infoDaVisualizzare.append("Data Gioco: "+arrayStatistiche.get(i).getDataGioco());
            infoDaVisualizzare.append("\n--------------------------------------------------\n");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Punteggi Partite");
        alert.setHeaderText(null);
        Label messaggio = new Label( infoDaVisualizzare.toString());
        messaggio.setStyle("-fx-text-fill: green; -fx-font-size: 20px; -fx-font-weight: bold;");
        alert.getDialogPane().setContent(messaggio);
        alert.showAndWait();
        /*UsaStatistiche.chiudiEMF(); devo ricordarmi di farlo all'uscita dell'applicazione!*/
    }
    private void cancellaPunteggi()
    {
        UsaPunteggi.cancellaTutteRighe();
    }
    private void spostaEroe(String riga,String colonna) {
        /*Aggiorno le componenti JavaFx della view Eroe:*/
        this.eroeView.getTxtVitaEroe().setText(String.valueOf(ilNostroEroe.getPuntiVitaPersonaggio()));
        this.eroeView.getTxtPuntiAttaccoEroe().setText(String.valueOf(ilNostroEroe.getDannoCausato()));

        System.out.println("VUOI SPOSTARE L'EROE ALLA\n" +
                "Riga: " + riga + "\n" + "Colonna: " + colonna + "\n");
        int row = Integer.parseInt(riga);
        int col = Integer.parseInt(colonna);
        if (this.managerSpostamentoEroe.spostaIlNostroEroe(new CellaMatrice(row, col))) {
            /**Passo la matrice di elementi (dati grezzi) alla view della matrice, così può aggiornare */
            this.inizioGiocoView.getMatriceView().aggiorna(this.matriceModel.getMatriceElementi());
            /**Aggiorno le statistiche (oggetti e nemici rimanenti)!*/
            this.inizioGiocoView.getStatisticheView().aggiornaStatistiche(
                    matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.ARMA),
                    matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MEDIKIT),
                    matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MAGO),
                    matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.GUERRIERO));
            /*Aggiorno anche zainoView*, tramite il suo controller:*/
            this.controllerZaino.aggiornaComponentiZainoeEroe();
        }
        /*Devo metterlo fuori dall'if. Perchè se no me lo aggiorna solo quando l'eroe si riesce a muovere!*/
        this.eroeView.getTxtVitaEroe().setText(String.valueOf(ilNostroEroe.getPuntiVitaPersonaggio()));
        this.eroeView.getTxtPuntiAttaccoEroe().setText(String.valueOf(ilNostroEroe.getDannoCausato()));
        if (this.managerFineGioco.giocoFinito())
        {
            this.eroeView.getTxtVitaEroe().setText("GIOCO FINITO!");
            this.eroeView.getTxtPuntiAttaccoEroe().setText("GIOCO FINITO!");
        }
    }

}