package tools;

import java.util.InputMismatchException;
import java.util.Scanner;



public class Tools {

	public static final Scanner scanner = new Scanner(System.in);
	
	/**
	 * Asks the player to enter a value 
	 * @param question question that is asked to the player 
	 * @return the choice the player made
	 */
	public static int askValue(String question) {
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
