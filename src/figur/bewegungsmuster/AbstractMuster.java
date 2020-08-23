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
public abstract class AbstractMuster {
    
    protected AbstractFigur figur;
    protected ArrayList<Feld> Moeglichkeiten;
    
    public AbstractMuster(AbstractFigur f) {
        this.figur = f;
    }
    
    public ArrayList<Feld> GetMoeglichkeiten() {
        return Moeglichkeiten;
    }
    
    public abstract void FuelleAktuelleMoeglichkeiten(Feld[][] felder, int z, int sp);




    /**
     *
     * @param felder Das Array welches durchlaufen wird
     * @param z die Zeile der Figur von der aus gerechnet wird
     * @param sp die Spalte der Figur von der aus gerechnet wird
     * @param directionVertical ... false = horizontal, true = vertikal
     * @param left ... true = nach links bzw nach oben, false = nach rechts bzw nach unten
     * @param single ... true = es wird nur ein einziges Feld gerechnet, false = es wird bis zum Schluss bzw. bis zur nächsten Figur gerechnet
     * @return ArrayList<Feld> ... gibt die zu markierenden Felder zurück als ArrayList
     */
    protected ArrayList<Feld> MarkiereGerade(Feld[][] felder, int z, int sp, boolean directionVertical, boolean left, boolean single, AbstractFigur figurAmZug, boolean kannSchlagen) {
        ArrayList<Feld> returnFeld = new ArrayList<Feld>();
        boolean weitermachen = true;

        if(left) { //wir bewegen uns nach links oder nach oben, das heißt wir müssen die schleifen rückwärts durchlaufen...
            for (int ztmp = felder.length-1; ztmp >= 0; ztmp--) {
                for (int sptmp = felder[ztmp].length-1; sptmp >= 0; sptmp--) {
                    weitermachen = selektiereUndMarkiereHilfsfunktion(directionVertical, left, sp, sptmp, z, ztmp, returnFeld, felder, figurAmZug, kannSchlagen);
                    if(returnFeld.size() >= 1 && single) weitermachen = false;
                    if(weitermachen != true) break;
                }
                if(weitermachen != true) break;
            }
        }
        else { //wir bewegen uns nach rechts oder nach unten, also können wir die schleifen normal durchlaufen (vorwärts).
            for (int ztmp = 0; ztmp < felder.length; ztmp++) {
                for (int sptmp = 0; sptmp < felder[ztmp].length; sptmp++) {
                    weitermachen = selektiereUndMarkiereHilfsfunktion(directionVertical, left, sp, sptmp, z, ztmp, returnFeld, felder, figurAmZug, kannSchlagen);
                    if(returnFeld.size() >= 1 && single) weitermachen = false;
                    if(weitermachen != true) break;
                }
                if(weitermachen != true) break;
            }
        }

        return returnFeld;
    }


    /**
     *
     * Hilfsmethode zum Auswählen der Markierungen...
     * returnt 1 wenn alles ok ist, und 0 wenn die markierung gestoppt werden soll.
     */
    private boolean selektiereUndMarkiereHilfsfunktion(boolean directionVertical, boolean left, int sp, int sptmp, int z, int ztmp, ArrayList<Feld> returnFeld, Feld[][] felder, AbstractFigur figurAmZug, boolean kannSchlagen) {
        boolean returnVal = true;
        Feld aktuellesFeldZumMarkieren = felder[ztmp][sptmp];
        AbstractFigur stehtFigurAufMarkierFeld = aktuellesFeldZumMarkieren.getFigur();

        if(directionVertical) { //wir wollen alle mit der gleichen spaltennummer
            if(left && sp == sptmp && ztmp < z) { //nach oben
                returnVal = EntscheidungsHilfsMethode(stehtFigurAufMarkierFeld, returnFeld, aktuellesFeldZumMarkieren, figurAmZug, kannSchlagen);
            }
            if(!left && sp == sptmp && ztmp > z) { //nach unten
                returnVal = EntscheidungsHilfsMethode(stehtFigurAufMarkierFeld, returnFeld, aktuellesFeldZumMarkieren, figurAmZug, kannSchlagen);
            }
        }
        else { //wir wollen alle mit der gleichen zeilennummer
            if(left && z == ztmp && sptmp < sp) { //nach links
                returnVal = EntscheidungsHilfsMethode(stehtFigurAufMarkierFeld, returnFeld, aktuellesFeldZumMarkieren, figurAmZug, kannSchlagen);
            }
            if(!left && z == ztmp && sptmp > sp) { //nach rechts
                returnVal = EntscheidungsHilfsMethode(stehtFigurAufMarkierFeld, returnFeld, aktuellesFeldZumMarkieren, figurAmZug, kannSchlagen);
            }
        }
        return returnVal;
    }




    /**
     *
     * @param felder Das Array welches durchlaufen wird
     * @param z die Zeile der Figur von der aus gerechnet wird
     * @param sp die Spalte der Figur von der aus gerechnet wird
     * @param top ... false = Diagonale von links unten nach rechts oben, true = Diagonale von rechts unten nach links oben
     * @param left ... true = nach links bzw nach oben, false = nach rechts bzw nach unten
     * @param single ... true = es wird nur ein einziges Feld gerechnet, false = es wird bis zum Schluss bzw. bis zur nächsten Figur gerechnet
     * @return
     */
    protected ArrayList<Feld> MarkiereDiagonal(Feld[][] felder, int z, int sp, boolean top, boolean left, boolean single, AbstractFigur figurAmZug) {
        ArrayList<Feld> returnFeld = new ArrayList<Feld>();

        int ztmp = z;
        int sptmp = sp;

        int xAdder = left ? -1 : 1;
        int yAdder = top ? -1 : 1;

        boolean weitermachen = true;

        do
        {
            ztmp += yAdder;
            sptmp += xAdder;

            if(ztmp >= 0 && ztmp < felder.length && sptmp >= 0 && sptmp < felder.length)
            {
                Feld aktuellesFeldZumMarkieren = felder[ztmp][sptmp];
                AbstractFigur stehtFigurAufMarkierFeld = aktuellesFeldZumMarkieren.getFigur();
                weitermachen = EntscheidungsHilfsMethode(stehtFigurAufMarkierFeld, returnFeld, aktuellesFeldZumMarkieren, figurAmZug, true);
            }

            if(single && returnFeld.size() >= 1) weitermachen = false;
        }
        while(ztmp >= 0 && ztmp < felder.length && sptmp >= 0 && sptmp < felder.length && weitermachen);  //felder.length in beiden Fällen, da Feld quadratisch ist.

        return returnFeld;
    }





    private boolean EntscheidungsHilfsMethode(AbstractFigur stehtFigurAufMarkierFeld, ArrayList<Feld> returnFeld, Feld aktuellesFeldZumMarkieren, AbstractFigur figurAmZug, boolean kannSchlagen) {
        if(stehtFigurAufMarkierFeld == null) {
            returnFeld.add(aktuellesFeldZumMarkieren);
            return true;
        } else {
            if(kannSchlagen && stehtFigurAufMarkierFeld.getSchwarz() != figurAmZug.getSchwarz()) {
                returnFeld.add(aktuellesFeldZumMarkieren);
            }
            return false;
        }
    }


}
