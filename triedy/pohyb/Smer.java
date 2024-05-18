package triedy.pohyb;

/**@Matus Korman
*Enum reprezentuje rôzne smerové vektory a k nim patrace obrazky.
*triedy.pohyb.Smer HORE vracia x suradnicu 0 y suradnicu -1 a obrazok hraca iduceho hore.
*triedy.pohyb.Smer DOLE vracia x suradnicu 0 y suradnicu 1 a obrazok hraca iduceho dole.
*triedy.pohyb.Smer VPRAVO vracia x suradnicu 1 y suradnicu 0 a obrazok hraca iduceho vpravo.
*triedy.pohyb.Smer VLAVO vracia x suradnicu -1 y suradnicu  a obrazok hraca iduceho vlavo.
*triedy.pohyb.Smer STOJ vracia x suradnicu 0 y suradnicu 0
*SMRTHRACA;  //triedy.pohyb.Smer SMRTHRACA vracia x suradnicu 0 y suradnicu 0 a obrazok mrtveho hraca.
*/
public enum Smer {
    HORE, //triedy.pohyb.Smer HORE vracia x suradnicu 0 y suradnicu -1 a obrazok hraca iduceho hore
    DOLE,   //triedy.pohyb.Smer DOLE vracia x suradnicu 0 y suradnicu 1 a obrazok hraca iduceho dole
    VPRAVO, //triedy.pohyb.Smer VPRAVO vracia x suradnicu 1 y suradnicu 0 a obrazok hraca iduceho vpravo
    VLAVO,  //triedy.pohyb.Smer VLAVO vracia x suradnicu -1 y suradnicu  a obrazok hraca iduceho vlavo
    STOJ,   //triedy.pohyb.Smer STOJ vracia x suradnicu 0 y suradnicu 0
    SMRTHRACA;  //triedy.pohyb.Smer SMRTHRACA vracia x suradnicu 0 y suradnicu 0 a obrazok mrtveho hraca
    /**
     * Vracia suradnicu X
     */
    public int getX() {
        switch (this) {
            case VPRAVO: 
                return 1;
            case VLAVO:
                return -1;
            default: 
                return 0;

        }
    }
    /**
     * Vracia suradnicu Y
     */
    public int getY() {
        switch (this) {
            case HORE: 
                return -1;
            case DOLE:
                return 1;
            default: 
                return 0;
        }
    }
    /**
     * Vracia obrazok
     */
    public String getCesta() {
        switch (this) {
            case HORE:
                return "HORE";
            case DOLE:
                return "DOLE";
            case VPRAVO:
                return "VPRAVO";
            case VLAVO:
                return "VLAVO";
            case SMRTHRACA:
                return "MRTVOLAHRACA";
            default:
                return null;
        }
         
    }
}
