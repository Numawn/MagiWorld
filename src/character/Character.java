package character;

/**
 * 
 * @author Numawn
 * 
 * This interface represents the behaviour of all the 
 * types of characters (warrior, mage, rover).
 *
 */

public interface Character {
	
//	public int getPV();
//	
//	public void setPV(int newValue);
	
	public int getStat(String stat);
	
	public void setStat (String stat, int newValue);
	
	public void getDescription();
	
	public void basicAttack(Character opponent);
	
	public void specialAttack(Character opponent);
	
}
