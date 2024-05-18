package triedy.bonus;

import fri.shapesge.Obrazok;
import triedy.pohyb.Hrac;

/**
 * Trieda predstavuje bonus, ktorý zvyšuje rýchlosť hráča.
 */
public class BonusRychlost implements Bonus {

    private Obrazok obrazok;

    private final int x;
    private final int y;

    /**
     * Konštruktor, ktorý vytvorí nový bonus rýchlosti na zadaných súradniciach.
     *
     * @param x horizontálna súradnica bonusu
     * @param y vertikálna súradnica bonusu
     */
    public BonusRychlost(int x, int y) {
        this.obrazok = new Obrazok("files/AUTO.png", x, y);
        this.obrazok.zobraz();

        this.x = x;
        this.y = y;
    }

    /**
     * @return názov bonusu
     */
    @Override
    public String getNazov() {
        return "Rychlost navise";
    }

    /**
     * Aktivuje bonus, ktorý zdvojnásobí veľkosť kroku hráča.
     *
     * @param hrac hráč, ktorý aktivuje bonus
     */
    @Override
    public void aktivuj(Hrac hrac) {
        hrac.setVelkostKroku(hrac.getVelkostKroku() * 2);
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
        return "Momentalne dvojnasobnu rychlost.";
    }
}
