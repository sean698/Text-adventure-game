package logic;

/**
 * This enum makes it easier to set up code to let the user choose what they want
 * to do in the first story choice.
 */
public enum Storys {
	GIVEUP,
	FIGHT;
	
	/**
	 * A method for returning the set of constants as strings in an array.
	 * 
	 * @return
	 *   An array of strings giving the names of the constants 
	 *   inside this Enum, so that this Enum can be used easier.
	 */
	public static String[] getValues() {
		int n = Storys.values().length;
		String[] vals = new String[n];
		int i = 0;
		for (Storys type : Storys.values()) {
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
		return Storys.values().length;
	}
}