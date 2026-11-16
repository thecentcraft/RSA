import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class Menu extends JFrame {

    private JTextArea klartext;
    private JButton button1;
    private JTextField pfeld;
    private JTextField qfeld;
    private JPanel panel;
    private JButton button2;
    private JTextField nfeld;
    private JTextField phifeld;
    private JButton umrechnenButton;
    private JTextField dfeld;
    private JButton inKlarButton;
    private JTextField pKfeld;
    private JTextField gSFeld;
    private JTextArea asciitext;
    private JButton kodierenButton;
    private JButton dekodierenButton;
    private JTextArea codetext;
    private JTextField efeld;

    int p;
    int q;
    int n;
    int phi;
    int e;
    int d;

    public Menu() {
        setContentPane(panel);
        setSize(1600,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RSA-Kryptosystem");

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                p = Integer.parseInt(pfeld.getText());
                q = Integer.parseInt(qfeld.getText());
                n = p * q;
                phi = (p-1)*(q-1);
                e = 2;
                nfeld.setText(n + "");
                phifeld.setText(phi + "");

                while (ggt(e, phi) != 1) {
                    e++;
                }

                efeld.setText(e + "");
            }

            public static int ggt(int a, int b) {
                if (b==0) {
                    return a;
                }
                return ggt(b,a % b);
            }
        });
        umrechnenButton.addActionListener(event -> {
            e = Integer.parseInt(efeld.getText());
            d = 0;
            while ((e * d % phi) != 1) {
                d++;
            }
            dfeld.setText(d + "");
            pKfeld.setText(e + ", " + n);
            gSFeld.setText(d + "");
        });
        button1.addActionListener(event -> {
            char[] chars = klartext.getText().toCharArray();
            for (char aChar : chars) asciitext.setText(asciitext.getText() + (int) aChar);
        });
        kodierenButton.addActionListener(event -> codetext.setText(String.format("%.0f", Math.pow(Integer.parseInt(asciitext.getText()), e) % n)));
        dekodierenButton.addActionListener(event -> {
            BigInteger code = new BigInteger(codetext.getText());
            BigInteger exponent = BigInteger.valueOf(d);
            BigInteger modulo = BigInteger.valueOf(n);
            BigInteger sum = code.modPow(exponent, modulo);
            asciitext.setText(sum + "");
        });
        inKlarButton.addActionListener(e -> {
            int ascii = Integer.parseInt(asciitext.getText());
            klartext.setText(String.valueOf((char) ascii));
        });
    }
}
