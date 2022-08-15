package JavaGUI;

/*
   This class stores each player’s name, classification(9, 10, 11, 12),
   and weight max for each of the four exercises: bench, squat, incline,  
   and power clean.
*/

public class Player implements Comparable<Player>
{
    // instance variables  
    private String firstName;
    private String lastName;
    private String team;
    private int classification;
    private int  benchMax;
    private int  squatMax;
    private int  inclineMax;
    private int  powerMax;
    
    // constructor    
    public Player()
    {
        firstName = "";
        lastName = "";
        team = "";
        classification = 0;
        benchMax = 0;
        squatMax = 0;
        inclineMax = 0;
        powerMax = 0;

    }
    
    // constructor
    public Player(String last, String first, String t, int c, int b, int s, int i, int p)
    {
        firstName = first;
        lastName = last;
        team = t;
        classification = c;
        benchMax = b;
        squatMax = s;
        inclineMax = i;
        powerMax = p;
    }
    

    // accessor methods 
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public int getBenchMax()
    {
        return benchMax;
    }
    
    public int getSquatMax()
    {
        return squatMax;
    }
        
    public int getInclineMax()
    {
        return inclineMax;
    }
            
    public int getPowerMax()
    {
        return powerMax;
    }
    
    public String getTeam()
    {
        return team;
    }
    
    public int getClassification()
    {
        return classification;
    }
    
    // mutator method
    public void setFirstName(String n)
    {
        firstName = n;
    }
    
    public void setLastName(String n)
    {
        lastName = n;
    }
    
    public void setBenchMax(int b)
    {
        benchMax = b;
    }
    
    public void setSquatMax(int s)
    {
        squatMax = s;
    }
        
    public void setInclineMax(int i)
    {
        inclineMax = i;
    }
    
    public void setPowerMax(int p)
    {
        powerMax = p;
    }
    
    public void setTeam(String t)
    {
        team = t;
    }
    
    public void setClassification(int c)
    {
        classification = c;
    }
    
    // toString
    @Override
    public String toString()
    {
        return String.format("%-17s%4s%n%-17s%4s%n%-17s%4s%n%-17s%4d%n%-17s%4d%n%-17s%4d%n%-17s%4d%n%-17s%4d%n%-17s%4d",
                      "First Name:", getFirstName(),
                      "Last Name:", getLastName(),
                      "Team:", getTeam(),
                      "Classification:", getClassification(),
                      "Bench Max:", getBenchMax(), 
                      "Squat Max:", getSquatMax(),  
                      "Incline Max:", getInclineMax(), 
                      "Power Clean Max:", getPowerMax());
    }
    
    // CompareTo Method
    @Override
    public int compareTo(Player obj)
    {
        int result = 0;
        result = this.team.compareTo(obj.team);
        if(result == 0)
        {
            if(this.benchMax < obj.benchMax)
                return -1;
            else if(this.benchMax> obj.benchMax)
                return 1;
            else 
                return 0;
        }
        return result;
    }
    
}
