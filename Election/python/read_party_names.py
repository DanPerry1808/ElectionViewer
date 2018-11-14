import sqlite3

# Opens connection to database
conn = sqlite3.connect("db/election.db")
c = conn.cursor()

command = """
SELECT * FROM Party;
"""

c.execute(command)
results = c.fetchall()

# Prints out names of every constituency in the table
for s in results:
    print(str(s[1]) + "/" + str(s[2]) + "/" + str(s[3]) + "/" + str(s[4]) + "/" + str(s[5]))

# Closes the connection
conn.close()
