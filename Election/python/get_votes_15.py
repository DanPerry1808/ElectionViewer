import sqlite3
import sys

conn = sqlite3.connect("db/election.db")
c = conn.cursor()

con_id = sys.argv[1]

command = """
SELECT Results15.Party_ID, Results15.Votes FROM Results15
WHERE Results15.Con_ID = ?
ORDER BY Results15.Votes DESC;
"""

# Outputs the party id, then a space then their vote
for line in c.execute(command, (con_id,)):
    print(str(line[0]) + " " + str(line[1]))

conn.close()
