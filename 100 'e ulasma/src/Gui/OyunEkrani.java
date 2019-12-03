package Gui;

import Logic.Actions;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OyunEkrani extends JPanel {

    private int satir = 0, sutun = 0; // şeklimiz kare olacağı icin bir tanesi bile yeterdi ama 2 tanesini kullandımF
    JButton[][] buttonlar = new JButton[10][10]; // Sekilli kare ile de değiştirebiliriz ya da naısl isterseniz
    private int sayac_i = 0, sayac_j = 0;
    JFrame jf_oe = null;

    private int JFrame_baslangic_uzunluk = 1360;
    private int JFrame_baslangic_yukseklik = 720;

    public int width = 0, height = 0;
    public int width_btn = 70;
    public int height_btn = 50;
    Actions action = null;
    private boolean ilk_giris = true;
    private boolean gidilen_bolgeler[][] = new boolean[10][10];

    public OyunEkrani() {
        bitmeyen_oyun();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gidilen_bolgeler[i][j] = false;
            }
        }
        this.setLayout(null);

    }

    public JFrame getJf_oe() {
        if (jf_oe == null) {
            jf_oe = new JFrame();
            jf_oe.setBackground(Color.BLACK);
            jf_oe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf_oe.setBounds(0, 0, width, height);

            jf_oe.setResizable(false);
            jf_oe.setLocationRelativeTo(null);
            jf_oe.setLayout(null);

        }
        return jf_oe;
    }

    public void setJf_oe(JFrame jf_oe) {
        this.jf_oe = jf_oe;
    }

    public void oyunu_getir() {

        setBackground(Color.BLACK);
        for (int i = getSatir() - 1; i >= 0; i--) {
            setSayac_i(i);
            for (int j = 0; j < getSutun(); j++) {
                setSayac_j(j);
                int x = 1420 - getSatir() * 100;
                int y = 650 - getSutun() * 55;
                int x_ekseni = x / 2 + (width_btn + 4) * j + 30;
                int y_ekseni = y / 2 + (height_btn + 4) * (getSatir() - i) + 20;

                getButtonlar()[i][j].setBounds(x_ekseni, y_ekseni, width_btn, height_btn);
                this.add(getButtonlar()[i][j], new Integer(2));
                getButtonlar()[i][j].addActionListener(getAction());
                getButtonlar()[i][j].setBackground(Color.BLACK);
                getButtonlar()[i][j].setCursor(new Cursor(12));
                getButtonlar()[i][j].setForeground(Color.WHITE);
            }

        }
        //   action.setOe(this); // Bunu yazınca niye hata veriyor ?? 
        getJf_oe().add(this);
        this.setLayout(null);
    }

    /* @Override
    public void paint(Graphics g) {

        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        // g.setColor(Color.blue);

        g.fillRect(0, 0, width, height);
        setBounds(0, 0, width, height);

    }*/
    public int getSatir() {
        return satir;
    }

    public void setSatir(int satir) {
        this.satir = satir;
    }

    public int getSutun() {
        return sutun;
    }

    public void setSutun(int sutun) {
        this.sutun = sutun;
    }

    public int getSayac_i() {
        return sayac_i;
    }

    public void setSayac_i(int sayac_i) {
        this.sayac_i = sayac_i;
    }

    public int getSayac_j() {
        return sayac_j;
    }

    public void setSayac_j(int sayac_j) {
        this.sayac_j = sayac_j;
    }

    public JButton[][] getButtonlar() {
        if (buttonlar[getSayac_i()][getSayac_j()] == null) {
            buttonlar[getSayac_i()][getSayac_j()] = new JButton();
            buttonlar[getSayac_i()][getSayac_j()].setLayout(null);

        }
        return buttonlar;
    }

    public void setButtonlar(JButton[][] buttonlar) {
        this.buttonlar = buttonlar;
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

    public Actions getAction() {
        if (action == null) {
            action = new Actions();
            action.setOe(this);
            action.setBeg(null);
            System.out.println("oe : " + action.getOe());
            System.out.println("beg : " + action.getBeg());
        }
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public boolean isIlk_giris() {
        return ilk_giris;
    }

    public void setIlk_giris(boolean ilk_giris) {
        this.ilk_giris = ilk_giris;
    }

    public boolean[][] getGidilen_bolgeler() {

        return gidilen_bolgeler;
    }

    public void setGidilen_bolgeler(boolean[][] gidilen_bolgeler) {
        this.gidilen_bolgeler = gidilen_bolgeler;
    }

    public void Alkisla() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Gui/oyun_bitmistir.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void bitmeyen_oyun() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Gui/oyun_bitmemistir.mp3 "));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
