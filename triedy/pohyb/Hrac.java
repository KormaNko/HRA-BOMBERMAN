package triedy.pohyb;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**@Matus Korman
 * Trieda reprezentuje hráča v hre.
 */
public class Hrac {
    private  Poloha poloha;          // Aktuálna poloha hráča
    private final Obrazok postava;   // Obrázok hráča
    private boolean hracJeZivy;      // Určuje, či je hráč nažive
    private int cisloAnimaciaHraca;  // Číslo aktuálnej animácie hráča
    private boolean bonusovyZivot;   // Určuje, či má hráč bonusový život
    private  int velkostKroku;       // Veľkosť kroku hráča pri pohybe

    /**
     * Konštruktor pre vytvorenie hráča na základe počiatočnej polohy.
     *
     * @param poloha Počiatočná poloha hráča
     */
    public Hrac(Poloha poloha) {
        // Manazer pre interakciu s hráčom
        Manazer manazer = new Manazer(); // Inicializácia manazéra
        manazer.spravujObjekt(this); // Pripojenie hráča k manazeru
        this.hracJeZivy = true; // Nastavenie stavu hráča na "nažive"
        this.poloha = poloha; // Nastavenie počiatočnej polohy hráča
        this.postava = new Obrazok("files/"+poloha.getCesta() + "1.png" ); // Vytvorenie obrázka hráča
        this.postava.zmenPolohu(poloha.getX(), poloha.getY()); // Nastavenie počiatočnej polohy obrázka
        this.postava.zobraz(); // Zobrazenie obrázka hráča
        this.cisloAnimaciaHraca = 0; // Inicializácia čísla animácie hráča
        this.velkostKroku = 7; // Nastavenie veľkosti kroku hráča
    }

    /**
     * Získa aktuálnu polohu hráča.
     *
     * @return Aktuálna poloha hráča
     */
    public Poloha getPoloha() {
        return this.poloha;
    }

    /**
     * Získa súradnicu x aktuálnej polohy hráča.
     *
     * @return Súradnica x aktuálnej polohy hráča
     */
    public int getPolohaX() {
        return this.poloha.getX();
    }

    /**
     * Získa súradnicu y aktuálnej polohy hráča.
     *
     * @return Súradnica y aktuálnej polohy hráča
     */
    public int getPolohaY() {
        return this.poloha.getY();
    }

    /**
     * Zvýši počet životov hráča (použije sa bonusový život).
     */
    public void zvysZviot() {
        this.bonusovyZivot = true;
    }

    /**
     * Získa veľkosť kroku hráča.
     *
     * @return Veľkosť kroku hráča
     */
    public int getVelkostKroku() {
        return velkostKroku;
    }

    /**
     * Nastaví veľkosť kroku hráča.
     *
     * @param velkostKroku Nová veľkosť kroku hráča
     */
    public void setVelkostKroku(int velkostKroku) {
        this.velkostKroku = velkostKroku;
    }

    /**
     * Odstráni bonusový život po určitom čase.
     */
    public void odoberBonusovyZivotWithDelay() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // Vytvorenie plánovača úloh
        // Plánované odstránenie bonusového života po 5 sekundách
        scheduler.schedule(() -> {
            this.bonusovyZivot = false;
        }, 5, TimeUnit.SECONDS);
        scheduler.shutdown(); // Ukončenie plánovača úloh po dokončení
    }

    /**
     * Overí, či má hráč aktuálne bonusový život.
     *
     * @return True, ak má hráč bonusový život, inak false
     */
    public boolean isBonusovyZivot() {
        // Ak má hráč bonusový život
        if(this.bonusovyZivot) {
            // Zobrazenie efektu bonusového života
            Obrazok obrazok = new Obrazok("files/FLASH.png", -150, -150);
            obrazok.zobraz();

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // Vytvorenie plánovača úloh
            // Plánované skrytie efektu bonusového života po 2 sekundách
            scheduler.schedule(obrazok::skry, 2, TimeUnit.SECONDS);
            // Ukončenie plánovača úloh po 6 sekundách
            scheduler.schedule(scheduler::shutdown, 6, TimeUnit.SECONDS);
            odoberBonusovyZivotWithDelay(); // Odstránenie bonusového života po určitom čase
        }

        return this.bonusovyZivot; // Vrátenie hodnoty, či má hráč bonusový život
    }

    /**
     * Nastaví obrázok pre hráča po jeho smrti.
     */
    public void smrtHrca() {
        // Zmena obrázka na obrázok mŕtveho hráča
        this.postava.zmenObrazok("files/MRTVOLAHRACA.png");
        this.hracJeZivy = false; // Nastavenie
        // Nastavenie stavu hráča na "mŕtvy"
    }

    /**
     * Overí, či je hráč nažive.
     *
     * @return True, ak je hráč nažive, inak false
     */
    public boolean isHracZivy() {
        return this.hracJeZivy; // Vrátenie stavu hráča
    }

    /**
     * Nastaví novú polohu pre hráča a aktualizuje obrázok.
     *
     * @param poloha Nová poloha hráča
     */
    public void setPoloha(Poloha poloha) {
        this.postava.zmenPolohu(poloha.getX(), poloha.getY()); // Zmena polohy obrázka na novú polohu
        this.poloha = poloha; // Aktualizácia aktuálnej polohy hráča
    }

    /**
     * Vytvára animáciu hráča.
     *
     * @param smer Smer, do ktorého sa hráč pohybuje
     */
    public void animaciaHraca(Smer smer) {
        this.cisloAnimaciaHraca = (this.cisloAnimaciaHraca % 3) + 1; // Cyklická zmena čísla animácie hráča
        this.postava.zmenObrazok("files/"+smer.getCesta() + this.cisloAnimaciaHraca + ".png"); // Zmena obrázka hráča
    }

    /**
     * Skryje a znovu zobrazí hráča.
     */
    public void zobrazHraca() {
        this.postava.skry(); // Skrytie obrázka hráča
        this.postava.zobraz(); // Zobrazenie obrázka hráča
    }

    /**
     * Vynuluje číslo animácie hráča, ak sa mení jeho smer pohybu.
     *
     * @param novySmer Nový smer pohybu hráča
     * @param smer Aktuálny smer pohybu hráča
     */
    public void vynulujCisloAnimacieHrace(Smer novySmer, Smer smer) {
        // Ak sa mení smer pohybu hráča
        if (smer != novySmer) {
            this.cisloAnimaciaHraca = 0; // Nastavenie čísla animácie hráča na začiatok
        }
    }
}
