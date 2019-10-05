import java.util.*;
public class Detect
{
    HashMap<String,String> symMap;
    HashSet<String> tuples;
    public void create_symMap(ArrayList<String> synonyms) //Will make key value pairs where except the first word
    // in the list all others would point to the first one , so that we can replace them later

    {
        symMap = new HashMap<>();
        for(String line : synonyms)
        {
            String[] words=line.split("\\s+");
            for(int i = 1;i<words.length;i++)
            {
                if(!symMap.containsKey(words[i]))
                {
                    symMap.put(words[i],words[0]);
                }
            }
        }
    }

    public ArrayList<String> replace_words(ArrayList<String> words) //replaces words by primary synonym
    {
        for(int i =0;i<words.size();i++) {
            if (symMap.containsKey(words.get(i)))
            {
                String temp = words.get(i);
                words.set(i,symMap.get(words.get(i)));
            }
        }

        return words;
    }
    public void make_tupleMap(ArrayList<String> words, int tupleSize) //Make's tuples from file 2
    {
        tuples=new HashSet<>();
        for(int i = 0; i<=words.size()-tupleSize;i++) //we'll chose tuplesize elements at a time
        {
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<tupleSize;j++)
            {
                sb.append(words.get(i+j));
                sb.append(" ");
            }
            tuples.add(sb.toString());
        }
    }
    public float Plagiarism_Ratio (ArrayList<String> words, int tupleSize) //Calculates number of N-Tuples of file 1 present in file 2
    {
        int plagiarized = 0;
        int normal = 0;
        for(int i=0;i<=words.size()-tupleSize;i++)
        {
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<tupleSize;j++)
            {
                sb.append(words.get(i+j));
                sb.append(" ");
            }
            if(tuples.contains(sb.toString()))
            {
                plagiarized++;
            }
            else
            {
                normal++;
            }
        }
        return (float)plagiarized/(float)(plagiarized+normal);
    }
}
