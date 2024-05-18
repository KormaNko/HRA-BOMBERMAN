package triedy.menu;

import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
import triedy.mechanika.Hra;

/**
 * Trieda reprezentuje úvodné menu hry.
 */
public class StartMenu {
    private Obrazok obrazok; // Obrázok pre úvodné menu
    private Manazer manazer; // Manazer pre interakciu s úvodným menu

    private boolean hraBezi; // Určuje, či už beží hra alebo nie

    /**
     * Konštruktor pre vytvorenie úvodného menu.
     */
    public StartMenu() {
        this.obrazok = new Obrazok("files/BOMBERMANSTART.png", 0, 0); // Inicializácia obrázku pre úvodné menu
        this.obrazok.zobraz(); // Zobrazenie obrázku

        this.manazer = new Manazer(); // Inicializácia manazéra
        this.manazer.spravujObjekt(this); // Pripojenie úvodného menu k manazeru

        this.hraBezi = false; // Nastavenie počiatočného stavu hry na "nebeží"
    }

    /**
     * Metóda na výber súradníc v úvodnom menu.
     *
     * @param x Súradnica X kliknutia
     * @param y Súradnica Y kliknutia
     */
    public void vyberSuradnice(int x, int y)  {
        // Ak sa kliklo na prvú možnosť a hra ešte nebeží
        if(x < 475 && !this.hraBezi){
            Hra hra = new Hra("files/m.txt"); // Vytvorenie novej hry s prvou mapou
            this.obrazok.skry(); // Skrytie úvodného menu
            this.hraBezi = true; // Nastavenie stavu hry na "beží"
        }
        // Ak sa kliklo na druhú možnosť a hra ešte nebeží
        else if (x < 950 && !this.hraBezi){
            Hra hra = new Hra("files/m2.txt"); // Vytvorenie novej hry s druhou mapou
            this.obrazok.skry(); // Skrytie úvodného menu
            this.hraBezi = true; // Nastavenie stavu hry na "beží"
        }
    }
}
