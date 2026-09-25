package it.unicam.cs.mpgc.rpg120666.Model.Oggetto;
import it.unicam.cs.mpgc.rpg120666.Model.Eroe.Eroe;

/*Questa interfaccia viene "implementata" dalla classe abstract Oggetto ( super classe di Arma e Medikit).
* Perchè ogni Oggetto ha un modo diverso di essere utilizzato!
* Solo la classe Oggetto "usa" questa interfaccia, ma (in quella classe Oggetto che usa questa interfaccia)
* Questo metodo è abstract! Così da essere riscritto nelle sottoclassi di Oggetto!
* In questo modo posso dare un utilizzo diverso a ogni sottoclasse di Oggetto!*/
public interface UsaOggetto
{
    public boolean usaOggetto(Eroe e);
}
