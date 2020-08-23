/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import figur.AbstractFigur;

import java.awt.*;


/**
 *
 * @author anaki
 */
public class Feld extends javax.swing.JButton {
    
    private Brett brett;
    private AbstractFigur figur = null;
    private boolean schwarz;
    private boolean markiert;
    
    public Feld(Brett brett, boolean schwarz){
        this.brett = brett;
        this.schwarz = schwarz;
        this.markiert = false;
        this.setFont(new Font("Courier", Font.PLAIN, 40));
    }
    
    public void setFigur(AbstractFigur figur, boolean imSpiel){
        this.figur = figur;
        figur.setFeld(this, imSpiel);
    }

    public void removeFigur() {
        this.figur = null;
    }
    
    public Brett getBrett(){
        return brett;
    }
    
    public AbstractFigur getFigur(){
        return figur;
    }

    public boolean getSchwarz() {
        return this.schwarz;
    }

    public void markiereFeld() {
        this.markiert = true;
    }

    public void unmarkiereFeld() {
        this.markiert = false;
    }

    public boolean istFeldMarkiert() {
        return this.markiert;
    }



    /********************************************/
    //Hilfsmethoden zur Darstellung und Aktualisierung des Brettes


    public void farbeAktualisieren() {
        AbstractFigur figur = this.getFigur();

        if(figur != null && figur.pruefeObAmZug()) {
            this.setBackground(Color.decode("#FFFB0A"));
        } else {
            if (this.markiert){
                this.setBackground(Color.decode("#FFFBB7"));
            } else {
                if(this.schwarz){
                    this.setBackground(Color.DARK_GRAY);
                } else {
                    this.setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    public void symbolAktualsieren() {
        AbstractFigur figur = this.getFigur();

        //wenn figur auf feld steht, die Figur dort anzeigen
        if(figur != null) {
            this.setForeground(figur.getSchwarz() ? Color.black : Color.decode("#2092BA")/*Color.white*/);
            this.setText(figur.getSymbol());
        } else {
            this.setText("");
        }
    }

}
