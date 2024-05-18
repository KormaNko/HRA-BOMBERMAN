package triedy.zombici;

import fri.shapesge.Obrazok;
import triedy.mapa.Mapa;
import triedy.mapa.StavPolicka;
import triedy.pohyb.Hrac;


import java.io.IOException;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zaba extends Zombik{
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
    private  int y;
    private final Mapa mapa;
    private String cesta;
    private Hrac hrac;
    private final int  velkostPohybu = 1;
    public Zaba(int x, int y, Mapa mapa, Hrac hrac) throws IOException {
        super(x, y, mapa, "files/ZABIAK1.png");
        this.x = x;
        this.y = y;
        this.mapa = mapa;
        this.cesta = "files/ZABIAK1.png";

        this.hrac = hrac;
    }
    private void vybchni() {
        this.zabiZombika();
        Timer delayBeforeExplosion = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Obrazok explozia = new Obrazok("files/EXPLOZIA/EXPLOZIA0.png", Zaba.this.x - 100, Zaba.this.y - 100);
                explozia.zobraz();
                Timer animationTimer = new Timer(100, new ActionListener() {
                    private int index = 1;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (index < 12) {
                            explozia.zmenObrazok("files/EXPLOZIA/EXPLOZIA" + index + ".png");
                            index++;
                        } else {
                            ((Timer) e.getSource()).stop();
                            Timer delayBeforeHide = new Timer(1000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    explozia.skry();
                                    Zaba.this.zabiHracaAkJeVExplozii();
                                    ((Timer) e.getSource()).stop();
                                }
                            });
                            delayBeforeHide.setRepeats(false);
                            delayBeforeHide.start();
                        }
                    }
                });
                animationTimer.setRepeats(true);
                animationTimer.start();

                ((Timer) e.getSource()).stop();
            }
        });
        delayBeforeExplosion.setRepeats(false);
        delayBeforeExplosion.start();
    }

    public void zabiHracaAkJeVExplozii() {
        int vzdialenostX = Math.abs(this.hrac.getPolohaX() - this.x);
        int vzdialenostY = Math.abs(this.hrac.getPolohaY() - this.y);
        double vzdialenost = Math.sqrt(vzdialenostX * vzdialenostX + vzdialenostY * vzdialenostY);
        if (vzdialenost <= 100) {
            this.hrac.smrtHrca();
        }
    }
    @Override
    public void pohybZombikaY() {
        if(this.isZombikZivy()) {
            int vzdialenostY = Math.abs(this.hrac.getPolohaY() - this.y);
            int vzdialenostX = Math.abs(this.hrac.getPolohaX() - this.x);
            if (vzdialenostY > 60) {
                if (this.hrac.getPolohaY() > this.y && this.mozemSaPohnut(this.x, this.y + 1)) {
                    this.setPolohuZombika(this.x, this.y + 1);
                    this.y = this.y + 1;
                } else if (this.mozemSaPohnut(this.x, this.y - 1)) {
                    this.setPolohuZombika(this.x, this.y - 1);
                    this.y = this.y - 1;
                }
            }
            if (vzdialenostX <= 60 && vzdialenostY <= 60) {
                this.vybchni();
            }
        }

    }

    @Override
    public void pohybZombikaX() {
        if(this.isZombikZivy()) {
            int vzdialenostY = Math.abs(this.hrac.getPolohaY() - this.y);
            int vzdialenostX = Math.abs(this.hrac.getPolohaX() - this.x);
            if (vzdialenostX > 60) {
                if (this.hrac.getPolohaX() > this.x && this.mozemSaPohnut(this.x + 1, this.y)) {
                    this.setPolohuZombika(this.x + 1, this.y);
                    this.x = this.x + 1;
                } else if (this.mozemSaPohnut(this.x - 1, this.y)) {
                    this.setPolohuZombika(this.x - 1, this.y);
                    this.x = this.x - 1;
                }
            }
            if (vzdialenostX <= 60 && vzdialenostY <= 60) {
                this.vybchni();
            }
        }
    }



    @Override
    public void zmenObrazok() {
        this.setObrazok("files/ZABIAK5.png");
        this.skryZombika();
    }

    @Override
    public void animaciaZombika() {
        if(this.isZombikZivy()){
            switch (this.cesta) {
                case "files/ZABIAK1.png":
                    this.setObrazok("files/ZABIAK2.png");
                    this.cesta = "files/ZABIAK2.png";
                    break;
                case "files/ZABIAK2.png":
                    this.setObrazok("files/ZABIAK3.png");
                    this.cesta = "files/ZABIAK3.png";
                    break;
                case "files/ZABIAK3.png":
                    this.setObrazok("files/ZABIAK4.png");
                    this.cesta = "files/ZABIAK4.png";
                    break;
                case "files/ZABIAK4.png":
                    this.setObrazok("files/ZABIAK1.png");
                    this.cesta = "files/ZABIAK1.png";
                    break;
            }
        }
    }

    private boolean mozemSaPohnut(int x, int y){
        if(this.mapa.getPolicko(x, y) != StavPolicka.TRAVA){
            return false;
        }
        if(this.mapa.getPolicko(x + 30, y) != StavPolicka.TRAVA){
            return false;
        }
        if(this.mapa.getPolicko(x, y + 30) != StavPolicka.TRAVA){
            return false;
        }
        return this.mapa.getPolicko(x + 30, y + 30) == StavPolicka.TRAVA;
    }
}
