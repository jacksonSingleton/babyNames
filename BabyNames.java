/**
 * BabyNames
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.*;

public class BabyNames {
    public static final String fileName = "names.txt";
    public static final int startYear = 1900;
    public static final int numDecades = 11;
    public static final int width = 50;

    public static void main(String[] args)throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        File file = new File(fileName);
        Scanner fileData = new Scanner(file);
        //Calls the search data method to look through the list of names
        String dataPull = searchData(fileData, console);       
        if(dataPull != ""){
            DrawingPanel panel = new DrawingPanel(550,560);
            Graphics g = panel.getGraphics();
            drawUI(panel, g, dataPull);
            drawData(panel, g, dataPull);
        }
        console.close();
        fileData.close();
    }
    public static String searchData(Scanner fileData, Scanner console){
        System.out.println("Type a name: ");
        String name = console.nextLine();
        String nameFormat = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        String nameData = "";
        boolean found = false;
        while (fileData.hasNext()) {
            String line = fileData.nextLine();
            if (line.contains(nameFormat)) {
                nameData = line;
                System.out.println("Found matching name");
                System.out.println(nameData);
                found = true;
                break;
            }
            else{
                continue;
            }
        }
        if(found == false){
            System.out.println("\"" + name + "\" not found");
        }
        return nameData;
    }
    public static void drawUI(DrawingPanel panel, Graphics g, String nameInfo){
        //DrawBoxes
        g.setColor(Color.lightGray);
        for (int i = 1; i < numDecades; i++) {
            g.drawLine(width*i, 0, width*i, panel.getHeight());
            g.drawLine(0, width*i+30, panel.getWidth(), width*i+30);
        }

        
        
        //DrawUI
        String name = nameInfo.substring(0, nameInfo.indexOf(' '));
        g.setColor(Color.yellow);
        g.fillRect(0, 0, panel.getWidth(), 30);
        g.fillRect(0,panel.getHeight()-30, panel.getWidth(), 30);
        g.setFont(new Font("SansSerif", Font.BOLD, 16)); 
        g.setColor(Color.black);
        g.drawString("Ranking of name \"" + name + "\":", 10, 15);
        for (int i = 0; i < numDecades; i++) {
            g.drawString("" + (startYear + i*10), width*i, 546);
        }

        
    }
    public static void drawData(DrawingPanel panel, Graphics g, String nameInfo){
        int x = 0;
        int y = 0;
        int newX = x+50;
        int newY = 0;
        int[] numList = new int[numDecades];
        Scanner check = new Scanner(nameInfo);
        check.next();
        for (int i = 0; i < numDecades; i++) {
            numList[i] = check.nextInt();
        }
        
        check.close();
    }
}