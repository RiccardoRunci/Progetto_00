/**@implNote Questa classe utilizza gli elementi della matrice.
 * Nel senso che guarda un metodo di quegli elementi che viene firmato
 * nell'interfaccia ElementiPerMatrice.
 * Il senso pratico è questo:
 * Gli elementi che implementano quell'interfaccia avevano già un metodo che si chiamava
 *"", ma questo restituiva il nome dell'oggetto particolare, non il nome dell'oggetto "generico".
 * Esempio: Arma restituiva Arma, non Oggetto!
 * Allora ho creato una nuova firma nell'interfaccia (chiamandola "veroVolto", perchè ero proprio a corto di idee),
 * e lho sovrascritta con final dentro le super classi (esempio in Nemico).
 * Il problema più grosso è stato che nella classe Eroe ho violato ISP (come la volta precedente con l'altro metodo
 * che ad eroe non serviva, e l'ho attoppato con un toString).
 * L'altro problema è stato che la classe Oggetto non ha la stessa struttura della classe Nemico
 * Questo non dovrebbe pregiudicare il funzionamento, ma non è elegante come in Nemico*/
package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti.EnumOggNem;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.ContaElementiRimanenti.MatriceContaElementi;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class ManagerFineGioco
{
    private final ElementiPerMatrice[][] matrice;
    private Eroe ilNostroEroe;
    private MatriceContaElementi matriceContaElementi;

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
    /**Metodo che torna true se il gioco è terminato!
     * Può terminare in 2 modi: o l'eroe muore, oppure tutti
     * i nemici sono stati sconfitti.
     * Non fa niente se non sono stati presi tutti gli oggetti-*/
    public boolean giocoFinito()
    {
        //eroe morto ritorno true, gioco finito!
        if(!this.ilNostroEroe.controllaSeVivo())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Partita Terminata");
            alert.setHeaderText(null);
            Label messaggio = new Label("❌ HAI PERSO! ❌\n EROE MORTO!");
            messaggio.setStyle(
                    "-fx-text-fill: red;" +
                            "-fx-font-size: 24px;" +
                            "-fx-font-weight: bold;"
            );
            alert.getDialogPane().setContent(messaggio);
            alert.showAndWait();
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
                return true; //HAI VINTO!
            } else //se ci sono ancora nemici da combattere, return false!
                return false;
        }

    }


}
