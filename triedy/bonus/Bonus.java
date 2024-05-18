package triedy.bonus;

import triedy.pohyb.Hrac;

/**
 * Rozhranie pre bonusy, ktoré môžu byť aplikované na hráča.
 */
public interface Bonus {

    /**
     * @return názov bonusu
     */
    String getNazov();

    /**
     * Aktivuje bonus pre zadaného hráča.
     *
     * @param hrac hráč, ktorý aktivuje bonus
     */
    void aktivuj(Hrac hrac);

    /**
     * @return horizontálna súradnica bonusu
     */
    int getX();

    /**
     * @return vertikálna súradnica bonusu
     */
    int getY();

    /**
     * @return opis aktuálneho stavu bonusu
     */
    String getVypis();

}
