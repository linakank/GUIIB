package JavaGUI;

import java.awt.*;

public class AddPlayer1 extends EasyApp
{
Label Title = addLabel("Add a Player Section", 40, 40, 400, 50, this);
Label TeacherL = addLabel("Name", 50, 100, 80, 30, this);

TextField Teacher = addTextField("", 170, 100, 150, 30, this);
TextField Subject = addTextField("", 170, 140, 150, 30, this);

 Button TimeTable = addButton("Time-Table", 40, 310, 100, 50, this);
 Button Accept = addButton("Accept", 160, 310, 100, 50, this);
 Button Exit = addButton("Exit", 280, 310, 100, 50, this);

        public AddPlayer1() {
        setTitle("AddPlayer1");
        setBounds(425, 78, 440, 390);
        Title.setFont(new Font("Arial", 0, 20));
        Title.setForeground(Color.blue);
        }
}
