package JavaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class searchForm extends JFrame {
    // JFrame f=new JFrame();
    private JPanel panel1;
    private JTextField searchFLame;
    private JTextField searchFName;
    private JButton button1;


    // keyboard = new Scanner(System.in);
    public searchForm(ArrayList<Player> players) {
        super("My fancy contacts project");
        this.panel1 = panel1;
        this.setContentPane(this.panel1);
    //    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        // start();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchFName.getText();
                Player sr = searchByName(name, players);
                if (sr != null) {
                    System.out.println("found " + sr.getFirstName()+sr.getLastName());
                }
            }
        });

    }
    /*
       Performs a linear search for a player in the database
       @param name the player's name
       @return the Player found or null if player not found
    */
    public Player searchByName(String name, ArrayList<Player> players) {
        // linear search algorithm
         for (Player player : players) {
            if (player.getFirstName().equals(name)) {
                return player;
            }
        }
        return null;  // player not in list
    }
}
