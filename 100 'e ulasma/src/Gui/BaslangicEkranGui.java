package Gui;

import Logic.Actions;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;

public class BaslangicEkranGui extends JLayeredPane {

    public Clip clip = null;
    JFrame jf_beg = null; //  --> BaslangicEkraniGui  (..._beg) 
    JLabel jl = null;
    JRadioButton[] jrb = new JRadioButton[6];
    int degerler;
    JButton basla = null;
    JButton cikis = null;
    ButtonGroup group = new ButtonGroup();
    Actions action = new Actions();
    private int JFrame_baslangic_x = 50;
    private int JFrame_baslangic_y = 100;
    private int JFrame_baslangic_uzunluk = 500;
    private int JFrame_baslangic_yukseklik = 600;

    public BaslangicEkranGui() {
        OyunEkrani oe = new OyunEkrani();
        baslangic_muzigi();
        this.setLayout(null);/* jpanel'i extends ettiğimizden  (burayı) this kullandık  
        
         */

        this.add(getBasla());
        this.add(getCikis());

        this.add(getJl());
        for (int i = 0; i < 6; i++) {
            setDegerler(i);
            group.add(getJrb()[getDegerler()]);
            getJrb()[getDegerler()].setCursor(new Cursor(12));
            getJrb()[getDegerler()].setToolTipText((int) Math.pow((getDegerler() + 5), 2) + " adet kare hazırlanılacak");
            getJrb()[getDegerler()].setBackground(Color.BLUE);
            getJrb()[getDegerler()].setForeground(Color.white);
            this.add(getJrb()[getDegerler()]);

        }
        action.setBeg(this);

        getJf_beg().setVisible(true);

    }

    public JLabel getJl() {
        if (jl == null) {
            jl = new JLabel("Başlamak İçin Oyun Boyutunu Seçiniz");
            jl.setForeground(Color.GRAY);
            jl.setBounds(70, 70, 350, 30);
            Font font = new Font("monospaced", Font.BOLD, 17); // bu classın sadece en sağdakini yazının pixeli olduğunu biliyorum
            jl.setFont(font);

        }
        return jl;
    }

    public void setJl(JLabel jl) {

        this.jl = jl;
    }

    public JRadioButton[] getJrb() {
        if (jrb[getDegerler()] == null) {
            Font font = new Font("monospaced", Font.BOLD, 13); // bu classın sadece en sağdakini yazının pixeli olduğunu biliyorum
            jrb[getDegerler()] = new JRadioButton((getDegerler() + 5) + " x " + (getDegerler() + 5) + " Bölümü Oyna");
            jrb[getDegerler()].setFont(font);
            jrb[getDegerler()].setBounds(150, 125 + getDegerler() * 50, 200, 30);
            jrb[getDegerler()].setSelected(false);
            jrb[getDegerler()].setFocusable(false);
            jrb[getDegerler()].addActionListener(action);

//jrb[getDegerler()]
        }
        return jrb;
    }

    public void setJrb(JRadioButton[] jrb) {
        this.jrb = jrb;
    }

    public int getDegerler() {
        return degerler;
    }

    public void setDegerler(int degerler) {
        this.degerler = degerler;
    }

    public JButton getBasla() {
        if (basla == null) {
            basla = new JButton("Başla");
            basla.setBounds(150, 450, 90, 40);
            basla.setBackground(Color.gray);
            basla.setForeground(Color.WHITE);
            Font font = new Font("", Font.BOLD, 15);
            basla.setFont(font);
            basla.setCursor(new Cursor(12)); // başla butonuna gelince el işareti oluşur 
            //basla.setBorderPainted(false); // aynı butonun kenar çizgileri kaldırılır
            //basla. // butona basınca arka ekran beyaz oluyor onu kaldırma kodunu eklicem buraya 
            basla.setFocusable(false);
            basla.addActionListener(action);

        }
        return basla;

    }

    public void setBasla(JButton basla) {
        this.basla = basla;
    }

    public JButton getCikis() {
        if (cikis == null) {
            cikis = new JButton("Çıkış");
            cikis.setBounds(260, 450, 90, 40);
            cikis.setBackground(Color.white);
            cikis.setForeground(Color.gray);
            //cikis.setBackground(Color.white);
            Font font = new Font("", Font.BOLD, 15);
            cikis.setFont(font);
            cikis.setCursor(new Cursor(12));
            //cikis.setFocusPainted(false);
            cikis.setFocusable(false);
            cikis.addActionListener(action);
        }
        return cikis;
    }

    public void setCikis(JButton cikis) {
        this.cikis = cikis;
    }

    public JFrame getJf_beg() {
        if (jf_beg == null) {
            jf_beg = new JFrame("BirDenYüze");
            jf_beg.setSize(JFrame_baslangic_uzunluk, JFrame_baslangic_yukseklik);
            jf_beg.setLocation(JFrame_baslangic_x, JFrame_baslangic_y);
            jf_beg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf_beg.setLocationRelativeTo(null);
            jf_beg.setContentPane(this);
            jf_beg.setBackground(Color.BLACK);
            jf_beg.setResizable(false);

        }
        return jf_beg;
    }

    public void setJf_beg(JFrame jf_beg) {
        this.jf_beg = jf_beg;
    }

    public int getJFrame_baslangic_x() {
        return JFrame_baslangic_x;
    }

    public void setJFrame_baslangic_x(int JFrame_baslangic_x) {
        this.JFrame_baslangic_x = JFrame_baslangic_x;
    }

    public int getJFrame_baslangic_y() {
        return JFrame_baslangic_y;
    }

    public void setJFrame_baslangic_y(int JFrame_baslangic_y) {
        this.JFrame_baslangic_y = JFrame_baslangic_y;
    }

    public int getJFrame_baslangic_uzunluk() {
        return JFrame_baslangic_uzunluk;
    }

    public void setJFrame_baslangic_uzunluk(int JFrame_baslangic_uzunluk) {
        this.JFrame_baslangic_uzunluk = JFrame_baslangic_uzunluk;
    }

    public int getJFrame_baslangic_yukseklik() {
        return JFrame_baslangic_yukseklik;
    }

    public void setJFrame_baslangic_yukseklik(int JFrame_baslangic_yukseklik) {
        this.JFrame_baslangic_yukseklik = JFrame_baslangic_yukseklik;
    }

    public void baslangic_muzigi() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Gui/baslangic_muzigi.wav"));
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
