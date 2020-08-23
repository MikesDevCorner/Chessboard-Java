/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figur;

import figur.bewegungsmuster.KoenigBewegungsMuster;
import figur.bewegungsmuster.KoeniginBewegungsMuster;
import gui.Feld;

/**
 *
 * @author anaki
 */
public class KoeniginFigur extends AbstractFigur {

    public KoeniginFigur(Feld feld, boolean schwarz) {
        super(feld, schwarz);
        this.figurSymbol = "\u2654";
        this.setBewegungsMuster(new KoeniginBewegungsMuster(this));
    }
    
}
