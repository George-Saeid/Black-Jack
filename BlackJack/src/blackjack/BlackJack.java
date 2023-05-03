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
public class BlackJack {
    
    static Game Bj = new Game();
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        GUI gui = new GUI();
         
        
        Bj.gen_carddeck();
        Bj.PlayerInfo();
        gui.runGUI( Bj.carddeck, Bj.players[0].playercard, Bj.players[1].playercard, Bj.players[2].playercard, Bj.players[3].playercard );
        System.out.println("\tSTART GAME\t\n\n");
        
        PlayerTurn(gui);
        highScoreFn();
        WhoWin();
        DealerTurn(gui);
        Winner();
        
        

        
        
        
        
    }
    
    
    public static void PlayerTurn(GUI gui)
    {
        int Hit_Stand;
        boolean pass = true;
                for (int i =0; i<Bj.players.length-1;i++)
        {
            do
            {
                System.out.println("\nTurn of Player No. "+(i+1));
                System.out.println("Player's Score : " + Bj.players[i].getScore());
                System.out.println("1: Hit\n2: Stand");
                Hit_Stand = input.nextInt();
                
                if (Hit_Stand == 1)
                {
                    
                    
                   // Bj.players[i].addCard(Bj.RandCard());
                    addCardPlayer(i,gui);
                                       
                    
                    if (Bj.players[i].ifblackjack == true)
                        {
                            System.out.println("Player's Score : " + Bj.players[i].getScore());
                            System.out.println("BLACK JACK !!");
                            pass = false;
                        }
                    else if (Bj.players[i].getScore() > 21)
                        {
                            System.out.println("Player's Score : " + Bj.players[i].getScore());
                            System.out.println("Busted !!");
                            pass = false;
                        }
                    else
                    pass = true;
                    
                }
                else if (Hit_Stand == 2)
                {
                    System.out.println("\nPlayer's Score : " + Bj.players[i].getScore());
                    System.out.println("STAND !!");
                    pass = false;
                }
                else
                {
                    System.out.println("Please Try again");
                    pass = true;
                }
            }while(pass);
        }

    }
    
    public static void DealerTurn(GUI gui)
    {
        int Hit_Stand;
        boolean pass = true;
        if(Bj.players[3].getScore() <= Bj.getHighscore())
        {
            do
            {
            System.out.println("\nIt's the Dealer turn");
            System.out.println("Dealesr's Score : " + Bj.players[3].getScore());
            System.out.println("\n1: Hit");
            Hit_Stand = input.nextInt();
            if (Hit_Stand == 1)
                {
                    Card c = Bj.RandCard();
                    Bj.players[3].addCard(c);
                            
                    gui.updateDealerHand(c, Bj.carddeck);
                    pass = true;
                    
                    for(int i=0; i<3; i++ )
                    {
                        if(Bj.players[3].getScore() == Bj.players[i].getScore() && Bj.players[3].getScore()<21)
                        {
                            System.out.println("PUSH !!");
                            Bj.players[3].ifpush = true;
                        }
                        pass = false;
                       
                    }
                    
                    if (Bj.players[3].ifblackjack == true)
                        {
                            System.out.println("BLACK JACK !!");
                            System.out.println("Dealer's Score : " + Bj.players[3].getScore());
                            Bj.players[3].ifwon = true;
                            pass = false;
                        }
                    else if (Bj.players[3].getScore() > 21)
                        {
                            System.out.println("Busted !!");
                            System.out.println("Dealesr's Score : " + Bj.players[3].getScore());
                            Bj.players[3].ifwon = false;
                            pass = false;
                        }
                    else if (Bj.players[3].getScore() > Bj.getHighscore())
                    {
                        System.out.println("Dealer WON !!");
                        System.out.println("Dealesr's Score : " + Bj.players[3].getScore());
                        Bj.players[3].ifwon =true;
                        pass = false;
                    }
                    else
                        pass = true;
                    
                }
            else
                {
                    System.out.println("Please Try again");
                    pass = true;
                }
                
                
            }while(pass);   
        }
        else
        {
            System.out.println("the dealer won !!");
            System.out.println("Dealer's Score : " + Bj.players[3].getScore());
            Bj.players[3].ifwon = true;
            
        }
    }
    
    public static void addCardPlayer(int i,GUI gui)
    {
        Card c = Bj.RandCard();
        Bj.players[i].addCard(c);
        gui.updatePlayerHand(c, i);
        
        
    }
    
    public static void highScoreFn()
    {
        Bj.UpdateHighScore();
        System.out.println("\nHigh Score in the 3 players : "+ Bj.getHighscore()+ "\n");
    }
    
    public static void WhoWin()
    {
        for (int i=0; i<3; i++)
        {
            if (Bj.players[i].ifblackjack == true)
            {
                System.out.println("The BlackJack is Player No. : "+ (i+1) );
                Bj.players[i].ifwon = true;
            }
        }
        if (Bj.players[0].getScore() > Bj.players[1].getScore()  && Bj.players[0].getScore() > Bj.players[2].getScore() && Bj.players[0].getScore() < 21)
        {
            System.out.println("The Winner is player NO. 1 ");
            Bj.players[0].ifwon = true;
        }
        else if (Bj.players[1].getScore() > Bj.players[0].getScore()  && Bj.players[1].getScore() > Bj.players[2].getScore() && Bj.players[1].getScore() < 21 )
        {
            System.out.println("The Winner is player NO. 2 ");
            Bj.players[1].ifwon = true;
        }
        else if (Bj.players[2].getScore() > Bj.players[0].getScore()  && Bj.players[2].getScore() > Bj.players[1].getScore() && Bj.players[2].getScore() < 21 )
        {
            System.out.println("The Winner is player NO. 3 ");
            Bj.players[2].ifwon = true;
        }
        if (Bj.players[0].getScore() == Bj.players[1].getScore()  || Bj.players[0].getScore() == Bj.players[2].getScore() ||
                Bj.players[1].getScore() == Bj.players[2].getScore() || Bj.players[0].getScore() == Bj.players[1].getScore() 
                && Bj.players[0].getScore()<21 && Bj.players[1].getScore()<21 && Bj.players[2].getScore()<21 )
        {
            System.out.println("PUSH !!");
            for(int i=0; i<4; i++)
            {
                Bj.players[i].ifpush =true;
            }
        }   
    }
    public static void Winner()
    {
        for (int i=0; i<4; i++)
        {
            if(Bj.players[i].ifwon == true)
            {
                System.out.println("\nAND THE WINNER IS PLAYER NUMBER" + "\n\t\t"+ (i+1));
                System.out.println("Player Name : " +"\t\t" + Bj.players[i].getName());
                System.out.println("Player Score : " + "\t\t" +Bj.players[i].getScore());
                
            }
            else if (Bj.players[i].ifpush == true)
            {
                System.out.println("\t\tPUSH \n\tNO One WON !!");
                break;
            }
            for(int j=0; j<4;j++)
            {
            if (Bj.players[i].ifpush == true && Bj.players[j].ifwon == true)
            {
                System.out.println("congruatiluation");
                break;
            }
            }
            
        }
        
    }
}


