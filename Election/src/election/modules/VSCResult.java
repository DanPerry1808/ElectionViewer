package election.modules;

import election.util.PartyClass;

public class VSCResult {
	
	private PartyClass party;
	private double perc15;
	private double perc17;
	private double percChange;
	
	public VSCResult(PartyClass party, double perc15, double perc17) {
		this.party = party;
		this.perc15 = perc15;
		this.perc17 = perc17;
		this.percChange = perc17 - perc15;
	}
	
	public PartyClass getParty() {return party;}
	public double getPerc15() {return perc15;}
	public double getPerc17() {return perc17;}
	public double getPercChange() {return percChange;}
}
