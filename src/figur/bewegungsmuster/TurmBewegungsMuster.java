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
public class TurmBewegungsMuster extends AbstractMuster {

    public TurmBewegungsMuster(AbstractFigur f) {
        super(f);
    }
    
    public void FuelleAktuelleMoeglichkeiten(Feld[][] felder, int z, int sp) {

        ArrayList<Feld> temp1 = this.MarkiereGerade(felder, z, sp, true, true, false, figur, true);
        ArrayList<Feld> temp2 = this.MarkiereGerade(felder, z, sp, true, false, false, figur, true);
        ArrayList<Feld> temp3 = this.MarkiereGerade(felder, z, sp, false, true, false, figur, true);
        ArrayList<Feld> temp4 = this.MarkiereGerade(felder, z, sp, false, false, false, figur, true);

        Moeglichkeiten = new ArrayList<Feld>();
        Moeglichkeiten.addAll(temp1);
        Moeglichkeiten.addAll(temp2);
        Moeglichkeiten.addAll(temp3);
        Moeglichkeiten.addAll(temp4);
    }
    
}
