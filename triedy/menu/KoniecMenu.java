package triedy.menu;

import fri.shapesge.Obrazok;
import fri.shapesge.BlokTextu;
import java.util.ArrayList;
import fri.shapesge.StylFontu;
import triedy.zombici.*;

/**
 * Trieda reprezentuje obrazovku konca hry.
 */
public class KoniecMenu {
    private static Obrazok prehraMenu; // Obrázok pre obrazovku konca hry
    private BlokTextu pocetZvysnychBalonikov; // Text s počtom zostávajúcich zombikov
    private ArrayList<Zombik> baloniky; // Zoznam zombikov ešte nažive
    private StylFontu bold; // Štýl písma pre text

    /**
     * Konštruktor pre vytvorenie obrazovky konca hry.
     *
     * @param baloniky Zoznam zombíkov, ktorí sú ešte nažive
     * @param cesta Cesta k obrázku pre obrazovku konca hry (výhra, prehra)
     */
    public KoniecMenu(ArrayList<Zombik> baloniky, String cesta) {
        this.prehraMenu = new Obrazok(cesta, 80, -50); // Inicializácia obrázku pre obrazovku konca hry
        this.prehraMenu.zobraz(); // Zobrazenie obrázku

        // Vytvorenie textu s počtom zostávajúcich zombikov
        this.pocetZvysnychBalonikov = new BlokTextu("Stačilo ti už zabiť iba\n" + "       " + String.valueOf(baloniky.size()) + " balónikov.", 200, 600);
        this.pocetZvysnychBalonikov.zmenFont("Times New Roman", this.bold, 60); // Zmena fontu textu
        this.pocetZvysnychBalonikov.zmenFarbu("yellow"); // Zmena farby textu
        this.pocetZvysnychBalonikov.zobraz(); // Zobrazenie textu
    }

    /**
     * Skryje počet zostávajúcich zombikov.
     */
    public void skryZvysneBaloniky() {
        this.pocetZvysnychBalonikov.skry(); // Skrytie textu s počtom zostávajúcich zombikov
    }
}
