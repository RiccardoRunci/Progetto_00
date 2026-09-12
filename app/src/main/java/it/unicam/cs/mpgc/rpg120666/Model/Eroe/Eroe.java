package it.unicam.cs.mpgc.rpg120666.Model.Eroe;
import it.unicam.cs.mpgc.rpg120666.Model.ElementiPerMatrice.ElementiPerMatrice;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.ClasseUtility;
import it.unicam.cs.mpgc.rpg120666.Model.Utility.EnumControlloCreazioneAggiornamento;

public class Eroe implements ElementiPerMatrice
{
    private String nomeEroe;
    private int puntiVitaEroe;
    private ZainoEroe oggettiEroe;

    public Eroe(){}
    public Eroe(String nomeEroe, int puntiVitaEroe)
    {
        if((ClasseUtility.controllaStringa(nomeEroe,"EROE")) &&
                (ClasseUtility.verificaNumeriInteri(puntiVitaEroe, EnumControlloCreazioneAggiornamento.CREAZIONE,"EROE")))
                {
                    this.puntiVitaEroe=puntiVitaEroe;
                    this.nomeEroe = nomeEroe;
                    this.oggettiEroe=new ZainoEroe();
                }
    }
    public String getNomeEroe(){return this.nomeEroe;}
    public int getPuntiVitaEroe(){return this.puntiVitaEroe;}
    public ZainoEroe getOggettiEroe() { return this.oggettiEroe; }

}
