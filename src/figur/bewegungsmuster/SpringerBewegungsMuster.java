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
public class SpringerBewegungsMuster extends AbstractMuster {

    public SpringerBewegungsMuster(AbstractFigur f) {
        super(f);
    }
    
    public void FuelleAktuelleMoeglichkeiten(Feld[][] felder, int z, int sp) {

        int z1 = z+1;
        ArrayList<Feld> temp1 = this.MarkiereDiagonal(felder, z1, sp, false, false, true, figur);
        ArrayList<Feld> temp2 = this.MarkiereDiagonal(felder, z1, sp, false, true, true, figur);

        z1 = z-1;
        ArrayList<Feld> temp3 = this.MarkiereDiagonal(felder, z1, sp, true, false, true, figur);
        ArrayList<Feld> temp4 = this.MarkiereDiagonal(felder, z1, sp, true, true, true, figur);

        int sp1 = sp + 1;
        ArrayList<Feld> temp5 = this.MarkiereDiagonal(felder, z, sp1, false, false, true, figur);
        ArrayList<Feld> temp6 = this.MarkiereDiagonal(felder, z, sp1, true, false, true, figur);

        sp1 = sp - 1;
        ArrayList<Feld> temp7 = this.MarkiereDiagonal(felder, z, sp1, false, true, true, figur);
        ArrayList<Feld> temp8 = this.MarkiereDiagonal(felder, z, sp1, true, true, true, figur);

        Moeglichkeiten = new ArrayList<Feld>();
        Moeglichkeiten.addAll(temp1);
        Moeglichkeiten.addAll(temp2);
        Moeglichkeiten.addAll(temp3);
        Moeglichkeiten.addAll(temp4);
        Moeglichkeiten.addAll(temp5);
        Moeglichkeiten.addAll(temp6);
        Moeglichkeiten.addAll(temp7);
        Moeglichkeiten.addAll(temp8);
    }
    
}
