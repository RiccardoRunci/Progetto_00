/**@Autor Copilot, soluzione per cliccare le label della
 * matrice di label della classe MatriceView,
 * senza passare alla classe MatriceView il controller!
 * Perchè passare il controller alla view è considerato un errore del pattern MVC!*/
package it.unicam.cs.mpgc.rpg120666.View;

public interface CellaClickListener
{
    void onCellaClick(int riga, int colonna);
}
/***/
