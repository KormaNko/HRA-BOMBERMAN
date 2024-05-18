package triedy.zombici;
import triedy.pohyb.Hrac;
import triedy.mapa.Mapa;
import triedy.mapa.StavPolicka;
import java.io.IOException;
public class Duch extends Zombik {
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
     * @param cesta
     */
    private int x;
    private int y;
    private final Mapa mapa;
    private final Hrac hrac;
    public Duch(int x, int y, Mapa mapa, Hrac hrac) throws IOException {
        super(x, y, mapa, "files/ROVNODUCH.png");
        this.x = x;
        this.y = y;
        this.mapa = mapa;
        this.hrac =   hrac;
    }

    @Override
    public void pohybZombikaY() {
        if(this.hrac.getPolohaY() > this.y && this.mozemSaPohnut(this.x, this.y + 1)){
            this.setPolohuZombika(this.x, this.y + 1);
            this.y = this.y + 1;
        }else if(this.hrac.getPolohaY() < this.y && this.mozemSaPohnut(this.x, this.y - 1)){
            this.setPolohuZombika(this.x, this.y - 1) ;
            this.y = this.y - 1;
        }
    }

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


    @Override
    public void zmenObrazok() {
        this.setObrazok("files/DOLEDUCH.png");

    }

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
    private boolean mozemSaPohnut(int x, int y){
        if(this.mapa.getPolicko(x, y) == StavPolicka.PEVNASTENA){
            return false;
        }
        if(this.mapa.getPolicko(x + 34, y) == StavPolicka.PEVNASTENA){
            return false;
        }
        if(this.mapa.getPolicko(x, y + 34) == StavPolicka.PEVNASTENA){
            return false;
        }
        return this.mapa.getPolicko(x + 34, y + 34) != StavPolicka.PEVNASTENA;
    }


}




