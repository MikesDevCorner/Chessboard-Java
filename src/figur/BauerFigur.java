/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figur;

import figur.bewegungsmuster.BauerBewegungsMuster;
import figur.bewegungsmuster.TurmBewegungsMuster;
import gui.Feld;

/**
 *
 * @author anaki
 */
public class BauerFigur extends AbstractFigur {

    public BauerFigur(Feld feld, boolean schwarz) {
        super(feld, schwarz);
        this.figurSymbol = "\u2659";
        this.setBewegungsMuster(new BauerBewegungsMuster(this));
    }
    
}
