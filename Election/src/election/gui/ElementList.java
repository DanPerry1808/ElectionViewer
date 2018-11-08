package election.gui;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ElementList {
	
	// The list of Elements
	private ArrayList<Element> elements;
	
	/**
	 * ElementList is an ArrayList of Element objects with a few utility
	 * methods that allows for all Elements in the list to have a specific
	 * method call with one call by iterating through a loop
	 */
	public ElementList() {
		
		elements = new ArrayList<Element>();
		
	}
	
	/**
	 * Adds the element to the list, allowing it to have its methods called
	 * by calling the list's  corresponding method
	 * @param e  The Element object to add
	 */
	public void add(Element e) {
		elements.add(e);
	}
	
	// Initialises elements as a new list, removing all items
	public void deleteList() {
		elements = new ArrayList<Element>();
	}
	
	/**
	 * Calls the update method of all Elements in the list
	 * @param x X coordinate of mouse
	 * @param y Y coordinate of mouse
	 */
	public void update(int x, int y) {
		for(Element e: elements) {
			e.update(x, y);
		}
	}
	
	/**
	 * Calls the onHover method of all Elements in the list
	 * @param x X coordinate of mouse
	 * @param y Y coordinate of mouse
	 */
	public void onHover(int x, int y) {
		for(Element e: elements) {
			e.onHover(x, y);
		}
	}
	
	/**
	 * Calls the onClick method of all Elements in the list
	 * @param x X coordinate of mouse
	 * @param y Y coordinate of mouse
	 */
	public void onClick(int x, int y) {
		for(Element e: elements) {
			e.onClick(x, y);
		}
	}
	
	/**
	 * Calls the draw method of all Elements in the list
	 * @param g The Graphics2D object to be used for drawing with
	 */
	public void draw(Graphics2D g) {
		for (Element e : elements) {
			e.draw(g);
		}
	}

}
