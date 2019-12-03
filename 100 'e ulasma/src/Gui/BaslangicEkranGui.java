package Gui;

import Logic.Actions;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;

public class BaslangicEkranGui extends JLayeredPane {

    JFrame jf_beg = null; //  --> BaslangicEkraniGui  (..._beg) 
    JLabel jl = null;
    JRadioButton[] jrb = new JRadioButton[6];
    int degerler;
    JButton basla = null;
    ButtonGroup group = new ButtonGroup();
    Actions action = new Actions();
    private int JFrame_baslangic_x = 50;
    private int JFrame_baslangic_y = 100;
    private int JFrame_baslangic_uzunluk = 800;
    private int JFrame_baslangic_yukseklik = 600;

    public BaslangicEkranGui() {
        this.setLayout(null);/* jpanel'i extends ettiğimizden  (burayı) this kullandık  
        
         */

        this.add(getBasla());

        this.add(getJl());
        for (int i = 0; i < 6; i++) {
            setDegerler(i);
            group.add(getJrb()[getDegerler()]);
            getJrb()[getDegerler()].setCursor(new Cursor(3));
            getJrb()[getDegerler()].setToolTipText((int) Math.pow((getDegerler() + 5), 2) + " adet kare hazırlanılacak");
            this.add(getJrb()[getDegerler()]);

        }
        action.setBeg(this);

        getJf_beg().setVisible(true);
    }

    public JLabel getJl() {
        if (jl == null) {
            jl = new JLabel("Aşağıdaki seçeneklerden birisini seçip oyuna başlayabilirsiniz ... ");
            jl.setForeground(Color.white);
            jl.setBounds(50, 70, 800, 30);
            Font font = new Font("monospaced", Font.BOLD, 17); // bu classın sadece en sağdakini yazının pixeli olduğunu biliyorum
            jl.setFont(font);
            jl.setForeground(Color.red);
        }
        return jl;
    }

    public void setJl(JLabel jl) {

        this.jl = jl;
    }

    public JRadioButton[] getJrb() {
        if (jrb[getDegerler()] == null) {
            Font font = new Font("monospaced", Font.BOLD, 13); // bu classın sadece en sağdakini yazının pixeli olduğunu biliyorum
            jrb[getDegerler()] = new JRadioButton((getDegerler() + 5) + " - " + (getDegerler() + 5) + " Hedef :  " + (getDegerler() + 5) * (5 + getDegerler()));
            jrb[getDegerler()].setFont(font);
            jrb[getDegerler()].setBounds(150, 175 + getDegerler() * 50, 200, 30);
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
            basla.setBounds(450, 425, 90, 40);
            basla.setBackground(Color.red);
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

    public JFrame getJf_beg() {
        if (jf_beg == null) {
            jf_beg = new JFrame("100'e ulaşma oyunu ");
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

}