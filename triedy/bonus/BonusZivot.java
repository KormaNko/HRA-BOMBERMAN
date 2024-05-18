package triedy.bonus;

import fri.shapesge.Obrazok;
import triedy.pohyb.Hrac;

/**
 * Trieda reprezentuje bonus zivot.
 */


public class BonusZivot implements Bonus {
    private final Obrazok obrazok;

    private final int x;
    private final int y;

    public BonusZivot(int x, int y) {
        this.obrazok = new Obrazok("files/SRDCE.png", x, y);
        this.obrazok.zobraz();
        this.x = x;
        this.y = y;

    }


    @Override
    public String getNazov() {
        return "Zivot navise";
    }

    @Override
    public void aktivuj(Hrac hrac) {
        hrac.zvysZviot();
        this.obrazok.skry();
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public String getVypis() {
        return "Momentalne mas o jeden zivot navise";
    }


}
