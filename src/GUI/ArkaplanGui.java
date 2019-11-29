package GUI;

import Logic.Actions;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ArkaplanGui {

    private int satir, sutun;

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
    JPanel jp = null;
    JButton[][] kutular = new JButton[10][10]; //null --> hata verirse bunu kullanırım
    Actions action = new Actions(this);

    public JButton[][] getKutular() {
        if (kutular[getSatir()][getSutun()] == null) {
            kutular[getSatir()][getSutun()] = new JButton();
            kutular[getSatir()][getSutun()].setBackground(Color.black);
            // kutular[getSatir()][getSutun()].setSize(10, 60);
            //kutular[getSatir()][getSutun()].setMargin(new Insets(5, 0, 5, 0));
            kutular[getSatir()][getSutun()].addActionListener(action);
            //kutular[getSatir()][getSutun()].setText(Integer.toString(getSatir() + getSutun()));
        }

        return kutular;
    }

    public ArkaplanGui() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                satir = i;
                sutun = j;
                getJp().add(getKutular()[getSatir()][getSutun()]);
                kutular[getSatir()][getSutun()].setPreferredSize(new Dimension(70, 40));
                kutular[getSatir()][getSutun()].setMargin(new Insets(5, 0, 5, 0));// işe yaramadi
                // kutular[getSatir()][getSutun()].setBorder(BorderFactory.createMatteBorder(10, 1, 1, 1, Color.red)); // guzel birşey oldu
//kutular[getSatir()][getSutun()].setMargin();
            }
        }
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(100, 70, 800, 600);
            jp.setBackground(Color.black);
            jp.setForeground(Color.black);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public void setKutular(JButton[][] kutular) {
        this.kutular = kutular;
    }

}
