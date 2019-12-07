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
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OyunEkrani extends JPanel {
    
    private int satir = 0, sutun = 0; // şeklimiz kare olacağı icin bir tanesi bile yeterdi ama 2 tanesini kullandımF
    JButton[][] buttonlar = new JButton[10][10]; // Sekilli kare ile de değiştirebiliriz ya da naısl isterseniz
    private int sayac_i = 0, sayac_j = 0;
    JFrame jf_oe = null;
    private int JFrame_baslangic_uzunluk = 1360;
    private int JFrame_baslangic_yukseklik = 720;
    
    Actions action = null;
    private boolean ilk_giris = true;
    private boolean gidilen_bolgeler[][] = new boolean[10][10];
    JButton Ana_menu = null;
    public int width = 0, height = 0;
    public int width_btn = 70;
    public int height_btn = 50;
    public int sagdaki_secenekler_x_ekseni = 1116;
    public int sagadaki_secenekler_y_ekseni = 170;
    public int sagdaki_secenekler_y_ekseni_kere_kullanıldı = 0;
    JTextField kronometre = null;
    JTextField jtextfield_skor = null;
    JButton sıfırla = null;
    public Clip oyun_ici_music_clip = null;
    Gecen_Sure gecen_sure = null; // buna bir bak 
    JButton geri_adim_at = null;
    JButton music_ac_kapat = null;
    JButton ayarlar = null;
    JFrame jf_ayarlar = null;
    JPanel jp_ayarlar = null;
    public Icon music_on = new ImageIcon(getClass().getResource("music_on.jpg"));
    public Icon music_off = new ImageIcon(getClass().getResource("music_off.jpg"));
    
    public OyunEkrani() {
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gidilen_bolgeler[i][j] = false;
            }
        }
        this.setLayout(null);
        
    }
    
    public void oyunu_getir() {

        //    getGecen_sure().sureyi_baslat();
        setBackground(Color.BLACK);
        for (int i = getSatir() - 1; i >= 0; i--) {
            setSayac_i(i);
            for (int j = 0; j < getSutun(); j++) {
                setSayac_j(j);
                int x = 1420 - getSatir() * 100;
                int y = 650 - getSutun() * 55;
                int x_ekseni = x / 2 + (width_btn + 4) * j + 30;
                int y_ekseni = y / 2 + (height_btn + 4) * (getSatir() - i) + 20;
                
                if (sagadaki_secenekler_y_ekseni == 0) {
                    sagadaki_secenekler_y_ekseni = y_ekseni;
                }
                getButtonlar()[i][j].setBounds(x_ekseni, y_ekseni, width_btn, height_btn);
                this.add(getButtonlar()[i][j], new Integer(2));
                getButtonlar()[i][j].addActionListener(getAction());
                getButtonlar()[i][j].setBackground(Color.BLACK);
                getButtonlar()[i][j].setCursor(new Cursor(12));
                getButtonlar()[i][j].setForeground(Color.WHITE);
                getButtonlar()[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
                getButtonlar()[i][j].setFocusable(false);
                Font font = new Font("", Font.BOLD, 20);
                getButtonlar()[i][j].setFont(font);
            }
            
        }
        
        getJf_oe().add(this);
        this.add(getKronometre());
        this.setLayout(null);
        this.add(getAna_menu());
        this.add(getJtextfield_skor());
        this.add(getGeri_adim_at());
        this.add(getSıfırla());
        this.add(getAyarlar());
        getGeri_adim_at().addActionListener(action);
        getAna_menu().addActionListener(action);
        getSıfırla().addActionListener(action);
        this.add(getAyarlar());
        getAyarlar().addActionListener(action);
        
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
    
    public JFrame getJf_ayarlar() {
        if (jf_ayarlar == null) {
            jf_ayarlar = new JFrame();
            jf_ayarlar.setBackground(Color.yellow);
            //jf_ayarlar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf_ayarlar.setResizable(false);
            jf_ayarlar.setLocationRelativeTo(this);
            jf_ayarlar.setBounds(0, 0, 300, 200);
            jf_ayarlar.setLayout(null);
            jf_ayarlar.setVisible(true);
            jf_ayarlar.add(getJp_ayarlar());
            jf_ayarlar.setLocation(500, 300);
            
        }
        return jf_ayarlar;
    }
    
    public void setJf_ayarlar(JFrame jf_ayarlar) {
        this.jf_ayarlar = jf_ayarlar;
    }
    
    public JPanel getJp_ayarlar() {
        if (jp_ayarlar == null) {
            
            jp_ayarlar = new JPanel();
            // jp_ayarlar.setBackground(Color.red);
            jp_ayarlar.setBounds(getJf_ayarlar().getBounds());
            jp_ayarlar.setLayout(null);
            jp_ayarlar.add(getMusic_ac_kapat());
            getMusic_ac_kapat().addActionListener(action);
        }
        return jp_ayarlar;
    }
    
    public JButton getMusic_ac_kapat() {
        if (music_ac_kapat == null) {
            music_ac_kapat = new JButton();
            Icon ayarlarResim = new ImageIcon(getClass().getResource("music_on.jpg"));
            music_ac_kapat.setIcon(ayarlarResim);
            music_ac_kapat.setBounds(50, 50, 100, 52);
            music_ac_kapat.setLayout(null);
            music_ac_kapat.setCursor(new Cursor(12));
        }
        return music_ac_kapat;
    }
    
    public void setMusic_ac_kapat(JButton music_ac_kapat) {
        this.music_ac_kapat = music_ac_kapat;
    }
    
    public void setJp_ayarlar(JPanel jp_ayarlar) {
        this.jp_ayarlar = jp_ayarlar;
    }
    
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
    
    public void Oyun_ici_music_cal() {
        AudioInputStream stream;
        try {
            stream = AudioSystem.getAudioInputStream(new File("src/Gui/oyun_ici_music.wav"));
            oyun_ici_music_clip = AudioSystem.getClip();
            oyun_ici_music_clip.open(stream);
            oyun_ici_music_clip.start();
            oyun_ici_music_clip.loop(Clip.LOOP_CONTINUOUSLY);
            
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
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/Gui/oyun_bitmemistir .wav"));
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
    
    public JTextField getKronometre() {
        if (kronometre == null) {
            kronometre = new JTextField(" Geçen Süre : 00:00:00 ");
            kronometre.setBounds(520 - (50 * (getSatir() - 5)) + (width_btn * (getSatir() - 5)) / 2,
                    50 + ((10 - getSatir()) * 30), 300, 50);
            kronometre.setBackground(Color.BLACK);
            kronometre.setForeground(Color.WHITE);
            kronometre.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
            //   getButtonlar()[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
            kronometre.setEditable(false);
            kronometre.setFont(new Font("monospaced", Font.BOLD, 20));
        }
        return kronometre;
    }
    
    public void setKronometre(JTextField kronometre) {
        this.kronometre = kronometre;
    }
    
    public JTextField getJtextfield_skor() {
        if (jtextfield_skor == null) {
            jtextfield_skor = new JTextField("SKOR :   ");
            jtextfield_skor.setBounds(sagdaki_secenekler_x_ekseni, sagadaki_secenekler_y_ekseni + 50 * (sagdaki_secenekler_y_ekseni_kere_kullanıldı * 2), 200, 50);
            jtextfield_skor.setFocusable(false);
            jtextfield_skor.setBackground(Color.WHITE);
            jtextfield_skor.setEditable(false);
            sagdaki_secenekler_y_ekseni_kere_kullanıldı++;
            jtextfield_skor.setFont(new Font("monospaced", Font.BOLD, 25));
        }
        return jtextfield_skor;
    }
    
    public void setJtextfield_skor(JTextField jtextfield_skor) {
        this.jtextfield_skor = jtextfield_skor;
    }
    
    public JButton getAna_menu() {
        if (Ana_menu == null) {
            Ana_menu = new JButton("ANA MENÜYE DÖN");
            Ana_menu.setBackground(Color.WHITE);
            Ana_menu.setBounds(sagdaki_secenekler_x_ekseni, sagadaki_secenekler_y_ekseni + 50 * (2 * sagdaki_secenekler_y_ekseni_kere_kullanıldı), 200, 50);
            Ana_menu.setCursor(new Cursor(12));
            Ana_menu.setFocusable(false);
            sagdaki_secenekler_y_ekseni_kere_kullanıldı++;
            Ana_menu.setFont(new Font("", Font.BOLD, 15));
            
        }
        
        return Ana_menu;
    }
    
    public void setAna_menu(JButton Ana_menu) {
        this.Ana_menu = Ana_menu;
    }
    
    public JButton getSıfırla() {
        if (sıfırla == null) {
            sıfırla = new JButton();
            sıfırla = new JButton("SIFIRLA");
            sıfırla.setBackground(Color.WHITE);
            sıfırla.setBounds(sagdaki_secenekler_x_ekseni, sagadaki_secenekler_y_ekseni + 50 * (2 * sagdaki_secenekler_y_ekseni_kere_kullanıldı), 200, 50);
            sıfırla.setCursor(new Cursor(12));
            sıfırla.setFocusable(false);
            sıfırla.setFont(new Font("", Font.BOLD, 15));
            sagdaki_secenekler_y_ekseni_kere_kullanıldı++;
        }
        return sıfırla;
    }
    
    public void setSıfırla(JButton sıfırla) {
        this.sıfırla = sıfırla;
    }
    
    public JButton getGeri_adim_at() {
        if (geri_adim_at == null) {
            geri_adim_at = new JButton();
            geri_adim_at = new JButton("GERİ ADIM AT");
            geri_adim_at.setBackground(Color.WHITE);
            geri_adim_at.setBounds(sagdaki_secenekler_x_ekseni, sagadaki_secenekler_y_ekseni + 50 * (2 * sagdaki_secenekler_y_ekseni_kere_kullanıldı), 200, 50);
            geri_adim_at.setCursor(new Cursor(12));
            geri_adim_at.setFocusable(false);
            geri_adim_at.setFont(new Font("", Font.BOLD, 15));
            sagdaki_secenekler_y_ekseni_kere_kullanıldı++;
        }
        return geri_adim_at;
    }
    
    public void setGeri_adim_at(JButton geri_adim_at) {
        this.geri_adim_at = geri_adim_at;
    }
    
    public JButton getAyarlar() {
        if (ayarlar == null) {
            ayarlar = new JButton(" AYARLAR ");
            ayarlar.setFont(new Font("", Font.BOLD, 15));
            ayarlar.setBounds(sagdaki_secenekler_x_ekseni, sagadaki_secenekler_y_ekseni + 50 * (sagdaki_secenekler_y_ekseni_kere_kullanıldı * 2), 200, 50);
            ayarlar.setBackground(Color.WHITE);
            sagdaki_secenekler_y_ekseni_kere_kullanıldı++;
            ayarlar.setFocusable(false);
            
        }
        return ayarlar;
    }
    
    public void setAyarlar(JButton ayarlar) {
        this.ayarlar = ayarlar;
    }
    
}
