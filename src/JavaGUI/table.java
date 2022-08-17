package JavaGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class table extends JFrame {
    private JPanel mainPanelTable;
    private JButton button1;
    private JPanel panelTabel;
    private JTable showTable;
    private JScrollPane scrollPane;
    private JButton button2;
    private JPanel panelDown;
    private JButton filterButton;
    private JPanel panel;
    private JComboBox teamCombo;
    private JComboBox lastCombo;
    private JTextField textField1;
    private JButton button4;

    public table() {
        super("Players list");
        MaxDatabase max = new MaxDatabase();
        ArrayList<Player> players = max.getPlayers();
        //   ArrayList<Player> players = max.getPlayers();
        createTable(players);
        createTeamCombo(players);
        createTeamCombo(players);
        this.setSize(1000, 500);
        this.mainPanelTable = mainPanelTable;
        //  mainPanelTable.add(scrollPane);
        this.setContentPane(this.mainPanelTable);
        //    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTeam(players);
            }
        });

        teamCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ArrayList<Player> teamplayers = filterTeam(players);
                createTable(teamplayers);
            }
        });
    }

    private ArrayList<Player> filterTeam(ArrayList<Player> players) {
        ArrayList<Player> teamplayers = new ArrayList<>();
        String team = (String) teamCombo.getSelectedItem();//filter team
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getTeam().equals(team)) teamplayers.add(players.get(i));
        }

        for (int i = 0; i < teamplayers.size(); i++) {
            System.out.println(" " + teamplayers.get(i).getTeam() + "  " + teamplayers.get(i).getLastName());
        }
        if (team.equals("all")) return players;
        else return teamplayers;

    }

    private void createTeamCombo(ArrayList<Player> players) {

        List<String> listteam = Arrays.asList("all", "Varsity", "Purple", "Red", "Blue");


        for (int i = 0; i < listteam.size(); i++) {
            teamCombo.addItem(listteam.get(i));
        }
        filterTeam(players);

    }

    private void createLastCombo(ArrayList<Player> players) {
        String[] lasts = {"Volvo", "BMW", "Ford", "Mazda"};
        lastCombo.setModel(new DefaultComboBoxModel(lasts));
    }

    public JPanel getPanelTabel() {
        return panelTabel;
    }

    private void createTable(ArrayList<Player> players) {// String[] columnNames = {"First Name", "Last Name"};
        //  Object[][] data = {{"Kathy", "Smith"},{"John", "Doe"}};
        int count = players.size();
        if (count == 0) count = 1;
        Object[][] data = new String[count][8];
        String columnNames[] = {"FirstName", "LastName", "Team", "BenchMax", "squatMax", "inclineMax", "powerMax", "classification"};
        //   System.out.println(players.get(0).getFirstName());
        if (players.size() == 0) {
            for (int i = 0; i < 8; i++)
                data[0][i] = "";
        } else {
            for (int i = 0; i < players.size(); i++) {
                data[i][0] = players.get(i).getFirstName();
                data[i][1] = players.get(i).getLastName();
                data[i][2] = players.get(i).getTeam();
                data[i][3] = String.valueOf(players.get(i).getBenchMax());
                data[i][4] = String.valueOf(players.get(i).getSquatMax());
                data[i][5] = String.valueOf(players.get(i).getInclineMax());
                data[i][6] = String.valueOf(players.get(i).getPowerMax());
                data[i][7] = String.valueOf(players.get(i).getClassification());
            }
        }
        showTable.setModel(new DefaultTableModel(data, columnNames));

        TableColumnModel columns = showTable.getColumnModel();
        columns.getColumn(1).setMinWidth(100);//the width of column "Last Name"

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(1).setCellRenderer(centerRenderer);
        columns.getColumn(2).setCellRenderer(centerRenderer);
        columns.getColumn(3).setCellRenderer(centerRenderer);

    }

}