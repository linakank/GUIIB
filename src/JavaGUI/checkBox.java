package JavaGUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class checkBox {
    private JPanel panelMain;
    private JCheckBox ccCheckBox;
    private JCheckBox javaCheckBox;
    private JCheckBox cSharpCheckBox;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    public checkBox() {


        ccCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                // if the state of checkbox2 is changed
                if (e.getSource() == ccCheckBox) {
                    if (e.getStateChange() == 1)
                        label1.setText("C++  selected");
                    else
                        label1.setText("C++  not selected");
                }


            }

            ;
        });
        javaCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == javaCheckBox) {
                    if (e.getStateChange() == 1)
                        label2.setText("Java selected");
                    else
                        label2.setText("Java not selected");
                }
            }
        });
        cSharpCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == cSharpCheckBox) {
                    if (e.getStateChange() == 1)
                        label3.setText("C sharp selected");
                    else
                        label3.setText("C sharp  not selected");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("My first GUI");
        frame.setContentPane(new checkBox().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(250, 200);

    }
}
