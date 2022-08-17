package JavaGUI;
//File-Project Structure-Project Settings-Libraries –add from Maven… org,json.
//org.json:json:20220320
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.*;
import java.io.*;

/*
  This class maintains a list of Player’s. It also provides methods for
  savinging and loading the data from a text file and manipulation of this list.
*/

public class MaxDatabase
{
    // constants
   public static String FILENAME = "weightTraining.dat";  // data file name
    public static String BACKUP = "weightTraining.bak";  // backup file name
    // instance variables
    private ArrayList<Player> players;  // list of players (database)
    private ArrayList<Group> groups;    // list of workout groups


    // constructor  
    public MaxDatabase()
    {
        players = new ArrayList<>();
        groups = new ArrayList<>();
        loadPlayerFile();// popluate players from data file json
      //  readFile(FILENAME);
    }
    
    /* 
        This method reads the data file if it exists and loads the data into the
        database. If the data file does not exist it creates it.
        @param player the player to be added
    */
    private String loadFileToString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            // Load the JSON file as String data
            File f = new File(fileName);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                sb.append(reader.nextLine());
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            System.out.println("Error  " + e.getStackTrace()+e.getMessage());
            return null;
        }
    }
    public void loadPlayerFile() {
        String filename = "players_data.json";
      //  String filename = "output.json";
        String fileData = loadFileToString(filename);
        // System.out.println(fileData);
        if (fileData != null) {
            // Create a JSONArray from the String
            JSONArray ja = new JSONArray(fileData);
            // Iterate over the JSONObjects within the JSONArray
            for (int i = 0; i < ja.length(); i++) {
                JSONObject o = ja.getJSONObject(i);
                // Use each JSONObject to create a Player object
                Player p = new Player(o.getString("firstName"),
                        o.getString("lastName"),
                        o.getString("team"),
                        o.getInt("classification") ,
                        o.getInt("benchMax"),
                        o.getInt("squatMax"),
                        o.getInt("inclineMax"),
                        o.getInt("powerMax")

                );
                // Add the Player object to the ArrayList of players
                players.add(p);

            }

        }}

    /* 
       Saves the database to the data file.
       @param player the player to be added
    */

    
    /*
        @return the number of players in database
    */
    public int getSize()
    {
        return players.size();
    }
    
    /*
        Adds a Player to database
        @param player the player to be added
    */
    public void addPlayer(Player player)
    {
        players.add(player);
    }
    
    /* Deletes a Player from database 
       @param player the player to be removed
    */
    public void deletePlayer(Player player)
    {
        players.remove(player);
    }
    
   /*  Modifies a Player's info in the database
       @param player the player to be edited
    */


    /*
        An accessor method for the list of players
        @ return a reference to the ArrayList players 
    */
    public ArrayList<Player> getPlayers()
    {
        return players;
    }
    

}
