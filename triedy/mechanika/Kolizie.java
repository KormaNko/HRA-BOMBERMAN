package triedy.mechanika;

import java.awt.Rectangle;
import java.util.ArrayList;

/**@Matus Korman
 * V tejto triede pouzivam Rectangle z Javadoc dokumentáciu(Java Class Libraries).Pouzivam tu tvar Rectangle kvoli tomu
 * ze tvar Stvorec neobsahuje metodu intersects ktorou zistujem ci sa hrac a balonik navzajom dotkli.
 * https://docs.oracle.com/javase/8/docs/api/java/awt/Rectangle.html
 * 
 * 
 * Trieda zabezpečuje kontrolu kolízií medzi hráčom a balonikmi.
 */
public class Kolizie {
    private Rectangle obdlznikHraca;
    private ArrayList<Rectangle> obdlznikyZombikov;

    /**
     * Konštruktor pre inicializáciu kolízií.
     *
     * Parameter obdĺžnik reprezentujúci hráča
     * Parameter zoznam obdĺžnikov reprezentuje baloniky na mape
     */
    public Kolizie(Rectangle obdlznikHraca, ArrayList<Rectangle> obdlznikyZombikov) {
        this.obdlznikHraca = obdlznikHraca;
        this.obdlznikyZombikov = obdlznikyZombikov;
    }

    /**
     * Kontroluje kolíziu medzi hráčom a balonimi.
     *
     * Vracia true, ak došlo ku kolízii, inak false
     */
    public boolean kontrolaKolizie() {
        for (Rectangle obdlznikZombika : this.obdlznikyZombikov) {
            if (this.obdlznikHraca.intersects(obdlznikZombika)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Aktualizuje obdĺžnik reprezentujúci hráča.
     *
     * Parameter nový obdĺžnik hráča
     */
    public void aktualizujObdlznikHraca(Rectangle novyObdlznikHraca) {
        this.obdlznikHraca = novyObdlznikHraca;
    }

    /**
     * Aktualizuje zoznam obdĺžnikov reprezentujúcich baloniky.
     *
     * Parameter nový zoznam obdĺžnikov balonikov.
     */
    public void aktualizujObdlznikyBalonikov(ArrayList<Rectangle> noveObdlznikyZombikov) {
        this.obdlznikyZombikov.clear();
        this.obdlznikyZombikov.addAll(noveObdlznikyZombikov);
    }
}
