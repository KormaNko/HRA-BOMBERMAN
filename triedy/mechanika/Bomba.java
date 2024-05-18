package triedy.mechanika;

import fri.shapesge.Obrazok;
import triedy.mapa.Mapa;
import triedy.mapa.StavPolicka;
import triedy.pohyb.Hrac;
import triedy.zombici.Zombik;

import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

/**
 * Trieda reprezentuje bombu, ktorá môže explodovať a zabíjať veci, ktoré má v dosahu.
 * <p>
 * V tejto triede používam časovač z Javadoc dokumentácie (Java Class Libraries).
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html">...</a>!
 * </p>
 */
public class Bomba {
    private static Obrazok bomba;             // Obrázok bomby
    private final int bombaX;                 // Súradnica x bomby
    private final int bombaY;                 // Súradnica y bomby
    private Explozia explozia;                // Objekt explózie spojenej s bombou
    private final Mapa mapa;                  // Mapa, na ktorej sa bomba nachádza
    private final Hrac hrac;                  // Hráč, ktorého môže bomba zabiť
    private final ArrayList<Zombik> zombici;  // Zoznam zombíkov, ktorých môže bomba zabiť

    /**
     * Konštruktor pre vytvorenie bomby na zadaných súradniciach.
     *
     * @param x       Súradnica x bomby
     * @param y       Súradnica y bomby
     * @param mapa    Mapa, na ktorej sa bomba nachádza
     * @param hrac    Hráč, ktorého môže bomba zabiť
     * @param zombici Zoznam zombíkov, ktorých môže bomba zabiť
     */
    public Bomba(int x, int y, Mapa mapa, Hrac hrac, ArrayList<Zombik> zombici) {
        this.bombaX = x;
        this.bombaY = y;
        bomba = new Obrazok("files/bomba.png", x, y);
        bomba.zobraz();
        this.mapa = mapa;
        this.hrac = hrac;
        this.zombici = zombici;
    }

    /**
     * Skryje obrázok bomby.
     */
    public void skry() {
        bomba.skry();
    }

    /**
     * Získa súradnicu x bomby.
     *
     * @return súradnica x bomby
     */
    public int getBombaX() {
        return this.bombaX;
    }

    /**
     * Získa súradnicu y bomby.
     *
     * @return súradnica y bomby
     */
    public int getBombaY() {
        return this.bombaY;
    }

    /**
     * Spustí explóziu bomby s oneskorením.
     */
    public void vybuchBombySOneskorenim() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> {
            bomba.skry();
            this.explozia = new Explozia(this.getBombaX() / 50 * 50, this.getBombaY() / 50 * 50);
            this.skryExploziu();
            int bombaX = this.getBombaX();
            int bombaY = this.getBombaY();
            skontrolUkazExploziu(bombaX, bombaY - 50, "hore");
            skontrolUkazExploziu(bombaX, bombaY + 50, "dole");
            skontrolUkazExploziu(bombaX + 60, bombaY, "vpravo");
            skontrolUkazExploziu(bombaX - 30, bombaY, "vlavo");
            this.bombaZabiVsetko();
        }, 5, TimeUnit.SECONDS);
        executor.shutdown();
    }

    /**
     * Skontroluje a zobrazí explóziu v danom smere.
     *
     * @param x         Súradnica x
     * @param y         Súradnica y
     * @param direction Smer explózie
     */
    private void skontrolUkazExploziu(int x, int y, String direction) {
        if (this.mapa.getPolicko(x, y) != StavPolicka.PEVNASTENA) {
            this.mapa.zmenaPolickoNaTravu(x, y);
            switch (direction) {
                case "hore":
                    this.explozia.zobrazHore();
                    break;
                case "dole":
                    this.explozia.zobrazDole();
                    break;
                case "vpravo":
                    this.explozia.zobrazVpravo();
                    break;
                case "vlavo":
                    this.explozia.zobrazVlavo();
                    break;
            }
        }
    }

    /**
     * Skryje explóziu s oneskorením.
     */
    private void skryExploziu() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> this.explozia.skryExploziu(), 1, TimeUnit.SECONDS);
        executor.shutdown();
    }

    /**
     * Zabije hráča a zombíkov, ktorí sú v blízkosti bomby.
     */
    private void bombaZabiVsetko() {
        int hracRiadok = this.hrac.getPolohaX() / 50;
        int hracStlpec = this.hrac.getPolohaY() / 50;
        int bombaRiadok = this.getBombaX() / 50;
        int bombaStlpec = this.getBombaY() / 50;

        if (jeVDosahu(hracRiadok, hracStlpec, bombaRiadok, bombaStlpec)) {
            this.hrac.smrtHrca();
        }

        Iterator<Zombik> iterator = this.zombici.iterator();
        while (iterator.hasNext()) {
            Zombik aktBalonik = iterator.next();
            int balonikRiadok = aktBalonik.getZombikaX() / 50;
            int balonikStlpec = aktBalonik.getZombikaY() / 50;
            if (jeVDosahu(balonikRiadok, balonikStlpec, bombaRiadok, bombaStlpec)) {
                aktBalonik.zabiZombika();
                iterator.remove();
                aktBalonik.skryZombika();
            }
        }
    }

    /**
     * Skontroluje, či je pozícia v dosahu výbuchu.
     *
     * @param entityRiadok  Riadok entity
     * @param entityStlpec  Stĺpec entity
     * @param bombaRiadok   Riadok bomby
     * @param bombaStlpec   Stĺpec bomby
     * @return True, ak je v dosahu výbuchu, inak false
     */
    private boolean jeVDosahu(int entityRiadok, int entityStlpec, int bombaRiadok, int bombaStlpec) {
        return entityRiadok == bombaRiadok && (entityStlpec == bombaStlpec || entityStlpec == bombaStlpec - 1 || entityStlpec == bombaStlpec + 1) || entityStlpec == bombaStlpec && (entityRiadok == bombaRiadok - 1 || entityRiadok == bombaRiadok + 1);
    }
}
