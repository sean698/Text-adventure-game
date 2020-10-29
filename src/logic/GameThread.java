package logic;

/**
 * This class controls the execution of the main game code on
 * a separate thread from the GUI. We need this if there is a component
 * of the GUI that is frequently changed, like with animation. The animation
 * should never have to wait for other parts of the program to finish executing.
 * 
 * @author Dr Russell Campbell
 */
public class GameThread extends Thread {
	
	/**
	 * Executes the code we would like to run on this Thread.
	 */
	public void run() {
		Game.gameLoop(); // execute the game code
	}
	
	/**
	 * Allows the GUI to pause this Thread execution, so it can wait
	 * for the user to enter some input before continuing.
	 */
	public void pause() {
		// Synchronized means that if there were multiple of these threads all executing,
		// only ONE instance would be able to execute the code in the following block at any
		// given moment. Not something you really need to worry about understanding right now.
		synchronized(this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// We do not really NEED synchronization, but the compiler forces us when we
		// want to use the wait() method.
	}
	
	/**
	 * Resumes execution of this Thread after being paused. There were issues with naming this
	 * thread "resume," because it is already defined in the Thread class, and we do not want
	 * to override that method.
	 */
	public void unpause() {
		synchronized(this) {
			this.notify();
		}
	}
}