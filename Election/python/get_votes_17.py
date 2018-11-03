import sqlite3
import sys

conn = sqlite3.connect("db/election.db")
c = conn.cursor()

con_id = sys.argv[1]

command = """
SELECT Results17.Party_ID, Results17.Votes FROM Results17
WHERE Results17.Con_ID = ?
ORDER BY Results17.Votes DESC;
"""

# Outputs the party id, then a space then their vote
for line in c.execute(command, (con_id,)):
    print(str(line[0]) + " " + str(line[1]))

conn.close()
