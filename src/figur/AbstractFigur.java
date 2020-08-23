/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figur;

import gui.*;
import figur.bewegungsmuster.AbstractMuster;

/**
 *
 * @author anaki
 */
public abstract class AbstractFigur {
    
    protected AbstractMuster BewegungsMuster;
    protected Feld feld;
    protected boolean schwarz;
    protected String figurSymbol = "";
    protected boolean amZug;
    private boolean firstMove;
    
    
    public AbstractFigur (Feld feld, boolean schwarz) {
        this.feld = feld;
        this.schwarz = schwarz;
        this.amZug = false;
        this.firstMove = true;
    }
    
    public void setBewegungsMuster(AbstractMuster muster) {
        this.BewegungsMuster = muster;
    }
    
    public void setFeld(Feld feld, boolean imSpiel){
        this.feld = feld;
        if(imSpiel) this.firstMove = false;
    }


    public Feld getFeld() {
        return this.feld;
    }
    
    public String getSymbol() {
        return figurSymbol;
    }
    
    public boolean getSchwarz(){

        return schwarz;
    }

    public boolean pruefeObAmZug() {
        return amZug;
    }

    public void setzeAmZug() {
        Brett brett = this.feld.getBrett();
        brett.alleFigurZuegeBeendenUndFelderUnmarkieren();
        this.amZug = true;
        brett.markiereBewegungsOptionen();
    }

    public void setzeNichtAmZug() {
        this.amZug = false;
    }

    public boolean pruefeObErsterZug() {
        return this.firstMove;
    }


    public AbstractMuster GetBewegungsMuster() {
        return BewegungsMuster;
    }
    
}
