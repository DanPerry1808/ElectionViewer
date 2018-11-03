package election.modules;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import election.gui.Element;
import election.util.Result;

public class ResultsList extends Element {
	
	private ArrayList<Result> results;
	
	private FontMetrics fm;
	private int fHeightNum;
	private int fHeightText;
	
	private Font textFont;
	private Font numFont;
	
	private final int PADDING = 10;
	private final int NAME_WIDTH = 200;
	private final int VOTES_WIDTH = 100;
	private final int ROW_HEIGHT = 40;
	private final int GAP = 40;

	public ResultsList(int x, int y, int width, int height, ArrayList<Result> res) {
		super(x, y, width, height);
		results = res;
		numFont = new Font("Helvetica", Font.BOLD, 20);
		textFont = new Font("Helvetica", Font.PLAIN, 20);
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		if (fm == null) {
			fm = g.getFontMetrics(numFont);
			fHeightNum = fm.getHeight();
			fm = g.getFontMetrics(textFont);
			fHeightText = fm.getHeight();
		}
		for (int i = 0; i < results.size(); i++) {
			// Set to party colour
			g.setColor(results.get(i).getParty().getColour());
			// Draw name block
			g.fillRect(x + PADDING, y + ((i + 1) * PADDING) + (i * ROW_HEIGHT), NAME_WIDTH, ROW_HEIGHT);
			// Draw votes block
			g.fillRect(x + PADDING + NAME_WIDTH + GAP, y + ((i + 1) * PADDING) + (i * ROW_HEIGHT), VOTES_WIDTH, ROW_HEIGHT);
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2));
			// Draw names border
			g.drawRect(x + PADDING, y + ((i + 1) * PADDING) + (i * ROW_HEIGHT), NAME_WIDTH, ROW_HEIGHT);
			// Draw votes border
			g.drawRect(x + PADDING + NAME_WIDTH + GAP, y + ((i + 1) * PADDING) + (i * ROW_HEIGHT), VOTES_WIDTH, ROW_HEIGHT);
			
			// Draw party names
			Font temp = g.getFont();
			g.setFont(textFont);
			g.drawString(results.get(i).getParty().getName(), x + PADDING + 5, y + ((i + 1) * PADDING + (i * ROW_HEIGHT) +fHeightText + 5));
			// Draw votes
			g.setFont(numFont);
			g.drawString(Integer.toString(results.get(i).getVotes()), x + PADDING + NAME_WIDTH + GAP + 5, y + ((i + 1) * PADDING) + (i * ROW_HEIGHT) + fHeightNum + 5);
			g.setFont(temp);
		}
	}

}
