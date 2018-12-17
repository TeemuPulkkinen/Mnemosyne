package mnemosyne;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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
 * @author Teemu Pulkkinen
 *
 * Mnemosyne 1.0. JFrame versio
 *
 *
 */

/*
Luodaan JFrame-luokan alaluokka TekstiEditori. Se käyttää ActionListeneriä
painikkeiden toimintojen mahdollistamiseksi.
 */
public class TekstiEditori extends JFrame implements ActionListener {

    // Luodaan JFrame
    JFrame f = new JFrame("Mnemosyne");
    // TextArean luonti
    JTextArea ta = new JTextArea(5, 20);
    // fontteja muuntimen käyttöön
    Font fonttil = new Font("Lihavoitu", Font.BOLD, 12);
    Font fonttik = new Font("Kursivoitu", Font.ITALIC, 12);

    /*
    JTextArea tukee vain "plain textiä" eli muutos yhteen merkkiin vaikuttaa
    koko tekstikenttään. Yksittäisten tekstielementtien muokkaamiseksi parempia
    Swing-luokkia ovat JEditorPane ja JTextPane. Katso esimerkki Oraclen
    dokumentaatiosta.
     */
    public TekstiEditori() {
        /*
        Metodi luo käyttöliitymän muut komponentit, kuten kaikki valikot sekä
        alavalikot. Lisäksi määritellään ActionListener-komennot kaikille
        painikkeille.
         */

        //luotu TekxtArea liitetään ScrollPaneen
        JScrollPane scpane = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JMenuBar mb = new JMenuBar();

        f.setJMenuBar(mb);

        // Luodaan File-osio menupalkkiin
        JMenu m1 = new JMenu("Tiedosto");

        // Luodaan vaihtoehtoja
        JMenuItem mi1 = new JMenuItem("Uusi");
        JMenuItem mi2 = new JMenuItem("Avaa");
        JMenuItem mi3 = new JMenuItem("Tallenna");
        JMenuItem mi4 = new JMenuItem("Tulosta");

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi4);

        // Luodaan toinen valikko
        JMenu m2 = new JMenu("Muokkaa");

        // Luodaan menuitemit toiselle valikolle
        JMenuItem mi5 = new JMenuItem("Leikkaa");
        JMenuItem mi6 = new JMenuItem("Kopioi");
        JMenuItem mi7 = new JMenuItem("Liitä");

