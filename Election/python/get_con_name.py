import sqlite3
import sys

# Opens connection to database
conn = sqlite3.connect("db/election.db")
c = conn.cursor()

# Uses the second command line argument as the consituency id
# Command is "python3 get_con_names.py x" where x is the con id
con_id = sys.argv[1]

command = """
SELECT Con.Name From Con
WHERE Con.Con_ID = ?;
"""

# Runs the command above to find the entry with that ID
c.execute(command, (con_id,))
# Returns the first field of the first result (the name)
print(c.fetchone()[0])

# Closes the connection
conn.close()
