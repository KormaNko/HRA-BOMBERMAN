package triedy.bonus;

import fri.shapesge.Obrazok;
import triedy.pohyb.Hrac;

/**
 * Trieda reprezentuje bonus život.
 */
public class BonusZivot implements Bonus {
    private final Obrazok obrazok;

    private final int x;
    private final int y;

    /**
     * Konštruktor, ktorý vytvorí nový bonus život na zadaných súradniciach.
     *
     * @param x horizontálna súradnica bonusu
     * @param y vertikálna súradnica bonusu
     */
    public BonusZivot(int x, int y) {
        this.obrazok = new Obrazok("files/SRDCE.png", x, y);
        this.obrazok.zobraz();
        this.x = x;
        this.y = y;
    }

    /**
     * @return názov bonusu
     */
    @Override
    public String getNazov() {
        return "Zivot navise";
    }

    /**
     * Aktivuje bonus, ktorý zvýši počet životov hráča.
     *
     * @param hrac hráč, ktorý aktivuje bonus
     */
    @Override
    public void aktivuj(Hrac hrac) {
        hrac.zvysZviot();
        this.obrazok.skry();
    }

    /**
     * @return horizontálna súradnica bonusu
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @return vertikálna súradnica bonusu
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * @return opis aktuálneho stavu bonusu
     */
    @Override
    public String getVypis() {
        return "Momentalne mas o jeden zivot navise";
    }
}
