package it.unicam.cs.mpgc.rpg120666.View;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class InizioGiocoView
{
    private VBox ManagerLayoutTotale;
    private Button btnIndietro = new Button("<-INDIETRO");
    private Button btnCancellaCampi = new Button("CANCELLA CAMPI");

    /**Per 'snellire' il codice di questa classe, ho creato altre classi view che si 'appoggiano'  su di qusta.
     * In modo tale di avere (ad esempio):
     * la classe vistaEroe con solo i componenti grafici che servono a visualizzare la vita dell'Eroe.
     * Oppure, ho creato, vistaSpostamentoEroe la quale ha solo i componenti (bottoni, label, ecc...).
     * I quali servono per Muovere il nostro eroe all'interno della matrice!
     * La classe 'vista matrice' con solo i componenti della matrice e la classe
     * 'vista statistiche' con solo i 'pezzi' per le statistiche.*/
    private EroeView vistaEroe;
    private SpostamentoEroeView vistaSpostamentoEroe;
    private MatriceView vistaMatrice;
    private StatisticheView vistaStatistiche;

   /**Metodo Costruttore:*/
    public InizioGiocoView(int dimensioneMatrice)
    {
        /**Le 4 classi 'snellenti'(vedi sopra) di gui per i vari pezzi di questa Finestra.
         * Nel controller però bisogna prenderli con i metodi set da questa classe.*/
        this.vistaEroe = new EroeView();
        this.vistaSpostamentoEroe= new SpostamentoEroeView();
        this.vistaMatrice = new MatriceView(dimensioneMatrice);
        this.vistaStatistiche = new StatisticheView();
        this.ManagerLayoutTotale = new VBox(10);

          //Aggiungo tutti i componenti al manager totale (ossia il Vbox)!
          ManagerLayoutTotale.getChildren().addAll(
                  this.vistaMatrice.getLayoutTotale(),
                  this.vistaStatistiche.getLayoutTotale(),
                  this.vistaSpostamentoEroe.getLayoutTotale(), //la classe di componenti gui per spostare l'eroe.
                  this.vistaEroe.getLayotVistaEroe(), //la classe di componenti gui per vedere la vita dell'eroe.
                  btnCancellaCampi,
                  btnIndietro
          );
    }
   /**Metodi set e get di tutti i componenti grafici!*/
    public Button getBtnIndietro(){ return this.btnIndietro; }
    public Button getBtnCancellaCampi(){ return this.btnCancellaCampi; }
    public void setBtnIndietro(Button btnIndietro) { this.btnIndietro=btnIndietro; }
    public void setBtnCancellaCampi(Button btnCancellaCampi) { this.btnCancellaCampi = btnCancellaCampi; }
    public EroeView getEroeView() { return this.vistaEroe; }
    public SpostamentoEroeView getSpostamentoEroeView () {return this.vistaSpostamentoEroe; }
    public MatriceView getMatriceView() {return this.vistaMatrice; }
    public StatisticheView getStatisticheView() {return this.vistaStatistiche; }

    /**Questo metodo serve alle classi che devono visualizzare questa View
     * a ottenere il suo layout manager.*/
    public Parent getLayoutManagerTotaleView()
    {
        return this.ManagerLayoutTotale;
    }
}
