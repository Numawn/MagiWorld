package character;

import stat.StatNames;
import stat.Stats;

public class Rover extends Player {

	
	
	public Rover(String name, Stats statistics) {
		super(name, statistics);
	}
	

	@Override
	public void getDescription() {
		System.out.print("Shhhh je suis le vagabond ");
		super.getDescription();
		
	}

	@Override
	public void basicAttack(Character target) {
		int agi = this.getStat(StatNames.AGI);
		
		System.out.print(this.getName() 
				+ " utilise Tir à l'Arc et inflige " + agi + " dommages.\n"
				+ target.getName()  + " perd " + agi + " points de vie.\n");
		
		target.update(StatNames.HP, - agi);
		
	}

	@Override
	public void specialAttack(Character target) {
		int halfLevel = this.getStat(StatNames.LVL) / 2;
		
		System.out.print(this.getName() + " utilise Concentration et gagne "
				+ halfLevel + " en agilité.\n");
		
		target.update(StatNames.AGI, halfLevel);
	}

}
