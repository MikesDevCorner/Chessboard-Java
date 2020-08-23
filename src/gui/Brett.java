/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import figur.*;

import java.awt.*;

/**
 *
 * @author anaki
 */
public class Brett extends javax.swing.JFrame {

    private boolean weissIstAmZug;
    private Feld[][] felder = new Feld[8][8];
    private FeldListener fl = new FeldListener();

    /**
     * Dieser Konstruktor setzt das ganze Spiel in Gang. Hier wird das Spielbrett generiert
     */
    public Brett() {

        initComponents();

        erstelleNeuesSpielfeld();

        updateBrett();

        weissIstAmZug = true;

        pack();
    }
    
    
    /**
     * 
     * Diese Methode geht jedes Feld durch und stellt die Symbole und Farben der aktuell
     * darauf befindlichen Figuren ein. Sie löscht auch alle Symbole, wo keine Figuren mehr
     * stehen.
     * 
     */
    public void updateBrett() {
        for(int z = 0; z<felder.length; z++){
            for(int sp = 0; sp<felder[z].length; sp++){
                Feld feld = felder[z][sp];
                feld.farbeAktualisieren();
                feld.symbolAktualsieren();
            }
        }
    }

    /**
     * Diese Methode geht alle Felder durch und setzt sie wieder auf "unmarkiert".
     * Alle offenen Züge von daraufstehenden Figuren werden beendet.
     * Das sollte man immer machen, wenn man einen neuen Zug beginnt
     */
    public void alleFigurZuegeBeendenUndFelderUnmarkieren() {
        for(int z = 0; z<felder.length; z++) {
            for (int sp = 0; sp < felder[z].length; sp++) {
                Feld feld = felder[z][sp];
                feld.unmarkiereFeld();
                AbstractFigur figur = feld.getFigur();
                if(figur != null) figur.setzeNichtAmZug();
            }
        }
    }



    public void GegenspielerIstDran() {
        this.weissIstAmZug = !this.weissIstAmZug;
    }




    public void markiereBewegungsOptionen() {
        for(int z = 0; z<felder.length; z++) {
            for (int sp = 0; sp < felder[z].length; sp++) {
                Feld feld = felder[z][sp];
                AbstractFigur figur = feld.getFigur();
                if(figur != null && figur.pruefeObAmZug()) {
                    figur.GetBewegungsMuster().FuelleAktuelleMoeglichkeiten(felder, z, sp);
                    for(Feld markFeld: figur.GetBewegungsMuster().GetMoeglichkeiten()) {
                        markFeld.markiereFeld();
                    }
                }
            }
        }
    }



    public AbstractFigur holeFigurAmZug() {
        for(int z = 0; z<felder.length; z++){
            for(int sp = 0; sp<felder[z].length; sp++){
                Feld feld = felder[z][sp];
                AbstractFigur figur = feld.getFigur();
                if(figur != null && figur.pruefeObAmZug()) return figur;
            }
        }
        return null;
    }
    


    public void erstelleNeuesSpielfeld() {

        //Vorbereitungs Schritt: alle Felder auf dem jPanel löschen
        Spielfeld.removeAll();

        //wir fangen mit einem weißen Feld an
        boolean schwarz = false;

        //Felder generieren
        for(int z = 0; z<felder.length; z++){
            for(int sp = 0; sp<felder[z].length; sp++){
                Feld feld = new Feld (this, schwarz);
                felder[z][sp]=feld;

                //click Listener auf Feld legen
                feld.addActionListener(fl);

                if(z == 0){
                    if(sp == 0 || sp == 7) feld.setFigur ( new TurmFigur(feld, true), false );
                    if(sp == 1 || sp == 6) feld.setFigur ( new SpringerFigur(feld, true), false );
                    if(sp == 2 || sp == 5) feld.setFigur ( new LaeuferFigur(feld, true), false );
                    if(sp == 3) feld.setFigur ( new KoenigFigur(feld, true), false );
                    if(sp == 4) feld.setFigur ( new KoeniginFigur(feld, true), false );
                }
                else if ( z == 1){
                    feld.setFigur ( new BauerFigur(feld, true), false );
                }
                else if ( z == 6){
                    feld.setFigur ( new BauerFigur(feld, false), false );
                }
                else if ( z == 7){
                    if(sp == 0 || sp == 7) feld.setFigur ( new TurmFigur(feld, false), false );
                    if(sp == 1 || sp == 6) feld.setFigur ( new SpringerFigur(feld, false), false );
                    if(sp == 2 || sp == 5) feld.setFigur ( new LaeuferFigur(feld, false), false );
                    if(sp == 3) feld.setFigur ( new KoenigFigur(feld, false), false );
                    if(sp == 4) feld.setFigur ( new KoeniginFigur(feld, false), false );
                }

                Spielfeld.add(feld); //Feld zum frisch entleerten (siehe Vorbereitung Schritt 2) jPanel hinzufügen
                schwarz = !schwarz; //fürs nächste Feld andere Farbe benützen
            }
            schwarz = !schwarz;
        }
    }


