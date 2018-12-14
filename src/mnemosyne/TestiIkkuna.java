/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnemosyne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

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

        //luotu TekxtArea liitetään ScrollPaneen
        //JScrollPane scpane = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //JMenuBar mb = new JMenuBar();
        
        JButton bt = new JButton();

        //ft.setJMenuBar(mb);
        
        
        
        

        //JMenu m1 = new JMenu("Tiedosto");

        //JMenuItem mi4 = new JMenuItem("Tulosta");

        //mi4.addActionListener(this);

        //m1.add(mi4);

        //mi4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        //mi4.addActionListener(this);

        //mb.add(m1);
        ft.add(bt);
        bt.addActionListener(new Tulostus());
        //ft.add(scpane);
        ft.setSize(500, 500);
        ft.show();
        ft.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    
    
    public static void main(String[] args) {
        TestiIkkuna e = new TestiIkkuna();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class Tulostus implements ActionListener{
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
