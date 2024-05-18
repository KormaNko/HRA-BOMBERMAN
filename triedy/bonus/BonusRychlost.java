package triedy.bonus;

import fri.shapesge.Obrazok;
import triedy.pohyb.Hrac;

public class BonusRychlost implements Bonus{

    private Obrazok obrazok;

    private final int x;
    private final int y;

    public BonusRychlost(int x, int y) {
        this.obrazok = new Obrazok("files/AUTO.png", x, y);
        this.obrazok.zobraz();

        this.x = x;
        this.y = y;

    }
    @Override
    public String getNazov() {
        return "Rychlost navise";
    }

    @Override
    public void aktivuj(Hrac hrac) {
        hrac.setVelkostKroku(hrac.getVelkostKroku() * 2);
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
        return "Momentalne dvojnasobnu rychlost.";
    }
}
