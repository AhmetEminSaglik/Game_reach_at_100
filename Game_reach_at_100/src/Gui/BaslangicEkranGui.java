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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class BaslangicEkranGui extends JLayeredPane {

    public Clip clip = null;
    JFrame jf_beg = null;
    JFrame jf_oyun_kurallari = null;
    JPanel jp_oyun_kurallari = null;
    JLabel jl = null;
    JRadioButton[] jrb = new JRadioButton[6];
    int degerler;
    JButton basla = null;
    JButton cikis = null;
    JButton Oyun_Kurallari = null;
    ButtonGroup group = new ButtonGroup();
    Actions action = new Actions();
    private int JFrame_baslangic_x = 50;
    private int JFrame_baslangic_y = 100;
    private int JFrame_baslangic_uzunluk = 500;
    private int JFrame_baslangic_yukseklik = 600;
    JButton Oyuna_geri_don = null;
    JTextArea OyunKurallari_Aciklamasi = null;

    public BaslangicEkranGui() {
        OyunEkrani oe = new OyunEkrani();
        baslangic_muzigi();
        this.setLayout(null);

        this.add(getBasla());
        this.add(getCikis());
        this.add(getOyun_Kurallari());
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
            Font font = new Font("monospaced", Font.BOLD, 17);
            jl.setFont(font);

        }
        return jl;
    }

    public void setJl(JLabel jl) {

        this.jl = jl;
    }

    public JRadioButton[] getJrb() {
        if (jrb[getDegerler()] == null) {
            Font font = new Font("monospaced", Font.BOLD, 13);

            jrb[getDegerler()] = new JRadioButton((getDegerler() + 5) + " x " + (getDegerler() + 5) + " Bölümü Oyna");
            jrb[getDegerler()].setFont(font);
            jrb[getDegerler()].setBounds(150, 125 + getDegerler() * 50, 200, 30);
            jrb[getDegerler()].setSelected(false);
            jrb[getDegerler()].setFocusable(false);
            jrb[getDegerler()].addActionListener(action);

        }
        return jrb;
    }

    public void setJrb(JRadioButton[] jrb) {
        this.jrb = jrb;
    }

    public JButton getOyuna_geri_don() {
        if (Oyuna_geri_don == null) {
            Oyuna_geri_don = new JButton("OYUNA GERİ DÖN");
            Oyuna_geri_don.setBounds(150, 420, 200, 40);
            Oyuna_geri_don.addActionListener(action);
            Font font = new Font("", Font.BOLD, 15);
            Oyuna_geri_don.setFont(font);
            Oyuna_geri_don.setCursor(new Cursor(12));
            Oyuna_geri_don.setFocusable(false);

        }
        return Oyuna_geri_don;
    }

    public void setOyuna_geri_don(JButton Oyuna_geri_don) {
        this.Oyuna_geri_don = Oyuna_geri_don;
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
            basla.setBounds(150, 510, 90, 40);
            basla.setBackground(Color.gray);
            basla.setForeground(Color.WHITE);
            Font font = new Font("", Font.BOLD, 15);
            basla.setFont(font);
            basla.setCursor(new Cursor(12));
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
            cikis.setBounds(260, 510, 90, 40);
            cikis.setBackground(Color.white);
            cikis.setForeground(Color.gray);
            //cikis.setBackground(Color.white);
            Font font = new Font("", Font.BOLD, 15);
            cikis.setFont(font);
            cikis.setCursor(new Cursor(12));

            cikis.setFocusable(false);
            cikis.addActionListener(action);
        }
        return cikis;
    }

    public void setCikis(JButton cikis) {
        this.cikis = cikis;
    }

    public JButton getOyun_Kurallari() {
        if (Oyun_Kurallari == null) {
            Oyun_Kurallari = new JButton("OYUN KURALLARI ");
            Oyun_Kurallari.setBounds(150, 450, 200, 40);
            Oyun_Kurallari.setBackground(Color.yellow);
            Oyun_Kurallari.setForeground(Color.BLACK);
            Font font = new Font("", Font.BOLD, 15);
            Oyun_Kurallari.setFont(font);
            Oyun_Kurallari.setCursor(new Cursor(12));
            Oyun_Kurallari.setFocusable(false);
            Oyun_Kurallari.addActionListener(action);

        }
        return Oyun_Kurallari;
    }

    public void setOyun_Kurallari(JButton Oyun_Kurallari) {
        this.Oyun_Kurallari = Oyun_Kurallari;
    }

    public JFrame getJf_beg() {
        if (jf_beg == null) {
            jf_beg = new JFrame();
            jf_beg.setSize(JFrame_baslangic_uzunluk, JFrame_baslangic_yukseklik);
            jf_beg.setLocation(JFrame_baslangic_x, JFrame_baslangic_y);
            jf_beg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf_beg.setLocationRelativeTo(null);
            jf_beg.setContentPane(this);
            jf_beg.setBackground(Color.BLACK);
            jf_beg.setResizable(false);
            jf_beg.setEnabled(true);
            jf_beg.setUndecorated(true);
        }
        return jf_beg;
    }

    public void setJf_beg(JFrame jf_beg) {

        this.jf_beg = jf_beg;
    }

    public JFrame getJf_oyun_kurallari() {
        if (jf_oyun_kurallari == null) {
            jf_oyun_kurallari = new JFrame();
            jf_oyun_kurallari.setBounds(436, 130, 494, 530);
            jf_oyun_kurallari.setContentPane(getJp_oyun_kurallari());
            jf_oyun_kurallari.setUndecorated(true);

        }
        return jf_oyun_kurallari;
    }

    public void setJf_oyun_kurallari(JFrame jf_oyun_kurallari) {
        this.jf_oyun_kurallari = jf_oyun_kurallari;
    }

    public JTextArea getOyunKurallari_Aciklamasi() {
        if (OyunKurallari_Aciklamasi == null) {
            OyunKurallari_Aciklamasi = new JTextArea("Oyun Nasıl Oynanır?:\n\n"
                    + "1) İstenilen bir kare seçilerek oyuna başlanır.  \n"
                    + "2) Tüm kareler düz ilerlerken iki kare ve çapraz \n"
                    + "ilerlerken bir kare atlamak koşuluyla doldurulur.\n"
                    + "3) Mavi kare bulunulan adımı gösterir.\n"
                    + "4) Beyaz kareler ilerlenebilecek yönleri gösterir. \n"
                    + "5) Gri kare yanlış adımı gösterir.\n\n\n"
                    + "Oyun İçinde Bulunan Butonlar Ne İşe Yarar?:\n\n"
                    + "-‘Geri Adım At’  :Oyunda 10 geri adim hakkınız vardır.\n"
                    + "-‘Sıfırla’       :Aynı kategoriye baştan başlar. \n"
                    + "-‘Ana Menüye Dön’:Farklı bir bölüm seçebilirsiniz.\n"
                    + "-‘Müzik Aç-Kapa’ :Müziğin sesini açıp kapatılabilir.");
            OyunKurallari_Aciklamasi.setBounds(30, 20, 510, 400);
            OyunKurallari_Aciklamasi.setEditable(false);
            OyunKurallari_Aciklamasi.setBackground(getJp_oyun_kurallari().getBackground());
            OyunKurallari_Aciklamasi.setFocusable(false);
            OyunKurallari_Aciklamasi.setFont(new Font("monospaced", 0, 14));
            OyunKurallari_Aciklamasi.setForeground(Color.WHITE);

        }
        return OyunKurallari_Aciklamasi;
    }

    public void setOyunKurallari_Aciklamasi(JTextArea OyunKurallari_Aciklamasi) {
        this.OyunKurallari_Aciklamasi = OyunKurallari_Aciklamasi;
    }

    public JPanel getJp_oyun_kurallari() {
        if (jp_oyun_kurallari == null) {
            jp_oyun_kurallari = new JPanel();
            jp_oyun_kurallari.setBounds(getJf_oyun_kurallari().getBounds());
            jp_oyun_kurallari.setBackground(Color.BLACK);
            jp_oyun_kurallari.add(getOyuna_geri_don());
            jp_oyun_kurallari.add(getOyunKurallari_Aciklamasi());
            jp_oyun_kurallari.setLayout(null);
        }
        return jp_oyun_kurallari;
    }

    public void setJp_oyun_kurallari(JPanel jp_oyun_kurallari) {
        this.jp_oyun_kurallari = jp_oyun_kurallari;
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
