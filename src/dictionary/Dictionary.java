package dictionary;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author humzarahman
 */
public class Dictionary 
{
    //File Name Holder.
    private static final String WORDS_FILE = "words.txt";
    
    //Creating a new Trie.
    Trie trie;
    
    public Dictionary()
    {
        Scanner inputFile;
        String word;
        
        try
        {
            this.trie = new Trie();
            URL url = getClass().getResource(WORDS_FILE);
            File file = new File(url.toURI());
            
            inputFile = new Scanner(file);
            
            if(inputFile == null)
            {
                throw new IOException("Error: File could not be opened!");
                      
            }
            
            while(inputFile.hasNext())
            {
                word = inputFile.next();
                word = word.trim().toLowerCase();
                trie.insert(word);
            }
            
            System.out.println("File has been read and successfully inserted into Trie!");
        }
        
        catch(IOException | URISyntaxException e)
        {
            System.out.println("Error: Could not load file OR load words into Trie!"); 
            throw new RuntimeException(e);
        }
        
    }   //END OF CONSTRUCTOR
    
    //Search function
    public int search(String word)
    {
        return this.trie.search(word);
    }
    
    public boolean prefix(String word)
    {
        return this.trie.prefix(word);
    }
    
    
}
