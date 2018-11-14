# ElectionViewer
A piece of software for viewing graphs of UK election results. Warning: bodged together during a hackathon

# Download
This software works on both Windows and Linux machines. It might work on MacOS but I don't own a Mac so I couldn't test it. Just download the zip file below and extract it to its own folder. Then run Election.jar from inside the folder it is in

## Prerequisites
You will need installed on your machine:
- Java 8
- Python 3

## File
The zip file containing the program is [here](https://drive.google.com/open?id=16Uex33uvZboJzd-_BFEAPB4yUrLYwkOK).

# Adding new constituencies
1. Open up the database with [DB Browser for SQLite](https://sqlitebrowser.org/)
2. Open the 'Con' table to add the consituency name and electorate. Note the CON_ID value for later
3. Open the 'Results15' table and make as many new records as there were parties standing, fill in the 'Con_ID' field with the value from earlier
Note: other than the parties listed in the 'Party' table, no other parties can be added. Please use the 'Other' entry if you need more parties than those provided
4. For each party standing, take their 'Party_ID' from the 'Party' table and enter it into the 'Party_ID' of the 'Results15' table. Then enter the number of votes that party got in that election
5. Repeat steps 3 and 4 for the 'Results17' table
6. Press 'Write Changes'

# Adding new parties
1. Open up the database with [DB Browser for SQLite](https://sqlitebrowser.org/)
2. Open the 'Party' table and make a new records
3. Fill in 'Party_ID' with a new, unique value. 'Name' should be the official party name. 'Short_Name' should be a 2 - 5 character abreviation of the party name in upper case (Eg. Conservative is shortened to CON). The three Colour coloumns should contains an RGB colour to represent that party.
4. Press 'Write Changes'

# Known Issues
- Drop down list on main menu currently limited by height of the screen and has no scroll bar
