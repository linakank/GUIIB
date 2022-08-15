package JavaGUI;

import java.util.*; 

/*
    This class stores up to maxGroupSize players in an array to form a weight training workout group.
*/

public class Group 
{
    // instance variables
    private Player[] group;
    private int maxGroupSize;
    private int numPlayers;
    
    // constructor
    public Group(int groupSize)
    {
        maxGroupSize = groupSize;
        group = new Player[maxGroupSize];
        numPlayers = 0;
    }
    
    // constructor
    public Group(int groupSize, int numPlayers, Player[] group)
    {
        maxGroupSize = groupSize;
        group = new Player[groupSize];
        this.numPlayers = numPlayers;
    }
    
    /*
        Adds a player to the group
    */
    public void addPlayer(Player player)
    {
        if(numPlayers < maxGroupSize)
        {
            group[numPlayers] = player;
            numPlayers++;
        }
    }
    
    /*
        @return the number of players in this group
    */
    public int getGroupSize()
    {
        return numPlayers;
    }
    
   /*
        @return a reference to the group array
    */
    public Player[] getGroup()
    {
        return group;
    }
    
}
