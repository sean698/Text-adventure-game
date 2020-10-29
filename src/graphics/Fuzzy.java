package graphics;

import gui.Panel2D;
import logic.Random;

/**
 * A random character repeated in random coordinates around a central point
 * that can be animated in its <code>Panel2D parentPanel</code>. An example of object
 * that is not a Shape, but can still be collected into an ArrayList of Drawables.
 * 
 * @author Dr Russell Campbell
 */
public class Fuzzy implements Drawable {
	
	private static Panel2D parentPanel;
	private Point2D[] points;
	private Point2D centre;
	private int pixelsIndex, colorsIndex;
	private int dx, dy; // for basic animation
	
	/**
	 * Creates an instance of a random character repeated in random coordinates around
	 * a central point. There is a default animation assigned for diagonal motion.
	 *
	 * @param x
	 *   The horizontal position of the centre of this Fuzzy.
	 * @param y
	 *   The vertical position of the centre of this Fuzzy.
	 */
	public Fuzzy(int x, int y) {
		centre = new Point2D(x, y);
		points = new Point2D[Random.rand(10) + 10];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point2D(
				// Who said points have to be positive?
				Random.rand(7) - 3, Random.rand(7) - 3
			);
		}
		pixelsIndex = Random.rand(8) + 1;
		colorsIndex = Random.rand(8);
		
		dx = dy = 1; // diagonal motion
	}
	
	/**
	 * Set the <code>parentPanel</code> to draw inside for later.
	 * 
	 * @param parentPanel
	 *   An instance of a <code>Panel2D</code> to be used for drawing inside later.
	 */
	@Override
	public void setParent(Panel2D parentPanel) {
		this.parentPanel = parentPanel;
	}
	
	private void moveCentre(int x1, int y1) {
		centre.translate(x1, y1);
	}
	
	private void update() {
		if (centre.x < 1 || parentPanel.getWidth() < centre.x+2)  dx *= -1;
		if (centre.y < 1 || parentPanel.getHeight() < centre.y+2) dy *= -1;
		// NOTE: if dx, dy have magnitude smaller than 1, then the range
		// tests above must be restricted further, or else points can get stuck.
		moveCentre(dx, dy);
	}
	
	/**
	 * Move, and then actually draw each point in this Fuzzy, relative to its centre.
	 */
	@Override
	public void draw() {
		update();
		for (Point2D pt : points) {
			parentPanel.set(centre.x + pt.x, centre.y + pt.y, pixelsIndex, colorsIndex);
		}
	}
}
