/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scraggle;

import dictionary.Dictionary;
import game.Game;
import userInterface.ScraggleUI;
/**
 *
 * @author humzarahman
 */
public class Scraggle 
{
    
    public static void main(String args [])
    {
        //Need dictionary object to estahlish valid words.
        Dictionary dictionary = new Dictionary();
       
        //Need a game object that contains a dictionary list.
        Game game = new Game(dictionary);
        
        ScraggleUI UI = new ScraggleUI(game);
        
    }
    
}