    /**
     * Der Listener für die einzelnen Schachbrett-Felder. Beim Anklicken eines Feldes passiert das...
     */
    private class FeldListener implements java.awt.event.ActionListener {

        AbstractFigur figur = null;
        public void actionPerformed(java.awt.event.ActionEvent evt){
            for(int z = 0; z < felder.length; z++){
                for(int sp = 0; sp < felder.length; sp++){
                    if(evt.getSource() == felder[z][sp]){
                        Feld feld = felder[z][sp];
                        AbstractFigur figur = feld.getFigur();
                        AbstractFigur figurAmZug = holeFigurAmZug();

                        if(feld.istFeldMarkiert() && figurAmZug != null) {
                            figurAmZug.getFeld().removeFigur();
                            feld.setFigur(figurAmZug, true);
                            alleFigurZuegeBeendenUndFelderUnmarkieren();
                            GegenspielerIstDran();
                        } else {
                            if(figur != null) {
                                if (figur.pruefeObAmZug()) alleFigurZuegeBeendenUndFelderUnmarkieren();
                                else {
                                    if(weissIstAmZug == true && figur.getSchwarz() == false) figur.setzeAmZug();
                                    if(weissIstAmZug == false && figur.getSchwarz() == true) figur.setzeAmZug();
                                }
                            }
                        }
                        System.out.println("Feld" + z + ", " + sp + " geklickt");
                        feld.getBrett().updateBrett();
                        break;
                    }
                }
            }
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NorthBeschreibung = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        WestBeschreibung = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Spielfeld = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 800));

        NorthBeschreibung.setMinimumSize(new java.awt.Dimension(50, 50));
        NorthBeschreibung.setLayout(new java.awt.GridLayout(1, 8));

        jLabel1.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("A");
        NorthBeschreibung.add(jLabel1);

        jLabel3.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("B");
        NorthBeschreibung.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("C");
        NorthBeschreibung.add(jLabel4);

        jLabel6.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("D");
        NorthBeschreibung.add(jLabel6);

        jLabel8.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("E");
        NorthBeschreibung.add(jLabel8);

        jLabel5.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("F");
        NorthBeschreibung.add(jLabel5);

        jLabel7.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("G");
        NorthBeschreibung.add(jLabel7);

        jLabel2.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("H");
        NorthBeschreibung.add(jLabel2);

        getContentPane().add(NorthBeschreibung, java.awt.BorderLayout.NORTH);

        WestBeschreibung.setMinimumSize(new java.awt.Dimension(50, 50));
        WestBeschreibung.setLayout(new java.awt.GridLayout(8, 1));

        jLabel9.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("8");
        WestBeschreibung.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("7");
        WestBeschreibung.add(jLabel10);

        jLabel11.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("6");
        WestBeschreibung.add(jLabel11);

        jLabel12.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("5");
        WestBeschreibung.add(jLabel12);

        jLabel13.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("4");
        WestBeschreibung.add(jLabel13);

        jLabel14.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("3");
        WestBeschreibung.add(jLabel14);

        jLabel16.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("2");
        WestBeschreibung.add(jLabel16);

        jLabel15.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("1");
        WestBeschreibung.add(jLabel15);

        getContentPane().add(WestBeschreibung, java.awt.BorderLayout.WEST);

        Spielfeld.setLayout(new java.awt.GridLayout(8, 8));
        getContentPane().add(Spielfeld, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Brett.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Brett.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Brett.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Brett.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Brett().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NorthBeschreibung;
    private javax.swing.JPanel Spielfeld;
    private javax.swing.JPanel WestBeschreibung;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables


}

