package Logic;

import Gui.BaslangicEkranGui;
import Gui.Gecen_Sure;
import Gui.OyunEkrani;
import com.sun.scenario.Settings;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Actions implements ActionListener {

    BaslangicEkranGui beg = null;
    int deger = 0;
    boolean baslangic_ekrani_calismaya_devam_ediyor = true;
    OyunEkrani oe = new OyunEkrani();
    Gecen_Sure gecen_sure = null;
    private int adim_sayisi = 0, satir = 0, sutun = 0;
    boolean kuzey = false, kuzey_dogu = false,
            dogu = false,
            guney_dogu = false,
            guney = false,
            guney_bati = false,
            bati = false,
            kuzey_bati = false;
    Color anlık_renk = Color.BLUE;
    Color gidilebilecek_renk = Color.WHITE;

    final int adim_i = 0;
    final int adim_j = 0;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (beg != null) {

            if (baslangic_ekrani_calismaya_devam_ediyor) {
                for (int i = 0; i < 6; i++) {
                    if (e.getSource() == getBeg().getJrb()[i]) {
                        deger = i + 5;

                        getBeg().getJl().setForeground(Color.WHITE);
                        getBeg().getJl().setText("Artık Oyuna Başlamaya Hazırsınız");
                        getBeg().getJl().setBounds(85, 70, 350, 30);
                        getBeg().getBasla().setBackground(Color.WHITE);
                        getBeg().getBasla().setForeground(Color.GRAY);
                        getBeg().getCikis().setBackground(Color.gray);
                        getBeg().getCikis().setForeground(Color.white);

                    }
                }
                if (e.getSource() == getBeg().getBasla()) {
                    if (getBeg().getBasla().getBackground() != Color.WHITE) {
                        JOptionPane.showMessageDialog(null, "Oyuna başlamak için alan büyüklüğünü seçmelisiniz");
                    } else {
                        if (getBeg().clip.isRunning()) {
                            getBeg().clip.stop();
                            getOe().Oyun_ici_music_cal();

                        }
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

                        //  getOe().Oyun_ici_music_cal();
                        getOe().oyunu_getir();

                        //System.out.println("gecen süre : " + getGecen_sure());
                    }
                }
            }

            if (e.getSource() == getBeg().getCikis()) {
                int cevap = JOptionPane.showConfirmDialog(null, "Çıkmak İstediğinize Emin Misiniz?", "ÇIKIŞ UYARISI", 2);
                if (cevap == 0) {
                    System.exit(0);
                }

                // 0=yes, 1=no, 2=cancel
            }
        } else {
            getGecen_sure().sureyi_baslat();
            for (int i = 0; i < getOe().getSatir(); i++) {
                for (int j = 0; j < getOe().getSutun(); j++) {
                    if (getOe().getButtonlar()[i][j].getBackground() == Color.gray
                            || getOe().getButtonlar()[i][j].getBackground() == anlık_renk) {
                        getOe().getButtonlar()[i][j].setBackground(Color.BLACK);
                    }
                }
            }

            for (int i = 0; i < getOe().getSatir(); i++) {
                for (int j = 0; j < getOe().getSutun(); j++) {

                    if (e.getSource() == getOe().getButtonlar()[i][j]) {

                        if (getOe().isIlk_giris()) {

                            setSatir(i);
                            setSutun(j);
                            setAdim_sayisi(1);

                            getOe().setIlk_giris(false);

                            if (ilerlenebilecek_yonler());
                            {
                            }
                            gidilen_bolge();
                            yonleri_boya(gidilebilecek_renk);
                            getOe().getJtextfield_skor().setText("SKOR : " + getAdim_sayisi());

                            getOe().getButtonlar()[i][j].setText(Integer.toString(getAdim_sayisi()));
                        } else {

                            if (getOe().getButtonlar()[i][j].getBackground() == gidilebilecek_renk) {

                                //   yonleri_boya(Color.BLACK);
                                yonleri_temizle();
                                setSatir(i);
                                setSutun(j);
                                gidilen_bolge();
                                setAdim_sayisi(getAdim_sayisi() + 1);
                                getOe().getJtextfield_skor().setText("SKOR : " + getAdim_sayisi());
                                getOe().getButtonlar()[i][j].setText(Integer.toString(getAdim_sayisi()));

                                if (!ilerlenebilecek_yonler()) {
                                    if (getOe().oyun_ici_music_clip.isRunning()) {
                                        getOe().oyun_ici_music_clip.stop();

                                    }
                                    getGecen_sure().sureyi_durdur();
                                    if (getAdim_sayisi() == getOe().getSatir() * getOe().getSatir()) {

                                        getOe().Alkisla();
                                        JOptionPane.showMessageDialog(null, " TEBRİKLER OYUNU BAŞARIYLA BİTİRDİNİZ ");
                                    } else {

                                        getOe().bitmeyen_oyun();
                                        JOptionPane.showMessageDialog(null, "Gidilecek yolunuz kalmamıştır. \n" + getOe().getKronometre().getText() + "\nSkorunuz : " + getAdim_sayisi());
                                    }
                                    getOe().getJf_oe().setVisible(false);
                                    BaslangicEkranGui bg = new BaslangicEkranGui();

                                    return;
                                }

                                yonleri_boya(gidilebilecek_renk);

                            } else {
                                java.awt.Toolkit.getDefaultToolkit().beep();
                                getOe().getButtonlar()[i][j].setBackground(Color.gray);
                            }
                        }
                        getOe().getButtonlar()[getSatir()][getSutun()].setBackground(anlık_renk);

                    }

                }
            }

            if (e.getSource() == getOe().getAna_menu()) {

                getGecen_sure().sureyi_durdur();
                int cevap = JOptionPane.showConfirmDialog(null, "Oyununuz Kaydedilmeyecektir.  Çıkmak İstediğinize Emin Misiniz ? ", " UYARI ", 0, 2);

                if (cevap == 0) {
                    if (getOe().oyun_ici_music_clip.isRunning()) {
                        getOe().oyun_ici_music_clip.stop();

                    }
                    getOe().getJf_oe().setVisible(false);
                    BaslangicEkranGui bg = new BaslangicEkranGui();
                } else {
                    getGecen_sure().sureyi_baslat();
                }
            } else if (e.getSource() == getOe().getSıfırla()) {
                getGecen_sure().sureyi_durdur();
                int cevap = JOptionPane.showConfirmDialog(null, "Oyunuz Sıfırlandığında Geri Dönüşünüz Olmayacaktır. Sıfırlamak İstediğinize Emin Misiniz?", "OYUNU SIFIRLAMA", 0, 2);
                if (cevap == 0) {
                    if (getAdim_sayisi() >= 1) {
                        Oyun_Sıfırlama();
                    } else {
                        JOptionPane.showMessageDialog(null, "Oyunu Sıfırlamak için en az bir adım atmış olmanız gerekiyor");
                    }
                } else {
                    getGecen_sure().sureyi_baslat();
                }
            }
            if (e.getSource() == getOe().getGeri_adim_at()) {
                if (getAdim_sayisi() > 1) {

                    Geri_adim_at();
                } else {
                    getGecen_sure().sureyi_durdur();
                    JOptionPane.showMessageDialog(null, "Zaten En düşük Adımdasınız Başlangic Yerinizi Değiştirmek İstiyorsanız Oyunu Sıfırlayın");
                    getGecen_sure().sureyi_baslat();
                }
            }

        }

    }

    public void Oyun_Sıfırlama() {
        for (int i = 0; i < getOe().getSatir(); i++) {
            for (int j = 0; j < getOe().getSutun(); j++) {
                getOe().getGidilen_bolgeler()[i][j] = false;
                getOe().getButtonlar()[i][j].setText("");

            }
        }
        yonleri_temizle();
        getOe().setIlk_giris(true);

        getGecen_sure().timer.stop();
        gecen_sure = new Gecen_Sure(this);
        gecen_sure.sureyi_baslat();
        getOe().getKronometre().setText(" Geçen Süre : 00:00:00");// süreyi sıfırlar
        System.out.println(" getGecen_sure() : " + getGecen_sure());
    }

    /*    yonleri_temizle();
                                setSatir(i);
                                setSutun(j);
                                gidilen_bolge();
                                setAdim_sayisi(getAdim_sayisi() + 1);
                                getOe().getJtextfield_skor().setText("SKOR : " + getAdim_sayisi());
                                getOe().getButtonlar()[i][j].setText(Integer.toString(getAdim_sayisi()));
     */
    public void Geri_adim_at() {

        getOe().getGidilen_bolgeler()[getSatir()][getSutun()] = false;
        getOe().getButtonlar()[getSatir()][getSutun()].setText("");
        setAdim_sayisi(getAdim_sayisi() - 1);
        for (int i = 0; i < getOe().getSatir(); i++) {
            for (int j = 0; j < getOe().getSutun(); j++) {

                if (getOe().getButtonlar()[i][j].getText().equals(Integer.toString(getAdim_sayisi()))) {
                    yonleri_temizle();
                    setSatir(i);
                    setSutun(j);

                    if (ilerlenebilecek_yonler()) {

                        yonleri_boya(gidilebilecek_renk);
                        getOe().getButtonlar()[getSatir()][getSutun()].setBackground(anlık_renk);
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

        if (getSutun() + 3 < getOe().getSutun() && !bolge_gidilmismi_kontrolu(getSatir(), getSutun() + 3)) {

            dogu = true;
            kac_tane_yon_var++;
        }

        if (getSatir() - 3 >= 0 && !bolge_gidilmismi_kontrolu(getSatir() - 3, getSutun())) {
            guney = true;
            kac_tane_yon_var++;
        }

        if (getSutun() - 3 >= 0 && !bolge_gidilmismi_kontrolu(getSatir(), getSutun() - 3)) {
            bati = true;
            kac_tane_yon_var++;
        }

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
            //   System.out.println("KUZEY getSatir() + 3 : " + (getSatir() + 3));
            //  System.out.println("KUZEY getSutun() " + getSutun());
            getOe().getButtonlar()[getSatir() + 3][getSutun()].setBackground(color);
        }
        if (kuzey_dogu) {
            // System.out.println("KUZEY_DOGU getSatir() + 2 : " + (getSatir() + 2));
            //  System.out.println("KUZEY_DOGU getSutun() + 2 " + (getSutun() + 2));
            getOe().getButtonlar()[getSatir() + 2][getSutun() + 2].setBackground(color);
        }
        if (dogu) {
            // System.out.println("DOGU getSatir()  : " + getSatir());
            //  System.out.println("DOGU getSutun() + 3" + (getSutun() + 3));
            getOe().getButtonlar()[getSatir()][getSutun() + 3].setBackground(color);
        }
        if (guney_dogu) {
            //  System.out.println("GUNEY_DOGU getSatir() - 2  : " + (getSatir() - 2));
            //  System.out.println("GUNEY_DOGU getSutun()+2 " + (getSutun() + 2));
            getOe().getButtonlar()[getSatir() - 2][getSutun() + 2].setBackground(color);
        }
        if (guney) {
            //   System.out.println("GUNEY getSatir() - 3 : " + (getSatir() - 3));
            //  System.out.println("GUNEYgetSutun() " + getSutun());
            getOe().getButtonlar()[getSatir() - 3][getSutun()].setBackground(color);
        }
        if (guney_bati) {
            //   System.out.println("GUNEY_BATİ getSatir() - 2 : " + (getSatir() - 2));
            //   System.out.println("GUNEY_BATİgetSutun() -2  " + (getSutun() - 2));
            getOe().getButtonlar()[getSatir() - 2][getSutun() - 2].setBackground(color);
        }
        if (bati) {
            //  System.out.println("BATİ getSatir()  : " + (getSatir()));
            //  System.out.println("BATİ  getSutun() " + (getSutun() - 3));
            getOe().getButtonlar()[getSatir()][getSutun() - 3].setBackground(color);
        }
        if (kuzey_bati) {
            //  System.out.println("KUZEY_BATİ getSatir() +2 : " + (getSatir() + 2));
            //  System.out.println("KUZEY_BATİ getSutun() -2 " + (getSutun() - 2));
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

    public Gecen_Sure getGecen_sure() {
        if (gecen_sure == null) {
            gecen_sure = new Gecen_Sure(this);
        }

        return gecen_sure;
    }

    public void setGecen_sure(Gecen_Sure gecen_sure) {
        this.gecen_sure = gecen_sure;
    }

}

//istersen oyunundaki kutu rengi, yazı rengi ve arkaplan rengini oyuncuya bırakabiliriz
