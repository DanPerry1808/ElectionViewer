package election.gui;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ElementList {
	
	private ArrayList<Element> elements;
	
	public ElementList() {
		
		elements = new ArrayList<Element>();
		
	}
	
	public void add(Element e) {
		elements.add(e);
	}
	
	public void deleteList() {
		elements = new ArrayList<Element>();
	}
	
	public void update(int x, int y) {
		for(Element e: elements) {
			e.update(x, y);
		}
	}
	
	public void onHover(int x, int y) {
		for(Element e: elements) {
			e.onHover(x, y);
		}
	}
	
	public void onClick(int x, int y) {
		for(Element e: elements) {
			e.onClick(x, y);
		}
	}
	
	public void draw(Graphics2D g) {
		for (Element e : elements) {
			e.draw(g);
		}
	}

}
