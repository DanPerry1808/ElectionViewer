package election.util;

import java.util.ArrayList;

import election.db.Queries;

public class PartyList {
	
	private static ArrayList<PartyClass> parties;
	
	public static ArrayList<PartyClass> getParties(){
		if (parties == null) {
			parties = Queries.getParties("read_party_names.py");
		}
		return parties;
	}
}
