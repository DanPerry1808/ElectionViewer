package election.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Dropdown extends Element {
	
	private final int BORDER_WIDTH = 2;
	
	private int optionWidth;
	private int optionHeight;
	private final int TRI_OFFSET = 10;
	
	private String[] options;
	private int currOption;
	private boolean open;
	
	private Font f;
	
	private int buttonWidth;
	private CollRect buttonRect;
	private CollRect listRect;
	
	private FontMetrics fm;
	private int fHeight;
	
	/**
	 * A drop down box that allows selection from multiple options
	 * @param x The x-coord of the top left corner
	 * @param y The y-coord of the top left corner
	 * @param width Width of the box not including the button
	 * @param height Height of the box not including the button
	 * @param buttonWidth The width of the side button
	 * @param options The string[] of possible options to choose from
	 */
	public Dropdown(int x, int y, int width, int height, int buttonWidth, String[] options) {
		super(x, y, width, height);
		f = new Font("Helvetica", Font.PLAIN, 32);
		this.optionWidth = width;
		this.optionHeight = height;
		this.buttonWidth = buttonWidth;
		this.options = options;
		currOption = 0;
		open = false;
		buttonRect = new CollRect(x + optionWidth, y, buttonWidth, optionHeight);
		listRect = new CollRect(x, y + optionHeight, optionWidth, optionHeight * options.length);
		
	}
	
	/**
	 * Opens the menu to expand to show all options
	 */
	private void openMenu() {
		open = true;
		height = optionHeight * (options.length + 1);
		listRect.setActive(true);
	}
	
	/**
	 * Stops showing all options and just the selected option
	 */
	private void closeMenu() {
		open = false;
		height = optionHeight;
		listRect.setActive(false);
	}
	
	/**
	 * When a click is detected, checks whether is is inside the dropdown box
	 * and acts accordingly
	 */
	@Override
	public void onClick(int mouseX, int mouseY) {
		
		// If mouse inside the expand toggle button
		// toggle whether the menu is open or not
		if(isInRect(buttonRect, mouseX, mouseY)) {
			if(open) {
				closeMenu();
			}else {
				openMenu();
			}			
		}
		
		// If the menu is open and the mouse is in the list, change the
		// currently selected option and close the menu
		if (open) {
			if (isInRect(listRect, mouseX, mouseY)) {
				currOption = getSelectedOption(mouseX, mouseY);
				closeMenu();
			}
		}
	}
	
	/**
	 * Given mouse co-ords, gets the option the user selected from the drop down box
	 * @param x
	 * @param y
	 * @return
	 */
	private int getSelectedOption(int xCoord, int yCoord) {
		yCoord -= (y + optionHeight);
		int index = (int)Math.floor(yCoord / optionHeight);
		return index;
		
	}
	
	/**
	 * @return The String inside the main box, the currently selected option
	 */
	public String getCurrentOptionString() {
		return options[currOption];
	}
	
	/**
	 * @return The index in the array of options that the currently selected
	 * option is at
	 */
	public int getCurrentOptionIndex() {
		return currOption;
	}
	
	public void draw(Graphics2D g) {
		
		if (fm == null) {
			fm = g.getFontMetrics(f);
			fHeight = fm.getHeight();
		}
		
		Font temp = g.getFont();
		g.setFont(f);
		// Draw background
		g.setColor(new Color(120, 120, 120));
		g.fillRect(x, y, width, height);
		
		//Draw border
		g.setColor(Color.BLACK);
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(BORDER_WIDTH));
		g.drawRect(x, y, width, height);
		
		// Drawing extra stuff if open
		if (open) {
			for (int i = 1; i < (options.length + 1); i++) {
				g.drawLine(x, y + (i * optionHeight), x + width, y + (i * optionHeight));
				g.drawString(options[i-1], x + 5, y + (i * optionHeight) + fHeight + 5);
			}
		}
		
		//Drawing button
		g.setColor(new Color(120, 120, 120));
		g.fillRect(x + optionWidth, y, buttonWidth, optionHeight);
		g.setColor(Color.BLACK);
		g.drawRect(x + optionWidth, y, buttonWidth, optionHeight);
		g.setColor(new Color(200, 200, 200));
		g.fillPolygon(new int[] {x + optionWidth + TRI_OFFSET, x + optionWidth + (int)Math.round(buttonWidth / 2), x + optionWidth + buttonWidth - TRI_OFFSET}, new int[] {y + TRI_OFFSET, y + optionHeight - TRI_OFFSET, y + TRI_OFFSET}, 3);;
		g.setColor(Color.BLACK);
		
		g.setStroke(s);
		
		//Draw text for chosen option
		g.drawString(options[currOption], x + 5, y + 5 + fHeight);
		g.setFont(temp);
	}

}
