package JavaGUI;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IAexample extends JFrame {
    private MaxDatabase max;   // needed to communicate with the database
    private Scanner keyboard;
    private JTextField texFirstName;
    private JTextField textLastName;
    private JTextField textBenchMax;
    private JComboBox comboBoxTeam;
    private JComboBox comboBoxClasification;
    private JTextField textSquartMax;
    private JTextField textInclineMax;
    private JTextField textPowerCleanMax;
    private JPanel AddPlayer;
    private JButton addButton;
    private JButton clearButton;
    private JPanel panelMain;
    private JButton addPlayerFormButton;
    private JList listPlayers;
    private JButton updateButton;
    private JButton searchButton;
    private JButton filterButton;
    private JButton JTableButton;
    private JButton viewButton;
    private JButton showListButton;
    private JButton saveButton;
    private JButton deleteSelectedButton;
    private JTable table;
    private DefaultListModel listPlayersModel;
    JFrame f = new JFrame();
    ArrayList<Player> players;
    List<String> listteam = Arrays.asList("Varsity", "Purple", "Red", "Blue");
    List<Integer> listclasification = Arrays.asList(9, 10, 11, 12);
    private JTable playersTable;

    public IAexample() {
        super("My fancy contacts project");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        max = new MaxDatabase();
        keyboard = new Scanner(System.in);
        listPlayersModel = new DefaultListModel();
        listPlayers.setModel(listPlayersModel);
        start();


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAddClick(e);


            }

            public void buttonAddClick(ActionEvent e) {
                //(String last, String first, String t, int c, int g, int b, int s, int i, int p)
                Player p = new Player(
                        textLastName.getText(),
                        texFirstName.getText(),
                        comboBoxTeam.getSelectedItem().toString(),
                        // comboBoxTeam.getSelectedItem(), //cb.getItemAt(cb.getSelectedIndex()
                        (Integer) comboBoxClasification.getSelectedItem(),
                        Integer.valueOf(textBenchMax.getText()),
                        Integer.valueOf(textSquartMax.getText()),
                        Integer.valueOf(textInclineMax.getText()),
                        Integer.valueOf(textPowerCleanMax.getText())

                );
                players.add(p);
                refreshPlayersList();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonUpdaateClick(e);
            }
        });

        listPlayers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listPlayerSelection(e);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //    new searchForm();
                searchForm sf = new searchForm(players);
                sf.setVisible(true);
            }
        });
        JTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listPlayersModel.removeAllElements();

            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                table t = new table();
                t.setVisible(true);
            }
        });
        showListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPlayersList();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePlayersFile();

            }


        });

        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonDeleteClick(e);
            }


        });


    }

    private void savePlayersFile() {
        // Convert the ArrayList of Person objects into a JSONArray of JSONObjects
        JSONArray ja = new JSONArray();
        for (Player p : players) {
            JSONObject jo = new JSONObject(p);
            ja.put(jo);
        }
        // Save the JSONArray to a text file
        saveFileFromString("players_data.json", ja.toString());

    }

    private boolean saveFileFromString(String filename, String data) {
        try {
            FileWriter fw = new FileWriter(filename);
            PrintWriter output = new PrintWriter(fw);
            output.println(data);
            output.close();
            fw.close();
            return true;
        } catch (Exception e) {
            System.out.println("[saveFileFromString] error saving " + filename);
            return false;
        }
    }


    public void listPlayerSelection(ListSelectionEvent e) {
        int playerNumber = listPlayers.getSelectedIndex();
        if (playerNumber >= 0) {
            Player p = players.get(playerNumber);
            texFirstName.setText(p.getFirstName());
            textLastName.setText(p.getLastName());
            textBenchMax.setText(String.valueOf(p.getBenchMax()));

            int teamindex = listteam.indexOf(p.getTeam());
            comboBoxTeam.setSelectedIndex(teamindex);
            //   comboBoxTeam.setSelectedIndex(teamindex);

            int clasificationindex = listclasification.indexOf(p.getClassification());
            comboBoxClasification.setSelectedIndex(clasificationindex);

            textPowerCleanMax.setText(Integer.toString(p.getPowerMax()));
            textSquartMax.setText(Integer.toString(p.getSquatMax()));
            textInclineMax.setText(Integer.toString(p.getInclineMax()));
            // textDateOfBirth.setText(p.getDateOfBirth().toString());
            // labelAge.setText(Integer.toString(p.getAge()) + "  years");
            updateButton.setEnabled(true);
        } else {
            updateButton.setEnabled(false);
        }
    }


    private void buttonUpdaateClick(ActionEvent e) {
        int playerNumber = listPlayers.getSelectedIndex();
        if (playerNumber >= 0) {
            Player p = players.get(playerNumber);
            p.setFirstName(texFirstName.getText());
            p.setLastName(textLastName.getText());
            p.setBenchMax(Integer.parseInt(textBenchMax.getText()));
            //   p.setTeam(comboBoxTeam.getName());
            p.setTeam((String) comboBoxTeam.getSelectedItem());
            p.setClassification((Integer) comboBoxClasification.getSelectedItem());
            p.setSquatMax(Integer.parseInt(textSquartMax.getText()));
            p.setInclineMax(Integer.parseInt(textInclineMax.getText()));
            refreshPlayersList();

        }

    }

    private void buttonDeleteClick(ActionEvent e) {
        int playerNumber = listPlayers.getSelectedIndex();
        players.remove(playerNumber);
        refreshPlayersList();
    }

    private void start() {
        ArrayList<Player> players = max.getPlayers();

        for (int i = 0; i < listteam.size(); i++) {
            comboBoxTeam.addItem(listteam.get(i));
        }
        for (int i = 0; i < listteam.size(); i++) {
            comboBoxClasification.addItem(listclasification.get(i));
        }
        //   comboBoxTeam=new JComboBox(listteam.toArray());
        //  comboBoxClasification=new JComboBox(listclasification.toArray());
        System.out.println(listteam.get(0));
        comboBoxTeam.setSelectedIndex(0);
        comboBoxClasification.setSelectedIndex(0);

        //showTable(players);
        //  showPlayersList(players);
        refreshPlayersList();
    }

    public static void main(String[] args) {
        IAexample ia = new IAexample();
        ia.setVisible(true);
    }


    public void showPlayersList(ArrayList<Player> players) {

        for (Player p : players) {
            System.out.println("Adding person to list: " + p.getLastName());
            listPlayersModel.addElement(p.getLastName() + " " + p.getTeam());
        }

    }

    public void refreshPlayersList() {
      // players=null;
      //  max = new MaxDatabase();
       players = max.getPlayers();
        listPlayersModel.removeAllElements();
        System.out.println("Removing all people from list ");
        showPlayersList(players);
    }

}



