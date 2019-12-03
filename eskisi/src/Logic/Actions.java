/* 
MAVİ RENK YANLIŞ TIKLAMADA KAYBOLUYOR,
 */
package Logic;

import GUI.ArkaplanGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Actions implements ActionListener {

    boolean[][] onceden_buraya_gelinmis = new boolean[10][10];
    boolean ilk_giris = true;
    // boolean yanlis_tiklandi = false;
    ArkaplanGui apg = null;
    int sayac = 1;
    int satir = 0, sutun = 0;

    public Actions(ArkaplanGui apg) {
        this.apg = apg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int serbert_yon = 0;
        if (ilk_giris != true) {
        }
        for (int k = 0; k < 10; k++) {
            for (int m = 0; m < 10; m++) {
                if (apg.getKutular()[m][k].getBackground() == Color.yellow) {
                    apg.getKutular()[m][k].setBackground(Color.black);
                }
            }
        }

        if (satir + 3 < 10) {
            apg.getKutular()[satir + 3][sutun].setBackground(Color.black);
        }
        if (sutun + 3 < 10) {
            apg.getKutular()[satir][sutun + 3].setBackground(Color.black);
        }
        if (satir - 3 >= 0) {
            apg.getKutular()[satir - 3][sutun].setBackground(Color.black);
        }
        if (sutun - 3 >= 0) {
            apg.getKutular()[satir][sutun - 3].setBackground(Color.black);
        }

        if (satir + 2 < 10) {
            if (sutun + 2 < 10) {
                apg.getKutular()[satir + 2][sutun + 2].setBackground(Color.black);
            }
            if (sutun - 2 >= 0) {
                apg.getKutular()[satir + 2][sutun - 2].setBackground(Color.black);
            }
        }
        if (satir - 2 >= 0) {
            if (sutun + 2 < 10) {
                apg.getKutular()[satir - 2][sutun + 2].setBackground(Color.black);
            }
            if (sutun - 2 >= 0) {
                apg.getKutular()[satir - 2][sutun - 2].setBackground(Color.black);
            }
        }
        /* if (ilk_giris != true && yanlis_tiklandi == true) {
            apg.getKutular()[satir][sutun].setBackground(Color.blue);
        }*/

        //yedek.getKutular()[yedek.getSatir()][yedek.getSutun()] = (JButton) e.getSource();
        //System.out.println("yedek.getSatir()][yedek.getSutun() " + yedek.getSatir() + ", " + yedek.getSutun());
        if (ilk_giris == true) {

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    onceden_buraya_gelinmis[i][j] = false;
                }
            }

        }
        //JOptionPane.showMessageDialog(null, " Tikladiginiz button : " + satir + "," + sutun);
        //  JButton[][] deneme = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (e.getSource() == apg.getKutular()[i][j]) {//apg.getKutular()[apg.getSatir()][apg.getSutun()]
                    System.out.println("sayac : " + sayac);

                    //apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                    if (ilk_giris != true) {
                        System.out.println("sayac : (ilk giris!=true)" + sayac);
                        /*     satir = i;
                        sutun = j;
                        ilk_giris = false;
                        apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                        apg.getKutular()[satir][sutun].setBackground(Color.blue);
                        apg.getKutular()[satir][sutun].setForeground(Color.white);*/
                        System.out.println(" i :" + i + " ; satir :   --> " + satir);
                        if (satir + 3 == i && sutun == j && onceden_buraya_gelinmis[satir + 3][sutun] == false) {
                            satir = i;
                            System.out.println("sayac : (ilk giris!=true satir +3)" + sayac);
                            apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                            apg.getKutular()[satir][sutun].setBackground(Color.blue);
                            apg.getKutular()[satir][sutun].setForeground(Color.white);
                            onceden_buraya_gelinmis[satir][sutun] = true;
                            sayac++;

                            //satir + 3 < 10 && gerek yok
                        } else if (satir == i && sutun + 3 == j && onceden_buraya_gelinmis[satir][sutun + 3] == false) {

                            sutun = j;
                            apg.getKutular()[satir][sutun].setForeground(Color.white);

                            apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                            apg.getKutular()[satir][sutun].setBackground(Color.blue);
                            onceden_buraya_gelinmis[satir][sutun] = true;
                            sayac++;

                            //satir + 3 < 10 && gerek yok
                        } else if (satir == i && sutun - 3 == j && onceden_buraya_gelinmis[satir][sutun - 3] == false) {

                            sutun = j;
                            apg.getKutular()[satir][sutun].setForeground(Color.white);

                            apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                            apg.getKutular()[satir][sutun].setBackground(Color.blue);
                            onceden_buraya_gelinmis[satir][sutun] = true;
                            sayac++;

                        } else if (satir - 3 == i && sutun == j && onceden_buraya_gelinmis[satir - 3][sutun] == false) {
                            satir = i;
                            apg.getKutular()[satir][sutun].setForeground(Color.white);

                            apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                            apg.getKutular()[satir][sutun].setBackground(Color.blue);
                            onceden_buraya_gelinmis[satir][sutun] = true;
                            sayac++;

                        } else if (satir + 2 == i && sutun + 2 == j && onceden_buraya_gelinmis[satir + 2][sutun + 2] == false) {
                            satir = i;
                            sutun = j;
                            apg.getKutular()[satir][sutun].setForeground(Color.white);

                            apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                            apg.getKutular()[satir][sutun].setBackground(Color.blue);
                            onceden_buraya_gelinmis[satir][sutun] = true;
                            sayac++;

                        } else if (satir + 2 == i && sutun - 2 == j && onceden_buraya_gelinmis[satir + 2][sutun - 2] == false) {
                            satir = i;
                            sutun = j;
                            apg.getKutular()[satir][sutun].setForeground(Color.white);

                            apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                            apg.getKutular()[satir][sutun].setBackground(Color.blue);
                            onceden_buraya_gelinmis[satir][sutun] = true;
                            sayac++;

                        } else if (satir - 2 == i && sutun + 2 == j && onceden_buraya_gelinmis[satir - 2][sutun + 2] == false) {
                            satir = i;
                            sutun = j;
                            apg.getKutular()[satir][sutun].setForeground(Color.white);

                            apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                            apg.getKutular()[satir][sutun].setBackground(Color.blue);
                            onceden_buraya_gelinmis[satir][sutun] = true;
                            sayac++;

                        } else if (satir - 2 == i && sutun - 2 == j && onceden_buraya_gelinmis[satir - 2][sutun - 2] == false) {
                            satir = i;
                            sutun = j;
                            apg.getKutular()[satir][sutun].setForeground(Color.white);

                            apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                            apg.getKutular()[satir][sutun].setBackground(Color.blue);
                            onceden_buraya_gelinmis[satir][sutun] = true;
                            sayac++;

                        } else {
                            if (i != satir || j != sutun) {
                                apg.getKutular()[i][j].setBackground(Color.yellow);
                            }
                            //   yanlis_tiklandi = true;
                            java.awt.Toolkit.getDefaultToolkit().beep();

                        }

                    } else if (ilk_giris == true) {
                        satir = i;
                        sutun = j;
                        ilk_giris = false;
                        apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
                        apg.getKutular()[satir][sutun].setBackground(Color.blue);
                        apg.getKutular()[satir][sutun].setForeground(Color.white);
                        onceden_buraya_gelinmis[satir][sutun] = true;
                        sayac++;

                    }

                    break;
                }

            }
        }
        System.out.println("deneme dizisi : satir ve sutun : " + satir + " | " + sutun);
        //deneme[satir][sutun] = sayac;

        /*  if (onceden_buraya_gelinmis[satir][sutun] == false) {
            apg.getKutular()[satir][sutun].setForeground(Color.white);

            apg.getKutular()[satir][sutun].setText(Integer.toString(sayac));
            apg.getKutular()[satir][sutun].setBackground(Color.blue);
            onceden_buraya_gelinmis[satir][sutun] = true; sayac++;
               ilerleme_kaydedildi=true;
            onceden_buraya_gelinmis[satir][sutun] = true; sayac++;
        } else {
            //JOptionPane.showMessageDialog(null, "Buraya tıklayamazınız burası doludur");
            apg.getKutular()[satir][sutun].setBackground(Color.red);
            java.awt.Toolkit.getDefaultToolkit().beep();

        }*/
        if (satir + 3 < 10 && !onceden_buraya_gelinmis[satir + 3][sutun]) {
            serbert_yon++;
            apg.getKutular()[satir + 3][sutun].setBackground(Color.green);
        }
        if (sutun + 3 < 10 && !onceden_buraya_gelinmis[satir][sutun + 3]) {
            serbert_yon++;
            apg.getKutular()[satir][sutun + 3].setBackground(Color.green);
        }
        if (satir - 3 >= 0 && !onceden_buraya_gelinmis[satir - 3][sutun]) {
            serbert_yon++;
            apg.getKutular()[satir - 3][sutun].setBackground(Color.green);
        }
        if (sutun - 3 >= 0 && !onceden_buraya_gelinmis[satir][sutun - 3]) {
            serbert_yon++;
            apg.getKutular()[satir][sutun - 3].setBackground(Color.green);
        }

        if (satir + 2 < 10) {
            if (sutun + 2 < 10 && !onceden_buraya_gelinmis[satir + 2][sutun + 2]) {
                serbert_yon++;
                apg.getKutular()[satir + 2][sutun + 2].setBackground(Color.green);
            }
            if (sutun - 2 >= 0 && !onceden_buraya_gelinmis[satir + 2][sutun - 2]) {
                serbert_yon++;
                apg.getKutular()[satir + 2][sutun - 2].setBackground(Color.green);
            }
        }
        if (satir - 2 >= 0) {
            if (sutun + 2 < 10 && !onceden_buraya_gelinmis[satir - 2][sutun + 2]) {
                serbert_yon++;
                apg.getKutular()[satir - 2][sutun + 2].setBackground(Color.green);
            }
            if (sutun - 2 >= 0 && !onceden_buraya_gelinmis[satir - 2][sutun - 2]) {
                serbert_yon++;
                apg.getKutular()[satir - 2][sutun - 2].setBackground(Color.green);
            }
        }

        if ((sayac - 1) == 100) {
            JOptionPane.showMessageDialog(null, "TEBRİKLE OYUNU BİTİRDİNİZ SAYILI RECORLARA ULAŞAN NADİR KİŞİSİNİZ");
            JOptionPane.showMessageDialog(null, "Oyun Baştan Başlatılacaktır");
            ilk_giris = true;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    apg.getKutular()[i][j].setBackground(Color.black);
                    apg.getKutular()[i][j].setText("");
                    sayac = 1;
                }
            }
        } else if (serbert_yon == 0) {
            JOptionPane.showMessageDialog(null, "\n...Malesef sıkıştınız...\nRekorunuz : " + (sayac - 1));

            ilk_giris = true;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    apg.getKutular()[i][j].setBackground(Color.black);
                    apg.getKutular()[i][j].setText("");
                    sayac = 1;
                }
            }
        }

    }

}
