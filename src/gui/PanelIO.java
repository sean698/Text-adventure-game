package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension; // for controlling sizes of components
import java.awt.Insets; // for padding inside components
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import logic.GameThread;

/**
 * This class manages the input GUI that the user interacts with, and the output GUI
 * that the user can read while they play the game. It can manage scrolling if there
 * is a lot of output lines, and can be cleared for successive output. There is a
 * submit button that the user must press to allow the game to use the data they enter.
 * 
 * @author Dr Russell Campbell
 */
public class PanelIO {
	private final static int OUTPUT_FONT_SIZE = 12; // size of text characters for JTextArea output
	private final static int WIDTH = 40; // of JTextArea output; the units for this are really off
	private final static int HEIGHT = 30; // of JTextArea output; the units for this are really off
	// NOTE: the *actual* number of characters the JTextArea output seems to display is not the same
	// as the maximum number of characters restricted in the Billboard design for throwing an exception.
	private static JPanel wrapper = new JPanel();
	private static JTextArea output = new JTextArea(HEIGHT, WIDTH);
	private static Font mono = new Font(Font.MONOSPACED, Font.PLAIN, OUTPUT_FONT_SIZE);
	private static JScrollPane scroller = new JScrollPane(output);
	private static JPanel inputWrapper = new JPanel();
	private static JPanel inputLine = new JPanel();
	private static JLabel inputLabel = new JLabel("Enter choice:");
	private static JTextField userInput = new JTextField(WIDTH - 10);
	private static JButton button = new JButton("submit");
	private static GameThread gameThread;
	
	/**
	 * Creates an instance of the input/output GUI for use in the application window.
	 */
	PanelIO() {
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.PAGE_AXIS));
		inputLine.setLayout(new BoxLayout(inputLine, BoxLayout.LINE_AXIS));
		
		// Top part that displays output inside the scroller.
		output.setMargin(new Insets(5, 5, 5, 5));
		output.setFont(mono);
		output.setLineWrap(true);
		output.setFocusable(false);
		wrapper.add(scroller);
		
		// Lower part that displays input and button for user.
		inputWrapper.add(inputLine);
		userInput.setBorder(new EmptyBorder(5, 5, 5, 5));
		inputLine.add(inputLabel);
		inputLine.add(userInput);
		inputLine.add(button);
		button.setMargin(new Insets(10, 10, 10, 10));
		setupButtonEvents(); // user interactivity
		
		// Finish off setting up the layout.
		wrapper.add(inputWrapper);
		wrapper.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	/**
	 * For AnimatedGUI to get access to the needed component for arranging it in
	 * the application window.
	 *
	 * @return
	 *   The top-most component of this part of the GUI.
	 */
	public JPanel getIOContainer() {
		return wrapper;
	}
	
	/**
	 * Allows the game to get access to the user input that they typed. This is set up to
	 * be called from the game's InputManager.
	 *
	 * @return
	 *   The string of input that the user typed in the JTextField.
	 */
	public String getInputString() {
		String line = userInput.getText();
		userInput.setText("");
		return line;
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
	
	/**
	 * Makes sure that this PanelIO can control resuming the correct game instance when
	 * it has been paused while waiting for the user to press the submit button.
	 *
	 * @param gameThread
	 *   There is probably only one instance of the game being played, and this should be it.
	 */
	public void setGameThread(GameThread gameThread) {
		this.gameThread = gameThread;
	}
	
	// Manages resuming the game execution when the submit button is pressed.
	private void setupButtonEvents() {
		button.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					gameThread.unpause();
				}
			}
		);
	}
}