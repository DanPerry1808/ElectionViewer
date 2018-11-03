package election.util;

import java.awt.Color;

public class PartyClass {
	
	private String name;
	private String shortName;
	private Color colour;

	/**
	 * Represents a party
	 * @param name Official party name
	 * @param shortName 2-4 character name for graphs Eg. CON for conservative, LAB for labour
	 * @param c An RGB colour associated with the party
	 */
	public PartyClass(String name, String shortName, Color c) {
		this.name = name;
		this.shortName = shortName;
		colour = c;
	}
	
	public String getName() {return name;}
	public String getShortName() {return shortName;}
	public Color getColour() {return colour;}
}
