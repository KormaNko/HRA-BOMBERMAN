package triedy.mechanika;

import java.awt.*;
import java.io.IOException;

import fri.shapesge.BlokTextu;
import fri.shapesge.Manazer;
import fri.shapesge.StylFontu;
import triedy.bonus.Bonus;
import triedy.bonus.BonusRychlost;
import triedy.bonus.BonusZivot;
import triedy.mapa.Mapa;
import triedy.mapa.StavPolicka;
import triedy.menu.KoniecMenu;
import triedy.pohyb.Hrac;
import triedy.pohyb.Poloha;
import triedy.pohyb.Smer;
import triedy.zombici.Balonik;
import triedy.zombici.Duch;
import triedy.zombici.Zaba;
import triedy.zombici.Zombik;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**@Matus Korman
 * Trieda triedy.mechanika.Hra reprezentuje herné prostredie stara sa o ovladanie hraca.Kontroluje koliziu balonikov s hracom.
 * Taktiez ma nastarosti stav hry a koniec hry.
 */
public class Hra {

    private final Manazer manazer;
    private final Mapa mapa;
    private final Hrac hrac;
    private Smer smer;
    private boolean bombaAktivovana;
    private final ArrayList<Zombik> zombici;
    private final Kolizie kolizie;

    private ArrayList<Bonus> bonusy;
    private StylFontu bold;

    /**
     * 
     * Konštruktor inicializuje herné prostredie a objekty v ňom.
     * <p>
     * throws IOException Ak nastane chyba pri načítaní mapy.
     */
    public Hra(String mapa)  {
        // Inicializácia premenných
        this.smer = Smer.DOLE;
        this.bonusy = new ArrayList<>();
        this.hrac = new Hrac(new Poloha(200, 250, this.smer.getCesta()));
        this.mapa = new Mapa(mapa);
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.zombici = new ArrayList<>();
        try {
            if (mapa.equals("files/m.txt")) {
                this.pripravMapu1();
            } else if (mapa.equals("files/m2.txt")) {
                this.pripravMapu2();
            }
        }catch (IOException e) {
            throw new RuntimeException("Chyba pri náčítani mapy");
        }
        Rectangle obdlznikHraca = new Rectangle(this.hrac.getPolohaX(), this.hrac.getPolohaY(), 32, 38);
        this.kolizie = new Kolizie(obdlznikHraca, this.vytvorObdlznikyBalonikov());
    }

    /**
     * Vytvorí obdĺžniky(hitboxy) pre všetky balóniky.

     */
    private ArrayList<Rectangle> vytvorObdlznikyBalonikov() {
        ArrayList<Rectangle> obdlzniky = new ArrayList<>();
        for (Zombik balonik : this.zombici) {
            obdlzniky.add(new Rectangle(balonik.getZombikaX(), balonik.getZombikaY(), 36, 40));
        }
        return obdlzniky;
    }

    private void pripravMapu1()  {
        try {
            this.zombici.add(new Balonik(355, 800, this.mapa, true));
            this.zombici.add(new Balonik(555, 600, this.mapa, true));
            this.zombici.add(new Balonik(700, 755, this.mapa, false));
            this.zombici.add(new Balonik(55, 555, this.mapa, false));
            this.zombici.add(new Balonik(155, 155, this.mapa, false));
            this.zombici.add(new Balonik(355, 55, this.mapa, false));
            this.zombici.add(new Balonik(850, 850, this.mapa, true));
            this.zombici.add(new Duch(850, 150, this.mapa, this.hrac));
            this.bonusy.add(new BonusZivot(640, 700));
            this.bonusy.add(new BonusZivot(250, 250));
        }catch (IOException e) {
            throw new RuntimeException("Chyba pri načítani mapy.");
        }
    }

    private void pripravMapu2() throws IOException {
        try {
            this.zombici.add(new Balonik(850, 500, this.mapa, true));
            this.zombici.add(new Balonik(250, 350, this.mapa, false));
            this.zombici.add(new Duch(850, 100, this.mapa, this.hrac));
            this.zombici.add(new Zaba(200, 850, this.mapa, this.hrac));
            this.zombici.add(new Zaba(750, 50, this.mapa, this.hrac));
            this.zombici.add(new Zaba(50, 50, this.mapa, this.hrac));
            this.zombici.add(new Zaba(750, 600, this.mapa, this.hrac));
            this.zombici.add(new Zaba(700, 750, this.mapa, this.hrac));
            this.bonusy.add(new BonusZivot(100, 750));
            this.bonusy.add(new BonusRychlost(400, 50));
            this.bonusy.add(new BonusZivot(850, 750));
        }catch (IOException e) {
            throw new RuntimeException("Chyba pri načítani mapy.");
        }
    }

