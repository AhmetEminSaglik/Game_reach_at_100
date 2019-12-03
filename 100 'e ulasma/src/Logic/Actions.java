package Logic;

import Gui.BaslangicEkranGui;
import Gui.OyunEkrani;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

public class Actions implements ActionListener {

    BaslangicEkranGui beg = null;
    int deger = 0;
    boolean baslangic_ekrani_calismaya_devam_ediyor = true;
    OyunEkrani oe = new OyunEkrani();

    private int adim_sayisi = 0, satir = 0, sutun = 0;
    boolean kuzey = false, kuzey_dogu = false,
            dogu = false,
            guney_dogu = false,
            guney = false,
            guney_bati = false,
            bati = false,
            kuzey_bati = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("beg : " + beg);
        if (beg != null) {
            if (baslangic_ekrani_calismaya_devam_ediyor) {
                for (int i = 0; i < 6; i++) {
                    if (e.getSource() == getBeg().getJrb()[i]) {
                        deger = i + 5;
// diğer class'a bu degeri gönderecem
                        getBeg().getJl().setForeground(Color.GREEN);
                        getBeg().getJl().setText("                ARTIK OYUNA  BAŞLAYABİLİRSİNİZ ");
                        getBeg().getBasla().setBackground(Color.GREEN);
                        getBeg().getBasla().setForeground(Color.BLUE);

                    }
                }
                if (e.getSource() == getBeg().getBasla()) {
                    if (getBeg().getBasla().getBackground() != Color.GREEN) {
                        JOptionPane.showMessageDialog(null, "Oyuna başlamak için alan büyüklüğünü seçmelisiniz");
                    } else {

                        baslangic_ekrani_calismaya_devam_ediyor = false;
                        getBeg().getJf_beg().setVisible(false);
                        oe.setSatir(deger);
                        oe.setSutun(deger);
                        int jframe_x_baslangic = 0;
                        int jframe_y_baslangic = 0;
                        int jframe_uzunluk = 100;
                        int jframe_yukseklik = 100;

                        oe.getJf_oe().setVisible(true);
                        oe.getJf_oe().setBackground(Color.BLACK);

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        boolean jframe_genisliyor = true;

                        while (jframe_genisliyor) {

                            jframe_uzunluk += 63;
                            jframe_yukseklik += 36;
                            getOe().getJf_oe().setSize(jframe_uzunluk, jframe_yukseklik);
                            getOe().width += jframe_uzunluk;
                            getOe().height += jframe_yukseklik;
                            //getOe().setSize(getOe(), deger);
                            //getOe().getJf_oe().setContentPane(getOe());
                            getOe().setBounds(0, 0, getOe().width, getOe().height);

                            try {
                                Thread.sleep(15);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            oe.getJf_oe().setLocationRelativeTo(null);
                            if (jframe_uzunluk == getOe().getJFrame_baslangic_uzunluk()) {

                                jframe_genisliyor = false;
                            }
                        }
                        getOe().oyunu_getir();

                    }
                }
            }
        } else {
            for (int i = 0; i < getOe().getSatir(); i++) {
                for (int j = 0; j < getOe().getSutun(); j++) {
                    if (getOe().getButtonlar()[i][j].getBackground() == Color.ORANGE) {
                        getOe().getButtonlar()[i][j].setBackground(Color.BLACK);
                    }
                }
            }

            for (int i = 0; i < getOe().getSatir(); i++) {
                for (int j = 0; j < getOe().getSutun(); j++) {

                    if (e.getSource() == getOe().getButtonlar()[i][j]) {

                        System.out.println(i + " - " + j);
                        if (getOe().isIlk_giris()) {
                            setAdim_sayisi(1);
                            setSatir(i);
                            setSutun(j);
                            System.out.println("i : " + i + " j : " + j);
                            getOe().setIlk_giris(false);
                            getOe().getButtonlar()[i][j].setText(Integer.toString(getAdim_sayisi()));
                            if (ilerlenebilecek_yonler());
                            {
                            }
                            gidilen_bolge();
                            yonleri_boya(Color.GREEN);

                        } else {

                            if (getOe().getButtonlar()[i][j].getBackground() == Color.GREEN) {

                                System.out.println("siyaha boyama");
                                //   yonleri_boya(Color.BLACK);
                                yonleri_temizle();
                                setSatir(i);
                                setSutun(j);
                                gidilen_bolge();
                                setAdim_sayisi(getAdim_sayisi() + 1);
                                getOe().getButtonlar()[getSatir()][getSutun()].setText(Integer.toString(getAdim_sayisi()));
                                if (!ilerlenebilecek_yonler()) {

                                    if (getAdim_sayisi() == getOe().getSatir() * getOe().getSatir()) {
                                        getOe().Alkisla();
                                    } else {

                                        getOe().bitmeyen_oyun();

                                    }
                                    JOptionPane.showMessageDialog(null, "Gidilecek yolunuz kalmamıştır. Skorunuz : " + getAdim_sayisi());
                                    getOe().getJf_oe().setVisible(false);
                                    BaslangicEkranGui bg = new BaslangicEkranGui();

                                    return;
                                }
                                System.out.println("yeşile boyama");
                                yonleri_boya(Color.GREEN);

                            } else {
                                java.awt.Toolkit.getDefaultToolkit().beep();
                                getOe().getButtonlar()[i][j].setBackground(Color.ORANGE);
                            }
                        }

                    }
                }
            }

        }

    }

