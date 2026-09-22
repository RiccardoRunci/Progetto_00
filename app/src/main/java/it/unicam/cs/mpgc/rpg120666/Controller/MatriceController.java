package it.unicam.cs.mpgc.rpg120666.Controller;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager.ManagerSpostamentoEroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.View.EroeView;
import it.unicam.cs.mpgc.rpg120666.View.InizioGiocoView;
import it.unicam.cs.mpgc.rpg120666.View.SpostamentoEroeView;

public class MatriceController
{
    private InizioGiocoView inizioGiocoView;
    private MatriceGioco matriceModel;
    private ManagerSpostamentoEroe managerSpostamentoEroe;

    private SpostamentoEroeView  spostamentoEroeView;
    private EroeView eroeView;

    private Eroe ilNostroEroe;
    public MatriceController(InizioGiocoView inizioGiocoView, MatriceGioco matriceModel)
    {
         /*ERRORE! DEVO PRENDERE gli oggetti spostamentoEroeView e eroeView
         * che sono contenuti all'interno di matriceView!
         * NON DEVO FARNE 2 NUOVI!
         * NO ---> this.spostamentoEroeView = new SpostamentoEroeView();
         * NO ---> this.eroeView = new EroeView(); */
        this.spostamentoEroeView = inizioGiocoView.getSpostamentoEroeView(); //SI
        this.eroeView = inizioGiocoView.getEroeView(); //SI

        this.inizioGiocoView = inizioGiocoView;
        this.matriceModel = matriceModel;
        this.ilNostroEroe = this.matriceModel.getIlNostroEroe();
        this.managerSpostamentoEroe=new ManagerSpostamentoEroe(this.matriceModel,this.ilNostroEroe);

        inizioGiocoView.getBtnCancellaCampi().setOnAction(e -> pulisciTxtDellaView());
        inizioGiocoView.getBtnIndietro().setOnAction(e -> tornaAllaSchermataPrecedente());
        inizioGiocoView.getTxtNumOggetti().setText(""+matriceModel.getNumeroOggetti());
        inizioGiocoView.getTxtNumNemici().setText(""+matriceModel.getNumeroNemici());
        spostamentoEroeView.getBtnMandaEroeAllaCasella().setOnAction(
           e ->spostaEroe(spostamentoEroeView.getTxtRigaMatrice().getText(), spostamentoEroeView.getTxtColonnaMatrice().getText())
        );

        // @Author copilot REGISTRAZIONE EVENTO (per far funzionare il click alla label del view e non importare il controller nella view).
        inizioGiocoView.setCellaClickListener((riga, colonna) -> {
            cellaCliccata(riga, colonna);
        });
    }

    private void pulisciTxtDellaView()
    {
        inizioGiocoView.getTxtNumNemici().clear();
        inizioGiocoView.getTxtNumOggetti().clear();
        spostamentoEroeView.getTxtColonnaMatrice().clear();
        spostamentoEroeView.getTxtRigaMatrice().clear();
    }
    private void tornaAllaSchermataPrecedente()
    {

    }
    private void spostaEroe(String riga,String colonna)
    {
        this.eroeView.getTxtVitaEroe().setText(String.valueOf(ilNostroEroe.getPuntiVitaEroe()));
        System.out.println("VUOI SPOSTARE L'EROE ALLA\n"+
            "Riga: "+riga+"\n"+"Colonna: "+colonna+"\n");
        int row = Integer.parseInt(riga);
        int col = Integer.parseInt(colonna);
        if(this.managerSpostamentoEroe.spostaIlNostroEroe(new CellaMatrice(row,col)))
        {
            //this.matriceView.rimuoviElemento(row,col);
            this.inizioGiocoView.aggiorna(this.matriceModel.getMatriceElementi());
        }
        /*Devo metterlo fuori dall'if. Perchè se no me lo aggiorna solo quando l'eroe si riesce a muovere!*/
        this.eroeView.getTxtVitaEroe().setText(String.valueOf(ilNostroEroe.getPuntiVitaEroe()));
    }
    public void cellaCliccata(int riga,int colonna)
    {
        System.out.println("Hai cliccato la cella: " + riga + "," +colonna);
        ElementiPerMatrice ogg = this.matriceModel.getOggettoDellaMatrice(new CellaMatrice(riga, colonna));
        if (ogg == null) { System.out.println("Cella vuota."); }
        else { System.out.println("Contenuto della cella: \n" + ogg.toString()); }
        this.inizioGiocoView.aggiorna(this.matriceModel.getMatriceElementi());
    }
}