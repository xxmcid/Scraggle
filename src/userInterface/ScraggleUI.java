package userInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import game.Game;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;



/**
 *
 * @author kwhiting
 */
public class ScraggleUI
{
    private static final Color MIDNIGHT_PURPLE = new Color(102,0,153);
    
    // top level containter
    JFrame frame;
    Game game;
    
   
    //Components for Current Word panel
    JPanel curWordPanel;
    JLabel curWordLabel;
    JLabel playerScoreLabel;
    JButton submitButton;
    
    //Components for Scraggle Board panel
    JPanel boardPanel;
    JButton die [] [];
    
    //Components for information panel
    JPanel infoPanel;
    //JTextArea textArea;
    JTextPane wordsPane;
    JScrollPane scrollPane;
    JLabel timeLabel;
    JButton shakeButton;
    
    
    //Menu components 
    JMenuBar menuBar;   //Creates area to add menu items
    JMenu Scraggle;     //A "dropDown" tab
    
    //JMenuItems are Individual items in the "dropDown" tab
    JMenuItem newGame;  
    JMenuItem exit;
    
    
    //Functional Member Variables
    int score = 0;
    Timer timer;
    int minutes;
    int seconds;
    ArrayList<String> foundWords = new ArrayList();
    
    //Event handler for resetting the board for a new game.
    private ResetGameListener resetGameListener;
         
   
    
