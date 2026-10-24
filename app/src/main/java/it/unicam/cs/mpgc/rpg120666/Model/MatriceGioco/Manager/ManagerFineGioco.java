/**Classe ManagerFineGioco:
 * Serve per controllare se il gioco è terminato o meno.
 * Il gioco termina se l'Eroe è morto oppure se tutti i nemici sono stati sconfitti.
 *
 * Questa classe utilizza il metodo della classe: matriceContaElementi.calcolaNumOggettiNemiciRimanenti(EnumOggNem.NEMICO)
 * @see it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti.MatriceContaElementi
 * la quale prende il tipo di Oggetto (o Nemico, in questa situazione) tramite un enumerazione e lo passa a dei suoi metodi
 * privati che ritornano il numero di oggetti o nemici rimanti.
 * C'è una distinzione tra Nemici (quindi tra Nemici generici) & Arma, Mago, Eroe, Guerriero, Medikit, ecc... (quindi tra cose e nemici specifici).
 * La differenza consiste nel fatto che, quando viene passata a quella classe l'enumerazione Nemico,
 * essa ricerca all'interno della matrice utilizzando il metodo veroVolto(), definito nell'interfaccia ElementiPerMatrice.
 * Quando passo Arma, Medikit, Eroe, ecc... Lei lo cerca tramite il metodo restituisciTipoOggetto (sempre definito nell'interfaccia ElementiPerMatrice).
 *
 * @author Runci Riccardo
 * @since versione 1.0.*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti.EnumOggNem;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti.MatriceContaElementi;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate.Punteggi;
import it.unicam.cs.mpgc.rpg120666.Model.Serializzazione.Persistenza.Hybernate.UsaPunteggi;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.MieAnnotazioni;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class ManagerFineGioco
{
    private final ElementiPerMatrice[][] matrice;
    private Eroe ilNostroEroe;
    private MatriceContaElementi matriceContaElementi;
    /**Metodo costruttore:
     * @param matrice = la griglia che contiene Eroe, i Nemici e gli Oggetti che si possono incontrare durante il gioco.
     *                  La matrice è di tipo ElementiPerMatrice (interfaccia):
     *                  @see ElementiPerMatrice
     *@param ilNostroEroe = è l'Eroe del gioco.
     *@throws IllegalArgumentException se i parametri sopra passati sono = null!
     * In questo costruttore c'è anche la creazione di un oggetto di tipo MatriceContaElementi.
     * @see MatriceContaElementi
     * È una classe che si occupa di restituire il numero di nemici e oggetti ancora presenti in gioco.
     *@author Runci Riccardo
     *@since versione 1.0.*/
    public  ManagerFineGioco(ElementiPerMatrice[][] matrice, Eroe ilNostroEroe)
    {
        if (matrice==null || ilNostroEroe==null)
            throw new IllegalArgumentException("Matrice passata al metodo costruttore di ManagerFineGioco = null!");
        else
        {
            this.matrice=matrice;
            this.ilNostroEroe=ilNostroEroe;
            this.matriceContaElementi=new MatriceContaElementi(this.matrice);
        }
    }
    /**Metodo giocoFinito:
     * @return true se il gioco è terminato!
     * Può terminare in 2 modi: o l'eroe muore, oppure tutti i nemici sono stati sconfitti.
     * Non fa niente se non sono stati presi tutti gli oggetti.
     * @author Runci Riccardo
     * @since versione 1.0.*/
    @MieAnnotazioni(
            domanda="Se faccio un programma java, la sua interfaccia grafica può essere " +
                    "sviluppata con altri linguaggi di programmazione? SI! ho commesso un errore mettendo degli Alert qui!",
            osservazione="In questa classe ho utilizzato degli oggetti Alert di Javafx sapendo che stavo commettendo un errore." +
                    "L'ho fatto perché, arrivando in fondo al progetto, non mi ero reso conto che non avevo fatto niente per controllare" +
                    "la fine del gioco.")
    public boolean giocoFinito()
    {
        //eroe morto ritorno true, gioco finito!
        if(!this.ilNostroEroe.controllaSeVivo())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Partita Terminata");
            alert.setHeaderText(null);
            Label messaggio = new Label("HAI PERSO!\n EROE MORTO!");
            messaggio.setStyle(
                    "-fx-text-fill: red;" +
                            "-fx-font-size: 24px;" +
                            "-fx-font-weight: bold;"
            );
            alert.getDialogPane().setContent(messaggio);
            alert.showAndWait();
            this.inserisciPunteggio();
            return true;
        }

        else /*quindi eroe vivo*/
        {
            if (this.matriceContaElementi.calcolaNumOggettiNemiciRimanenti(EnumOggNem.NEMICO) == 0)
            {   //Eroe Anora vivo e 0 nemici|
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Partita Terminata");
                alert.setHeaderText(null);
                Label messaggio = new Label("Hai vinto!\nTutti nemici KO!");
                messaggio.setStyle("-fx-text-fill: green; -fx-font-size: 20px; -fx-font-weight: bold;");
                alert.getDialogPane().setContent(messaggio);
                alert.showAndWait();
                this.inserisciPunteggio();
                return true; //HAI VINTO!
            } else //se ci sono ancora nemici da combattere, return false!
                return false;
        }
    }
    /**Metodo che inserisce un record nella tabella del DB tramite Hibernate.
     * Sia se la partita è stata vinta, sia se la partita è persa!
     * @see Punteggi
     * @since versione 2.0.
     * @author Runci Riccardo.*/
    private void inserisciPunteggio()
    {
        UsaPunteggi.inserisciPunteggio(this.ilNostroEroe.getNomePersonaggio(),
                this.matriceContaElementi.calcolaNumOggettiNemiciRimanenti(EnumOggNem.NEMICO),
                (this.matriceContaElementi.calcolaNumOggettiNemiciRimanenti(EnumOggNem.ARMA)+
                            this.matriceContaElementi.calcolaNumOggettiNemiciRimanenti(EnumOggNem.MEDIKIT)));
    }
}
