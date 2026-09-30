package it.unicam.cs.mpgc.rpg120666.Controller;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti.EnumOggNem;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerSpostamentoEroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.View.*;

public class MatriceController
{
    /*La view principale:*/
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
    //Il controller e la vista della TableView Zaino:
    private ZainoController controllerZaino;
    private ZainoView zainoView;

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
        this.controllerZaino=new ZainoController(this.ilNostroEroe,this.zainoView);

        inizioGiocoView.getBtnCancellaCampi().setOnAction(e -> pulisciTxtDellaView());
        inizioGiocoView.getBtnIndietro().setOnAction(e -> tornaAllaSchermataPrecedente());
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
        System.out.println("Hai cliccato la cella: " + riga + "," +colonna);
        ElementiPerMatrice ogg = this.matriceModel.getOggettoDellaMatrice(new CellaMatrice(riga, colonna));
        if (ogg == null) { System.out.println("Cella vuota."); }
        else { System.out.println("Contenuto della cella: \n" + ogg.toString()); }
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
        inizioGiocoView.getStatisticheView().getTxtNumArmi().clear();
        inizioGiocoView.getStatisticheView().getTxtNumMedikit().clear();
        inizioGiocoView.getStatisticheView().getTxtNumGuerrieri().clear();
        inizioGiocoView.getStatisticheView().getTxtNumMaghi().clear();

        spostamentoEroeView.getTxtColonnaMatrice().clear();
        spostamentoEroeView.getTxtRigaMatrice().clear();
    }
    private void tornaAllaSchermataPrecedente()
    {

    }
    private void spostaEroe(String riga,String colonna)
    {
        this.eroeView.getTxtVitaEroe().setText(String.valueOf(ilNostroEroe.getPuntiVitaPersonaggio()));
        System.out.println("VUOI SPOSTARE L'EROE ALLA\n"+
            "Riga: "+riga+"\n"+"Colonna: "+colonna+"\n");
        int row = Integer.parseInt(riga);
        int col = Integer.parseInt(colonna);
        if(this.managerSpostamentoEroe.spostaIlNostroEroe(new CellaMatrice(row,col)))
        {
            /**Passo la matrice di elementi (dati grezzi) alla view della matrice, così può aggiornare */
            this.inizioGiocoView.getMatriceView().aggiorna(this.matriceModel.getMatriceElementi());
            /**Aggiorno le statistiche (oggetti e nemici rimanenti)!*/
            this.inizioGiocoView.getStatisticheView().aggiornaStatistiche(
                    matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.ARMA),
                    matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MEDIKIT),
                    matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.MAGO),
                    matriceModel.getMatriceContaElementi().calcolaNumOggettiNemiciRimanenti(EnumOggNem.GUERRIERO));
            /*Aggiorno anche zainoView*, tramite il suo controller:*/
            this.controllerZaino.aggiornaComponentiZaino();
        }
        /*Devo metterlo fuori dall'if. Perchè se no me lo aggiorna solo quando l'eroe si riesce a muovere!*/
        this.eroeView.getTxtVitaEroe().setText(String.valueOf(ilNostroEroe.getPuntiVitaPersonaggio()));
    }

}