    /**
     *
     * @param passedGame
     */
    public ScraggleUI(Game passedGame)
    {
        this.game = passedGame;
        
        //Instantiating the event handler of the ResetGameListener class
        resetGameListener = new ResetGameListener();
        
        initComponents();
    }
    
    
    private void initComponents()
    {
        
        // Initialize the JFrame
        frame = new JFrame("Scraggle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(620,500));
        frame.setLayout(new BorderLayout());

        // Initialize the JMenuBar and add to the JFrame
        menuBar = new JMenuBar();
        
        // Initialize the File menu and add the JMenuItems with action listener
        Scraggle = new JMenu("Scraggle");
        newGame = new JMenuItem("New Game");
        exit = new JMenuItem("Exit");
        
        //Add actions to New Game and Exit menu items
        newGame.addActionListener(resetGameListener);
        exit.addActionListener(new ExitListener());
        
        
        // Adding our new game and exit buttons to the dropdown menu
        Scraggle.add(newGame);
        Scraggle.add(exit);
        
        // Add our menu to the actual JMenuBar
        menuBar.add(Scraggle);
        
        // Place the JMenuBar onto the JFrame.
        frame.setJMenuBar(menuBar);
        
        
 
        
        //Current Word Panel configuration
        curWordPanel = new JPanel(new FlowLayout());
        curWordLabel = new JLabel();
        playerScoreLabel = new JLabel();
        submitButton = new JButton("Submit Word");
        submitButton.addActionListener(SubmitListener);
        submitButton.setBackground(MIDNIGHT_PURPLE);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        
        //Set dimensions for elements in the Current word panel
        curWordLabel.setPreferredSize(new Dimension(250, 50));
        submitButton.setPreferredSize(new Dimension(200, 75));
        playerScoreLabel.setPreferredSize(new Dimension(120,50));
        
        //Set Bordering for Current word panels and labels.
        curWordPanel.setBorder(BorderFactory.createTitledBorder("Current Word"));
        curWordLabel.setBorder(BorderFactory.createTitledBorder("Current Word"));
        playerScoreLabel.setBorder(BorderFactory.createTitledBorder("Score"));
        
        //Add our 2 labels and 1 button to our Current Word panel
        curWordPanel.add(curWordLabel);
        curWordPanel.add(submitButton);
        curWordPanel.add(playerScoreLabel);
        
      
        //Scraggle Board Panel configuration
        boardPanel = new JPanel(new GridLayout(4, 4));
        boardPanel.setBorder(BorderFactory.createTitledBorder("Scraggle Board"));
        die = new JButton [4] [4];
        
        //Adding our buttons to the boardPanel
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                URL imgURL = getClass().getResource((game.getGrid()[i][j]).getImgPath());
                ImageIcon icon = new ImageIcon(imgURL);
              
                die[i] [j] = new JButton(icon);
                die[i][j].putClientProperty("row", i);
                die[i][j].putClientProperty("col", j);
                TileListener tileListener = new TileListener();
                LetterListener letterListener = new LetterListener();
                die[i][j].addActionListener(tileListener);
                die[j][j].addActionListener(letterListener);
                
                boardPanel.add(die[i][j]);
            }
        }  
        
      
        
        
        //info panel configuration
        infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder("Enter Words Found"));
        infoPanel.setLayout(new GridLayout(3,1));
       
        //Text area and scroll configuration
        wordsPane = new JTextPane();
        wordsPane.setPreferredSize(new Dimension(30, 25));
        wordsPane.setPreferredSize(new Dimension(290,100));
       
        scrollPane = new JScrollPane(wordsPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        
        
        //Timer label configuration
        timeLabel = new JLabel("3:00", JLabel.CENTER);
        timeLabel.setMinimumSize(new Dimension(290,100));
        timeLabel.setBorder(BorderFactory.createTitledBorder("Time Left"));
        timeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        
        
        
        //Shake Dice button configuration
        shakeButton = new JButton();
        shakeButton.addActionListener(resetGameListener);
        shakeButton.setText("*  Shake Dice  *");
        shakeButton.setMinimumSize(new Dimension(290,100));
        shakeButton.setBackground(MIDNIGHT_PURPLE);
        shakeButton.setOpaque(true);
        shakeButton.setBorderPainted(false);
         
        //Adding elements to our information panel
        infoPanel.add(scrollPane);
        infoPanel.add(timeLabel);
        infoPanel.add(shakeButton);
        
        //Instantiating timer
        timer = new Timer(1000, new TimerListener());
        
        
        
        
        
        //Adding all panels to our main frame.
        frame.add(curWordPanel, BorderLayout.SOUTH);
        frame.add(infoPanel, BorderLayout.EAST);
        frame.add(boardPanel, BorderLayout.WEST);
        
        frame.setVisible(true);

    } 
    
    //Utility Method
    private void resetBoard()
    {
        
        boardPanel = new JPanel(new GridLayout(4, 4));
        boardPanel.setBorder(BorderFactory.createTitledBorder("Scraggle Board"));
            
            //Repopulate the board
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < 4; j++)
                {
                   URL imgURL = getClass().getResource((game.getGrid()[i][j]).getImgPath());
                   ImageIcon icon = new ImageIcon(imgURL);
              
                   die[i] [j] = new JButton(icon);
                   die[i][j].putClientProperty("row", i);
                   die[i][j].putClientProperty("col", j);
                   TileListener tileListener = new TileListener();
                   LetterListener letterListener = new LetterListener();
                   die[i][j].addActionListener(tileListener);
                   die[j][j].addActionListener(letterListener);
                
                   boardPanel.add(die[i][j]);
                }
            }

    }
    
    private void updateTextArea(String data)
    {
        wordsPane.setText(wordsPane.getText() + "\n" + data);
        wordsPane.setCaretPosition(wordsPane.getDocument().getLength());
    }
   
    
    //Inner class for exit listener
    private class ExitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            int response = JOptionPane.showConfirmDialog(frame, "Confirm to exit Scraggle?"
                    , "exit?", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
       
    }
    
    //Inner class for ResetGameListener
    private class ResetGameListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            //Resets score
            score = 0;
            //Populates grid once again
            game.populateGrid();
            //Removes the grid
            frame.remove(boardPanel);
            //Remove the rest of the components
            boardPanel.removeAll();
            
            //Repopulates our board
            resetBoard();
            
            boardPanel.revalidate();
            boardPanel.repaint();
            
            //Adding board back onto our frame
            frame.add(boardPanel, BorderLayout.WEST);
            
            //Clear text box
            wordsPane.setText("");
           
            //Reset score
            playerScoreLabel.setText("0");
            
            //Reset timer and current word label
            curWordLabel.setText("0");
            timeLabel.setText("3:00");
            timeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
            
            foundWords.removeAll(foundWords);
            
            timer.stop();
            minutes = 3;
            seconds = 0;
            timer.start();
        }
        
    }
    
    private class SubmitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            int wordScore = game.getDictionary().search(curWordLabel.getText().toLowerCase());
           
            if(foundWords.contains(curWordLabel.getText().toLowerCase()))
            {
                JOptionPane.showMessageDialog(frame, "Word found Already!"); 
            }
            else
            {
                if(score > 0)
                {
                    updateTextArea(curWordLabel.getText());
                    foundWords.add(curWordLabel.getText().toLowerCase());
                    score
                }
            }
            
            
        }
        
    }
    
    
    
    
    
    
}

