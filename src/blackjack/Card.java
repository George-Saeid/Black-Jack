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
public class Card {
    private final int suit;
    private final int rank;
    private final int value;
    
//**************** Parameterized constructor  ************************** 
    Card (int s, int r, int v)
    {
        suit = s;
        rank = r;
        value = v;
    }

//**************** COPY CONSTRUCTOR  *************************
    public Card (Card c)
    {
        suit = c.suit;
        rank = c.rank;
        value = c.value;
    }
    
//**************** GETTERS  **************************
    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }
    
}
