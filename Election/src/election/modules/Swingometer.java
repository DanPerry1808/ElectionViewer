package election.modules;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import election.gui.Element;
import election.util.Swing;

public class Swingometer extends Element {
	
	private final double maxSwing = 15.0;
	
	private Swing swing;
	private Font bigSwingFont;
	private Font swingFont;
	private String swingString;
	private double roundedSwing;
	
	private FontMetrics fm;
	private int fHeight;
	private int fHeightBig;
	private int width15;
	
	private int bigAngle;
	private int smallAngle;
	private final int RAD = 200;
	private final int DIAM = RAD * 2;
	private final int X_OFFSET = 100;
	private final int Y_OFFSET = 200;

	public Swingometer(int x, int y, Swing s) {
		super(x, y, 600, 600);
		visible = false;
		swing = s;
		roundedSwing = Math.round(swing.getAmount() * 10.0) / 10.0;
		swingFont = new Font("Helvetica", Font.PLAIN, 20);
		width15 = 0;
		bigSwingFont = new Font("Helvetica", Font.PLAIN, 60);
		swingString = "From " + swing.getFrom().getShortName() + " TO " + swing.getTo().getShortName();
		bigAngle = 90 + calcAngle();
		smallAngle = 180 - bigAngle;
	}
	
	private int calcAngle() {
		double swAmount = swing.getAmount();
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
			g.drawString(Double.toString(roundedSwing) + "%", x + 200, y + 10 + (2 * fHeightBig));
			//Draw arcs
			g.setFont(swingFont);
			g.setColor(swing.getTo().getColour());
			g.fillArc(x + X_OFFSET, y + Y_OFFSET, DIAM, DIAM, 0, bigAngle);
			g.setColor(swing.getFrom().getColour());
			g.fillArc(x + X_OFFSET, y + Y_OFFSET, DIAM, DIAM, bigAngle, smallAngle);
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

}
