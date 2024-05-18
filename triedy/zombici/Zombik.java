package triedy.zombici;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import triedy.mapa.Mapa;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Trieda reprezentuje zombíka v hre.
 */
public abstract class Zombik {

    private final Manazer manazer;  // Manazer, ktorý slúži na ovládanie pohybu zombíka
    private final Obrazok zombik;   // Obrazok zombíka
    private int zombikX;            // Súradnica X zombíka
    private int zombikY;            // Súradnica Y zombíka
    private boolean isZombikZivy;   // Kontrola, či je zombík nažive

    /**
     * Konštruktor pre vytvorenie zombíka na základe súradníc, mapy a orientácie pohybu.
     *
     * @param x     Súradnica X zombíka
     * @param y     Súradnica Y zombíka
     * @param mapa  Mapa, na ktorej sa zombík pohybuje
     * @param cesta Cesta k obrázku zombíka
     */
    public Zombik(int x, int y, Mapa mapa, String cesta) throws IOException {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.zombikX = x;
        this.zombikY = y;

        this.zombik = new Obrazok(cesta, x, y);

        this.zombik.zobraz();
        this.isZombikZivy = true;
    }

    /**
     * Pohyb zombíka v smere osi Y (vertikálny pohyb).
     */
    public abstract void pohybZombikaY();

    public boolean isZombikZivy() {
        return isZombikZivy;
    }

    /**
     * Pohyb zombíka v smere osi X (horizontálny pohyb).
     */
    public abstract void pohybZombikaX();

    /**
     * Zobrazí obrázok zombíka na hracej ploche.
     */
    public void zobrazZombika() {
        this.zombik.zobraz();
    }

    /**
     * Vráti súradnicu X zombíka.
     */
    public int getZombikaX() {
        return this.zombikX;
    }

    /**
     * Vráti súradnicu Y zombíka.
     */
    public int getZombikaY() {
        return this.zombikY;
    }

    /**
     * Nastaví novú polohu pre zombíka.
     *
     * @param x Nová súradnica X
     * @param y Nová súradnica Y
     */
    public void setPolohuZombika(int x, int y) {
        if (this.zombik != null && this.isZombikZivy) {
            this.zombik.zmenPolohu(x, y);
            this.zombik.skry();
            this.zombik.zobraz();
            this.zombikX = x;
            this.zombikY = y;
        }
    }

    /**
     * Zabije zombíka.
     */
    public void zabiZombika() {
        zmenObrazok();
        this.zombik.skry();
        this.prestanSpravovat();
        if (isZombikZivy) {
            this.zobrazZombika();
        }
        this.isZombikZivy = false;
    }

    public abstract void zmenObrazok();

    /**
     * Skryje zombíka.
     */
    public void skryZombika() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(this.zombik::skry, 2, TimeUnit.SECONDS);
        executor.shutdown();
    }

    /**
     * Mení obrázky zombíka, vytvára animáciu.
     */
    public abstract void animaciaZombika();

    /**
     * Manazer prestava spravovat tento objekt.
     */
    public void prestanSpravovat() {
        this.manazer.prestanSpravovatObjekt(this);
    }

    /**
     * Nastaví nový obrázok pre zombíka.
     *
     * @param obrazok Cesta k novému obrázku
     */
    public void setObrazok(String obrazok) {
        this.zombik.zmenObrazok(obrazok);
    }
}