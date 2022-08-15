package JavaGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class radioButton extends Component {
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JPanel panelMain;
    private JButton clickButton;
    private JLabel label;

    public radioButton() {


        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (rb1.isSelected()) {
                    label.setText("You are Male.");
                }
                if (rb2.isSelected()) {
                    label.setText("You are Female.");
                }
             //   return null;
            }


        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("My first GUI");
        frame.setContentPane(new radioButton().panelMain);
        JOptionPane.showMessageDialog(frame, "Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(250, 200);

    }
}
