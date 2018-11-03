package election.modules;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import election.gui.Element;
import election.util.Majority;
import election.util.PartyClass;

public class NameBar extends Element {
	
	private String name;
	private PartyClass winner17;
	private PartyClass winner15;
	private Majority maj;
	private String gainString;
	private Font bigFont;
	private Font medFont;
	private FontMetrics fm;
	private int fHeight;

	/**
	 * This object is drawn at the top of the screen displaying the Constituency name, gain/hold status
	 * and majority. The background is set to the colour of the winning party
	 * @param x X position to draw to screen
	 * @param y Y position to draw to screen
	 * @param width Width of object
	 * @param height Height of object
	 * @param winner17 Winning party in this election
	 * @param winner15 Winning party in the last election
	 * @param maj Majority object for the winning party
	 */
	public NameBar(int x, int y, int width, int height, PartyClass winner17, PartyClass winner15, Majority maj) {
		super(x, y, width, height);
		this.winner17 = winner17;
		this.winner15 = winner15;
		this.maj = maj;
		// Placeholder text to prevent NPE
		this.name = "Set the name Dummy!";
		this.gainString = genGainString();
		bigFont = new Font("Dialog", Font.PLAIN, 32);
		medFont = new Font("Dialog", Font.PLAIN, 20);
	}
	
	/**
	 * Generates a string that is either:
	 * P1 GAIN FROM P2
	 * OR
	 * P1 HOLD
	 * Depending on whether the seat changed hands and where P1 is the current seat holder
	 * and P2 is the old seat holder if the seat changed hands
	 * @return A string as described above
	 */
	private String genGainString() {
		String s;
		if (winner17.getName() == winner15.getName()) {
			s = winner17.getShortName() + " HOLD";
		}else {
			s = winner17.getShortName() + " GAIN FROM " + winner15.getShortName();
		}
		return s;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Draws the bar to the screen at (x, y)
	 */
	public void draw(Graphics2D g) {
		
		if(fm == null) {
			fm = g.getFontMetrics(bigFont);
			fHeight = fm.getHeight();
			fm = g.getFontMetrics(medFont);
		}
		
		//Set colour to winning party colour
		g.setColor(winner17.getColour());
		// Draw background block
		g.fillRect(x, y, width, height);
		//Draw border
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		// Draw party text
		Font temp = g.getFont();
		g.setFont(bigFont);
		g.drawString(name, 10, 5 + fHeight);
		// Draw Gain string and majority
		g.setFont(medFont);
		g.drawString(gainString, 10, 80);
		g.drawString("Majority: " + Integer.toString(maj.getVotes()), 600, 80);
		g.setFont(temp);
	}

}
