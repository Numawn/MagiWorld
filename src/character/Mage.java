package character;

import stat.StatNames;
import stat.Stats;

public class Mage extends Player {
	
	
	public Mage(String name, Stats statistics) {
		super(name, statistics);
	}
	

	@Override
	public void getDescription() {
		System.out.print("Abracadabra je suis le mage ");
		super.getDescription();
	}

	@Override
	public void basicAttack(Character target) {
		int intel = this.getStat(StatNames.INT);
		
		System.out.print(this.getName() 
				+ " utilise Boule de Feu et inflige " + intel + " dommages.\n"
				+ target.getName()  + " perd " + intel + " points de vie.\n");
		
		target.update(StatNames.HP, - intel);
		
	}

	@Override
	public void specialAttack(Character target) {
		int intel = this.getStat(StatNames.INT);
		
		System.out.print(this.getName() + " utilise Soin et gagne " 
				+ intel * 2 + " en vitalité.\n");
		
		this.update(StatNames.HP, intel * 2);
	}

}
