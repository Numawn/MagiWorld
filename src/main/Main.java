package main;

import character.Warrior;
import exceptions.LevelValueIsInvalid;
import exceptions.StatValueIsInvalid;
import exceptions.StatisticPointsAreInvalid;
import fight.Fight;
import stat.Stat;
import stat.StatNames;
import stat.Stats;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
	
	private static final String[] questions = {
			"Niveau du personnage ?",
			"Force du personnage ?",
			"Agilité du personnage ?",
			"Intelligence du personnage ?"};
	
	private static final String[] statNames = {StatNames.LVL, StatNames.STR, StatNames.AGI, StatNames.INT};
	private static final Stat[] stats = {null, null, null, null};
	private static String classesChoice;
	
	
	public static void main(String[] args) {
		classes.add("Guerrier");
		classes.add("Rôdeur");
		classes.add("Mage");
		
		classesChoice = classesToString();
		
		Character player1 = createPlayer(1);
		player1.getDescription();
		Character player2 = createPlayer(2);
		player2.getDescription();
		
		Fight fight = new Fight(scanner);
		fight.start(player1, player2);
		
		scanner.close();
		
	}
	
	/**
	 * Creates a string that asks which class you want to choose
	 * @return the question created
	 */
	private static String classesToString() {
		String classesChoice = "";
		
		classesChoice += "Veuillez choisir la classe de votre personnage (";

		for(int j = 1; j <= classes.size(); j++) {
			if(j == classes.size())
				classesChoice += j + " : " +  classes.get(j-1) + ")";
			else
				classesChoice += j + " : " +  classes.get(j-1) + ", ";
		}
		return classesChoice;
	}
	
	/**
	 * Allows to create a player
	 * @param playerNumber 
	 * @return the new player
	 */
	private static Character createPlayer(int playerNumber) {
		int type = chooseType(playerNumber);
		boolean errCatched = true;
		Stats stats = null;
		do {
			try {
				stats = new Stats(collectStats());
				errCatched = false;
			}catch(LevelValueIsInvalid e) {
				System.err.print("Le niveau doit être situé entre 1 et 100.\n");
			}catch(StatisticPointsAreInvalid e) {
				System.err.print("La somme des statistiques doit être égale au niveau.\n");
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
			type = askValue(classesChoice);
		}while(type < 1 || type > classes.size());
		
		return type;
		
	}
	
	
	
	/**
	 * Creates the stats
	 * @return return the stats in a tab
	 */
	private static Stat[] collectStats() {
		int curVal = -1;
		
		for(int nbQ = 0; nbQ < questions.length; nbQ++) {
			do {
				curVal = askValue(questions[nbQ]);
				try { 
					stats[nbQ] = new Stat(statNames[nbQ], curVal);
				}catch(StatValueIsInvalid e) {
					System.err.print("Une stat soit se trouver entre 0 et 100.\n");
				}

			}while(curVal < 0 || curVal > 100);
			
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
