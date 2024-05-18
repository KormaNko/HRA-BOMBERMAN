package triedy.zombici;

import triedy.mapa.Mapa;
import triedy.mapa.StavPolicka;

import java.io.IOException;

public class Balonik extends Zombik {
    private boolean hore;
    /**
     * Konštruktor pre vytvorenie zombíka na základe súradníc, mapy a orientácie pohybu.
     * <p>
     * parameter   Súradnica x balonika
     * parameter   Súradnica y balonika
     * parameter   triedy.mapa.Mapa, na ktorej sa balonik pohybuje
     * parameter   Určuje, či sa balonik pohybuje vertikálne (true) alebo horizontálne (false)
     *
     * @param x
     * @param y
     * @param mapa
     * @param vertikalne
     */

    private int x;
    private int y;
    private final Mapa mapa;

    private String cesta;

    private final boolean vertikalne;

    private boolean vpravo;

    private final int velkostPohybu = 2;



    public Balonik(int x, int y, Mapa mapa, boolean vertikalne) throws IOException {
        super(x, y, mapa, "files/balonik2.png");
        this.hore = false;
        this.vpravo = false;
        this.vertikalne = vertikalne;


        this.x = x;
        this.y = y;
        this.mapa = mapa;

        this.cesta = "files/balonik2.png";
    }

    public void pohybZombikaY() {
        if (this.vertikalne && this.isZombikZivy()) {
            if (!this.hore) {
                if (this.mapa.getPolicko(this.getZombikaX(), this.getZombikaY() - velkostPohybu) == StavPolicka.TRAVA) {
                    this.setPolohuZombika(this.getZombikaX(), this.getZombikaY() - velkostPohybu);
                    this.y = this.getZombikaY() - velkostPohybu;
                } else {
                    this.hore = true;
                }
            } else {
                if (this.mapa.getPolicko(this.getZombikaX(), this.getZombikaY() + 40) == StavPolicka.TRAVA) {
                    this.setPolohuZombika(this.getZombikaX(), this.getZombikaY() + velkostPohybu);
                    this.y = this.getZombikaY() + velkostPohybu;
                } else {
                    this.hore = false;
                }
            }
        }
    }

    public void pohybZombikaX() {

        if (!this.vertikalne && this.isZombikZivy()) {
            if (!this.vpravo) {
                if (this.mapa.getPolicko(this.getZombikaX() - velkostPohybu, this.getZombikaY()) == StavPolicka.TRAVA) {
                    this.setPolohuZombika(this.getZombikaX() - velkostPohybu, this.getZombikaY());
                    this.x = this.getZombikaX() - velkostPohybu;
                } else {
                    this.vpravo = true;
                }
            } else {
                if (this.mapa.getPolicko(this.getZombikaX() + 42, this.getZombikaY()) == StavPolicka.TRAVA) {
                    this.setPolohuZombika(this.getZombikaX() + velkostPohybu, this.getZombikaY());
                    this.x = this.getZombikaX() + velkostPohybu;
                } else {
                    this.vpravo = false;
                }
            }
        }
    }



    @Override
    public void zmenObrazok() {

        this.setObrazok("files/MRTVOLABALONIKA.png");
    }

    @Override
    public void animaciaZombika() {
        if (this.isZombikZivy()) {
            switch (this.cesta) {
                case ("files/balonik2.png"):
                    this.cesta = ("files/balonik3.png");
                    this.setObrazok("files/balonik3.png");
                    break;
                case ("files/balonik3.png"):
                    this.cesta = ("files/balonik2.png");
                    this.setObrazok("files/balonik2.png");
                    break;
            }
        }
    }
}
