import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class Menu extends JFrame {

    private JTextArea klartext;
    private JButton asciiButton;
    private JTextField pfeld;
    private JTextField qfeld;
    private JPanel panel;
    private JButton convertButton;
    private JTextField nfeld;
    private JTextField phifeld;
    private JButton umrechnenButton;
    private JTextField dfeld;
    private JButton inKlarButton;
    private JTextField puKfeld;
    private JTextField prKFeld;
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

        convertButton.addActionListener(new ActionListener() {
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
            puKfeld.setText(e + ", " + n);
            prKFeld.setText(d + "");
        });
            asciiButton.addActionListener(event -> {
                char[] chars = klartext.getText().toCharArray();
                StringBuilder sb = new StringBuilder();

                for (char aChar : chars) {
                    sb.append((int) aChar).append(" ");
                }
                asciitext.setText(sb.toString().trim());
            });
            kodierenButton.addActionListener(event -> {
                String[] werte = asciitext.getText().split(" ");
                StringBuilder sb = new StringBuilder();
                for (String wert: werte) {
                    BigInteger ascii = new BigInteger(wert);
                    BigInteger exponent = BigInteger.valueOf(e);
                    BigInteger modulo = BigInteger.valueOf(n);
                    BigInteger encoded = ascii.modPow(exponent, modulo);
                    sb.append(encoded).append(" ");
                }
                codetext.setText(sb.toString().trim());
            });
            dekodierenButton.addActionListener(event -> {
                String[] werte = codetext.getText().split(" ");
                StringBuilder sb = new StringBuilder();
                for (String wert: werte) {
                    BigInteger code = new BigInteger(wert);
                    BigInteger exponent = BigInteger.valueOf(d);
                    BigInteger modulo = BigInteger.valueOf(n);
                    BigInteger sum = code.modPow(exponent, modulo);
                    sb.append(sum).append(" ");
                }
                asciitext.setText(sb.toString().trim());
            });
            inKlarButton.addActionListener(e -> {
                String[] werte = asciitext.getText().split(" ");
                StringBuilder sb = new StringBuilder();

                for (String wert: werte) {
                    int ascii = Integer.parseInt(wert);
                    sb.append((char) ascii);
                }
                klartext.setText(sb.toString().trim());
            });
    }
}
