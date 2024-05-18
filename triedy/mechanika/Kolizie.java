package triedy.mechanika;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Trieda zabezpečuje kontrolu kolízií medzi hráčom a zombikmi.
 */
public class Kolizie {
    private Rectangle obdlznikHraca; // Obdĺžnik reprezentujúci hráča
    private List<Rectangle> obdlznikyZombikov; // Zoznam obdĺžnikov reprezentujúcich zombikov na mape

    /**
     * Konštruktor pre inicializáciu kolízií.
     *
     * @param obdlznikHraca obdĺžnik reprezentujúci hráča
     * @param obdlznikyZombikov zoznam obdĺžnikov reprezentujúcich zombikov na mape
     */
    public Kolizie(Rectangle obdlznikHraca, List<Rectangle> obdlznikyZombikov) {
        // Inicializácia parametrov
        this.obdlznikHraca = obdlznikHraca;
        this.obdlznikyZombikov = new ArrayList<>(obdlznikyZombikov); // Použitie kópie zoznamu pre nezávislosť
    }

    /**
     * Kontroluje kolíziu medzi hráčom a zombikmi.
     *
     * @return true, ak došlo ku kolízii, inak false
     */
    public boolean kontrolaKolizie() {
        // Prechádza cez zoznam obdĺžnikov zombikov a kontroluje ich kolíziu s obdĺžnikom hráča
        for (Rectangle obdlznikZombika : this.obdlznikyZombikov) {
            if (this.obdlznikHraca.intersects(obdlznikZombika)) {
                return true; // Ak sa kolízia nájde, vráti true
            }
        }
        return false; // Ak sa žiadna kolízia nenájde, vráti false
    }

    /**
     * Aktualizuje obdĺžnik reprezentujúci hráča.
     *
     * @param novyObdlznikHraca nový obdĺžnik hráča
     */
    public void aktualizujObdlznikHraca(Rectangle novyObdlznikHraca) {
        // Aktualizuje obdĺžnik hráča na základe nového obdĺžnika
        this.obdlznikHraca = novyObdlznikHraca;
    }

    /**
     * Aktualizuje zoznam obdĺžnikov reprezentujúcich zombikov.
     *
     * @param noveObdlznikyZombikov nový zoznam obdĺžnikov zombikov
     */
    public void aktualizujObdlznikyZombikov(List<Rectangle> noveObdlznikyZombikov) {
        // Aktualizuje zoznam obdĺžnikov zombikov na základe nového zoznamu
        this.obdlznikyZombikov.clear(); // Vyčistí existujúci zoznam
        this.obdlznikyZombikov.addAll(noveObdlznikyZombikov); // Pridá všetky prvky z nového zoznamu
    }
}
