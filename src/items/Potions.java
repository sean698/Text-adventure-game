package items;

/**
 * A superclass for all kinds of potions.
 */
public abstract class Potions implements Bag {
	protected int number;
	
	/**
	 * Construct these potions.
	 * 
	 * @param number
	 *   How many potions dose a player have.
	 */
	public Potions(int number) {
		this.number = number;
	}
	
	/**
	 * Every kind of potion can be used.
	 * 
	 * @return
	 *   An array of strings, describing the status of the player after using a potion.
	 */
	@Override
	public abstract String[] use();
	
	/**
	 * Every kind of potion can be dropped.
	 * 
	 * @return
	 *   An array of strings, describing the results of dropping a potion.
	 */
	@Override
	public abstract String[] drop();
}
