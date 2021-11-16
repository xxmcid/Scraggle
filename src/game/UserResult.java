/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.LinkedHashMap;
import java.util.Map;
import model.WordResult;

/**
 *
 * @author humzarahman
 */
public class UserResult 
{
    private int totalScore;
    private final Map<String, WordResult>
    wordToResultMap = new LinkedHashMap<>();

    /**
     * @return the totalScore
     */
    public int getTotalScore() 
    {
        //Returns total score.
        return totalScore;
    }
    
    //Adding a word to our result map.
    public void add(String word, WordResult result)
    {
        this.wordToResultMap.put(word, result);
        totalScore += result.getScore();
    }
    
    public WordResult get(String word)
    {
        return this.wordToResultMap.get(word);
    }
    
    public Map<String, WordResult> all()
    {
        return this.wordToResultMap;
    }
    
    //Returns true if word is found.
    public boolean exist(String word)
    {
        return this.wordToResultMap.containsKey(word);
    }
    
    //Returns the word count.
    public int getWordCount()
    {
       return this.wordToResultMap.size();
    }
    
    
    
}
