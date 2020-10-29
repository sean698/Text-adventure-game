package items;

/**
 * A health potion is a kind of potion to increase the player's 50 hitpoints.
 * Every player has 3 health potions.
 */
public class HealthPotion extends Potions implements Bag {
	int increaseHealth = 50;
	
	public HealthPotion() {
		super(3);
	}
	
	/**
	 * Using a health potion to increase hitpoints.
	 * 
	 * @return
	 *   An array of strings, describing the status of the player after using a health potion.
	 */
	@Override
	public String[] use() {
		number --;
		String[] results = new String[] {};
		results = new String[] {
			"- You just used a health potion: health + 50!",
			"- You wounds are healed!",
			"- You have " + number + " health potions left."
		};
		return results;
	}
	
	/**
	 * Drop a health potion.
	 * 
	 * @return
	 *   An array of strings, describing the results of dropping a health potion.
	 */
	public String[] drop() {
		number --;
		String[] results = new String[] {
			"- You just dropped a health potion.",
			"- You have " + number + " health potions left."
		};
		return results;
	}
	
	/**
	 * Get the increase of a health potion.
	 * 
	 * @return
	 *   An integer 50 that is the increase of a health potion.
	 */
	public int getIncrease() {
		return increaseHealth;
	}
}
