package fight;

import java.util.InputMismatchException;
import java.util.Scanner;

import character.Character;
import stat.StatNames;

public class Fight {
	
	private static Scanner scanner;
	
	public Fight(Scanner sc) {
		scanner = sc;
	}
	
	/**
	 * Starts a fight and return the result of the battle (who lose)
	 */
	public  String start(Character player1, Character player2) {
		
		while(playersAreAlive(player1, player2)) {
			playerAttacks(player1, chooseAction(player1), player2);
			if(!playersAreAlive(player1, player2)) break;
			playerAttacks(player2, chooseAction(player2), player1);
		}
		
		return getResult(player1, player2);
	}
	
	/**
	 * Tells if both players are still alive
	 * @param player1 
	 * @param player2
	 * @return a boolean (true if they are alive, false if one of them is dead)
	 */
	private  boolean playersAreAlive(Character player1, Character player2) {
		return (player1.getStat(StatNames.HP) != 0 && player2.getStat(StatNames.HP) != 0);
	}
	
	/**
	 * Allows the player to choose which type of attack to do
	 * @param player
	 * @return the type of attack (int) 
	 */
	private int chooseAction(Character player) {
		int curentAttack;
		String question = player.getName() + " (" + player.getStat(StatNames.HP) + ") "
				+ "Veuillez choisir votre action "
				+ "(1 : Attaque Basique, 2 : Attaque Spéciale)";
		do {
			curentAttack = askValue(question);
		}while(curentAttack != 1 && curentAttack != 2);	
		
		return curentAttack;
	}
	
	/**
	 * Make the player to attack its opponent with the choosen attack
	 * @param player
	 * @param attackNumber
	 * @param opponent
	 */
	private void playerAttacks(Character player, int attackNumber, Character opponent) {
		if(attackNumber == 1) {
			player.basicAttack(opponent);
		}else if(attackNumber == 2) {
			player.specialAttack(opponent);
		}
	}
	
	/**
	 * Show the result of the fight
	 * @param player1
	 * @param player2
	 * @return A string that says who loses.
	 */
	private String getResult(Character player1, Character player2) {
		if(player1.getStat(StatNames.HP) == 0) {
			return player1.getName() + " est mort.";
		}else {
			return player2.getName() + " est mort.";
		}
	}
	
	/**
	 * Asks the player to enter a value 
	 * @param question question that is asked to the player 
	 * @return the choice the player made
	 */
	private static int askValue(String question) {
		int curVal = -1;
		boolean catched;
		System.out.println(question);
		do {
			try {
				catched = false;
				curVal = scanner.nextInt();
			}catch(InputMismatchException e) {
				scanner.next();
				System.err.print("Veuillez entrer un nombre.\n");
				System.out.println(question);
				catched = true;
			}
		}while(catched);
		
		return curVal;
	}

}
