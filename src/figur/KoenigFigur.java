/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figur;

import figur.AbstractFigur;
import figur.bewegungsmuster.KoenigBewegungsMuster;
import gui.Feld;

/**
 *
 * @author anaki
 */
public class KoenigFigur extends AbstractFigur {

    public KoenigFigur(Feld feld, boolean schwarz) {
        super(feld, schwarz);
        this.figurSymbol = "\u2655";
        this.setBewegungsMuster(new KoenigBewegungsMuster(this));
    }
    
}
