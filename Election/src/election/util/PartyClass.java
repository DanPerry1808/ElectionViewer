package election.util;

import java.awt.Color;

public class PartyClass {
	
	private String name;
	private String shortName;
	private Color colour;

	/**
	 * Object which represents a party
	 * @param name Official party name
	 * @param shortName 2-4 character name for graphs 
	 * Eg. CON for conservative, LAB for labour
	 * @param c An RGB colour associated with the party
	 */
	public PartyClass(String name, String shortName, Color c) {
		this.name = name;
		this.shortName = shortName;
		colour = c;
	}
	
	/**
	 * @return The party's full name
	 */
	public String getName() {return name;}
	
	/**
	 * @return A 2-4 character short hand representation of the party's name
	 * Eg. Conservative = CON
	 */
	public String getShortName() {return shortName;}
	
	/**
	 * @return A Color object of the party's associated colour
	 */
	public Color getColour() {return colour;}
}
