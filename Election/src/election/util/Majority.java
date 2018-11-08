package election.util;

public class Majority {
	
	private PartyClass party;
	private int votes;

	/**
	 * Simple wrapper class to represent a majority of votes in a constituency
	 * for one party
	 * @param p The PartyClass object representing the party that
	 * has the majority
	 * @param votes The size of the majority in number of votes
	 */
	public Majority(PartyClass p, int votes) {
		party = p;
		this.votes = votes;
	}
	
	/**
	 * @return A PartyClass representing the party that has this majority
	 */
	public PartyClass getParty() {return party;}
	
	/**
	 * @return The integer number of votes the party won the seat by
	 */
	public int getVotes() {return votes;}
}
