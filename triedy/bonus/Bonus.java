package triedy.bonus;

import triedy.pohyb.Hrac;

public interface Bonus {

    String getNazov();

    void aktivuj(Hrac hrac);

    int getX();

    int getY();

    String getVypis();






}
