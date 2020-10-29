package logic;

/**
 * This enum makes it easier to set up code to let the user choose what to do
 * when they are attacked by montsers in the game. Add more choices if you want.
 */
public enum Actions {
	DEFEND,
	ATTACK,
	CAST,
	BAG;
	
	/**
	 * A method for returning the set of constants as strings in an array.
	 * 
	 * @return
	 *   An array of strings giving the names of the constants 
	 *   inside this Enum, so that this Enum can be used easier.
	 */
	public static String[] getValues() {
		int n = Actions.values().length;
		String[] vals = new String[n];
		int i = 0;
		for (Actions type : Actions.values()) {
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
		return Actions.values().length;
	}
}
