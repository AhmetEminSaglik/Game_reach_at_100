package GUI;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGui {

    JFrame jf = null;
    JPanel jp = null;
    ArkaplanGui apg = null;

    public MainGui() {
        getJp().setBackground(Color.BLACK);
        getJp().add(getApg().getJp());
        getJf().setVisible(true);

    }

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame("Dunya şampiyonası 100'e ulaşma Oyunu");
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setLocation(100, 50);
            jf.setSize(1100, 650);
            jf.setContentPane(getJp());
        }
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public ArkaplanGui getApg() {
        if (apg == null) {
            apg = new ArkaplanGui();
        }
        return apg;
    }

    public void setApg(ArkaplanGui apg) {
        this.apg = apg;
    }

}
