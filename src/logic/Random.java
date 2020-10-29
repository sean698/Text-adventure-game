package logic;

/**
 * This class helps you generate random integers. It is a version that will
 * generate each possible value with approximately equal probability.
 * 
 * @author Dr Russell Campbell
 */
public class Random {
	// Variable count makes sure generated values are different when
	// the processor executes calls quickly within the same millisecond.
	private static int count = 0;
	
	/**
	 * Generates a pseudorandom integer in the range [0, max).
	 * 
	 * @param max
	 *   The strict upper bound for possible integers that will be generated.
	 * 
	 * @return
	 *   The generated pseudorandom integer.
	 */
	public static int rand(int max) {
		final int PRIME_1 = 2113; // different primes will change the random behaviour
		final int PRIME_2 = 7369; // its generally better to use larger primes
		long time = System.currentTimeMillis() % 1000000;
		long seed = time + count;
		double trig = Math.sin(PRIME_1 * seed + PRIME_2); // value between -1 and 1
		double func = Math.abs(PRIME_1 * trig); // make it positive and larger
		double frac = func - Math.floor(func); // digits past the decimal seem random
		count++;
		return (int) (frac * max); // get an integer in our range [0, max)
	}
}
