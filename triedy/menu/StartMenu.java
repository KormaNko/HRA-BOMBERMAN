package triedy.menu;

import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
import triedy.mechanika.Hra;

import java.io.IOException;


public class StartMenu {
    private Obrazok obrazok;
    private Manazer manazer;

    private boolean hraBezi;

    public StartMenu() {
        this.obrazok = new Obrazok("files/BOMBERMANSTART.png", 0, 0);
        this.obrazok.zobraz();

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        this.hraBezi = false;
    }

    public void vyberSuradnice(int x, int y)  {

        if(x < 475 && !this.hraBezi){
            Hra hra = new Hra("files/m.txt");
            this.obrazok.skry();
            this.hraBezi = true;
        }else if (x < 950 && !this.hraBezi){
            Hra hra = new Hra("files/m2.txt");
            this.obrazok.skry();
            this.hraBezi = true;
        }
    }

}
