package logic;

/**
 * This enum makes it easier to set up code to let the user choose what potion to use.
 */
public enum BagChoice {
	STRENGTH,
	WISDOM,
	HEALTH;
	
	/**
	 * A method for returning the set of constants as strings in an array.
	 * 
	 * @return
	 *   An array of strings giving the names of the constants 
	 *   inside this Enum, so that this Enum can be used easier.
	 */
	public static String[] getValues() {
		int n = BagChoice.values().length;
		String[] vals = new String[n];
		int i = 0;
		for (BagChoice type : BagChoice.values()) {
			vals[i] = type.toString();
			i++;
		}
		return vals;
	}
	
	/**
	 * A method to get the number of constants in this Enum.
	 * 
	 * @return
	 *   An integer for the number of constants in this Enum.
	 */
	public static int size() {
		return BagChoice.values().length;
	}
}