package graphics;

import gui.Panel2D;

/**
 * A shape of a circle that can be animated in its <code>Panel2D parentPanel</code>.
 * 
 * @author Dr Russell Campbell
 */
public class Circle extends Shape {
	
	private Point2D centre;
	private int radius;
	private int dx, dy;
	
	/**
	 * Creates an instance of a circle to animate. It is set for default diagonal motion.
	 * 
	 * @param x
	 *   The horizontal centre of this circle.
	 * @param y
	 *   The vertical centre of this circle.
	 * @param radius
	 *   The radius of this circle.
	 * @param pixelsIndex
	 *   The index of the character used to draw the circle.
	 * @param colorsIndex
	 *   The index of the colour used to draw the circle.
	 */
	public Circle(int x, int y, int radius, int pixelsIndex, int colorsIndex) {
		// If we do call it, we must call super first.
		super(pixelsIndex, colorsIndex); 
		centre = new Point2D(x, y);
		this.radius = radius;
		
		dx = dy = 1; // diagonal motion
	}
	
	private void moveCentre(int x, int y) {
		// Clockwise order.
		centre.translate(x, y);
	}
	
	private void update() {
		if (centre.x < 1 || parentPanel.getWidth() < centre.x+2)  dx *= -1;
		if (centre.y < 1 || parentPanel.getHeight() < centre.y+2) dy *= -1;
		// NOTE: if dx1, dy1, dx2, dy2 have magnitude smaller than 1, then the range
		// tests above must be restricted further, or else Box points can get stuck
		moveCentre(dx, dy);
	}
	
	/**
	 * Move, and then draw this circle shape in its <code>parentPanel</code>. This is Bresenham's
	 * Circle-Drawing Algorithm, which you can read a short explanation about at the website
	 * <a href="https://www.geeksforgeeks.org/bresenhams-circle-drawing-algorithm/">GeeksForGeeks</a>.
	 */
	@Override
	public void draw() {
		update();
		int x = 0;
		int y = radius;
		int d = 3 - 2*radius;
		drawSymmetricPositions(x, y);
		while (y >= x) {
			x++;
			if (d > 0) {
				y--;
				d = d + 4*(x - y) + 10;
			} else {
				d = d + 4*x + 6;
			}
			drawSymmetricPositions(x, y);
		}
	}
	
	private void drawSymmetricPositions(int x, int y) {
		// the eight symmetric points on this circle of the point (x, y)
		parentPanel.set(centre.x + x, centre.y + y, pixelsIndex, colorsIndex);
		parentPanel.set(centre.x - x, centre.y + y, pixelsIndex, colorsIndex);
		parentPanel.set(centre.x + x, centre.y - y, pixelsIndex, colorsIndex);
		parentPanel.set(centre.x - x, centre.y - y, pixelsIndex, colorsIndex);
		parentPanel.set(centre.x + y, centre.y + x, pixelsIndex, colorsIndex);
		parentPanel.set(centre.x - y, centre.y + x, pixelsIndex, colorsIndex);
		parentPanel.set(centre.x + y, centre.y - x, pixelsIndex, colorsIndex);
		parentPanel.set(centre.x - y, centre.y - x, pixelsIndex, colorsIndex);
	}
	
}