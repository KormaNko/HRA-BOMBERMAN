package triedy.pohyb;
/**
 * Trieda reprezentuje polohu v hernom svete.
 */
public class Poloha {
    private final int x;      // Súradnica x
    private final int y;      // Súradnica y
    private final String cesta;     // Cesta k obrázku s touto polohou

    /**
     * Konštruktor pre vytvorenie polohy na základe súradníc a cesty k obrázku.
     *
     * @param x     Súradnica x
     * @param y     Súradnica y
     * @param cesta Cesta k obrázku
     */
    public Poloha(int x, int y, String cesta) {
        this.x = x;
        this.y = y;
        this.cesta = cesta;
    }

    /**
     * Získa súradnicu x polohy.
     *
     * @return Súradnica x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Získa súradnicu y polohy.
     *
     * @return Súradnica y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Získa cestu k obrázku.
     *
     * @return Cesta k obrázku
     */
    public String getCesta() {
        return this.cesta;
    }

    /**
     * Vytvorí novú polohu posunutú v zadanom smere a kroku.
     *
     * @param krok Krok posunutia
     * @param smer Smer posunutia
     * @return Nová poloha po posunutí
     */
    public Poloha getPosunutuPolohu(int krok, Smer smer) {
        return new Poloha(this.x + krok * smer.getX(), this.y + krok * smer.getY(), smer.getCesta());
    }
}
