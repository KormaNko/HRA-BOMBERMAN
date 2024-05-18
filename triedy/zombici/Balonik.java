package triedy.zombici;

import triedy.mapa.Mapa;
import triedy.mapa.StavPolicka;

import java.io.IOException;

/**
 * Trieda reprezentuje balónika v hre.
 * Balónik sa môže pohybovať vertikálne alebo horizontálne po mape.
 */
public class Balonik extends Zombik {
    private boolean hore; // Indikuje, či sa balónik pohybuje hore
    private boolean vpravo; // Indikuje, či sa balónik pohybuje vpravo

    private int x; // Súradnica x balónika
    private int y; // Súradnica y balónika
    private final Mapa mapa; // Mapa, na ktorej sa balónik pohybuje

    private String cesta; // Cesta k obrázku balónika

    private final boolean vertikalne; // Indikuje, či sa balónik pohybuje vertikálne (true) alebo horizontálne (false)

    private final int velkostPohybu = 2; // Veľkosť kroku, o ktorý sa balónik posunie

    /**
     * Konštruktor pre vytvorenie balónika na základe súradníc, mapy a orientácie pohybu.
     *
     * @param x           Súradnica x balónika
     * @param y           Súradnica y balónika
     * @param mapa        Mapa, na ktorej sa balónik pohybuje
     * @param vertikalne  Určuje, či sa balónik pohybuje vertikálne (true) alebo horizontálne (false)
     */
    public Balonik(int x, int y, Mapa mapa, boolean vertikalne) throws IOException {
        super(x, y, mapa, "files/balonik2.png");
        this.hore = false;
        this.vpravo = false;
        this.vertikalne = vertikalne;
        this.mapa = mapa;

        this.x = x;
        this.y = y;
        this.cesta = "files/balonik2.png";
    }

    /**
     * Vykoná pohyb balónika po osi Y.
     */
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

    /**
     * Vykoná pohyb balónika po osi X.
     */
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

    /**
     * Zmení obrázok balónika na obrázok mŕtveho balónika.
     */
    @Override
    public void zmenObrazok() {
        this.setObrazok("files/MRTVOLABALONIKA.png");
    }

    /**
     * Vykoná animáciu balónika, ak je ešte nažive.
     * Po každom zavolaní metódy prehodí obrázok balónika medzi dvomi možnosťami.
     */
    @Override
    public void animaciaZombika() {
        if (this.isZombikZivy()) {
            switch (this.cesta) {
                case "files/balonik2.png":
                    this.cesta = "files/balonik3.png";
                    this.setObrazok("files/balonik3.png");
                    break;
                case "files/balonik3.png":
                    this.cesta = "files/balonik2.png";
                    this.setObrazok("files/balonik2.png");
                    break;
            }
        }
    }
}
