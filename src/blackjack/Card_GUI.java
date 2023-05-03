package blackjack;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D; 

public class Card_GUI {  
  private int suit; //suit of the card (Clubs[0], Diamonds[1], Hearts[3], or Spades[4]) 
  private int rank; //rank of the card (Ace[0], 2[1], 3[2], 4[3], 5[4], 6[5], 7[6], 8[7], 9[8], 10[9], Jack[10], Queen[11], or King[12])
  private int xPosition; //x position of the card image that you will draw to the screen
  private int yPosition; //y position of the card image that you will draw to the screen


  public Card_GUI() { //a default constructor of Card_GUI
    suit = 0;
    rank = 0;
  }
  
  public Card_GUI(int s, int r, int v) { //a constructor of Card_GUI that initializes the main attributes
    suit = s;
    rank = r;
  }

  
  public void printCard(Graphics2D g2, boolean dealerTurn, boolean faceDown, int cardNumber,int xpos_start_for_vertical_player, char player_position) throws IOException {//this method draws the card accordingly (looking to the parameters). It throws a IOException because we will be reading some image files.
    //The first parameter is the g2[powerful brush] since we will draw some images. The second parameter tells the method if it is dealer's turn. The third method checks if the card drawn will be face down or face up. The fourth parameter tells the method which card on the line will be drawn so that each line could be drawn next to each other in a horizontal line.
    BufferedImage deckImg = ImageIO.read(new File("cardSpriteSheet.png")); //we read the sprite sheet image.
    int imgWidth = 950; //this is the width of the sprite sheet image in pixels.
    int imgHeight = 392; //this is the height of the sprite sheet image in pixels.
    
    BufferedImage[][] cardPictures = new BufferedImage[4][13]; //we create this two-dimensional array to store the individiual card pictures.
    BufferedImage backOfACard = ImageIO.read(new File("backsideOfACard.jpg")); //this image will be the back of a card.
    
    for (int c = 0; c < 4; c++) { 
      for (int r = 0; r < 13; r++) {
        cardPictures[c][r] = deckImg.getSubimage(r*imgWidth/13, c*imgHeight/4, imgWidth/13, imgHeight/4);  //we assign the relative card pictures to the 2-D array.
      }
    }
    
    if (dealerTurn) { //if it is dealer's turn, then the card will be printed to the top where the dealer's hand is located.
        yPosition = 220;
        xPosition = 370 - 75*cardNumber;
    }
    else if(player_position=='H') { //if it is the turn ofa horizontal player, then the card will be printed to the below where the player's hand is located.
        yPosition = 220;
        xPosition = 700 + 75*cardNumber;
    }
    else if(player_position == 'D') //if it is the turn of a vertical DOWN player, then the card will be printed to the below where the player's hand is located.
    { 
        yPosition = 400;
        xPosition = xpos_start_for_vertical_player + 75*cardNumber;
    }
    else if(player_position == 'U')//if it is the turn of a vertical UP player, then the card will be printed to the below where the player's hand is located.
    {
        yPosition = 50;
        xPosition = xpos_start_for_vertical_player + 75*cardNumber;
    }
    else if(player_position == 'C')
    {
        yPosition = 520;
        xPosition = 30 + 25*cardNumber;
    }

    
    if (faceDown) //this boolean parameter will tell the method if the printed card will be face down or face up.
    { 
      g2.drawImage(backOfACard, xPosition, yPosition, null ); //if it is facedown, we draw the image of a back of a card.
    }
    else 
    {
      g2.drawImage(cardPictures[suit][rank], xPosition, yPosition, null); //if it is not face up, we draw the image from the 2-D array.
    }
    
  }
}