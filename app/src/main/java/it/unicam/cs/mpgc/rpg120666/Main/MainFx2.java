package it.unicam.cs.mpgc.rpg120666.Main;
import it.unicam.cs.mpgc.rpg120666.Controller.MatriceController;
import it.unicam.cs.mpgc.rpg120666.Model.Difficolta.EnumDifficolta;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.MatriceGioco;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.VerificaParametri;
import it.unicam.cs.mpgc.rpg120666.View.InizioGiocoView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javax.swing.*;

public class MainFx2 extends Application
{
    @Override
    public void start(Stage palcoscenico) throws Exception
    {
        String NomeEroe = this.scegliNomeEroe();
        //0 Creo l'EROE!
        Eroe IlNostroEroe = new Eroe(NomeEroe,50);

        EnumDifficolta difficoltaGioco=this.scegliDifficolta();
        // 1) MODEL
        MatriceGioco model = new MatriceGioco(difficoltaGioco,IlNostroEroe);

        // 2) VIEW
        int dimensione = model.getDimensione();
        /*Prima avevo che view prendeva (dimensione, controller)!
        * solo che controller l'ho dichiarato sotto!
        * e non potevo passarlo quando inizializzavo view!*/
        InizioGiocoView view = new InizioGiocoView(dimensione); //Allora ho risolto con view.setMatriceController! (4) E HO SBAGLIATO!!!!

        // 3) CONTROLLER
        MatriceController controller = new MatriceController(view, model);

        // 4) COLLEGO IL CONTROLLER ALLA VIEW
        //view.setMatriceController(controller); PERCHE???? ERRORISSIMO!!!!
        view.getMatriceView().aggiorna(model.getMatriceElementi()); //questo fa comparire subito la matrice "colorata", senza bisogna prima cliccarci dentro.

        // 5) SCENA
        Scene scena = new Scene(view.getLayoutManagerTotaleView());

        // 6) STAGE = palcoscenico (su cui si basa la scena)
        palcoscenico.setScene(scena);
        palcoscenico.setTitle("Matrice Gioco");
        palcoscenico.show();
    }
    private String scegliNomeEroe()
    {
        //Creo un textInputDialog per prendere il nome del giocatore:
        TextInputDialog dialog = new TextInputDialog("Riccardo");
        dialog.setTitle("INSERISCI IL NOME DELL'EROE!");
        dialog.setHeaderText("Configurazione Profilo");
        dialog.setContentText("Inserisci il tuo nome:");

        // salvo il nome e cotrollo se è corretto o se ha all'interno dei numeri!
        String nome = dialog.showAndWait().orElse("Eroe");
        // System.out.println("\nNOME INSERITO: "+nome);
        if(VerificaParametri.verificaStringhe(nome))
            return nome;
        else
        {
            /*Se si è verificato un errore mostro un Alert!*/
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Errore");
            alert.setHeaderText("Nome giocatore non valido!");
            alert.setContentText("Devi inserire un nome valido, non vuoto e non soli numeri!");
            alert.showAndWait();
            throw new IllegalArgumentException("Attenzione gioco annullato!\n Nome giocatore invalido!");
        }
    }
    private EnumDifficolta scegliDifficolta()
    {
        JRadioButton facile = new JRadioButton("FACILE");
        JRadioButton media = new JRadioButton("MEDIA");
        JRadioButton difficile = new JRadioButton("DIFFICILE");

        ButtonGroup gruppo = new ButtonGroup();
        gruppo.add(facile);
        gruppo.add(media);
        gruppo.add(difficile);

        JPanel pannello = new JPanel();
        pannello.add(facile);
        pannello.add(media);
        pannello.add(difficile);

        JOptionPane.showMessageDialog(null, pannello,
                "Scegli la difficoltà: ", JOptionPane.PLAIN_MESSAGE);

        if (facile.isSelected())
        {
            //System.out.println("Hai scelto facile");
            return EnumDifficolta.FACILE;
        }
        else if (media.isSelected())
        {
            //System.out.println("Hai scelto media");
            return EnumDifficolta.MEDIA;
        }
        else if (difficile.isSelected())
        {
            //System.out.println("Hai scelto difficile");
            return EnumDifficolta.DIFFICILE;
        }
        else
        { throw new IllegalArgumentException("Errore imprevisto, gioco annullato!"); }
    }
    public static void main(String[] args)
    {
        launch();
    }
}
