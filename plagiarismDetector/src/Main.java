// Plagiarism detector can be run with arguments :   data/"synonyms.txt" data/"input1.txt" data/"input2.txt"
/*
Assumptions :

This Program works for alphanumeric characters and is NOT case sensitive. Does not work for actual sentences
which contain period also, as they would get detected with the words
Extensive error checking is not done for file opening/reading, assumed that it will open/read properly

Improvements :

Ignore the cases where the sentence ends, this will save time e.g : He had nothing left to go for. A jog .....
Even thought the tuple might look the same (go for a jog) still since a new sentence has started I dont think that
should be considered plagiarized

To expand on the same idea, to have multiple files to check plagiarism from , we can easily add more tuples to both the
hashmaps.

Implement synonym creation while reading the file , and try to create tuples while reading the lines,instead of going
through all the words in the file again.Synonyms can also be replaced ny primary synonym while reading the line.
If tuples from one file have already been created, then you could check for plagiarism while reading from the
second line which would make it faster and more efficient.

Implement case where entered tuplesize > size of words in file : Think about how to handle this case
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Read_Files obj = new Read_Files();
        int tuple_size=3;
        if(args.length < 3 || args.length >4) {
            System.out.println("Wrong Input Format");
            System.out.println("Input format : ");
            System.out.println("java Main synonyms input1 input2 tupleSize"
                    + "\n Tuple size is optional, default is 3");
        }
        if(args.length > 2)
        {
            ArrayList<String> synonyms=new ArrayList<>();
            ArrayList<String> input1=new ArrayList<>();
            ArrayList<String> input2=new ArrayList<>();
            try
            {
                File syn =new File(args[0]);
                File file1 = new File(args[1]);
                File file2 = new File (args[2]);
                synonyms = obj.readToArrayListLines(syn);
                input1 = obj.readToArrayList(file1);
                input2 = obj.readToArrayList(file2);

            }
            catch (Exception e)
            {
                System.err.println("Exception occurred trying to read the files. " + e);
            }
            if(args.length==4) {
                tuple_size = Integer.parseInt(args[3]); //Add Error check if it was actually an integer
            }
            Detect d = new Detect();
            d.create_symMap(synonyms);
            input1=d.replace_words(input1);
            input2=d.replace_words(input2);
            d.make_tupleMap(input2,tuple_size);
            System.out.println("Plagiarism Percentage = "+d.Plagiarism_Ratio(input1,tuple_size)*100+"%");

        }

    }
}
