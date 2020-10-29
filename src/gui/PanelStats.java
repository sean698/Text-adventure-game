package gui;

import java.awt.Dimension; // for controlling sizes of components
import java.awt.Insets; // for padding inside components

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class manages the components that will allow the user to read about the data
 * of the various objects in the game, such as the player, and the monsters, etc.
 * 
 * @author Dr Russell Campbell
 */
public class PanelStats {
	private final static int TILE_SIZE = 12; // you could change this to work with fonts if you like
	private final static int WIDTH = 40;
	private final static int HEIGHT = 23;
	private static JPanel wrapper = new JPanel();
	private static JTextArea output = new JTextArea();
	
	/**
	 * Arranges a JPanel around a JTextArea for displaying statistics.
	 * Does not allow the user to type inside it, as it is only for display.
	 */
	PanelStats() {
		wrapper.add(output); // automatically centre JTextArea with default FlowLayout
		output.setMargin(new Insets(5, 5, 5, 5));
		output.setPreferredSize(
			new Dimension(WIDTH*TILE_SIZE, HEIGHT*TILE_SIZE + 6)
		);
		output.setLineWrap(true);
		output.setFocusable(false); // user should not be able to type in the text output area
	}
	
	/**
	 * For AnimatedGUI to get access to the needed component for arranging it in
	 * the application window.
	 *
	 * @return
	 *   The top-most component of this part of the GUI.
	 */
	public JPanel getStatsContainer() {
		return wrapper;
	}
	
	/**
	 * Adds more strings to the JTextArea <code>output</code> for the game.
	 *
	 * @param message
	 *   The sequence of strings that will be appended to the output for the user to read.
	 */
	public void append(String... message) {
		StringBuilder temp = new StringBuilder();
		for (String line : message) {
			temp.append(line + '\n');
		}
		output.append(temp.toString());
	}
	
	/**
	 * Clears away all the current text displayed in the JTextArea <code>output</code>.
	 */
	public void clear() {
		output.setText("");
	}
}