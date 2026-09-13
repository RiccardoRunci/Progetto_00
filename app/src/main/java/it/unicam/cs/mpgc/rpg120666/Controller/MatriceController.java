package it.unicam.cs.mpgc.rpg120666.Controller;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.CellaMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.View.MatriceView;

public class MatriceController
{
    private MatriceView matriceView;
    private MatriceGioco matriceModel;
    public MatriceController(MatriceView matriceView, MatriceGioco matriceModel)
    {
        this.matriceView = matriceView;
        this.matriceModel = matriceModel;

        matriceView.getBtnReset().setOnAction(e -> pulisciTxtDellaView());
        matriceView.getBtnIndietro().setOnAction(e -> tornaAllaSchermataPrecedente());
        matriceView.getTxtNumOggetti().setText(""+matriceModel.getNumeroOggetti());
        matriceView.getTxtNumNemici().setText(""+matriceModel.getNumeroNemici());
    }

    private void pulisciTxtDellaView()
    {
        matriceView.getTxtNumNemici().clear();
        matriceView.getTxtNumOggetti().clear();
    }
    private void tornaAllaSchermataPrecedente()
    {

    }
    public void cellaCliccata(int x, int y)
    {
        System.out.println("Hai cliccato la cella: " + x + "," + y);
        ElementiPerMatrice ogg = this.matriceModel.getOggettoDellaMatrice(new CellaMatrice(x, y));
        if (ogg == null) { System.out.println("Cella vuota."); }
        else { System.out.println("Contenuto della cella: \n" + ogg.toString()); }
        this.matriceView.aggiorna(matriceModel);
    }
}
