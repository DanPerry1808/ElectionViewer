package election.modules;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import election.util.Result;

public class VoteShareGraph extends Graph {
	
	private double maxVote;
	private int turnout;
	private ArrayList<Result> results;
	
	private FontMetrics fm;
	private int fHeight;
	private Font f;

	public VoteShareGraph(int x, int y, ArrayList<Result> res) {
		super(x, y);
		this.results = res;
		turnout = this.results.get(this.results.size() - 1).getVotes();
		this.results.remove(this.results.size() - 1);
		maxVote = Math.ceil(res.get(0).toPerc(turnout) / 5.0) * 5.0;
		bars = genBars();
		f = new Font("Helvetica", Font.PLAIN, 14);
	}
	
	private GraphBar[] genBars() {
		GraphBar[] bars = new GraphBar[results.size()];
		int val;
		for (int i = 0; i < results.size(); i++) {
			val = (int)Math.round(((results.get(i).toPerc(turnout)) / maxVote) * (double)Y_AXIS_LENGTH);
			bars[i] = new GraphBar(results.get(i).getParty().getColour(), val);
		}
		return bars;
	}
	
	public void draw(Graphics2D g) {
		
		if(visible) {
			
			// Font height calculation
			if(fm == null) {
				fm = g.getFontMetrics(f);
				fHeight = fm.getHeight();
			}
			
			Font temp = g.getFont();
			g.setFont(f);
			//Draw axis
			g.setColor(Color.BLACK);
			g.drawLine(x + 50, y + Y_AXIS_LENGTH + 50, x + 50 + width, y + Y_AXIS_LENGTH + 50);
			g.drawLine(x + 50, y + 50, x + 50, y + 50 + Y_AXIS_LENGTH);
		
			//Draw bars and text above it
			for (int i = 0; i < bars.length; i++) {
				g.setColor(bars[i].getColour());
				int barHeight = bars[i].getHeight();
				
				// If animation active, calculate bar height based on progress
				if (a.getActive()) {
					barHeight = (int)Math.round(bars[i].getHeight() * a.getPercDone());
				}
				int yPos = y + 50 + Y_AXIS_LENGTH - barHeight;
				g.fillRect(x + 50 + (i * BAR_WIDTH) + ((i + 1) * BAR_PADDING), yPos, BAR_WIDTH, barHeight);
				if (!a.getActive()) {
					g.setColor(Color.BLACK);
					g.drawString(Double.toString(Math.round(results.get(i).toPerc(turnout) * 10.0) / 10.0) + "%", x + 70 + (i * BAR_WIDTH) + ((i + 1) * BAR_PADDING), y + 40 + Y_AXIS_LENGTH - bars[i].getHeight());
				}
			}
			
			// Draw max value
			g.setColor(Color.BLACK);
			g.drawString(Long.toString(Math.round(maxVote)) + "%", x + 15, y + 50 + (int)(fHeight / 2));
		
			//Draw border
			g.drawRect(x, y, width, height);
			g.setFont(temp);
		}	
	}

}
