/*Questa enumerazione mi serve per prendere dai file txt salvati nella resources (quindi nei file statici di gradle)
* dei nomi tra quelli salvati in quei file txt.
* Grazie a questa enumerazione (con un singolo metodo) posso scegliere se estrarre dal file
* 'NOMI_GUERRIERO.txt' o dal file 'NOMI_MAGO.txt'.*/
package it.unicam.cs.mpgc.rpg120666.Model.Utility;

public enum EnumListaPersonaggiGioco
{
    MAGO,
    GUERRIERO
}
