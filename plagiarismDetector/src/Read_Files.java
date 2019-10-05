import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Read_Files
{
    public ArrayList<String> readToArrayList(File file) throws FileNotFoundException //Reads Each Word
    {
        Scanner sc = new Scanner(file);
        ArrayList<String> words = new ArrayList<>();
        while(sc.hasNext())
        {
            words.add(sc.next().toLowerCase());
        }
        return words;
    }
    public ArrayList<String> readToArrayListLines(File file) throws FileNotFoundException //Reads Each Line
    {
        Scanner sc = new Scanner(file);
        ArrayList<String> words = new ArrayList<>();
        while(sc.hasNextLine())
        {
            words.add(sc.nextLine().toLowerCase());
        }
        return words;
    }
}
