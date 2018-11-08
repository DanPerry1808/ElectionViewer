package election.modules;

import election.util.PartyClass;

public class VSCResult {
	
	private PartyClass party;
	private double perc15;
	private double perc17;
	private double percChange;
	
	/**
	 * Represents a party's change in vote share between last two elections
	 * @param party the Party whose results this is
	 * @param perc15 The number of votes they got in 2015
	 * @param perc17 The number of votes they got in 2017
	 */
	public VSCResult(PartyClass party, double perc15, double perc17) {
		this.party = party;
		this.perc15 = perc15;
		this.perc17 = perc17;
		this.percChange = perc17 - perc15;
	}
	
	public PartyClass getParty() {return party;}
	public double getPerc15() {return perc15;}
	public double getPerc17() {return perc17;}
	
	/**
	 * @return The number of percentage points the party's vote share
	 * change by compared to the last election
	 */
	public double getPercChange() {return percChange;}
}
