package blackjack;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.*;
import java.io.*;


public class GameComponent extends JComponent {//this class will implement the MouseListener because we will check if the user clicked a certain coordinate on the component.
  public BufferedImage backgroundImage; //we name three images: one for the background image, one for the blackjack logo that will be located in the center, and for the chip.
  public BufferedImage logo;
  public BufferedImage chip;
  private ArrayList<Card_GUI> dealerHand; //as usual, we have to card arraylists which will serve as hands: one for the dealer and for the player.
  private ArrayList<Card_GUI> playerHand1;
  private ArrayList<Card_GUI> playerHand2;
  private ArrayList<Card_GUI> playerHand3;
  private ArrayList<Card_GUI> Deck;
  public static boolean dealers_turn=false;
  public boolean faceDown = true; //this boolean value will tell the program if we have the card facedown or faceup.

  public GameComponent(ArrayList<Card_GUI>deck,ArrayList<Card_GUI> dH, ArrayList<Card_GUI> pH1, ArrayList<Card_GUI> pH2,ArrayList<Card_GUI> pH3) { //this will be the constructor for this class which will accept two hands as a parameter.
    dealerHand = dH; //we initalize the instant fields.
    playerHand1 = pH1;
    playerHand2 = pH2;
    playerHand3 = pH3;
    Deck=deck;
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g; //we first cast Graphics g to Graphics2D g2 in order to use a more powerful brush.

    try {
      backgroundImage = ImageIO.read(new File("background.png")); //we read a file which is the png image of a poker table for our background image.
      logo = ImageIO.read(new File("blackjackLogo.png")); //we read a file which is the png image of a blackjack logo for the logo on the poker table.
    
    }
    catch(IOException e) {}

    g2.drawImage(backgroundImage, 0, 0, null); //we draw these images to the component.
    g2.drawImage(logo, 510, 200, null);
	
    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Arial", Font.BOLD, 30));
    
    g2.drawString("P1", 555, 190); // Up player 1
    g2.drawString("P2", 650, 280); // Horizontal player 2
    g2.drawString("P3", 555, 380); // Down player 3
    g2.drawString("D.", 460, 280); // Dealer
    
    try { //we need to have the try and catch blocks here because the method printCard of a Card_GUI object draws images chopped off a spritesheet image from a 2D-array.
      for (int i = 0; i < dealerHand.size(); i++) {//we go through dealer's hand
        if (i == 1) { //if it is the first card,
          if(faceDown) { //we check if will be facedown or not.
            dealerHand.get(i).printCard(g2, true, true, i,0,' '); //we then draw each individual card.
          }
          else {
            dealerHand.get(i).printCard(g2, true, false, i,0,' '); //if it is not face down, we write the 3rd parameter as false and then draw each individual card in the hand again.
          }
        }
        else {
          dealerHand.get(i).printCard(g2, true, false, i,0,' '); //if it is not the first card, we write the 3rd parameter as false and then draw each individual card in the hand again.
        }
      }
    }
    catch (IOException e) {}

    try {
      for (int i = 0; i < playerHand2.size(); i++) { //we do the same thing for the user hand with a foor loop again: we go through each of the cards in user's hand.
        playerHand2.get(i).printCard(g2, false, false, i,0, 'H'); //we then draw each of the card on the component(screen). Extra information about parameters can be found in the Card_GUI class.
      }
    }
    catch (IOException e) {}
    
    
    try {
        int xpos=500;
        if(playerHand1.size() > 2)
        {
          xpos=xpos-40*(playerHand1.size()-2);   
        }

      for (int i = 0; i < playerHand1.size(); i++) { //we do the same thing for the user hand with a foor loop again: we go through each of the cards in user's hand.
        playerHand1.get(i).printCard(g2, false, false, i,xpos, 'U'); //we then draw each of the card on the component(screen). Extra information about parameters can be found in the Card_GUI class.
      }
    }
    catch (IOException e) {}
   

    try {
        int xpos=500;
        if(playerHand3.size() > 2)
        {
          xpos=xpos-40*(playerHand3.size()-2);   
        }
      for (int i = 0; i < playerHand3.size(); i++) { //we do the same thing for the user hand with a foor loop again: we go through each of the cards in user's hand.
        playerHand3.get(i).printCard(g2, false, false, i,xpos, 'D'); //we then draw each of the card on the component(screen). Extra information about parameters can be found in the Card_GUI class.
      }
    }
    catch (IOException e) {}  
    
    if(dealers_turn){
        try{

          for (int i = 0; i < Deck.size(); i++) { //we do the same thing for the user hand with a foor loop again: we go through each of the cards in user's hand.
            if(Deck.get(i) != null)
              Deck.get(i).printCard(g2, false, false, i,0, 'C'); //we then draw each of the card on the component(screen). Extra information about parameters can be found in the Card_GUI class.
          }

        }
        catch(IOException e)
        {}
    }
  }


  public void refresh(boolean fD) { //this refresh method will refresh the GameComponent when it is called.
    faceDown = fD;
    this.repaint();
    
  }
}