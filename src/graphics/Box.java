package graphics;

import gui.Panel2D;

/**
 * A shape of a rectangle that can be animated in its <code>Panel2D parentPanel</code>.
 * 
 * @author Dr Russell Campbell
 */
public class Box extends Shape {
	
	private Point2D topLeft, topRight, bottomRight, bottomLeft;
	// Velocity (or speed): the difference in x and y coordinates used in each update call
	private int dx1, dy1, dx2, dy2; // designed to modify topLeft and bottomRight points
	
	/**
	 * Create an instance of a rectangle shape and set up a formula to 
	 * conrol its movement for animation.
	 * 
	 * @param x1 
	 *   Horizontal position of the top-left corner.
	 * @param y1 
	 *   Vertical position of the top-left corner.
	 * @param x2 
	 *   Horizontal position of the bottom-right corner.
	 * @param y2 
	 *   Vertical position of the bottom-right corner.
	 * @param pixelsIndex
	 *   The index of the character used to draw the rectangle.
	 * @param colorsIndex
	 *   The index of the colour used to draw the rectangle.
	 */
	public Box(int x1, int y1, int x2, int y2, int pixelsIndex, int colorsIndex) {
		super(pixelsIndex, colorsIndex);
		topLeft = new Point2D(x1, y1);
		topRight = new Point2D(x2, y1);
		bottomRight = new Point2D(x2, y2);
		bottomLeft = new Point2D(x1, y2);
		
		dx1 = dy1 = dx2 = dy2 = 1; // diagonal motion
	}
	
	private void moveCorners(int x1, int y1, int x2, int y2) {
		// Clockwise order.
		topLeft.translate(x1, y1);
		topRight.translate(x2, y1);
		bottomRight.translate(x2, y2);
		bottomLeft.translate(x1, y2);
	}
	
	private void update() {
		if (topLeft.x < 1 || parentPanel.getWidth() < topLeft.x+2)  dx1 *= -1;
		if (topLeft.y < 1 || parentPanel.getHeight() < topLeft.y+2) dy1 *= -1;
		if (bottomRight.x < 1 || parentPanel.getWidth() < bottomRight.x+2)  dx2 *= -1;
		if (bottomRight.y < 1 || parentPanel.getHeight() < bottomRight.y+2) dy2 *= -1;
		// NOTE: if dx1, dy1, dx2, dy2 have magnitude smaller than 1, then the range
		// tests above must be restricted further, or else Box points can get stuck.
		moveCorners(dx1, dy1, dx2, dy2);
	}
	
	/**
	 * Moves, and then draws this rectangle shape in its <code>parentPanel</code>.
	 */
	@Override
	public void draw() {
		update();
		parentPanel.drawLine(topLeft, topRight, pixelsIndex, colorsIndex);
		parentPanel.drawLine(topRight, bottomRight, pixelsIndex, colorsIndex);
		parentPanel.drawLine(bottomRight, bottomLeft, pixelsIndex, colorsIndex);
		parentPanel.drawLine(bottomLeft, topLeft, pixelsIndex, colorsIndex);
	}
	
}
