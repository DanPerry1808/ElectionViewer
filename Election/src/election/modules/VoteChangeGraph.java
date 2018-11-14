package election.modules;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class VoteChangeGraph extends Graph{
	
	private ArrayList<VSCResult> res;
	private double maxVoteChange;
	
	private final int HALF_Y_AXIS = Y_AXIS_LENGTH / 2;
	
	private FontMetrics fm;
	private int fHeight;

	/**
	 * A graph that shows the amount each party's vote share changed between elections
	 * @param x The X coord to display at
	 * @param y The Y coord to display at
	 * @param res An ArrayList of VCSResult objects to store all the vote change details
	 */
	public VoteChangeGraph(int x, int y, ArrayList<VSCResult> res) {
		super(x, y);
		setVisible(false);
		this.res = res;
		maxVoteChange = getMax();
		bars = genBars();
		
	}
	
	/**
	 * Generates an array of GraphBar objects to hold the party and the height of its vote change bar
	 * @return An array of GraphBar objects, of length = to the size of the VCSResults ArrayList
	 */
	private GraphBar[] genBars() {
		bars = new GraphBar[res.size()];
		int val;
		for (int i = 0; i < res.size(); i++) {
			val = (int)Math.round((res.get(i).getPercChange() / maxVoteChange) * (double)HALF_Y_AXIS);
			bars[i] = new GraphBar(res.get(i).getParty().getColour(), val);
		}
		return bars;
	}
	
	/**
	 * Gets the maximum absolute value of vote share, ceil'd to highest 5%
	 * @return A positive double of the max change in vote share in this constituency ceil'd to highest 5%
	 */
	private double getMax() {
		double max = 0;
		for (VSCResult r : res) {
			if (Math.abs(r.getPercChange()) > max) {
				max = Math.abs(r.getPercChange());
			}
		}
		
		return Math.ceil(max / 5.0) * 5.0;
	}
	
	public void draw(Graphics2D g) {
		if (visible) {
			
			// Gets height of current font
			if(fm == null) {
				fm = g.getFontMetrics(f);
				fHeight = fm.getHeight();
			}
			Font temp = g.getFont();
			g.setFont(f);
			
			//Draw y axis
			g.drawLine(x + 50, y + 50, x + 50, y + 50 + Y_AXIS_LENGTH);
			//Draw max + min on y axis
			String vc = Double.toString(maxVoteChange) + "%";
			g.drawString("+" + vc, x + 50 - fm.stringWidth("+" + vc), y + 50);
			g.drawString("-" + vc, x + 50 - fm.stringWidth("-" + vc), y + 50 + Y_AXIS_LENGTH);
			
			//Draws bars
			for (int i = 0; i < bars.length; i++) {
				g.setColor(bars[i].getColour());
				int barX = x + 50 + (i * BAR_WIDTH) + ((i + 1) * BAR_PADDING);
				int barY;
				int barHeight = Math.abs(bars[i].getHeight());
				
				// If animation active, calculate bar height based on progress
				if (a.getActive()) {
					barHeight = (int)Math.round(barHeight * a.getPercDone());
				}
				
				// Calculates y position of the bar based on whether
				// the vote change is postive or negative
				if(bars[i].getHeight() >= 0) {
					barY = y + 50 + HALF_Y_AXIS - barHeight;
				} else {
					barY = y + 50 + HALF_Y_AXIS;
				}
				
				// Java does not allow shapes of negative height, so different
				// drawing methods are needed for positive and negative
				// changes in vote share
				if (bars[i].getHeight() >= 0) {
					// Positive vote share change bar
					g.fillRect(barX, barY, BAR_WIDTH, barHeight);
					g.setColor(Color.BLACK);
					if (!a.getActive()) {
						g.drawString("+" + Double.toString(Math.round(res.get(i).getPercChange() * 10.0) / 10.0) + "%", barX + 20, barY - fHeight);
					}
				}else {
					// Negative vote share change bar
					g.fillRect(barX, barY, BAR_WIDTH, barHeight);
					g.setColor(Color.BLACK);
					if (!a.getActive()) {
						g.drawString(Double.toString(Math.round(res.get(i).getPercChange() * 10.0) / 10.0) + "%", barX + 20, barY - bars[i].getHeight() + fHeight);
					}
				}
			}
			
			
			g.setColor(Color.BLACK);
			//Draw middle line
			g.drawLine(x + 50, y + 50 + HALF_Y_AXIS, x + 50 + X_AXIS_LENGTH, y + 50 + HALF_Y_AXIS);
			//Draws border
			g.drawRect(x, y, width, height);
			
			g.setFont(temp);
		}
	}
	
	
	public void olddraw(Graphics2D g) {
		if (visible) {
			
			// Gets height of current font
			if(fm == null) {
				fm = g.getFontMetrics(f);
				fHeight = fm.getHeight();
			}
			Font temp = g.getFont();
			g.setFont(f);
			//Draw y axis
			g.drawLine(x + 50, y + 50, x + 50, y + 50 + Y_AXIS_LENGTH);
			//Draw max + min on y axis
			String vc = Double.toString(maxVoteChange) + "%";
			g.drawString("+" + vc, x + 50 - fm.stringWidth("+" + vc), y + 50);
			g.drawString("-" + vc, x + 50 - fm.stringWidth("-" + vc), y + 50 + Y_AXIS_LENGTH);
			//Draws bars
			for (int i = 0; i < bars.length; i++) {
				int barX = x + 50 + (i * BAR_WIDTH) + ((i + 1) * BAR_PADDING);
				int barY;
				g.setColor(bars[i].getColour());
				
				// Java does not allow shapes of negative height, so different
				// drawing methods are needed for positive and negative
				// changes in vote share
				if (bars[i].getHeight() >= 0) {
					// Positive vote share change bar
					barY = y + 50 + HALF_Y_AXIS - bars[i].getHeight();
					g.fillRect(barX, barY, BAR_WIDTH, bars[i].getHeight());
					g.setColor(Color.BLACK);
					g.drawString("+" + Double.toString(Math.round(res.get(i).getPercChange() * 10.0) / 10.0) + "%", barX + 20, barY - fHeight);
				}else {
					// Negative vote share change bar
					barY = y + 50 + HALF_Y_AXIS;
					g.fillRect(barX, barY, BAR_WIDTH, Math.abs(bars[i].getHeight()));
					g.setColor(Color.BLACK);
					g.drawString(Double.toString(Math.round(res.get(i).getPercChange() * 10.0) / 10.0) + "%", barX + 20, barY - bars[i].getHeight() + fHeight);
				}
			}
			
			
			g.setColor(Color.BLACK);
			//Draw middle line
			g.drawLine(x + 50, y + 50 + HALF_Y_AXIS, x + 50 + X_AXIS_LENGTH, y + 50 + HALF_Y_AXIS);
			//Draws border
			g.drawRect(x, y, width, height);
			
			g.setFont(temp);
		}
	}

}
