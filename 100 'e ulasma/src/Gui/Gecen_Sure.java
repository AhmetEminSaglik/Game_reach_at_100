package Gui;

import Logic.Actions;
import java.util.TimerTask;
import javax.swing.Action;
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

            action.getOe().getKronometre().setText(" Gecen Sure : " + saat / 10 + saat % 10 + ":" + dakika / 10 + dakika % 10 + ":" + saniye / 10 + saniye % 10 + " ");
            if (saat == 100) {
                JOptionPane.showMessageDialog(null, "sure en fazla 100 saat olarak ayarlandı ve şimdi sıfırlanılıyor");
                saniye = 0;
                dakika = 0;
                saat = 0;

            }

        });
        timer.start();

    }

}
