/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figur;

import figur.bewegungsmuster.LaeuferBewegungsMuster;
import figur.bewegungsmuster.SpringerBewegungsMuster;
import gui.Feld;

/**
 *
 * @author anaki
 */
public class SpringerFigur extends AbstractFigur {

    public SpringerFigur(Feld feld, boolean schwarz) {
        super(feld, schwarz);
        this.figurSymbol = "\u2658";
        this.setBewegungsMuster(new SpringerBewegungsMuster(this));
    }
    
}
