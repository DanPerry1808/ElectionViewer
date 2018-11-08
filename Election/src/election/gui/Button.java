package election.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Button extends Element {
	
	// width in pixels of the border around the button
	private final int BORDER_WIDTH = 2;
	
	// RGB colour of the button's background
	private Color bgColour = new Color(120, 120, 120);
	
	// Necessary variables to deal with generating height of text
	private Font f;
	private FontMetrics fm;
	private int fHeight;
	
	// Text rendered inside the button
	private String label;
	
	// Private boolean to hold whether a click is detected
	// but not yet been dealt with
	private boolean pressed;
	
	/**
	 * A basic clickable button GUI element
	 * @param x The top left x-coord to draw it to the screen at
	 * @param y The top left y-coord to draw it to the screen at
	 * @param width The width in pixels that the Button takes up
	 * @param height The height in pixels that the Button takes up
	 * @param l The string rendered in the button as its label
	 */
	public Button(int x, int y, int width, int height, String l) {
		super(x, y, width, height);
		label = l;
		f =  new Font("Helvetica", Font.PLAIN, 32);
		pressed = false;
	}
	
	/**
	 * Checks if mouse is inside button, if it is then the background colour
	 * is made lighter
	 * @param x The x coordinate of the mouse
	 * @param y The y coordinate of the mouse
	 */
	@Override
	public void onHover(int x, int y) {
		if(isInRect(rect, x, y)) {
			bgColour = new Color(180, 180, 180);
		}else {
			bgColour = new Color(120, 120, 120);
		}
	}
	
	/**
	 * When called it checks if the mouse is within the button. If it is,
	 * the internal state variable pressed is set to true.
	 * Note: this method should only be called once a mouse click
	 * has been detected
	 */
	@Override
	public void onClick(int x, int y) {
		if (isInRect(rect, x, y)) {
			pressed = true;
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		// Gets the height of the label text
		if (fm == null) {
			fm = g.getFontMetrics(f);
			fHeight = fm.getHeight();
		}
		
		Font temp = g.getFont();
		g.setFont(f);
		
		// Background
		g.setColor(bgColour);
		g.fillRect(x, y, width, height);
		
		//Draw text
		g.setColor(Color.BLACK);
		g.drawString(label, x + 5, y + 5 + fHeight);
		
		// Start of border setup
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(BORDER_WIDTH));
		// Start of border
		g.drawRect(x, y, width, height);
		// End of border
		g.setStroke(s);
		g.setFont(temp);
		
	}
	
	/**
	 * If there has been a press since the last update, return true then set
	 * the internal state variable pressed to false so the object knows the
	 * click has been dealt with
	 * @return Whether there has been a click inside the button since the
	 * method was last called
	 */
	public boolean getPressed() {
		if (pressed) {
			pressed = false;
			return true;
		}else {
			return false;
		}
	}

	public void setPressed(boolean b) {
		pressed = b;
	}
	
	public void setFont(Font f) {
		this.f = f;
	}

}
