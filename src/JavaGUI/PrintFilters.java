package JavaGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import javafx.collections.ObservableList;

/*
   This class is responsible for interacting with the printer and printing a 
   filtered list of players.
*/

public class PrintFilters extends JFrame implements Printable
{
    final static int RECORD_SIZE = 1;  // 1 line per player

    private ObservableList <Player> players;  // stores players to be printed
    private String[] textLines;        // stores each line of data to be printed per record
    private int[] pageBreaks;          // array of page break line positions

    /*
       @param list filtered list of Players
    */
    public PrintFilters(ObservableList<Player> list) // prints all players in list
    {
        setVisible(false);       //don't show gui window
        
        players = list;  // copy reference of list into instance variable players

        PrinterJob job = PrinterJob.getPrinterJob();  // create print job
        
        // set print job page attributes
        PageFormat pf = job.defaultPage();
        Paper paper = new Paper();
        double margin = 72/2;        // half inch margin
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
        pf.setPaper(paper);
         
        job.setPrintable(this, pf);
        boolean ok = job.printDialog();   // show print dialog window
        if (ok)
        {
            try
            {
                job.print();  // if user presses ok button then print the job
            }
            catch (PrinterException ex)
            {
               // The job did not successfully complete
            }
         }
    }
    /*
        Prepares the data for printing by filling the textLines array with all
        the data from the array of players into a string format.
    */
    private void initTextLines()
    {
        if (textLines == null)
        {

            int numLines=players.size();
            textLines = new String[numLines + 2];
            textLines[0] = String.format("%-9s%-21s%-9s%-9s%-9s%-9s%n","Team", "Name", "Bench", "Squat", "Incline", "Power");
            textLines[1] = String.format("%s%n","--------------------------------------------------------------------");
            int i=2;
            while(i < numLines)
            { 
                textLines[i] = String.format("%-9s%-18s%8d%9d%11d%7d%n", players.get(i-2).getTeam(), 
                                              players.get(i-2).getLastName() + ", " + players.get(i-2).getFirstName(),
                                              players.get(i-2).getBenchMax(), players.get(i-2).getSquatMax(), 
                                              players.get(i-2).getInclineMax(), players.get(i-2).getPowerMax());
                
                i+=RECORD_SIZE;   // advance to next record
            }
            textLines[i++] = "\n";   // add to blank lines
            textLines[i++] = "\n";      
        }
    }
    // This method is responsible for printing a page of the print job
    @Override
   public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException
   {
        Font font = new Font("Consolas", Font.PLAIN, 14);   // set font
        g.setFont(font);    
        FontMetrics metrics = g.getFontMetrics(font);       // set page attributes
        int lineHeight = metrics.getHeight();

        // layout data on the page and marking break points
        if (pageBreaks == null)
        {
            initTextLines();
            int linesPerPage = (int)(pf.getImageableHeight()/lineHeight);
            int recordsPerPage = linesPerPage/RECORD_SIZE;
            int numRecords = textLines.length/RECORD_SIZE; 
            int numBreaks = (numRecords * RECORD_SIZE) / linesPerPage;
            pageBreaks = new int[numBreaks];
            for (int b=0; b<numBreaks; b++)
            {
                pageBreaks[b] = (b+1)* recordsPerPage * RECORD_SIZE;
            }
        }

        if (pageIndex > pageBreaks.length)
        {
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping.
         */
    
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Draw each line that is on this page.
         * Increment 'y' position by lineHeight for each line.
         */
    
        int y = 0;
        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
        int end   = (pageIndex == pageBreaks.length)
                         ? textLines.length : pageBreaks[pageIndex];
        for (int line=start; line<end; line++)
        {
            y += lineHeight;
            if(textLines[line] != null)
               g.drawString(textLines[line], 0, y);
        }    

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

}

