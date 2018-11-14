package election.modules;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import election.gui.Element;
import election.util.Swing;

public class Swingometer extends Element {
	
	// The maximum amount of swing the graph can display, currently 15%
	private final double maxSwing = 15.0;
	
	private Animation a;
	
	private Swing swing;
	private Font bigSwingFont;
	private Font swingFont;
	private String swingString;
	private double roundedSwing;
	
	private FontMetrics fm;
	private int fHeight;
	private int fHeightBig;
	private int width15;
	
	// Angle between 0 and 90 degrees that is added to default 90 degrees of
	// portion of graph for the party with increased vote share
	// One for changing, other should stay constant, needed of animation
	private int bigAngle;
	private int bigAngleConst;
	// Angle between 0 and 90 degrees that represents the portion of the graph
	// of the party with decreased vote share
	private int smallAngle;
	// Radius of the swingometer
	private final int RAD = 200;
	// Diameter of the swingometer
	private final int DIAM = RAD * 2;
	// How far to right of module's starting x coord to draw swingometer graph
	private final int X_OFFSET = 100;
	// How far down of top of module's starting y coords to draw swingometer graph
	private final int Y_OFFSET = 200;

	/**
	 * An object that draws a semicircle showing the change in vote share
	 * between two parties
	 * @param x The x coordinate to draw to screen
	 * @param y The y coordinate to draw to screen
	 * @param s The Swing object to represent
	 */
	public Swingometer(int x, int y, Swing s) {
		super(x, y, 600, 600);
		visible = false;
		swing = s;
		roundedSwing = Math.round(swing.getAmount() * 10.0) / 10.0;
		swingFont = new Font("Helvetica", Font.PLAIN, 20);
		width15 = 0;
		bigSwingFont = new Font("Helvetica", Font.PLAIN, 60);
		swingString = "From " + swing.getFrom().getShortName() + " TO " + swing.getTo().getShortName();
		bigAngleConst = 90 + calcAngle();
		bigAngle = bigAngleConst;
		smallAngle = 180 - bigAngle;
		a = new Animation(2);
	}
	
	/**
	 * Calculates how large the angle of the party of increased vote share 
	 * should be
	 * @return An integer between 0 and 90 representing the angle
	 * (with 90 subtracted) of the party with increased vote share
	 */
	private int calcAngle() {
		double swAmount = swing.getAmount();
		// Stops swingometer from going too far
		if (swAmount > maxSwing) {
			swAmount = maxSwing;
		}
		return (int)Math.round(90.0 * (swing.getAmount() / maxSwing));
	}
	
	public void draw(Graphics2D g) {
		if (visible) {
			
			if (fm == null) {
				fm = g.getFontMetrics(swingFont);
				width15 = fm.stringWidth("15%");
				fHeight = fm.getHeight();
				fm = g.getFontMetrics(bigSwingFont);
				fHeightBig = fm.getHeight();
			}
			
			//Draw top text
			Font temp = g.getFont();
			g.setFont(bigSwingFont);
			g.drawString(swingString, x + 10, y + fHeightBig);
			
			//Draw amount
			g.drawString(Double.toString(roundedSwing) + "%", x + 200, y + (2 * fHeightBig));
			
			//Draw arcs
			g.setFont(swingFont);
			// Drawing arc of party that swing is moving to
			g.setColor(swing.getTo().getColour());
			g.fillArc(x + X_OFFSET, y + Y_OFFSET, DIAM, DIAM, 0, bigAngle);
			// Drawing arc of party that swing is moving away from
			g.setColor(swing.getFrom().getColour());
			g.fillArc(x + X_OFFSET, y + Y_OFFSET, DIAM, DIAM, bigAngle, smallAngle);
			
			// Draws outlines
			g.setColor(Color.BLACK);
			g.draw(new Arc2D.Double(x + X_OFFSET, y + Y_OFFSET, DIAM, DIAM, 0, bigAngle, Arc2D.PIE));
			g.draw(new Arc2D.Double(x + X_OFFSET, y + Y_OFFSET, DIAM, DIAM, bigAngle, smallAngle, Arc2D.PIE));
			
			// Draw center line
			g.setColor(new Color(100, 100, 100));
			g.drawLine(x + X_OFFSET + RAD, y + Y_OFFSET - 20, x + X_OFFSET + RAD, y + 200 + RAD);
			
			//Draw number text
			g.setColor(Color.BLACK);
			g.drawString("0%", x + X_OFFSET - 10 + RAD, y + Y_OFFSET - fHeight);
			g.drawString("15%", x + X_OFFSET - width15, y + Y_OFFSET + RAD);
			g.drawString("15%", x + X_OFFSET + DIAM, y + Y_OFFSET + RAD);
			
			//Draw border
			g.setFont(temp);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
	}
	
	public void update(int mouseX, int mouseY) {
		a.update();
		
		//Calculate animation stuff if active
		if (a.getActive()) {
			bigAngle = (int)Math.round(90 + ((bigAngleConst - 90) * a.getPercDone()));
			smallAngle = 180 - bigAngle;
		} else if (a.getDeactFlag()) {
			// Run on the frame immediately after the animation finishes
			// and only run once
			bigAngle = bigAngleConst;
			smallAngle = 180 - bigAngle;
			a.deact();
		}
	}
	
	/**
	 * Called when component is activated by GSM
	 */
	public void activate() {
		visible = true;
		a.start();
	}
	
	/**
	 * Called when component is deactivated by GSM
	 */
	public void deactivate() {
		visible = false;
		a.reset();
	}

}
