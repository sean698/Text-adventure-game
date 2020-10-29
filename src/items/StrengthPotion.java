package items;

/**
 * A strength potion is a kind of potion to increase the player's 10 strength.
 * Every player has 3 strength potions.
 */
public class StrengthPotion extends Potions implements Bag {
	private int increaseStrength = 10;
	
	public StrengthPotion() {
		super(3);
	}
	
	/**
	 * Using a strength potion to increase strength.
	 * 
	 * @return
	 *   An array of strings, describing the status of the player after using a strength potion.
	 */
	@Override
	public String[] use() {
		number --;
		String[] results = new String[] {};
		results = new String[] {
			"- You just used a strength potion: strength + 10!",
			"- You feel you are full of power right now!",
			"- You have " + number + " strength potions left."
		};
		return results;
	}
	
	/**
	 * Drop a strength potion.
	 * 
	 * @return
	 *   An array of strings, describing the results of dropping a strength potion.
	 */
	public String[] drop() {
		number --;
		String[] results = new String[] {
			"- You just dropped a strength potion.",
			"- You have " + number + " strength potions left."
		};
		return results;
	}
	
	/**
	 * Get the increase of a strength potion.
	 * 
	 * @return
	 *   An integer 10 that is the increase of a strength potion.
	 */
	public int getIncrease() {
		return increaseStrength;
	}
}
