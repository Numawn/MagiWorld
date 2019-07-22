package character;

import stat.Stats;

public abstract class Player implements Character {
	
	private Stats stats;
	
	public Player(Stats statistics) {
		
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
	public abstract void getDescription();

	@Override
	public abstract void basicAttack(Character opponent);

	@Override
	public abstract void specialAttack(Character opponent);
}
