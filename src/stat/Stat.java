package stat;

/**
 * 
 * @author Numawn
 * 
 * This class represent a statistic of a character (att, def, int, ...).
 * A stat has a name and a value.
 * This class has for responsibility
 * to get and set the value of the stat.
 *
 */

public class Stat {
	
	private String name;
	private int value;
	
	public Stat(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public int getValue() {
		return 0;
	}
	
	public void setValue(int newValue) {
		
	}
	
}
