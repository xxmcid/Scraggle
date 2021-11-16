/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import dictionary.Alphabet;
import dictionary.Dictionary;
import model.*;
/**
 *
 * @author humzarahman
 */
public class Game 
{
    //Member Variables
    Dictionary dictionary;
  
    //Creates our basic grid
    private final GridUnit [] [] grid;
    
     /**
     * @param dictionary
     * @return the dictionary
     */
    public Dictionary getDictionary() 
    {
        return this.dictionary;
    }
    
    public Game(Dictionary dictionary)
    {
        this.dictionary = dictionary;
        
        //Creates a 4x4 game board.
        grid =  new GridUnit[4] [4];
        populateGrid();
    }

    /**
     * @return the grid
     */
    public GridUnit[][] getGrid() 
    {
        //Returns our current grid.
        return grid;
    }
    
    public GridUnit getGridUnit(GridPoint point)
    {
        //Returns our point at desired area.
        return this.grid[point.x][point.y];
    }
    
    public void populateGrid()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
                {
                    //Populates our grid with a random Letter.
                    grid[i][j] = new GridUnit(Alphabet.newRandom(), new GridPoint(i,j));
                    
                }
        }
    }
    
    //Displays our grid.
    public void displayGrid()
    {
        System.out.println("-----------------");
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
                {
                    if(j == 0)
                    {
                       System.out.print("| ");
                       System.out.print((grid[i][j]).getLetter() + " | ");
                       continue;
                    }
                    
                    System.out.print((grid[i][j]).getLetter() + " | ");
                }
            System.out.println();
        }
        System.out.println("-----------------");
    }


    
    
    
    
    
    
    
}
