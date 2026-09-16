package it.unicam.cs.mpgc.rpg120666.Model.MatriceGioco;

import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Guerriero;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.ElencoClassiEsteseNemico.Mago;
import it.unicam.cs.mpgc.rpg120666.Model.Nemico.Nemico;

public class ManagerCombattimentoEroeNemico
{
    private Eroe ilNostroEroe;
    private Nemico ilNemico;
    public ManagerCombattimentoEroeNemico(Eroe ilNostroEroe,Nemico ilNemico)
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
       /**@Documented purtroppo mi sono reso conto che qui il mio codice non è estendibile.
         * Perchè nel momento in cui vado a creare un nuovo Nemico, oltre che a fare l'interfaccia della sua tipologia
         * e la sua classe concreta (che estende Nemico), devo anche mettere un altro blocco
         * if(this.Nemico instance of NuovaClasseConcreata) {}.*/

        //Finché almeno uno dei due combattenti è in vita:
        while(!(this.ilNostroEroe.controllaSeMorto()) && this.ilNemico.controllaSeNemicoInVita())
        {
            if (this.ilNemico instanceof Guerriero g)
            {
                g.eseguiAttaccoFisico(ilNostroEroe);
                System.out.println("Il guerriero ha attaccato il nostro eroe!\n");
                if (ilNostroEroe.controllaSeMorto())
                {
                    System.out.println("Il Nostro eroe e' morto!\n");
                    return false;
                }
                ilNostroEroe.attaccaFisicamente(g);
                System.out.println("Il nostro eroe ha attaccato il guerriero!\n");
                if (!g.controllaSeNemicoInVita())
                {
                    System.out.println("Il Nostro eroe ha vinto contro il guerriero!\n");
                    return true;
                }

            }
            else if (this.ilNemico instanceof Mago m)
            {
                m.eseguiAttaccoMagico(ilNostroEroe);
                System.out.println("Il mago ha attaccato il nostro eroe!\n");
                if (ilNostroEroe.controllaSeMorto())
                {
                    System.out.println("Il nostro eroe è morto!\n");
                    return false;
                }
                ilNostroEroe.attaccaFisicamente(m);
                System.out.println("Il nostro eroe ha attaccato il mago!\n");
                if (!m.controllaSeNemicoInVita())
                {
                    System.out.println("Il nostro eroe ha vinto la battaglia contro il mago!\n");
                    return true;
                }
                m.eseguiAutoCuraMagica(); //il mago si può ricaricare la vita!
                System.out.println("Il mago ha usato una magia per ricaricarsi la vita!\n");
            }
        }
        return false; //se succede un imprevisto!
    }
}