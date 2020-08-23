/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figur;

import figur.bewegungsmuster.LaeuferBewegungsMuster;
import gui.Feld;

/**
 *
 * @author anaki
 */
public class LaeuferFigur extends AbstractFigur {

    public LaeuferFigur(Feld feld, boolean schwarz) {
        super(feld, schwarz);
        this.figurSymbol = "\u2657";
        this.setBewegungsMuster(new LaeuferBewegungsMuster(this));
    }
    
}
