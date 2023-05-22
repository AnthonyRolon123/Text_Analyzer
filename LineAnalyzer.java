package lineanalyzer;

import java.util.*;
import java.io.*;

public class LineAnalyzer 
{
    public static void main(String[] args) throws FileNotFoundException 
    {
        File file = new File("C:\\Users\\antho\\Downloads\\EdgarAllanPoePoem.txt");//file name
        ArrayList<String> list = new ArrayList<String>();//list to hold all words in the file
        ArrayList<Words> listOfWords = new ArrayList<Words>();//list for new words
        
        
        Scanner in = new Scanner(file);//reading in the file
        
        while(in.hasNextLine())//loop to go over every word in the file and add it to the list
        {
            String line = in.next();
            
            list.add(line.toLowerCase());
        }
        
        for(String word : list)//going over every list element and comparing it to the listofwords
        {
            Boolean duplicate = false;
            
            for(Words w : listOfWords)
            {
                if(word.equals(w.getWord()))//if the word is a duplicate up the count of the object
                {
                    w.counterUp();
                    duplicate = true;
                    break;
                }
            }
            
            if(duplicate == false)//if its a new word add the word to the list of words
            {
                listOfWords.add(new Words(word));
            }
        }
        
        Collections.sort(listOfWords, Words.order);//sorting the list of words
        
        for(int i = listOfWords.size()-1; i > listOfWords.size() - 20; i--)
        {
            System.out.println(listOfWords.get(i).toString());
        }
    }
}

class Words implements Comparator<Words>
{
    private String word;
    private int count = 0;
    
    public static Comparator<Words> order = new Comparator<Words>() {
        @Override
        public int compare(Words o1, Words o2) 
        {
            return Integer.compare(o1.getCount(), o2.getCount());
        }
    };

    public Words(String word)
    {
        this.word = word;
        count = 1;
    }

    public int getCount()
    {
        return count;
    }
    
    public void counterUp()
    {
        count += 1;
    }
    
    public String getWord()
    {
        return word;
    }
    
    @Override
    public String toString()
    {
        String output = "" + word + " " + count;
        return output;
    }

    @Override
    public int compare(Words o1, Words o2) 
    {
        return o1.getCount() - o2.getCount();
    }
}
