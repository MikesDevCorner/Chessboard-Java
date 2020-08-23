/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figur.bewegungsmuster;

import figur.AbstractFigur;
import gui.Feld;

import java.util.ArrayList;


/**
 *
 * @author anaki
 */
public class LaeuferBewegungsMuster extends AbstractMuster {

    public LaeuferBewegungsMuster(AbstractFigur f) {
        super(f);
    }
    
    public void FuelleAktuelleMoeglichkeiten(Feld[][] felder, int z, int sp) {

        ArrayList<Feld> temp1 = this.MarkiereDiagonal(felder, z, sp, true, true, false, figur);
        ArrayList<Feld> temp2 = this.MarkiereDiagonal(felder, z, sp, true, false, false, figur);
        ArrayList<Feld> temp3 = this.MarkiereDiagonal(felder, z, sp, false, true, false, figur);
        ArrayList<Feld> temp4 = this.MarkiereDiagonal(felder, z, sp, false, false, false, figur);

        Moeglichkeiten = new ArrayList<Feld>();
        Moeglichkeiten.addAll(temp1);
        Moeglichkeiten.addAll(temp2);
        Moeglichkeiten.addAll(temp3);
        Moeglichkeiten.addAll(temp4);
    }
    
}
