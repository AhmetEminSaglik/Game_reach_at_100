package Logic;

import Gui.BaslangicEkranGui;
import Gui.Gecen_Sure;
import Gui.OyunEkrani;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Actions implements ActionListener {

    BaslangicEkranGui beg = null;
    int deger = 0;
    boolean baslangic_ekrani_calismaya_devam_ediyor = true;
    OyunEkrani oe = new OyunEkrani();
    Gecen_Sure gecen_sure = null;
    int geriye_atılabilecek_max_adim_sayisi = 10;
    private int adim_sayisi = 0, satir, sutun;
    boolean kuzey = false, kuzey_dogu = false,
            dogu = false,
            guney_dogu = false,
            guney = false,
            guney_bati = false,
            bati = false,
            kuzey_bati = false;
    Color anlık_renk = Color.BLUE;
    Color gidilebilecek_renk = Color.WHITE;
    boolean music_acik_kontrol = true;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (getBeg() != null) {

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
                    if (getBeg().getJf_oyun_kurallari().isVisible()) {
                        getBeg().getJf_oyun_kurallari().setVisible(false);
                    }
                    baslangic_ekrani_calismaya_devam_ediyor = false;
                    String title_ek = "";
                    if (deger % 6 == 0) {
                        title_ek = Integer.toString((deger * deger)) + " 'ya";
                    } else if (deger % 7 == 0) {
                        title_ek = Integer.toString((deger * deger)) + " 'a";
                    } else {
                        title_ek = Integer.toString((deger * deger)) + " 'e";

                    }
                    getOe().getJf_oe().setTitle("BİRDEN " + title_ek);
                    getBeg().getJf_beg().setVisible(false);
                    oe.setSatir(deger);
                    oe.setSutun(deger);

                    oe.getJf_oe().setVisible(true);
                    oe.getJf_oe().setBackground(Color.BLACK);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    boolean jframe_genisliyor = true;

                    getOe().width = 100;
                    getOe().height = 100;
                    while (jframe_genisliyor) {

                        getOe().width += 63;
                        getOe().height += 36;
                        getOe().getJf_oe().setSize(getOe().width, getOe().height);

                        getOe().setBounds(0, 0, getOe().width, getOe().height);

                        try {
                            Thread.sleep(15);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        oe.getJf_oe().setLocationRelativeTo(null);
                        if (getOe().width == getOe().getJFrame_baslangic_uzunluk()) {

                            jframe_genisliyor = false;
                        }
                    }

                }
                getOe().oyunu_getir();
            }

            if (e.getSource() == getBeg().getCikis()) {
                int cevap = JOptionPane.showConfirmDialog(null, "Çıkmak İstediğinize Emin Misiniz?", "ÇIKIŞ UYARISI", 2);
                if (cevap == 0) {
                    System.exit(0);
                }

            }
            if (e.getSource() == getBeg().getOyun_Kurallari()) {
                getBeg().getJf_oyun_kurallari().setVisible(true);

            }
            if (e.getSource() == getBeg().getOyuna_geri_don()) {
                getBeg().getJf_oyun_kurallari().setVisible(false);
            }
        } else {
            if (e.getSource() != getOe().getAyarlar()) {
                getOe().getJf_ayarlar().setVisible(false);
            }
            for (int i = 0; i < getOe().getSatir(); i++) {
                for (int j = 0; j < getOe().getSutun(); j++) {

                    if (getOe().getButtonlar()[i][j].getBackground() == Color.gray
                            || getOe().getButtonlar()[i][j].getBackground() == anlık_renk) {
                        getOe().getButtonlar()[i][j].setBackground(Color.BLACK);
                    }
                }
            }

            if (e.getSource() == getOe().getAyarlar()) {
                getOe().getJf_ayarlar().setVisible(true);

            }
            if (e.getSource() == getOe().getMusic_ac_kapat()) {
                if (music_acik_kontrol) {
                    Icon music_off = getOe().music_off;
                    getOe().getMusic_ac_kapat().setIcon(music_off);
                    music_acik_kontrol = false;
                    if (getOe().oyun_ici_music_clip.isRunning()) {
                        getOe().oyun_ici_music_clip.stop();
                    }
                } else {
                    Icon music_on = getOe().music_on;
                    getOe().getMusic_ac_kapat().setIcon(music_on);
                    music_acik_kontrol = true;
                    if (!getOe().oyun_ici_music_clip.isRunning()) {
                        getOe().oyun_ici_music_clip.start();

                    }
                }

            }
            for (int i = 0; i < getOe().getSatir(); i++) {
                for (int j = 0; j < getOe().getSutun(); j++) {

                    if (e.getSource() == getOe().getButtonlar()[i][j]) {
                        getGecen_sure().sureyi_baslat();

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
                            if (!getGecen_sure().timer.isRunning()) {
                                getGecen_sure().sureyi_baslat();
                            }

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
                                   /* if (getOe().oyun_ici_music_clip.isRunning()) {
                                        getOe().oyun_ici_music_clip.stop();

                                    }*/
                                    getGecen_sure().sureyi_durdur();
                                    if (getAdim_sayisi() == getOe().getSatir() * getOe().getSatir()) {

                                        getOe().Alkisla();
                                        JOptionPane.showMessageDialog(null, " TEBRİKLER OYUNU BAŞARIYLA BİTİRDİNİZ ");
                                    } else {

                                   /*     getOe().bitmeyen_oyun();*/
                                        JOptionPane.showMessageDialog(null, "Gidilecek yolunuz kalmamıştır. \n" + getOe().getKronometre().getText() + "\nSkorunuz : " + getAdim_sayisi());
                                    }
                                    /*   getOe().getJf_oe().setVisible(false);
                                   BaslangicEkranGui bg = new BaslangicEkranGui();*/
                                    Oyun_Sıfırlama();
                                    getOe().Oyun_ici_music_cal();
                                   getOe().oyun_ici_music_clip.start();
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
                 /*   if (getOe().oyun_ici_music_clip.isRunning()) {
                        getOe().oyun_ici_music_clip.stop();

                    }*/
                    getOe().getJf_oe().setVisible(false);
                    BaslangicEkranGui bg = new BaslangicEkranGui();

                } else {
                    if (!getOe().isIlk_giris()) {
                        getGecen_sure().sureyi_baslat();
                    }
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
                    if (!getOe().isIlk_giris()) {
                        getGecen_sure().sureyi_baslat();
                    }
                }
            }
            if (e.getSource() == getOe().getGeri_adim_at()) {
                if (getAdim_sayisi() > 1) {

                    Geri_adim_at();
                } else {
                    getGecen_sure().sureyi_durdur();
                    JOptionPane.showMessageDialog(null, "Zaten En düşük Adımdasınız Başlangic Yerinizi Değiştirmek İstiyorsanız Oyunu Sıfırlayın");
                    if (!getOe().isIlk_giris()) {
                        if (!getOe().isIlk_giris()) {
                            getGecen_sure().sureyi_baslat();
                        }
                    }
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
        gecen_sure = new Gecen_Sure(this);//sureyi sıfırlar

        getOe().getJtextfield_skor().setText("SKOR : ");
        getOe().getKronometre().setText(" Geçen Süre : 00:00:00");
        geriye_atılabilecek_max_adim_sayisi = 10;
        setAdim_sayisi(0);

    }

    public void Geri_adim_at() {
        if (geriye_atılabilecek_max_adim_sayisi > 0) {
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
            geriye_atılabilecek_max_adim_sayisi--;
            getOe().getJtextfield_skor().setText("SKOR : " + getAdim_sayisi());
            JOptionPane.showMessageDialog(null, "Kalan Geriye adim atma hakkınız : " + geriye_atılabilecek_max_adim_sayisi);
        } else {
            getOe().getButtonlar()[getSatir()][getSutun()].setBackground(anlık_renk);
            JOptionPane.showMessageDialog(null, "Malesef geriye  adim atacak hiç hakkınız kalmamıştır");
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
