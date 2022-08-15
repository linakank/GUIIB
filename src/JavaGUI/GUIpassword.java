package JavaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIpassword {
    private JTextField passwordValue;
    private JPanel panelMain;
    private JButton loginButton;
    private JLabel password;
    private JTextField user;
    private JLabel label;

    public GUIpassword() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String data = "Username " + user.getText();
                data += ", Password: "
                        + new String(passwordValue.getText());
                password.setText(data);
                //return null;
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("My first GUI");
        frame.setContentPane(new GUIpassword().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(250, 200);

    }
}
