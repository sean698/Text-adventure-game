package format;

import java.util.Arrays;

import graphics.Point2D;
import logic.Random;


/**
 * We could have many different instances of 2D char arrays that could
 * display different graphics we design. This class encapsulates (hides) 
 * the confusion of working with the 2D char array directly elsewhere 
 * in our program. This is designed for monochromatic output, so no use
 * of colours in this format package anywhere.
 * 
 * @author Dr Russell Campbell
 */
class Chars2D {
	private final static char DEFAULT_CHAR = '`';
	private static StringBuilder output = new StringBuilder();
	private int width;
	private int height;
	private char[][] pixels;
	
	/**
	 * Creates an instance that manages a 2D char array for text formatting.
	 * You may want to have more than one of these to do some of your own
	 * graphics as part of your game (for example, maps, etc).
	 * This is not meant for animation, but controlling arrangements of
	 * text before printing to the userIO output pixels.
	 * 
	 * @param width
	 *   The number of chars in the horizontal axis of the 2D char array.
	 * 
	 * @param height
	 *   The number of chars in the vertical axis of the 2D char array.
	 */
	public Chars2D(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new char[height][width];
		clear();
	}
	
	/**
	 * This will fill the 2D char array with backtick <code>`</code>
	 * characters (usually under the tilde <code>~</code> in the 
	 * top-left corner of a keyboard).
	 */
	public void clear() {
		// Every character in the 2D array is set to a default char.
		for (int i = 0; i < pixels.length; i++) {
			Arrays.fill(pixels[i], DEFAULT_CHAR);
		}
	}

	/**
	 * This method swaps the order of the <code>x</code> and 
	 * <code>y</code> values for us (so we don't have to swap them
	 * everywhere else in our code) when we want access to the
	 * characters stored in the <code>pixels</code> 2D char array.
	 * Since pixels stores information temporarily, we do not need to
	 * vertically flip anything.
	 * 
	 * @param x 
	 *   The horizontal position inside the 2D char array.
	 * @param y 
	 *   The vertical position inside the 2D char array.
	 * @return
	 *   The character stored at position <code>(x, y)</code> in
	 *   the <code>pixels</code> 2D char array.
	 */
	public char get(int x, int y) {
		return pixels[y][x];
	}
	
	/**
	 * Similar to the <code>get</code> method, but assign a char
	 * value to an <code>x</code> and <code>y</code> position in 
	 * the <code>pixels</code> 2D char array.
	 * 
	 * @param x 
	 *   The horizontal position inside the 2D char array.
	 * @param y 
	 *   The vertical position inside the 2D char array.
	 * @param c 
	 *   The character to be stored in the <code>pixels</code> 2D char array.
	 */
	public void set(int x, int y, char c) {
		// Check if the point is outside the screen boundaries.
		if (0 <= x && x < width && 0 <= y && y < height)
			pixels[y][x] = c;
	}
	
	/**
	 * Prepare to print the <code>pixels</code> 2D char array to output.
	 * Note that we are not actually printing yet. This method helps to
	 * vertically flip the 2D array as we format output.
	 * 
	 * @param width
	 *   The number of chars in the horizontal axis to print.
	 * 
	 * @param height
	 *   The number of chars in the vertical axis to print.
	 * 
	 * @return
	 *   The complete output string for displaying this char to output.
	 */
	public String getFormattedString(int width, int height) {
		output.setLength(0);
		for (int y = height - 1; y >= 0; y--) {
			output.append(Arrays.copyOf(pixels[y], width)); // append an entire row of the 2D array
			output.append('\n'); // finish the line for this row
		}
		return output.toString();
	}
	
	/**
	 * Avoids using floating-point arithmetic for a faster drawing of a line.
	 * 
	 * @param first 
	 *   The start position of the straight line.
	 * @param second 
	 *   The end position of the straight line.
	 * @param stroke 
	 *   The character used to draw the line.
	 */
	public void drawLine(Point2D first, Point2D second, char stroke) {

		// Bresenham's Line Algorithm
		int x1 = first.x;
		int y1 = first.y;
		int x2 = second.x;
		int y2 = second.y;
		byte stepx, stepy;

		int dx = x2 - x1;
		int dy = y2 - y1;
		
		if (dy < 0) { dy = -dy; stepy = -1; } else { stepy = 1; }
		if (dx < 0) { dx = -dx; stepx = -1; } else { stepx = 1; }
		dy <<= 1; // dy is now 2*dy
		dx <<= 1; // dx is now 2*dx
		set(x1, y1, stroke);

		// The algorithm is simplified by ensuring slope m is always -1 < m < 1.
		if (dx > dy) {
			int error = dy - (dx >> 1);
			while (x1 != x2) {
				x1 += stepx;
				error += dy;
				if (error >= 0) {
					y1 += stepy;
					error -= dx;
				}
				set(x1, y1, stroke);
			}
		} else { // but this means we may have to swap roles of dy and dx
			int error = dx - (dy >> 1);
			while (y1 != y2) {
				y1 += stepy;
				error += dx;
				if (error >= 0) {
					x1 += stepx;
					error -= dy;
				}
				set(x1, y1, stroke);
			}
		}
	} // End of drawLine method.
}
