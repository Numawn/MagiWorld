package main;

import character.Warrior;
import exceptions.LevelValueIsInvalid;
import exceptions.StatValueIsInvalid;
import exceptions.StatisticPointsAreInvalid;
import stat.Stat;
import stat.StatNames;
import stat.Stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import character.Character;
import character.Mage;
import character.Rover;

/**
 * 
 * @author Numawn
 * 
 * Main class of MagiWorld game
 *
 */

public class Main {
	
	private static final List<String> classes = new ArrayList<String>();
	private static final Scanner scanner = new Scanner(System.in);
	
	
	private static final String[] questions = {"Niveau du personnage ?",
			"Force du personnage ?",
			"Agilité du personnage ?",
			"Intelligence du personnage ?"};
	
	private static final String[] statNames = {StatNames.LVL, StatNames.STR, StatNames.AGI, StatNames.INT};
	
	private static final Stat level = null;
	private static final Stat strength = null;
	private static final Stat agi = null;
	private static final Stat intel = null;
	
	private static final Stat[] stats = {level, strength, agi, intel};
	
	
	public static void main(String[] args) {
		classes.add("Guerrier");
		classes.add("Rôdeur");
		classes.add("Mage");
	
		Character player1 = createPlayer(1);
		player1.getDescription();
		
		Character player2 = createPlayer(2);
		player2.getDescription();
		
		startFight(player1, player2);
		
		scanner.close();
		
	}
	
	/**
	 * Allows to create a player
	 * @param playerNumber 
	 * @return the new player
	 */
	private static Character createPlayer(int playerNumber) {
		int type = chooseType(playerNumber);
		boolean errCatched;
		Stats stats = null;
		do {
			try {
				 stats = new Stats(collectStats());
				errCatched = false;
			}catch(LevelValueIsInvalid e) {
				System.out.println("Le niveau doit être situé entre 1 et 100.");
				errCatched = true;
			}catch(StatisticPointsAreInvalid e) {
				System.out.println("La somme des statistiques doit être égale au niveau.");
				errCatched = true;
			}
		}while(errCatched);
		
		Character player = selectType(type, "Joueur " + playerNumber, stats);
		
		return player;
	}
	
	/**
	 * Allows to choose the type (class) of the new player
	 * @param playerNumber 
	 * @return the type (class) of the character (an int)
	 */
	private static int chooseType(int playerNumber) {
		int type = 0;
		System.out.println("Création du Joueur " + playerNumber);
		
		do {
			System.out.print("Veuillez choisir la classe de votre personnage (");

			for(int j = 1; j <= classes.size(); j++) {
				if(j == classes.size())
					System.out.print(j + " : " +  classes.get(j-1) + ")\n");
				else
					System.out.print(j + " : " +  classes.get(j-1) + ", ");
			}

			type = scanner.nextInt(); 
		}while(type < 1 || type > classes.size());
		
		return type;
		
	}
	
	/**
	 * Creates the stats
	 * @return return the stats in a tab
	 */
	private static Stat[] collectStats() {
		int curVal;
		
		for(int nbQ = 0; nbQ < questions.length; nbQ++) {
			do {
				System.out.println(questions[nbQ]);
				curVal = scanner.nextInt();
				try { 
					stats[nbQ] = new Stat(statNames[nbQ], curVal);
				}catch(StatValueIsInvalid e) {
					System.out.println("Une stat soit se trouver entre 0 et 100.");
				}

			}while(curVal < 0 || curVal > 100);
			curVal = -1;
		}

		return stats;
		
	}
	
	/**
	 * Create the player with the collected datas
	 * @param type int representing the class of the new player
	 * @param name name of the new player
	 * @param stats stats of the new player
	 * @return the new player
	 */
	private static Character selectType(int type, String name, Stats stats) {
		switch(type){    
        case 1:    
            return new Warrior(name, stats);
        case 2:    
            return new Rover(name, stats);
        case 3:
        	return new Mage(name, stats);
        default:
        	return null;
		}
	}
	
	/**
	 * Starts a fight and return the result of the battle (who lose)
	 */
	private static String startFight(Character player1, Character player2) {
		
		while(player1.getStat(StatNames.HP) != 0 && player2.getStat(StatNames.HP) != 0) {
			playerAttacks(player1, chooseAction(player1), player2);
			if(player2.getStat(StatNames.HP) == 0) break;
			playerAttacks(player2, chooseAction(player2), player1);
		}
		
		return getResult(player1, player2);
	}
	
	/**
	 * Allows the player to choose which type of attack to do
	 * @param player
	 * @return the type of attack (int) 
	 */
	private static int chooseAction(Character player) {
		int curentAttack;
		do {
			System.out.println(player.getName() + " " + "(" + player.getStat(StatNames.HP) + ")"
					+ " Veuillez choisir votre action "
					+ "(1 : Attaque Basique, 2 : Attaque Spéciale)");
			curentAttack = scanner.nextInt();
		}while(curentAttack != 1 && curentAttack != 2);	
		
		return curentAttack;
	}
	
	/**
	 * Make the player to attack its opponent with the choosen attack
	 * @param player
	 * @param attackNumber
	 * @param opponent
	 */
	private static void playerAttacks(Character player, int attackNumber, Character opponent) {
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
	private static String getResult(Character player1, Character player2) {
		if(player1.getStat(StatNames.HP) == 0) {
			return player1.getName() + " est mort.";
		}else {
			return player2.getName() + " est mort.";
		}
	}
	

}
