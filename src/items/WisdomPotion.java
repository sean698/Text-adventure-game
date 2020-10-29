package items;

/**
 * A wisdom potion is a kind of potion to increase the player's 10 wisdom.
 * Every player has 3 wisdom potions.
 */
public class WisdomPotion extends Potions implements Bag {
	int increaseWisdom = 10;

	public WisdomPotion() {
		super(3);
	}
	
	/**
	 * Using a wisdom potion to increase strength.
	 * 
	 * @return
	 *   An array of strings, describing the status of the player after using a wisdom potion.
	 */
	@Override
	public String[] use() {
		number --;
		String[] results = new String[] {};
		results = new String[] {
			"- You just used a wisdom potion: wisdom + 10!",
			"- You feel you are full of magic right now!",
			"- You have " + number + " wisdom potions left."
		};
		return results;
	}
	
	/**
	 * Drop a wisdom potion.
	 * 
	 * @return
	 *   An array of strings, describing the results of dropping a wisdom potion.
	 */
	public String[] drop() {
		number --;
		String[] results = new String[] {
			"- You just dropped a wisdom potion.",
			"- You have " + number + " wisdom potions left."
		};
		return results;
	}
	
	/**
	 * Get the increase of a wisdom potion.
	 * 
	 * @return
	 *   An integer 10 that is the increase of a wisdom potion.
	 */
	public int getIncrease() {
		return increaseWisdom;
	}
}