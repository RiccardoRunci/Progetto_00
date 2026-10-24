package it.unicam.cs.mpgc.rpg120666.View;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InizioGiocoView
{
    private VBox ManagerLayoutTotale;
    private Button btnPunteggi = new Button("Vedi Punteggi");
    private Button btnCancellaTuttiPunteggi = new Button("Cancella tutti i punteggi");
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
    private ZainoView vistaZaino;     /*La classe view di zaino:*/

   /**Metodo Costruttore:*/
    public InizioGiocoView(int dimensioneMatrice)
    {
        /**Le 5 classi 'snellenti'(vedi sopra) di gui per i vari pezzi di questa Finestra.
         * Nel controller però bisogna prenderli con i metodi set da questa classe.*/
        this.vistaEroe = new EroeView();
        this.vistaSpostamentoEroe= new SpostamentoEroeView();
        this.vistaMatrice = new MatriceView(dimensioneMatrice);
        this.vistaStatistiche = new StatisticheView();
        this.vistaZaino = new ZainoView();

        //Il Layout Manager totale di questa classe (quello che racchiude tutto):
        this.ManagerLayoutTotale = new VBox(10);

        /*Creo un nuovo Layout orizzontale per mettere la matrice affiancata allo zaino:
        * Poi (nel blocco sotto a questo) lo aggiungo al layout totale della classe:*/
        HBox layotVistaMatriceZaino = new HBox(10);
        layotVistaMatriceZaino.getChildren().addAll(
                this.vistaMatrice.getLayoutTotale(),
                this.vistaZaino.getLayoutTotale());

        //Aggiungo tutti i componenti al manager totale (ossia il Vbox)!
          ManagerLayoutTotale.getChildren().addAll(
                  layotVistaMatriceZaino, //è il layout sopra dove Matrice e Zaino sono orizzontali!
                  this.vistaStatistiche.getLayoutTotale(), //Vista delle statistiche.
                  this.vistaSpostamentoEroe.getLayoutTotale(), //la classe di componenti gui per spostare l'eroe.
                  this.vistaEroe.getLayotVistaEroe(), //la classe di componenti gui per vedere la vita dell'eroe.
                  btnCancellaCampi,
                  btnPunteggi,
                  btnCancellaTuttiPunteggi
          );
    }
   /**Metodi set e get di tutti i componenti grafici!*/
    public Button getBtnPunteggi(){ return this.btnPunteggi; }
    public Button getBtnCancellaTuttiPunteggi(){ return this.btnCancellaTuttiPunteggi; }
    public Button getBtnCancellaCampi(){ return this.btnCancellaCampi; }
    public void setBtnPunteggi(Button btnPunteggi) { this.btnPunteggi = btnPunteggi; }
    public void setBtnCancellaTuttiPunteggi(Button btnCancellaTuttiPunteggi) { this.btnCancellaTuttiPunteggi = btnCancellaTuttiPunteggi; }
    public void setBtnCancellaCampi(Button btnCancellaCampi) { this.btnCancellaCampi = btnCancellaCampi; }
    /**Metodo get di tutte le view "incastonate" dentro questa classe
     * (messe dentro il layout totale di questa classe view).
     * Tutti questi get servono alle classi controller (principalmente a MatriceController, che è la classe "god" controller,
     * poi ZainoController prenderà il suo controller tramite MatriceController...) per prendere le varie classi view
     * inserite dentro il ManagerLayoutTotale di questa classe.*/
    public EroeView getEroeView() { return this.vistaEroe; }
    public SpostamentoEroeView getSpostamentoEroeView () {return this.vistaSpostamentoEroe; }
    public MatriceView getMatriceView() {return this.vistaMatrice; }
    public StatisticheView getStatisticheView() {return this.vistaStatistiche; }
    /*Serve per ritornare al controller MatriceController (poi MatriceController prenderà la TableView di zainoView)
    * La view dello zaino:*/
    public ZainoView getZainoView() {return this.vistaZaino;}
    /**Questo metodo serve alle classi che devono visualizzare questa View
     * a ottenere il suo layout manager.*/
    public Parent getLayoutManagerTotaleView()
    {
        return this.ManagerLayoutTotale;
    }
}