/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author George Saeid
 */
public class Player {

    private String name;
    private int score;
    public Card playercard[]= new Card[11];
    public boolean ifblackjack;
    public boolean ifwon ;
    public boolean ifpush;
    public boolean ifbusted;
    private int Ccounter;
    private int Cindex ;
    
//***********************  SETTERS  **************************
    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayercard(Card[] playercard) {
        this.playercard = playercard;
    }

    public void setIfblackjack(boolean ifblackjack) {
        this.ifblackjack = ifblackjack;
    }

    public void setIfwon(boolean ifwon) {
        this.ifwon = ifwon;
    }
//***********************  GETTERS  **************************
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Card[] getPlayercard() {
        return playercard;
    }

    public boolean isIfblackjack() {
        return ifblackjack;
    }

    public boolean isIfwon() {
        return ifwon;
    }
    
    public void addCard(Card c)
    {
        Cindex =0;
        if (Ccounter <11)
        {
            playercard[Ccounter] = new Card(0,0,0);
            playercard[Ccounter] = c;
            Ccounter ++;
            score += c.getValue();
            if(score == 21)
            {
                ifblackjack = true;
            }
            
            if(score > 21)
            {
                ifbusted = true;
            }
            else if (score < 21)
                ifbusted = false;
        } 
        Cindex++;
    }
    
    
}
