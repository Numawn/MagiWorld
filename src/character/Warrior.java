package character;

import stat.StatNames;
import stat.Stats;

public class Warrior extends Player {

	public Warrior(String name, Stats statistics) {
		super(name, statistics);
	}
	

	/**
	 * Gets the description of the character
	 */
	@Override
	public void getDescription() {
		System.out.print("Woarg je suis le guerrier ");
		super.getDescription();
	}

	/**
	 * The player attacks its opponent (decrease their HP)
	 */
	@Override
	public void basicAttack(Character target) {
		int strenght = this.getStat(StatNames.STR);
		
		System.out.print(this.getName() 
				+ " utilise Coup d'épée et inflige " + strenght + " dommages.\n"
				+ target.getName()  + " perd " + strenght + " points de vie.\n");
		
		target.update(StatNames.HP, - strenght);
	}

	/**
	 * The player uses a special attack (Rage)
	 * He half its STR of HP and the opponent loses twice the STR of HP
	 */
	@Override
	public void specialAttack(Character target) {
		int strenght = this.getStat(StatNames.STR);
		
		System.out.print(super.getName() + " utilise Coup de Rage et inflige "
				+ strenght * 2 + " dommages.\n"
				+ target.getName() + " perd " + strenght * 2 + " points de vie.\n");
		
		target.update(StatNames.HP, - strenght * 2);
		
		System.out.print(this.getName() + " perd " + strenght / 2 + " points de vie.\n");
		this.update(StatNames.HP, - strenght / 2);
		
		
		
	}
	
}
