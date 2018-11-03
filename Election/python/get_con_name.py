import sqlite3
import sys

conn = sqlite3.connect("db/election.db")
c = conn.cursor()

con_id = sys.argv[1]

command = """
SELECT Con.Name From Con
WHERE Con.Con_ID = ?;
"""

c.execute(command, (con_id,))
print(c.fetchone()[0])

conn.close()
