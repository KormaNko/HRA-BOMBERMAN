package triedy.mapa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**@Matus Korman
 * Trieda reprezentuje mapu hry so stavom jednotlivých políčok.
 */
public class Mapa {
    private final StavPolicka[][] hraciaPlocha;  // Dvojrozmerné pole reprezentujúce hraciu plochu

    /**
     * Konštruktor pre vytvorenie mapy na základe textoveho súboru s mapou.
     * <p>
     * parameter  Názov textoveho súboru s mapou
     * throws IOException Ak sa vyskytne chyba pri čítaní súboru
     */
    public Mapa(String nazovMapy)  {
        File suborMapy = new File(nazovMapy);  // Vytvoríme objekt reprezentujúci súbor s mapou
        Scanner skener;  // Inicializujeme skener pre čítanie zo súboru
        try {
            skener = new Scanner(suborMapy);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Neúspesne nájdenie súboru s mapou");
        }
        this.hraciaPlocha = new StavPolicka[19][19];  // Inicializujeme dvojrozmerné pole pre hraciu plochu

        // Načítame hodnoty s textoveho suboru a inicializujeme políčka na hracej ploche
        for (int r = 0; r < 19; r++) {
            for (int s = 0; s < 19; s++) {
                int hodnota = skener.nextInt();
                if (hodnota == 0) {
                    Policko pevnaStena = new Policko(r, s, "files/pevnaStena.png");
                    this.hraciaPlocha[r][s] = StavPolicka.PEVNASTENA;
                } else if (hodnota == 1) {
                    Policko slabaStena = new Policko(r, s, "files/slabaStena.png");
                    this.hraciaPlocha[r][s] = StavPolicka.SLABASTENA;
                } else if (hodnota == 3) {
                    Policko slabaStena = new Policko(r, s, "files/SlabaStena2.png");
                    this.hraciaPlocha[r][s] = StavPolicka.SLABASTENA;
                }
                    else{
                Policko trava = new Policko(r, s, "files/trava.jpg");
                this.hraciaPlocha[r][s] = StavPolicka.TRAVA;

            }
        }
        }
    }

    /**
     * Vráti stav políčka na zadaných súradniciach.
     * parameter Súradnica x políčka
     * parameter Súradnica y políčka
     * Vrati stav políčka na zadaných súradniciach
     */
    public StavPolicka getPolicko(int x, int y) {
        return this.hraciaPlocha[x / 50][y / 50];
    }

    /**
     * Zmení stav políčka na zadaných súradniciach na TRAVA a vytvorí nové políčko so stavom TRAVA.
     * parameter  súradnica x políčka
     * parameter  súradnica y políčka
     */
    public void  zmenaPolickoNaTravu(int x, int y) {
        this.hraciaPlocha[x / 50][y / 50] = StavPolicka.TRAVA;
        Policko policko = new Policko(x / 50, y / 50, "files/trava.jpg");
    }
}
