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
public class BauerBewegungsMuster extends AbstractMuster {

    public BauerBewegungsMuster(AbstractFigur f) {
        super(f);
    }
    
    public void FuelleAktuelleMoeglichkeiten(Feld[][] felder, int z, int sp) {

        Moeglichkeiten = new ArrayList<Feld>();
        if(figur.getSchwarz()) {
            ArrayList<Feld> temp1 = this.MarkiereGerade(felder, z, sp, true, false, true, figur, false);
            ArrayList<Feld> temp2 = this.MarkiereDiagonal(felder, z, sp, false, false, true, figur);
            ArrayList<Feld> temp3 = this.MarkiereDiagonal(felder, z, sp, false, true, true, figur);
            Moeglichkeiten.addAll(temp1);

            if(temp2.size() == 1) {
                AbstractFigur f = temp2.get(0).getFigur();
                if(f != null && f.getSchwarz() != figur.getSchwarz()) {
                    Moeglichkeiten.addAll(temp2);
                }
            }

            if(temp3.size() == 1) {
                AbstractFigur f = temp3.get(0).getFigur();
                if(f != null && f.getSchwarz() != figur.getSchwarz()) {
                    Moeglichkeiten.addAll(temp3);
                }
            }

            if(figur.pruefeObErsterZug() && Moeglichkeiten.size() > 0) {
                temp1 = this.MarkiereGerade(felder, z+1, sp, true, false, true, figur, false);
                Moeglichkeiten.addAll(temp1);
            }
        } else {
            ArrayList<Feld> temp1 = this.MarkiereGerade(felder, z, sp, true, true, true, figur, false);
            ArrayList<Feld> temp2 = this.MarkiereDiagonal(felder, z, sp, true, false, true, figur);
            ArrayList<Feld> temp3 = this.MarkiereDiagonal(felder, z, sp, true, true, true, figur);
            Moeglichkeiten.addAll(temp1);

            if(temp2.size() == 1) {
                AbstractFigur f = temp2.get(0).getFigur();
                if(f != null && f.getSchwarz() != figur.getSchwarz()) {
                    Moeglichkeiten.addAll(temp2);
                }
            }

            if(temp3.size() == 1) {
                AbstractFigur f = temp3.get(0).getFigur();
                if(f != null && f.getSchwarz() != figur.getSchwarz()) {
                    Moeglichkeiten.addAll(temp3);
                }
            }

            if(figur.pruefeObErsterZug() && Moeglichkeiten.size() > 0) {
                temp1 = this.MarkiereGerade(felder, z-1, sp, true, true, true, figur, false);
                Moeglichkeiten.addAll(temp1);
            }
        }

    }
    
}
