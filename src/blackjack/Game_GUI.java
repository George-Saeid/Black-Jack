package blackjack;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.*;
import java.util.Random;

public class Game_GUI {

  ArrayList<Card_GUI> dealerHand = new ArrayList<>(); //this is the arraylist for the dealer's hand.
  ArrayList<Card_GUI> playerHand1= new ArrayList<>(); //this is the arraylist for the player's hand.
  ArrayList<Card_GUI> playerHand2= new ArrayList<>(); //this is the arraylist for the player's hand.
  ArrayList<Card_GUI> playerHand3= new ArrayList<>(); //this is the arraylist for the player's hand.
  ArrayList<Card_GUI> Deck= new ArrayList<>();
  Random rand = new Random();
  
  
  public boolean faceDown; //this boolean value will tell the program if we have the card facedown or faceup.

  JFrame frame; //we create a JFrame.
  GameComponent atmosphereComponent; //Two GameComponents: one for the background images, buttons, and the overall atmosphere.
  GameComponent cardComponent; //Other for the cards printing out.


  public Game_GUI(JFrame f) {//with this constructor, we initialize the instant fields accordingly. This constructor gets a JFrame as a parameter.
    dealerHand = new ArrayList<Card_GUI>();
    playerHand1 = new ArrayList<Card_GUI>();
    atmosphereComponent = new GameComponent(Deck,dealerHand, playerHand1,playerHand2,playerHand3);
    frame = f;
    faceDown = true;
  }

  public void formGame(Card[] deck,Card []player1hand, Card []player2hand,Card []player3hand,Card []dealerhand) { 
    frame.setSize(1130, 665);
    frame.setLocationRelativeTo(null); //this centers the JFrame on the screen.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false); //we make it impossible for the computer user to resize the Frame.


    //btnExit.setBounds(930, 240, 190, 50);


    atmosphereComponent = new GameComponent(Deck,dealerHand, playerHand1,playerHand2,playerHand3); //we initialize the GameComponent that will be the overall atmosphere of our game.
    atmosphereComponent.setBounds(0, 0, 1130, 665);  //we set the bounds of the component.
    frame.add(atmosphereComponent); //we add the component to the frame.
    frame.setVisible(true); //we make the frame visible.
	
	startGame(deck,player1hand, player2hand, player3hand, dealerhand);
  }
  
  //rank of the card (Ace[0], 2[1], 3[2], 4[3], 5[4], 6[5], 7[6], 8[7], 9[8], 10[9], Jack[10], Queen[11], or King[12])
  //value of the card in blackjack (from 1 to 11) (images_value = 10, Aces= 1 or 11
  public void updatePlayerHand(Card c, int player_no, Card[]deck){

    if(player_no == 0)  
    {
        playerHand1.add(new Card_GUI(c.getSuit(), c.getRank(), c.getValue()));
    }
    else if(player_no == 1)  
    {
        playerHand2.add(new Card_GUI(c.getSuit(), c.getRank(), c.getValue()));
    }
    else if(player_no == 2)  
    {
        playerHand3.add(new Card_GUI(c.getSuit(), c.getRank(), c.getValue()));
    }
    
   
    faceDown = true;
          
  }
  
  public void updateDealerHand(Card c, Card[]deck){
    dealerHand.add(new Card_GUI(c.getSuit(), c.getRank(), c.getValue()));
    GameComponent.dealers_turn=true;
    Deck.clear();
    for(int i=0; i<deck.length;i++)
          if(deck[i]!=null)
          Deck.add(new Card_GUI(deck[i].getSuit(), deck[i].getRank(), deck[i].getValue()));

    faceDown = false;
  }
  

  public void startGame(Card[] deck,Card []player1hand, Card []player2hand,Card []player3hand,Card []dealerhand) { //this method starts the game: the cards are drawn and are printed out.
  
      playerHand1.add(new Card_GUI(player1hand[0].getSuit(), player1hand[0].getRank(), player1hand[0].getValue()));
      playerHand1.add(new Card_GUI(player1hand[1].getSuit(), player1hand[1].getRank(), player1hand[1].getValue()));
      
      playerHand2.add(new Card_GUI(player2hand[0].getSuit(), player2hand[0].getRank(), player2hand[0].getValue()));
      playerHand2.add(new Card_GUI(player2hand[1].getSuit(), player2hand[1].getRank(), player2hand[1].getValue()));

      playerHand3.add(new Card_GUI(player3hand[0].getSuit(), player3hand[0].getRank(), player3hand[0].getValue()));
      playerHand3.add(new Card_GUI(player3hand[1].getSuit(), player3hand[1].getRank(), player3hand[1].getValue()));

      dealerHand.add(new Card_GUI(dealerhand[0].getSuit(), dealerhand[0].getRank(), dealerhand[0].getValue()));
      dealerHand.add(new Card_GUI(dealerhand[1].getSuit(), dealerhand[1].getRank(), dealerhand[1].getValue()));
      
    cardComponent = new GameComponent(Deck,dealerHand, playerHand1,playerHand2,playerHand3); //we initialize our component which accepts two card arraylists.
    cardComponent.setBounds(0, 0, 1200, 665); //we set the bounds of our component.
    frame.add(cardComponent); //we add the component to the grame.
    frame.setVisible(true); //we make the frame visible.
  }
}