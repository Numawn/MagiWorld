package character;

import stat.StatNames;
import stat.Stats;

/**
 * 
 * @author Numawn
 * 
 * This abstract class implements Character and represent a player
 *
 */

public abstract class Player implements Character {
	
	private final String name; //(Joueur 1 / Joueur 2)
	private Stats stats;
	
	protected Player(String name, Stats statistics) {
		this.name = name;
		this.stats = statistics;
	}
	
	/**
	 * Gets the name of the player
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * get the value of the wanted stat
	 */
	@Override
	public int getStat(String stat) {
		return this.stats.getStat(stat);
	}

	/**
	 * update the value of the asked stat by adding the affectedVal
	 */
	@Override
	public void update(String stat, int affectedVal) {
		this.stats.update(stat, affectedVal);
		if(this.getStat(StatNames.HP) == 0) {
			System.out.print(this.getName() + " est mort.\n");
		}
		
	}

	/**
	 * Gets the player's description
	 */
	@Override
	public void getDescription() {
		System.out.print(this.name);
		this.stats.display();
	}

	/**
	 * The player uses its basic attack
	 */
	@Override
	public abstract void basicAttack(Character target);

	/**
	 * The player uses its special attack
	 */
	@Override
	public abstract void specialAttack(Character target);
}
