package election.util;

import java.awt.Color;

public class PartyList {
	
	/**
	 * List of all parties
	 */
	public static PartyClass[] parties = {
		new PartyClass("Total", "TOT", new Color(255, 255, 255)),
		new PartyClass("Conservative", "CON", new Color(51, 102, 255)),
		new PartyClass("Labour", "LAB", new Color(255, 0, 0)),
		new PartyClass("Liberal Democrats", "LD", new Color(255, 153, 51)),
		new PartyClass("UKIP", "UKIP", new Color(150, 0, 200)),
		new PartyClass("Greens", "GRN", new Color(0, 255, 0)),
		new PartyClass("SNP", "SNP", new Color(255, 255, 0)),
		new PartyClass("Plaid Cymru", "PC", new Color(0, 150, 0)),
		new PartyClass("Other", "OTH", new Color(120, 120, 120))
	};
}