    public void gidilen_bolge() {
        getOe().getGidilen_bolgeler()[getSatir()][getSutun()] = true;
    }

    public boolean bolge_gidilmismi_kontrolu(int satir, int sutun) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (getOe().getGidilen_bolgeler()[satir][sutun]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void yonleri_temizle() {
        yonleri_boya(Color.BLACK);
        kuzey = false;
        kuzey_dogu = false;
        dogu = false;
        guney_dogu = false;
        guney = false;
        guney_bati = false;
        bati = false;
        kuzey_bati = false;
    }

    public boolean ilerlenebilecek_yonler() {
        int kac_tane_yon_var = 0;

        if (getSatir() + 3 < getOe().getSatir() && !bolge_gidilmismi_kontrolu(getSatir() + 3, getSutun())) {
            //JOptionPane.showMessageDialog(null, "(kuzey ilerlenebilecek yon)"
            //  + "getSatir : " + getSatir() + "  oesatir : " + getOe().getSatir());
            kuzey = true;
            kac_tane_yon_var++;
        }

        System.out.println(kuzey);

        if (getSutun() + 3 < getOe().getSutun() && !bolge_gidilmismi_kontrolu(getSatir(), getSutun() + 3)) {

            dogu = true;
            kac_tane_yon_var++;
        }
        System.out.println(dogu);

        if (getSatir() - 3 >= 0 && !bolge_gidilmismi_kontrolu(getSatir() - 3, getSutun())) {
            guney = true;
            kac_tane_yon_var++;
        }
        System.out.println(guney);

        if (getSutun() - 3 >= 0 && !bolge_gidilmismi_kontrolu(getSatir(), getSutun() - 3)) {
            bati = true;
            kac_tane_yon_var++;
        }
        System.out.println(bati);

        if (getSatir() + 2 < getOe().getSatir()) {

            if (getSutun() + 2 < getOe().getSutun() && !bolge_gidilmismi_kontrolu(getSatir() + 2, getSutun() + 2)) {
                kuzey_dogu = true;
                kac_tane_yon_var++;
            }

            if (getSutun() - 2 >= 0 && !bolge_gidilmismi_kontrolu(getSatir() + 2, getSutun() - 2)) {
                kuzey_bati = true;
                kac_tane_yon_var++;
            }

        }
        System.out.println(kuzey_dogu);
        System.out.println(kuzey_bati);

        if (getSatir() - 2 >= 0) {

            if (getSutun() + 2 < getOe().getSutun() && !bolge_gidilmismi_kontrolu(getSatir() - 2, getSutun() + 2)) {
                guney_dogu = true;
                kac_tane_yon_var++;
            }

            if (getSutun() - 2 >= 0 && !bolge_gidilmismi_kontrolu(getSatir() - 2, getSutun() - 2)) {
                guney_bati = true;
                kac_tane_yon_var++;
            }

        }
        if (kac_tane_yon_var == 0) {
            return false;
        }
        return true;
    }

    public void yonleri_boya(Color color) {
        if (kuzey) {
//            JOptionPane.showMessageDialog(null, "getSatir : " + getSatir() + "------geosatir " + getOe().getSatir());

            System.out.println();
            //   System.out.println("getSatir +3 : " + (getSatir() + 3));
            //  JOptionPane.showMessageDialog(null, "getSatir : " + getSatir() + "   --- getsatir+üç : " + (getSatir() + 3));
            getOe().getButtonlar()[getSatir() + 3][getSutun()].setBackground(color);
        }
        if (kuzey_dogu) {

            getOe().getButtonlar()[getSatir() + 2][getSutun() + 2].setBackground(color);
        }
        if (dogu) {

            getOe().getButtonlar()[getSatir()][getSutun() + 3].setBackground(color);
        }
        if (guney_dogu) {
            getOe().getButtonlar()[getSatir() - 2][getSutun() + 2].setBackground(color);
        }
        if (guney) {
            getOe().getButtonlar()[getSatir() - 3][getSutun()].setBackground(color);
        }
        if (guney_bati) {
            getOe().getButtonlar()[getSatir() - 2][getSutun() - 2].setBackground(color);
        }
        if (bati) {
            getOe().getButtonlar()[getSatir()][getSutun() - 3].setBackground(color);
        }
        if (kuzey_bati) {
            getOe().getButtonlar()[getSatir() + 2][getSutun() - 2].setBackground(color);
        }
    }

    public BaslangicEkranGui getBeg() {

        return beg;
    }

    public void setBeg(BaslangicEkranGui beg) {
        this.beg = beg;
    }

    public OyunEkrani getOe() {
        if (oe == null) {
            oe = new OyunEkrani();
        }
        return oe;
    }

    public void setOe(OyunEkrani oe) {
        this.oe = oe;
    }

    public int getAdim_sayisi() {
        return adim_sayisi;
    }

    public void setAdim_sayisi(int adim_sayisi) {
        this.adim_sayisi = adim_sayisi;
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

}