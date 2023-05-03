/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import java.util.*;
/**
 *
 * @author George Saeid
 */
public class Game {
    public Player players[] = new Player[4];
    public Card carddeck[] = new Card[52];
    private int highscore =0; //(<=21)

    public void setHighscore(int highscore) 
    {
        this.highscore = highscore;
    }

    public int getHighscore() {
        return highscore;
    }
        
// **************** GENERATE CARDDECK ***************************    
    public void gen_carddeck()
    {
         Card card;
         int n=0;
        for (int i=0; i<4; i++)
        {
            for (int j=0; j<13; j++)
            {
                if(j>=9)
                {
                card = new Card(i, j, 10);
                }
                else
                {
                card = new Card(i, j, j+1);
                }
                 carddeck[n] = card;
                 n++;
            }
        }
       
    }
// **************** DRAW CARD ***************************    
    public Card RandCard()
    {
        boolean s = true;
        Card Crand = new Card(0,0,0);
        Random rand = new Random();
        while (s)
        {
            int random_index = rand.nextInt(52);
            if (carddeck[random_index] != null)
            {
                Crand = carddeck[random_index];
                carddeck[random_index]= null;
                s = false;
            }
            else 
            {
                s = true;
            }  
        }
        return Crand;   
    }
// **************** Player Info Fn ***************************    
    public void PlayerInfo()
    {
        Scanner input = new Scanner(System.in);
        
        for (int i=0; i<3;i++)
        {
            System.out.println("please enter the name of the player Number : " + (i+1) 
                                       + "\nNOTE : The last player is the dealer !! ");
            String plName = input.nextLine();
            players[i] = new Player();
            players[i].setName(plName);
            players[i].addCard(RandCard());
            players[i].addCard(RandCard());
            
        }  
        players[3] = new Player();
        players[3].setName("dealer");
        players[3].addCard(RandCard());
        players[3].addCard(RandCard());
    }
// **************** UPDATE HIGH SCORE Fn ***************************  
    public void UpdateHighScore()
    {
       for (int i=0; i<3; i++)
       {
           if(players[i].getScore() < 21 && highscore < players[i].getScore())
           {
               setHighscore(players[i].getScore()); 
           }
           else if (players[i].getScore()== 21)
               setHighscore(21);
               
       }
    }




}

