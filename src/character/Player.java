package character;

import stat.StatNames;
import stat.Stats;

public abstract class Player implements Character {
	
	private final String name; //(Joueur 1 / Joueur 2)
	private Stats stats;
	
	protected Player(String name, Stats statistics) {
		this.name = name;
		this.stats = statistics;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public int getStat(String stat) {
		return this.stats.getStat(stat);
	}

	@Override
	public void update(String stat, int affectedVal) {
		this.stats.update(stat, affectedVal);
		if(this.getStat(StatNames.HP) == 0) {
			System.out.print(this.getName() + " est mort.\n");
		}
		
	}

	@Override
	public void getDescription() {
		System.out.print(this.name);
		this.stats.display();
	}

	@Override
	public abstract void basicAttack(Character target);

	@Override
	public abstract void specialAttack(Character target);
}
