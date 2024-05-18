package triedy.zombici;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import triedy.mapa.Mapa;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**@Matus Korman
 * Trieda reprezentuje balonik v hre.
 */
public abstract class Zombik {
    
    private final Manazer manazer;  //Manazer ktory sluzi na ovladanie pohybu balonika
    private final Obrazok zombik;        //Obrazok balonika
    private  int zombikX;           // Suradnica balonika X
    private  int zombikY;           // Suradnica balonika Y
    //Kontrola ci balonik dosiel uz dokonca a ma sa otocit
        //Urcuje ci sa ma balonik pohybovat vertikalne alebo horizontalne
    private boolean isZombikZivy;  //Kontrola ci je balonik nazive

    /**
     * Konštruktor pre vytvorenie zombíka na základe súradníc, mapy a orientácie pohybu.
     *
     * parameter   Súradnica x balonika
     * parameter   Súradnica y balonika
     * parameter   triedy.mapa.Mapa, na ktorej sa balonik pohybuje
     * parameter   Určuje, či sa balonik pohybuje vertikálne (true) alebo horizontálne (false)
     * 
     */
    public Zombik(int x, int y, Mapa mapa, String cesta) throws IOException {
        //triedy.mapa.Mapa po ktorej sa balonik pohybuje
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.zombikX = x;
        this.zombikY = y;

        //Cesta ku obrazku balonika
        this.zombik = new Obrazok(cesta, x, y);

        this.zombik.zobraz();
        this.isZombikZivy = true;
        
    }

    /**
     * Pohyb balonika v smere osi Y (vertikálny pohyb).
     */
    public abstract void pohybZombikaY();

    public boolean isZombikZivy() {
        return isZombikZivy;
    }



    /**
     * Pohyb balonika v smere osi X (horizontálny pohyb).
     */


    public abstract void pohybZombikaX();






    /**
     * Zobrazí obrázok balonika na hracej ploche.
     */
    public void zobrazZombika() {
        this.zombik.zobraz();
    }

    /**
     * Vráti súradnicu x balonika.
     */
    public int getZombikaX() {
        return this.zombikX;
    }

    /**
     * Vráti súradnicu y balonika.
     */
    public int getZombikaY() {
        return this.zombikY;
    }

    /**
     * Nastaví novú polohu pre balonik.
     *
     * parameter x Nová súradnica x
     * parameter y Nová súradnica y
     */
    public void setPolohuZombika(int x, int y) {
        if (this.zombik != null && this.isZombikZivy) {
            this.zombik.zmenPolohu(x, y);
            this.zombik.skry();
            this.zombik.zobraz();
            this.zombikX = x;
            this.zombikY = y;
        }
    }
    
    /**
     * Zabije balonik
     */
    public void zabiZombika() {

        zmenObrazok();
        this.zombik.skry();
        this.prestanSpravovat();
        if(isZombikZivy) {
            this.zobrazZombika();
        }
        this.isZombikZivy = false;
    }

    public abstract void zmenObrazok();


    
    /**
     * Skryje balonik.
     */
    public void skryZombika() {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> {
            this.zombik.skry();
        }, 2, TimeUnit.SECONDS);
        executor.shutdown();
    }
    
    /**
     * Meni obrazky balonika, vytvara animaciu.
     */
    public  abstract void animaciaZombika();


    
    /**
     * Manazer prestava spravovat tento objekt.
     */
    public void prestanSpravovat() {
        this.manazer.prestanSpravovatObjekt(this);
    }

    public void setObrazok(String obrazok) {
        this.zombik.zmenObrazok(obrazok);
    }
}