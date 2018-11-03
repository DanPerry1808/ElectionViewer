package election.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Button extends Element {
	
	private final int BORDER_WIDTH = 2;
	private Color bgColour = new Color(120, 120, 120);
	private Font f;
	private FontMetrics fm;
	private int fHeight;
	
	private String label;
	
	private boolean pressed;
	
	public Button(int x, int y, int width, int height, String l) {
		super(x, y, width, height);
		label = l;
		f =  new Font("Helvetica", Font.PLAIN, 32);
		pressed = false;
	}
	
	@Override
	public void onHover(int x, int y) {
		if(isInRect(rect, x, y)) {
			bgColour = new Color(180, 180, 180);
		}else {
			bgColour = new Color(120, 120, 120);
		}
	}
	
	@Override
	public void onClick(int x, int y) {
		if (isInRect(rect, x, y)) {
			pressed = true;
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		
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
