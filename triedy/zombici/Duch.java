package triedy.zombici;

import triedy.pohyb.Hrac;
import triedy.mapa.Mapa;
import triedy.mapa.StavPolicka;

import java.io.IOException;

/**
 * Trieda reprezentuje ducha v hre.
 */
public class Duch extends Zombik {
    private int x; // Súradnica x ducha
    private int y; // Súradnica y ducha
    private final Mapa mapa; // Mapa, na ktorej sa duch pohybuje
    private final Hrac hrac; // Referencia na hráča

    /**
     * Konštruktor pre vytvorenie ducha na základe súradníc, mapy a hráča.
     *
     * @param x     Súradnica x ducha
     * @param y     Súradnica y ducha
     * @param mapa  Mapa, na ktorej sa duch pohybuje
     * @param hrac  Referencia na hráča
     */
    public Duch(int x, int y, Mapa mapa, Hrac hrac) throws IOException {
        super(x, y, mapa, "files/ROVNODUCH.png");
        this.x = x;
        this.y = y;
        this.mapa = mapa;
        this.hrac = hrac;
    }

    /**
     * Vykoná pohyb ducha po osi Y smerom k hráčovi.
     */
    @Override
    public void pohybZombikaY() {
        if (this.hrac.getPolohaY() > this.y && this.mozemSaPohnut(this.x, this.y + 1)) {
            this.setPolohuZombika(this.x, this.y + 1);
            this.y = this.y + 1;
        } else if (this.hrac.getPolohaY() < this.y && this.mozemSaPohnut(this.x, this.y - 1)) {
            this.setPolohuZombika(this.x, this.y - 1);
            this.y = this.y - 1;
        }
    }

    /**
     * Vykoná pohyb ducha po osi X smerom k hráčovi.
     */
    @Override
    public void pohybZombikaX() {
        if (this.hrac.getPolohaX() > this.x && this.mozemSaPohnut(this.x + 1, this.y)) {
            this.setPolohuZombika(this.x + 1, this.y);
            this.x = this.x + 1;
        } else if (this.hrac.getPolohaX() < this.x && this.mozemSaPohnut(this.x - 1, this.y)) {
            this.setPolohuZombika(this.x - 1, this.y);
            this.x = this.x - 1;
        }
    }

    /**
     * Zmení obrázok ducha na obrázok, keď sa pohybuje smerom dolu.
     */
    @Override
    public void zmenObrazok() {
        this.setObrazok("files/DOLEDUCH.png");
    }

    /**
     * Vykoná animáciu ducha podľa smeru, ktorým sa pohybuje.
     */
    @Override
    public void animaciaZombika() {
        if (this.isZombikZivy()) {
            if (this.hrac.getPolohaX() > this.x && this.hrac.getPolohaY() < this.y) {
                this.setObrazok("files/ROVNOVPRAVODUCH.png");
            } else if (this.hrac.getPolohaX() < this.x && this.hrac.getPolohaY() < this.y) {
                this.setObrazok("files/ROVNOVLAVODUCH.png");
            } else if (this.hrac.getPolohaX() < this.x && this.hrac.getPolohaY() > this.y) {
                this.setObrazok("files/DOLEVLAVODUCH.png");
            } else if (this.hrac.getPolohaX() > this.x && this.hrac.getPolohaY() > this.y) {
                this.setObrazok("files/DOLEVPRAVODUCH.png");
            } else if (this.hrac.getPolohaY() < this.y) {
                this.setObrazok("files/ROVNODUCH.png");
            } else if (this.hrac.getPolohaY() > this.y) {
                this.setObrazok("files/DOLEDUCH.png");
            } else if (this.hrac.getPolohaX() < this.x) {
                this.setObrazok("files/VLAVODUCH.png");
            } else if (this.hrac.getPolohaX() > this.x) {
                this.setObrazok("files/VPRAVODUCH.png");
            }
        }
    }

    /**
     * Kontroluje, či sa duch môže pohnúť na zadané súradnice.
     *
     * @param x Súradnica x, kam sa má duch pohnúť
     * @param y Súradnica y, kam sa má duch pohnúť
     * @return true, ak sa duch môže pohnúť na zadané súradnice, inak false
     */
    private boolean mozemSaPohnut(int x, int y) {
        if (this.mapa.getPolicko(x, y) == StavPolicka.PEVNASTENA) {
            return false;
        }
        if (this.mapa.getPolicko(x + 34, y) == StavPolicka.PEVNASTENA) {
            return false;
        }
        if (this.mapa.getPolicko(x, y + 34) == StavPolicka.PEVNASTENA) {
            return false;
        }
        return this.mapa.getPolicko(x + 34, y + 34) != StavPolicka.PEVNASTENA;
    }
}
