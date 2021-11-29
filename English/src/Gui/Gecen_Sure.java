package Gui;

import Logic.Actions;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Gecen_Sure {

    int saniye = 00, dakika = 0, saat = 0;
    public Timer timer = null;

    public Gecen_Sure(Actions action) {

        timer = new Timer(1000, (e) -> {
            saniye++;
            if (saniye == 60) {
                dakika++;
                saniye = 0;
            }
            if (dakika == 60) {
                dakika = 0;
                saat++;
            }

            action.getOe().getKronometre().setText(" Elapsed Time : " + saat / 10 + saat % 10 + ":" + dakika / 10 + dakika % 10 + ":" + saniye / 10 + saniye % 10 + " ");
            if (saat == 100) {
                JOptionPane.showMessageDialog(null, "sure en fazla 100 saat olarak ayarlandı ve şimdi sıfırlanılıyor");
                saniye = 0;
                dakika = 0;
                saat = 0;

            }

        });

    }

    public void sureyi_baslat() {
        timer.start();
    }

    public void sureyi_durdur() {
        timer.stop();
    }

}
