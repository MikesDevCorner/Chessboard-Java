/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figur;

import gui.Feld;
import figur.bewegungsmuster.TurmBewegungsMuster;

/**
 *
 * @author anaki
 */
public class TurmFigur extends AbstractFigur {
    
    public TurmFigur(Feld feld, boolean schwarz) {
        super(feld, schwarz);
        this.figurSymbol = "\u2656";
        this.setBewegungsMuster(new TurmBewegungsMuster(this));
    }
    
}
