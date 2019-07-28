package stat;

import exceptions.StatValueIsInvalid;

/**
 * 
 * @author Numawn
 * 
 * This class represent a statistic of a character (att, def, int, ...).
 * A stat has a name and a value.
 * This class has for responsibility
 * to create a stat and get its attributes.
 *
 */

public class Stat {
	
	private final String name;
	private int value;
	
	public Stat(String name , int value) {
		this.name = name;
		this.value = value;
		
		this.verifyValue();
		
	}
	
	private void verifyValue() {
		if(value < 0 || value > 100) {
			throw new StatValueIsInvalid("Une stat doit avoir entre 0 et 100 points !");
		}
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getName() {
		return this.name;
	}
	
}
