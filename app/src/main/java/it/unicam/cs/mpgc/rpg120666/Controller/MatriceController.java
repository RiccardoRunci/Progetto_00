package it.unicam.cs.mpgc.rpg120666.Controller;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ManagerSpostamentoEroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.View.MatriceView;

public class MatriceController
{
    private MatriceView matriceView;
    private MatriceGioco matriceModel;
    private ManagerSpostamentoEroe managerSpostamentoEroe;
    private Eroe ilNostroEroe;
    public MatriceController(MatriceView matriceView, MatriceGioco matriceModel)
    {
        this.matriceView = matriceView;
        this.matriceModel = matriceModel;
        this.ilNostroEroe = this.matriceModel.getIlNostroEroe();
        this.managerSpostamentoEroe=new ManagerSpostamentoEroe(this.matriceModel,this.ilNostroEroe);

        matriceView.getBtnReset().setOnAction(e -> pulisciTxtDellaView());
        matriceView.getBtnIndietro().setOnAction(e -> tornaAllaSchermataPrecedente());
        matriceView.getTxtNumOggetti().setText(""+matriceModel.getNumeroOggetti());
        matriceView.getTxtNumNemici().setText(""+matriceModel.getNumeroNemici());
        matriceView.getBtnMandaEroeAllaCasella().setOnAction(e ->spostaEroe(matriceView.getTxtRigaMatrice().getText(), matriceView.getTxtColonnaMatrice().getText()));
        // @Author copilot REGISTRAZIONE EVENTO (per far funzionare il click alla label del view e non importare il controller nella view).
        matriceView.setCellaClickListener((riga, colonna) -> {
            cellaCliccata(riga, colonna);
        });
    }

    private void pulisciTxtDellaView()
    {
        matriceView.getTxtNumNemici().clear();
        matriceView.getTxtNumOggetti().clear();
        matriceView.getTxtColonnaMatrice().clear();
        matriceView.getTxtRigaMatrice().clear();
    }
    private void tornaAllaSchermataPrecedente()
    {

    }
    private void spostaEroe(String riga,String colonna)
    {
        System.out.println("VUOI SPOSTARE L'EROE ALLA\n"+
            "Riga: "+riga+"\n"+"Colonna: "+colonna+"\n");
        int row = Integer.parseInt(riga);
        int col = Integer.parseInt(colonna);
        if(this.managerSpostamentoEroe.spostaIlNostroEroe(new CellaMatrice(row,col)))
            //this.matriceView.rimuoviElemento(row,col);

        this.matriceView.aggiorna(this.matriceModel.getMatriceElementi());
    }
    public void cellaCliccata(int riga,int colonna)
    {
        System.out.println("Hai cliccato la cella: " + riga + "," +colonna);
        ElementiPerMatrice ogg = this.matriceModel.getOggettoDellaMatrice(new CellaMatrice(riga, colonna));
        if (ogg == null) { System.out.println("Cella vuota."); }
        else { System.out.println("Contenuto della cella: \n" + ogg.toString()); }
        this.matriceView.aggiorna(this.matriceModel.getMatriceElementi());
    }
}
