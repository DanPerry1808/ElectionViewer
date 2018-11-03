package election.util;

public class Majority {
	
	private PartyClass party;
	private int votes;

	public Majority(PartyClass p, int votes) {
		party = p;
		this.votes = votes;
	}
	
	public PartyClass getParty() {return party;}
	public int getVotes() {return votes;}
}
