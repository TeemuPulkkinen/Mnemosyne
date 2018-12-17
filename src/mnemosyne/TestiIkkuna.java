/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnemosyne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author s1800591
 */
public class TestiIkkuna extends JFrame implements ActionListener {

    // Luodaan JFrame
    JFrame ft = new JFrame("Mnemosyne");
    // TextArean luonti
    JTextArea ta = new JTextArea(5, 20);

    public TestiIkkuna() {
        /*
        Metodi luo käyttöliitymän muut komponentit, kuten kaikki valikot sekä
        alavalikot. Lisäksi määritellään ActionListener-komennot kaikille
        painikkeille.
         */

        JButton bt = new JButton();

        ft.add(bt);
        bt.addActionListener(new Tulostus());

        ft.setSize(500, 500);
        ft.show();
        ft.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class Tulostus implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                ta.print();
            } catch (PrinterException ex) {
                Logger.getLogger(TestiIkkuna.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
