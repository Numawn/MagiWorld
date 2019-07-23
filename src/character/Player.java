package character;

import stat.Stats;

public abstract class Player implements Character {
	
	private String name; //(Joueur 1 / Joueur 2)
	private Stats stats;
	
	public Player(String name, Stats statistics) {
		
	}
	
	@Override
	public int getStat(String stat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStat(String stat, int newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDescription() {
		System.out.println(this.name);
	}

	@Override
	public abstract void basicAttack(Character opponent);

	@Override
	public abstract void specialAttack(Character opponent);
}