    /**
     * Posunie hráča smerom hore, ak je to možné.
     */
    public void posunHraca(Smer smer) {
        if (!this.hrac.isHracZivy()) {
            return;
        }

        int hracX = this.hrac.getPolohaX();
        int hracY = this.hrac.getPolohaY();

        boolean mozeSaPohnut = switch (smer) {
            case HORE -> this.mapa.getPolicko(hracX, hracY - 5) == StavPolicka.TRAVA &&
                    this.mapa.getPolicko(hracX + 27, hracY - 5) == StavPolicka.TRAVA;
            case DOLE -> this.mapa.getPolicko(hracX + 20, hracY + 44) == StavPolicka.TRAVA;
            case VLAVO -> this.mapa.getPolicko(hracX - 8, hracY + 38) == StavPolicka.TRAVA &&
                    this.mapa.getPolicko(hracX, hracY) == StavPolicka.TRAVA;
            case VPRAVO -> this.mapa.getPolicko(hracX + 40, hracY + 38) == StavPolicka.TRAVA &&
                    this.mapa.getPolicko(hracX + 32, hracY) == StavPolicka.TRAVA;
            default -> false;
        };

        if (mozeSaPohnut) {
            this.hrac.vynulujCisloAnimacieHrace(smer, this.smer);
            this.smer = smer;
            this.hrac.animaciaHraca(smer);
            this.hrac.setPoloha(this.getNovaPoloha(smer));
        }
    }

    public void posunHore() {
        posunHraca(Smer.HORE);
    }

    public void posunDole() {
        posunHraca(Smer.DOLE);
    }

    public void posunVlavo() {
        posunHraca(Smer.VLAVO);
    }

    public void posunVpravo() {
        posunHraca(Smer.VPRAVO);
    }


    /**
     * Aktivuje bombu, ak ešte nebola aktivovaná a hráč je nažive.
     */
    public void aktivujBombu() {
        if (!this.bombaAktivovana && this.hrac.isHracZivy()) {
            Bomba bomba = new Bomba((this.hrac.getPolohaX() + 15) / 50 * 50 + 12, (this.hrac.getPolohaY() + 15) / 50 * 50 + 12, this.mapa, this.hrac, this.zombici);
            this.bombaAktivovana = true;
            bomba.vybuchBombySOneskorenim();

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(() -> this.bombaAktivovana = false, 7, TimeUnit.SECONDS);
        }
    }

    public void aktivujBonus() {
        Iterator<Bonus> iterator = this.bonusy.iterator();
        while (iterator.hasNext()) {
            Bonus bonus = iterator.next();
            int vzdialenostY = Math.abs(this.hrac.getPolohaY() - bonus.getY());
            int vzdialenostX = Math.abs(this.hrac.getPolohaX() - bonus.getX());
            if (vzdialenostX <= 70 && vzdialenostY <= 70) {
                bonus.aktivuj(this.hrac);
                BlokTextu blokTextu = new BlokTextu("Zobral si totem " + bonus.getNazov() + ". " + bonus.getVypis(), 50, 25);
                blokTextu.zobraz();
                blokTextu.zmenFont("Times New Roman", this.bold, 30);
                blokTextu.zmenFarbu("green");
                iterator.remove();

                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.schedule(() -> blokTextu.skry(), 5, TimeUnit.SECONDS);
                scheduler.schedule(() -> scheduler.shutdown(), 6, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * Sluzi na to aby nebolo mozne aktivovat novu bombu pokial aktualna este nevybuchla.
     */


    /**
     * Vráti novú polohu hráča na základe zvoleného smeru.
     * <p>
     * parameter Zvolený smer pohybu hráča.    
     */
    private Poloha getNovaPoloha(Smer smer) {
        Poloha novaPoloha = this.hrac.getPoloha();
        return novaPoloha.getPosunutuPolohu(this.hrac.getVelkostKroku(), smer);
    }

    /**
     * Pohne hráča a aktualizuje Obdlznik ktory je okolo hraca na novu poziciu hraca.
     */
    public void vytvaranieHitboxov() {
        this.hrac.zobrazHraca();
        if (this.hrac.isHracZivy() && this.kolizie != null) {
            Rectangle novyObdlznikHraca = new Rectangle(this.hrac.getPolohaX(), this.hrac.getPolohaY(), 35, 40);
            this.kolizie.aktualizujObdlznikHraca(novyObdlznikHraca);
        }
    }

    /**
     * Kontroluje kolíziu medzi hráčom a balónikmi.
     */
    public void kontrolaKolizie() {


        if (this.kolizie != null) {
            ArrayList<Rectangle> obdlznikyBalonikov = new ArrayList<>();
            for (Zombik balonik : this.zombici) {
                if(balonik.isZombikZivy()) {
                    Rectangle noveObdlznikBalonika = new Rectangle(balonik.getZombikaX(), balonik.getZombikaY(), 40, 46);
                    obdlznikyBalonikov.add(noveObdlznikBalonika);
                }
            }
            this.kolizie.aktualizujObdlznikyBalonikov(obdlznikyBalonikov);
            if (this.hrac.isHracZivy() && this.kolizie.kontrolaKolizie()) {
                if(!hrac.isBonusovyZivot()) {
                    this.smer = Smer.SMRTHRACA;
                    this.hrac.smrtHrca();
                }




            }
        }
    }

    /**
     * Kontroluje stav hry - ukončenie v prípade víťazstva alebo prehry.
     */
    public  void  kontrolaStavuHry() {
        KoniecMenu finish;
        if (this.zombici.size() == 0) {
            finish = new KoniecMenu(this.zombici, "files/win.png");
            finish.skryZvysneBaloniky();
            this.manazer.prestanSpravovatObjekt(this); 
        }
        if (!this.hrac.isHracZivy()) {
            this.hrac.zobrazHraca();
            finish = new KoniecMenu(this.zombici, "files/gameover.png");

            for (Zombik balonik : this.zombici) {
                balonik.prestanSpravovat();               
            }
            this.manazer.prestanSpravovatObjekt(this);
        }
    }   
}