        //luodaan pikakomentomaininnat kuten Ctrl+S
        mi2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        mi3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        mi4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        mi5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        mi6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        mi7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        // Lisätään action listenerit
        mi4.addActionListener(new Tulostus());
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi5);
        m2.add(mi6);
        m2.add(mi7);

        JMenu m3 = new JMenu("Tyyli");
        JMenu m4 = new JMenu("Fontti");
        JMenu m5 = new JMenu("Koko");
        JMenu m6 = new JMenu("Väri");

        JMenuItem mi8 = new JMenuItem("Lihavoi");
        JMenuItem mi9 = new JMenuItem("Kursivoi");
        JMenuItem mi10 = new JMenuItem("Suurenna");
        JMenuItem mi11 = new JMenuItem("Pienennä");
        JMenuItem mi12 = new JMenuItem("Sininen");
        JMenuItem mi13 = new JMenuItem("Punainen");
        JMenuItem mi14 = new JMenuItem("Musta");
        
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi7.addActionListener(this);
        mi8.addActionListener(this);
        mi9.addActionListener(this);
        mi10.addActionListener(this);
        mi11.addActionListener(this);
        mi12.addActionListener(this);
        mi13.addActionListener(this);
        mi14.addActionListener(this);

        mi8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        mi9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

        m3.add(m4);
        m3.add(m5);
        m3.add(m6);

        m4.add(mi8);
        m4.add(mi9);

        m5.add(mi10);
        m5.add(mi11);

        m6.add(mi12);
        m6.add(mi13);
        m6.add(mi14);

        // Luodaan lopetusvalikko
        JMenuItem mc = new JMenuItem("Sulje");

        mc.addActionListener(this);

        m1.add(mc);

        // Lisätään valikot valikkopalkkiin
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);

        JOptionPane.showMessageDialog(f, "Minä olen Mnemosyne, muistin jumalatar. Miten voin auttaa?", "Tervetuloa!", JOptionPane.INFORMATION_MESSAGE);

        f.add(scpane);
        f.setSize(500, 500);
        f.show();
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /*
    Seuraava actionPerformed-tyyppinen metodi määrittelee String-muuttuja s:n
    joka saa arvokseen e.getActionCommand() mitä nappia painettaessa pitäisi tapahtua.
    If-else rakenne on sidottu siihen, mitä valikkopainiketta käyttäjä painaa,
    ja ohjelma muokkaa tekstikenttää sen perusteella. Samalla luodaan myös
    toiminnallisuudet tiedostojen tallentamiselle ja avaamiselle.
     */
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Leikkaa")) {
            ta.cut();
        } else if (s.equals("Kopioi")) {
            ta.copy();
        } else if (s.equals("Liitä")) {
            ta.paste();

        } else if (s.equals("Tallenna")) {

            // Luodaan JFileChooser-luokan olio
            JFileChooser j = new JFileChooser("f:");

            // Kutsutaan showSaveDialog funktiota näyttämään tallennusvalikko
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // luodaan tiedostokirjoitin
                    FileWriter wr = new FileWriter(fi, false);

                    // luodaan puskuroitu kirjoitin
                    BufferedWriter w = new BufferedWriter(wr);

                    // Kirjoita
                    w.write(ta.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } // jos toiminto perutaan
            else {
                JOptionPane.showMessageDialog(f, "Käyttäjä peruutti toiminnon.");
            }

        } else if (s.equals("Avaa")) {
            // luodaan FileChooser-olio
            JFileChooser j = new JFileChooser("f:");

            // Kutsutaan showsOpenDialog funktio
            int r = j.showOpenDialog(null);

            // jos valitaan tiedosto
            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Stringi
                    String s1 = "", sl = "";

                    // Tiedostolukija
                    FileReader fr = new FileReader(fi);

                    // Puskuroitu lukija
                    BufferedReader br = new BufferedReader(fr);

                    // Käynnistetään s1
                    sl = br.readLine();

                    // Haetaan inputti tiedostosta
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Asetetaa teksti
                    ta.setText(sl);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } //jos operaatio perutaan

            JOptionPane.showMessageDialog(f, "Käyttäjä peruutti toiminnon.");

        } else if (s.equals("Uusi")) {
            ta.setText("");
        } else if (s.equals("Lihavoi")) {
            ta.setFont(fonttil);

        } else if (s.equals("Kursivoi")) {
            ta.setFont(fonttik);

        } else if (s.equals("Suurenna")) {
            ta.setFont(ta.getFont().deriveFont(20f));
        } else if (s.equals("Pienennä")) {
            ta.setFont(ta.getFont().deriveFont(12f));
        } else if (s.equals("Punainen")) {
            ta.setForeground(Color.red);
        } else if (s.equals("Sininen")) {
            ta.setForeground(Color.blue);
        } else if (s.equals("Musta")) {
            ta.setForeground(Color.black);
        } else if (s.equals("Sulje")) {
            f.setVisible(false);
            System.exit(0);
        }
    }

    /* Tulostamisella on oma koodin pätkä koska se nyt vaan toimi näin. Ensi kerralla yritän varmaan tehdä
    koko homman olioilla niin voisi sujua helpommin tähän tapaan.
     */
    class Tulostus implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                ta.print();
            } catch (PrinterException ex) {

                
                JOptionPane.showMessageDialog(f, "Käyttäjä peruutti toiminnon.");
            }
        }
    }

    /*
    Lopuksi suoritetaan pääohjelma.
     */
    public static void main(String[] args) {
        TekstiEditori e = new TekstiEditori();
    }
    //lisää testicommittointia
}
