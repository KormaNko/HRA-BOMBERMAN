package triedy.mechanika;

import fri.shapesge.Obrazok;
import java.util.ArrayList;
import java.util.List;

/**
 * Trieda reprezentuje explóziu so stredom a vetvami v rôznych smeroch.
 */
public class Explozia {
    private final Obrazok stred;  // Obrázok stredovej časti explózie
    private final List<Obrazok> casti;  // Zoznam častí explózie

    /**
     * Konštruktor pre vytvorenie explózie na zadaných súradniciach.
     *
     * @param x Súradnica stredu explózie
     * @param y Súradnica stredu explózie
     */
    public Explozia(int x, int y) {
        this.stred = new Obrazok("files/stred.png", x, y);
        this.stred.zobraz();

        // Inicializácia a uloženie obrázkov pre jednotlivé vetvy explózie do zoznamu
        this.casti = new ArrayList<>();
        this.casti.add(new Obrazok("files/kraj.png", x + 45, y + 11)); // vpravo
        Obrazok vLavo = new Obrazok("files/kraj.png", x - 45, y + 11);
        vLavo.zmenUhol(180);
        this.casti.add(vLavo); // vlavo
        Obrazok hore = new Obrazok("files/kraj.png", x, y - 30);
        hore.zmenUhol(270);
        this.casti.add(hore); // hore
        Obrazok dole = new Obrazok("files/kraj.png", x, y + 50);
        dole.zmenUhol(90);
        this.casti.add(dole); // dole
    }

    /**
     * Zobrazí vetvu explózie v danom smere.
     *
     * @param index Index vetvy explózie
     */
    public void zobrazVetvu(int index) {
        if (index >= 0 && index < this.casti.size()) {
            this.casti.get(index).zobraz();
        }
    }

    /**
     * Zobrazí vetvu explózie smerom vpravo.
     */
    public void zobrazVpravo() {
        zobrazVetvu(0);
    }

    /**
     * Zobrazí vetvu explózie smerom vľavo.
     */
    public void zobrazVlavo() {
        zobrazVetvu(1);
    }

    /**
     * Zobrazí vetvu explózie smerom hore.
     */
    public void zobrazHore() {
        zobrazVetvu(2);
    }

    /**
     * Zobrazí vetvu explózie smerom dole.
     */
    public void zobrazDole() {
        zobrazVetvu(3);
    }

    /**
     * Skryje všetky časti explózie.
     */
    public void skryExploziu() {
        this.stred.skry();
        for (Obrazok cast : this.casti) {
            cast.skry();
        }
    }
}
