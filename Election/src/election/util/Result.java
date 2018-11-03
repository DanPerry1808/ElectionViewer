package election.util;

import java.util.ArrayList;

public class Result {
	
	private PartyClass party;
	private int votes;
	
	/**
	 * Represents one constituency result
	 * @param p The Party that got this number of votes
	 * @param votes The number of votes that party got
	 */
	public Result(PartyClass p, int votes) {
		party = p;
		this.votes = votes;
	}
	
	/**
	 * Given the turnout (total number of votes) calculates the percentage of the vote that party got
	 * @param turnout The total number of votes cast
	 * @return The percentage of votes that party got, 50% is represented as 50.0, not 0.5
	 */
	public double toPerc(int turnout) {
		return ((double)votes / (double)turnout) * 100.0;
	}
	
	public PartyClass getParty() {return party;}
	public int getVotes() {return votes;}
	
	/**
	 * Finds a result in a given list by matching party names and returns its index
	 * @param list List to search through
	 * @param partyName Name of party looking for
	 * @return The index of the ArrayList the Result is in
	 */
	public static int find(ArrayList<Result> list, String partyName) {
		boolean found = false;
		int index = -1;
		int i = 0;
		while(!found && i < list.size()) {
			if (list.get(i).getParty().getName() == partyName) {
				found = true;
				index = i;
			}
			i++;
		}
		return index;
	}

}
