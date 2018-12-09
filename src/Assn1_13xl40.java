//CMPE 212 Assignment1 Game_of_Pig    Xiaofeng_Lin  13xl40
//In this program the bot will always choose to roll unless its turn score reaches 25
//methods illustrations:
//game(): infinite loop before one player reaches 100 score. it calls action() to perform the gameplay
//action(): actually gameplay code. a infinite while loop which returns the value of score based on 
//dices rolled in the current turn. 2 out of 4 situations will force method to return the score,
//1 out of 4 situation will reset the while loop (for a reroll). The last one will first determine which
//player is playing: if the human is playing the turn it would pop out a window to let human choose to
//roll or hold; if the bot is playing the turn it would continue to reroll until bot's score reaches 25
//or its dices rerolled were in the first 2 situations.
//generate(): generate a random value between 1 and 6.
//roll(): generate two dice values and assign them to both two dices
//show winner(): print out the winner of the game. winner is determined by checking which player played 
//the latest turn.
import javax.swing.JOptionPane;
import java.util.Random;
import java.lang.Math;

public class Assn1_13xl40 {	
	//setting global variable
	public static Random rnd = new Random(System.currentTimeMillis());
	public static final int WIN = 100;
	// main method
	public static void main(String[] args) {
		//initialization
		int human_score = 0; //initialize player's accumulated score
		int bot_score = 0;	//initialize bot's accumulated score
		String player = "human"; //player playing the turn
		JOptionPane.showMessageDialog(null, "Welcome to Game of Pig!\nYou will go first! First one reaching 100 score win! GLHF!");
		game(player, human_score, bot_score);//main loop
		show_winner(player);//show winner
}
	public static void game(String player, int human_score, int bot_score){
		//infinite loop until there is a winner
		while (human_score < WIN && bot_score < WIN){
			if (player == "human"){
				human_score = action(player, human_score, bot_score);
				player = "bot"; //switch player
			}
			else {
				bot_score = action(player, human_score, bot_score);
				player = "human"; //switch player
			}
			JOptionPane.showMessageDialog(null, "Currently human's score is " + human_score +" and the bot's score is " + bot_score);
		}
	}		
	public static int action(String player, int human_score, int bot_score){ //calculate turn score based on dices value
		int dice1 = 0;
		int dice2 = 0;
		int round_score = 0;
		while(true){
			dice1 = generate(rnd); //roll dice 1
			dice2 = generate(rnd); //roll dice 2
			JOptionPane.showMessageDialog(null, "The " + player + " just rolled: " + dice1 + " and " + dice2);
			// use 4 conditions to determine the result
			if (dice1 == 1 & dice2 == dice1){ //rolled both ones
				JOptionPane.showMessageDialog(null, "The " + player + " accumulated score will be set to 0!");
				return 0;
			}
			else if (dice1 != dice2 & (dice1 == 1 || dice2 == 1)){ //only one of the dice is 1
				JOptionPane.showMessageDialog(null, "The " + player + " round score will be 0");
				if (player == "human")
					return human_score;
				else
					return bot_score;
			}
			else if (dice1 == dice2){ //rolled doubles, need to reroll
				JOptionPane.showMessageDialog(null, "The " + player + " rolled doubles! The " + player + " must reroll!");
			}
			else{ //other combination
				round_score += dice1 + dice2;
				JOptionPane.showMessageDialog(null, "The round score is currently " + round_score);
				if (player == "human"){ //the player is human
					int choice = JOptionPane.showOptionDialog(null, "Do you wish to roll the dice again?", "It's your turn!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (choice == 0){ //player chooses to roll
						JOptionPane.showMessageDialog(null, "Human decides to roll again!");
					}
					else{ //player chooses to hold
						JOptionPane.showMessageDialog(null, "Human decides to hold!");
						return human_score += round_score;
					}
				}
				else { //player is bot
					if (round_score > 25){
						JOptionPane.showMessageDialog(null, "Bot decides to hold!");
						return bot_score += round_score;
					}
					else {
						JOptionPane.showMessageDialog(null, "Bot decides to roll again!");
					}
				}		
			}
		}
	}
	public static int generate(Random rnd){
		return Math.abs(rnd.nextInt() % 6) + 1;
	}
	public static void show_winner(String player){
		if (player == "bot")
			JOptionPane.showMessageDialog(null,"The human wins!");
		else 
			JOptionPane.showMessageDialog(null,"The bot wins!");
	}
	public static void roll(int dice1, int dice2, String player){
		dice1 = generate(rnd);
		dice2 = generate(rnd);
		JOptionPane.showMessageDialog(null, "The " + player + " just rolled: " + dice1 + " and " + dice2);
	}
}