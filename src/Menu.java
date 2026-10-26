import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    private JTextArea textArea1;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel panel;
    private JButton button2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton umrechnenButton;
    private JTextField textField5;
    private JButton inKlarButton;
    private JTextField textField6;
    private JTextField textField7;
    private JTextArea textArea2;
    private JButton kodierenButton;
    private JButton dekodierenButton;
    private JTextArea textArea3;

    public Menu() {
        setContentPane(panel);
        setSize(1600,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RSA-Kryptosystem");
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.getText();
            }
        });
    }
}
