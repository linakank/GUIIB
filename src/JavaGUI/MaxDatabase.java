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
        loadPlayerFile();// popluate players from data file
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
        // String filename = "players_data.json";
        String filename = "output.json";
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
                        o.getInt("benchMax"),
                        o.getInt("squatMax"),
                        o.getInt("inclineMax"),
                        o.getInt("powerMax"),
                        o.getInt("classification")
                );
                // Add the Player object to the ArrayList of players
                players.add(p);

            }

        }}
    public void readFile(String fileName)    {
        Scanner inFile;

        try
        {
            inFile = new Scanner(new File(fileName));
            
            while(inFile.hasNextLine())
            {
                String lastName = inFile.nextLine();
                String firstName = inFile.nextLine();
                String team = inFile.nextLine();
                int classification = inFile.nextInt();
                int benchMax = inFile.nextInt();
                int squatMax = inFile.nextInt();
                int inclineMax = inFile.nextInt();
                int powerMax = inFile.nextInt();
                inFile.nextLine();   // dummy read
                players.add(new Player(lastName, firstName, team, classification, benchMax, squatMax, inclineMax, powerMax));
            }
                   
            inFile.close();
        }
        catch(FileNotFoundException e) // Data file does not exist; create it
        { 
            try {
                File file = new File(fileName);
                file.createNewFile();    // if file already exists will do nothing 
                FileOutputStream oFile = new FileOutputStream(file, false); 
            }
            catch(IOException ex)
            {
                System.out.println("Error Creating Datafile: " + FILENAME);
            }
        }
    }
    
    /* 
       Saves the database to the data file.
       @param player the player to be added
    */
    public void saveFile(String fileName)    {
        PrintWriter outFile;

        try
        {
            outFile = new PrintWriter(new File(fileName));

            // Write the ArrayList to the file
            for(Player p : players)
            {
                outFile.println(p.getLastName());
                outFile.println(p.getFirstName());
                outFile.println(p.getTeam());
                outFile.println(p.getClassification());
                outFile.println(p.getBenchMax());
                outFile.println(p.getSquatMax());
                outFile.println(p.getInclineMax());
                outFile.println(p.getPowerMax());
            }
            outFile.close();
        }
        catch (IOException e)
        {
            System.out.println("Error writing to data file: " + e.getMessage());
        }
    }
    
        
    /* 
       Creates a new database by erasing the current one from the data file 
       and clearing the players ArrayList.
       @param fileName name of the data file
    */
    public void newFile(String fileName)
    {
        try
        {
            players.clear();
            PrintWriter outFile = new PrintWriter(new File(fileName));
            outFile.print("");
            outFile.close();
        }
        catch(IOException e)
        { System.out.println("Error reading or writing file");  }
    }
    
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
    public void updatePlayer(Player player)
    {
        Player p = getPlayer(player);
        if(p != null)
        {
            p.setLastName(player.getLastName());
            p.setFirstName(player.getFirstName());
            p.setTeam(player.getTeam());
            p.setClassification(player.getClassification());
            p.setBenchMax(player.getBenchMax());
            p.setSquatMax(player.getSquatMax());
            p.setInclineMax(player.getInclineMax());
            p.setPowerMax(player.getPowerMax());
        }
    }
    
    /*
       Deletes every player in the database and erases the data file.
    */
    public void clearDatabase()
    {
        players.clear();
        
        // clear data file
        FileOutputStream newFileID;
        ObjectOutputStream outFile;
        try
        {
            // Create the output stream
            newFileID = new FileOutputStream(FILENAME);
            // create new data file
            outFile   = new ObjectOutputStream(newFileID);

            // Close the file
            outFile.close();
        }
        catch (IOException ex) // can't create data file
        {
            System.out.println("Error deleting data file: " + ex.getMessage());
        }
        
    }
    
    /*
        An accessor method for the list of players
        @ return a reference to the ArrayList players 
    */
    public ArrayList<Player> getPlayers()
    {
        return players;
    }
    
    /*
        Updates the database by deleting all seniors and promoting all
        underclassmen to the next grade level.
    */
    public void closeOutYear()
    {
        ArrayList <Player> list = getPlayers();
        int i = 0;
        while(i < list.size())
        {
            Player player = list.get(i);
            if(player.getClassification() == 12)
            {
                list.remove(i);
            }
            else
            {
                i++;
                player.setClassification(player.getClassification() + 1);
            }
        }
    }
    
    /*
       @ param player Player from the database
       @ return the Player that matches the parameter player's lastName and FirstName.
    */
    public Player getPlayer(Player player)
    {
       for(Player p : players)
       {
           if(p.getLastName().equals(player.getLastName()) && p.getFirstName().equals(player.getFirstName()))
           {
               return p;
           }
       }
       return null;
    }
    
    /*  
        Makes a copy of the given ArrayList
        param list the ArrayList to be copied
        return an ArrayList that is a copy of the database
    */
    public ArrayList<Player> copyList(ArrayList<Player> list)
    {
        ArrayList <Player> temp = new ArrayList<>();
        for(Player player: list)
        {
            temp.add(player);
        }
        return temp;
    }
   
    
    /*
       Arranges all players in list into groups according to two
       criterion: Team and BenchPress Max. Each group consists
       of up to groupSize players. All members of a group below
       to the same team. A reference to this list of Groups is stored
       in an instance variable named groups.
       param the ArrayList used to organize players into groups
       param the maximum size of each group.
    */
    public void createGroups(ArrayList <Player> list, int groupSize)
    {
        if(list != null)
            list = this.sortGroups(list);
        else
            list = this.sortGroups(players);
        int numPlayers = 0;
        if(list != null)
        {
            Group group = new Group(groupSize);
            group.addPlayer(list.get(0));
            for(int i = 1; i < list.size(); i++)
            {
                if(numPlayers == groupSize || !list.get(i-1).getTeam().equals(list.get(i).getTeam()))
                {
                    groups.add(group);
                    group = new Group(groupSize);
                    numPlayers = 0;
                } 
                group.addPlayer(list.get(i));  
                numPlayers++;
            }
            if(group != null)
                groups.add(group);
        }
    }
    
    /* 
       @return an Arraylist that is a copy of the database and is
               sorted using the insertion sort algorithm, which is a stable,
               therefore allowing sorting using multiple fields. In this case,
               order by team and then bench press max.
        @param players list of players to sort
    */
    public ArrayList <Player> sortGroups(ArrayList <Player> players)
    {
        ArrayList<Player> list = this.copyList(players);
        
        int i, j; 
        Player index;

        for (i=1; i < list.size(); i++)
        {
           index = list.get(i);
           j = i;
           while ((j > 0) && (list.get(j-1).compareTo(index) < 0))
           {
               list.set(j, list.get(j-1));
               j = j - 1;
           }
           list.set(j, index);
        }
        
        return list;
    }
    
    /* Accessor method
       @return a reference to the groups ArrayList
    */
    public ArrayList<Group> getGroups()
    {
        return groups;
    }
    
    /* Organizes groups into a single ArrayList so they can
       be viewed in a ListView component.
       @return a list of all groups
    */
    public ArrayList<Player> getGroupsAsList()
    {
        ArrayList<Player> list = new ArrayList<>();
        
        for(Group group: groups)
        {
            Player[] player = group.getGroup();
            for(int i=0; i < player.length; i++)
            {
                if(player[i] != null)
                    list.add(player[i]);
            }
        }
        return list;
    }

}
