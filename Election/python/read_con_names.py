import sqlite3

conn = sqlite3.connect("db/election.db")
c = conn.cursor()

command = """
SELECT Con.Name FROM Con;
"""

c.execute(command)
results = c.fetchall()

for s in results:
    print(s[0])

conn.close()
