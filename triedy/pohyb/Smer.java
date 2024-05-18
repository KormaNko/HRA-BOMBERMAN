package triedy.pohyb;

/**
 * Enum reprezentuje rôzne smerové vektory a k nim priradené obrázky.
 * Trieda triedy.pohyb.Smer definuje rôzne smerové vektory a priradzuje k nim cesty k obrázkom.
 * Napríklad, triedy.pohyb.Smer HORE má súradnicu x 0, súradnicu y -1 a cestu k obrázku hráča, ktorý sa pohybuje hore.
 */
public enum Smer {
    HORE,   // Smer HORE má súradnicu x 0, súradnicu y -1 a cestu k obrázku hráča, ktorý sa pohybuje hore
    DOLE,   // Smer DOLE má súradnicu x 0, súradnicu y 1 a cestu k obrázku hráča, ktorý sa pohybuje dole
    VPRAVO, // Smer VPRAVO má súradnicu x 1, súradnicu y 0 a cestu k obrázku hráča, ktorý sa pohybuje vpravo
    VLAVO,  // Smer VLAVO má súradnicu x -1, súradnicu y 0 a cestu k obrázku hráča, ktorý sa pohybuje vľavo
    STOJ,   // Smer STOJ má súradnicu x 0, súradnicu y 0
    SMRTHRACA;  // Smer SMRTHRACA má súradnicu x 0, súradnicu y 0 a cestu k obrázku mŕtveho hráča

    /**
     * Vracia súradnicu X.
     *
     * @return Súradnica X
     */
    public int getX() {
        return switch (this) {
            case VPRAVO -> 1;
            case VLAVO -> -1;
            default -> 0;
        };
    }

    /**
     * Vracia súradnicu Y.
     *
     * @return Súradnica Y
     */
    public int getY() {
        return switch (this) {
            case HORE -> -1;
            case DOLE -> 1;
            default -> 0;
        };
    }

    /**
     * Vracia cestu k obrázku.
     *
     * @return Cesta k obrázku
     */
    public String getCesta() {
        return switch (this) {
            case HORE -> "HORE";
            case DOLE -> "DOLE";
            case VPRAVO -> "VPRAVO";
            case VLAVO -> "VLAVO";
            case SMRTHRACA -> "MRTVOLAHRACA";
            default -> null;
        };
    }
}
