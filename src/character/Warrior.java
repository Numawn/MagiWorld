package character;

import stat.StatNames;
import stat.Stats;

public class Warrior extends Player {

	public Warrior(String name, Stats statistics) {
		super(name, statistics);
	}
	

	@Override
	public void getDescription() {
		System.out.print("Woarg je suis le guerrier ");
		super.getDescription();
	}

	@Override
	public void basicAttack(Character target) {
		int strenght = this.getStat(StatNames.STR);
		
		System.out.print(this.getName() 
				+ " utilise Coup d'épée et inflige " + strenght + " dommages.\n"
				+ target.getName()  + " perd " + strenght + " points de vie.\n");
		
		target.update(StatNames.HP, - strenght);
	}

	@Override
	public void specialAttack(Character target) {
		int strenght = this.getStat(StatNames.STR);
		
		System.out.print(super.getName() + " utilise Coup de Rage et inflige "
				+ strenght * 2 + " dommages.\n"
				+ target.getName() + " perd " + strenght * 2 + " points de vie.\n");
		
		target.update(StatNames.HP, - strenght * 2);
		this.update(StatNames.HP, - strenght / 2);
		
		System.out.print(this.getName() + " perd " + strenght / 2 + " points de vie.\n");
		
	}
	
}
