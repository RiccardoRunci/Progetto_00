package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco.Manager;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;
public class ManagerCombattimentoEroeNemico
{
    private Eroe ilNostroEroe;
    private Nemico ilNemico;
    public ManagerCombattimentoEroeNemico(Eroe ilNostroEroe, Nemico ilNemico)
    {
        if(ilNostroEroe == null || ilNemico == null)
            throw new IllegalArgumentException("I parametri: ilNostroEroe oppure ilNemico;" +"\n"+
                    "Passati al costruttore della classe: ManagerCombattimentoEroeNemico;" +"\n"+
                    "sono Nulli!\n");
        this.ilNostroEroe=ilNostroEroe;
        this.ilNemico=ilNemico;
    }
    /**Questo metodo ritorna true se il nostro eroe ha vinto lo scontro!
     * Quindi può spostarsi nella nuova cella di destinazione!*/
    public boolean combattimento()
    {
        //Finché almeno uno dei due combattenti è in vita:
        while (this.ilNostroEroe.controllaSeVivo() && this.ilNemico.controllaSeVivo())
        {
            //Il nemico attacca per primo l'eroe:
            this.ilNemico.eseguiAttacco(this.ilNostroEroe);
            //L'eroe risponde sferrando l'attacco al nemico:
            this.ilNostroEroe.eseguiAttacco(this.ilNemico);
            //Se il nemico o l'eroe è deceduto,interrompo il while!
            if(!(this.ilNemico.controllaSeVivo()) || !(this.ilNostroEroe.controllaSeVivo()) )
                break;
        }
        //Vedo chi ha vinto:
        if(this.ilNostroEroe.controllaSeVivo())
        {
            System.out.println("L'eroe ha vinto!");
            System.out.println("Vita Eroe: "+ilNostroEroe.getPuntiVitaPersonaggio() +"\n"+
                               "Vita Nemico: "+ilNemico.getPuntiVitaPersonaggio()+"\n");
            return true;
        }
        else
        {
            System.out.println("Il nemico ha vinto!");
            System.out.println("Vita Eroe: "+ilNostroEroe.getPuntiVitaPersonaggio() + "\n"+
                               "Vita Nemico: "+ilNemico.getPuntiVitaPersonaggio()+"\n");
            return false; //ha vinto il nemico
        }
    }
}
