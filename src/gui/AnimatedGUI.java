package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * NOTE: A different Box class was imported from the javax.swing library! Not the same as our Box!
 * NOTE: Event Dispatch Thread (EDT)
 * NOTE: Canadian spelling "colour" vs American spelling "color"
 * NOTE: Canadian spelling "centre" vs American spelling "center"
 * Comments will use Canadian spelling; names of code parts will use American spelling.
 */

/**
 * This class manages the layout and higher-level functionality of 
 * controlling animation components.
 * 
 * @author Dr Russell Campbell
 */
public class AnimatedGUI {
	
	// displayed in the window's top bar
	private final static String TITLE_BAR = "Adventure Game";
	// DO NOT CHANGE!
	private final static int MILLIS_PER_SECOND = 1000;
	// Not much need to set this above 30; slower computers might need to lower fps to 5 or less.
	private final static int FRAMES_PER_SECOND = 10;
	private final static int MILLIS_PER_FRAME = MILLIS_PER_SECOND / FRAMES_PER_SECOND;
	
	// The main window that has the familiar x close button, window resizing buttons, etc.
	private static JFrame frame;
	// Separate the User Interface (UI) into left and right columns
	private static JPanel leftSide, rightSide;
	// An extra JPanel to use default FlowLayout for horizontal centring of its child "display".
	private static JPanel wrapper;
	// Display will contain the 2D array of JTextAreas, each element controlling an individual char.
	private static Panel2D display;
	// UI for game output instructions, prompts, and input involving the user.
	private static PanelIO userIO;
	// UI for game statistics, to help the user read important data about objects during gameplay.
	private static PanelStats stats;
	
	/**
	 * Constructor to set up the GUI window heirarchy of objects, their layout, and dimensions.
	 */
	public AnimatedGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the x button closes the program
		leftSide = new JPanel();
		rightSide = new JPanel();
		wrapper = new JPanel();
		display = new Panel2D();
		userIO = new PanelIO();
		stats = new PanelStats();
		setGUILayout();
		
		// Prepare the layout of JFrame and let the operating system display it.
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Used by the main program to start animations AFTER setup.
	 */
	public void beginAnimation() {
		// Creates a separate thread of execution managed by EDT.
		scheduleFrameDrawing();
		// Creates a separate thread of execution apart from EDT.
		display.schedulePanelUpdates(MILLIS_PER_FRAME); 
	}
	
	private void setGUILayout() {
		frame.setTitle(TITLE_BAR);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.PAGE_AXIS));
		leftSide.setBorder(new EmptyBorder(10, 10, 10, 10));
		// RightSide will be default FlowLayout (centres its only contents).
		leftSide.add(wrapper);
		leftSide.add(stats.getStatsContainer());
		rightSide.add(userIO.getIOContainer());
		
		// This automatically centres display horizontally within JFrame.
		wrapper.add(display.getJPanel());
		
		// vertically centre layout
		frame.add(Box.createVerticalGlue());
		frame.add(leftSide);
		frame.add(rightSide);
	}
	
	/*
	 * Sets up regular redrawings of the frame for animation,
	 * but controlled by the EDT, which helps handle GUI events. 
	 * This is so the user has a better experience with
	 * responsive and fast interactivity.
	 */
	private void scheduleFrameDrawing() {
		// An example of writing a class inline where we use it.
		ActionListener animator = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// Redraws the display according to changes in a connected Panel2D.
				frame.repaint();
			}
		};
		
		// Schedules the periodic execution of actionPerformed().
		new Timer(MILLIS_PER_FRAME, animator).start();
	}
	
	/**
	 * Get the Panel2D <code>display</code>.
	 * 
	 * @return 
	 *   The Panel2D <code>display</code> instance used by this AnimatedGUI.
	 */
	public Panel2D getPanel2D() {
		return display;
	}
	
	/**
	 * Get the PanelIO <code>userIO</code>.
	 * 
	 * @return 
	 *   The PanelIO <code>userIO</code> instance used by this AnimatedGUI.
	 */
	public PanelIO getPanelIO() {
		return userIO;
	}
	
	/**
	 * Get the PanelStats <code>stats</code>.
	 * 
	 * @return 
	 *   The PanelStats <code>stats</code> instance used by this AnimatedGUI.
	 */
	public PanelStats getPanelStats() {
		return stats;
	}
	
	/**
	 * Get the the number of milliseconds between each repaint of the JFrame.
	 * 
	 * @return 
	 *   The number of milliseconds between each JFrame repaint.
	 */
	public int getMillisPerFrame() {
		return MILLIS_PER_FRAME;
	}
}