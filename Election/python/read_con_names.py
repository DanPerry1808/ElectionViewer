import sqlite3

# Opens connection to database
conn = sqlite3.connect("db/election.db")
c = conn.cursor()

command = """
SELECT Con.Name FROM Con;
"""

c.execute(command)
results = c.fetchall()

# Prints out names of every constituency in the table
for s in results:
    print(s[0])

# Closes the connection
conn.close()
