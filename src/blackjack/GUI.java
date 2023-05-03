//THIS IS A PROJECT DONE BY UZAY MACAR THAT IS DUE TO 08.01.2016 12:00.
//THIS PROJECT IS A FLUENT BLACKJACK GAME THAT HAS A MENU AND A GAMING COMPONENT.
//UZAY MACAR CODED BOTH THE INSIDE GAME MECHANICS (CARD, DECK, AND GAME CLASSES)
//AND OUTSIDE COMPONENTS (THE LAYOUT, BUTTONS, MENU, ETC.)
package blackjack;
import javax.swing.JFrame;

public class GUI {
  public static JFrame gameFrame = new JFrame(); //This is the frame in which the real blackjack game will be played.
  public static Game_GUI newGame = new Game_GUI(gameFrame); //we initialize a 'Game_GUI' in order to control, start, and calculate the blackjack game.
  
  
  public Thread gameCheckThread;
  
  public void runGUI(Card[] card_deck,Card []player1hand, Card []player2hand,Card []player3hand,Card []dealerhand){
	
	gameCheckThread = new Thread(new Runnable() {
        public Card player1[]=new Card[2];
        public Card player2[]=new Card[2];
        public Card player3[]=new Card[2];
        public Card dealer[]=new Card[2];
        public Card deck[]=new Card[52];
	

        public Runnable init(Card[]card_deck, Card []player1hand, Card []player2hand,Card []player3hand,Card []dealerhand) {
            try{
            for(int i=0;i<2;i++)// knowing that at the begining each player will have 2 cards by default
            {
                this.player1[i]=new Card(player1hand[i]);
                this.player2[i]=new Card(player2hand[i]);
                this.player3[i]=new Card(player3hand[i]);
                this.dealer[i] = new Card(dealerhand[i]);
            }
                        }
            catch(Exception e)
            {
                System.out.println("A Null Exception happened.. Make sure that you pass a 2 sized Cards Deck array");
            }
            
            try{
            for(int i=0;i<52;i++)// knowing that at the begining each player will have 2 cards by default
            {
                if(card_deck[i] != null)
                    this.deck[i]=new Card(card_deck[i]);
                    
            }
            }
            catch(Exception e)
            {
                System.out.println("A Null Exception happened.. Make sure that you pass a 52 sized Cards Deck array");
            }
            return this;
        }

        @Override
        public void run() {
			gameFrame.getContentPane().removeAll(); //we remove everything from the frame.
			newGame = new Game_GUI(gameFrame); //we initialize a new game on the same frame.
			newGame.formGame(this.deck,this.player1, this.player2,this.player3, this.dealer); //we set the atmosphere of the game(which is everything except the cards.)
        }
    }.init(card_deck,player1hand,player2hand,player3hand, dealerhand));
	
	gameCheckThread.start();
	gameRefreshThread.start();
	
  };
  
  public void updatePlayerHand(Card c, int player_no){
            Card[] Deck=new Card[4];
	  newGame.updatePlayerHand(c, player_no, Deck);
  }
  
  public void updateDealerHand(Card c,Card[] Deck){
	  newGame.updateDealerHand(c,Deck);
  }
  
   
  public static Thread gameRefreshThread = new Thread () { //this first thread (each thread must have a run method) serves to continuously [since we put true inside the while loop, it will continue forever.] refresh the component.
    public void run () {
      while(true){
        newGame.atmosphereComponent.refresh(newGame.faceDown);
      }
    }
  };

}

