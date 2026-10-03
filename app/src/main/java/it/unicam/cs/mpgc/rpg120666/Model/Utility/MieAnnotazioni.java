/**Classe di annotazioni personalizzata:
 * @Author Runci Riccardo
 * @since Versione 1.0*/
package it.unicam.cs.mpgc.rpg120666.Model.Utility;
import java.lang.annotation.*;

@Target({
        ElementType.TYPE, //indica che può essere scritta in classi, interfacce, enum.
        ElementType.METHOD, //indica che può essere scritta in metodo di un classe.
        ElementType.FIELD, //indica che può essere scritta in un campo/attributo di una classe.
        ElementType.CONSTRUCTOR, //nei costruttori
        ElementType.LOCAL_VARIABLE}) //Nelle variabili locali (no i campi di un oggetto, no FIELD)
@Retention(RetentionPolicy.SOURCE) //Annotazione che esiste solo nel codice sorgente (non a tempo di compilazione ecc...).
public @interface MieAnnotazioni
{
    String domanda();
    String osservazione();
}
