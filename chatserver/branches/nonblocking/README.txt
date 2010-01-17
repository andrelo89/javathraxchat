Notes:
chat server runs on port 3333
admin server runs on port 6666
Persitence - database.xml file.
Room names are case insensitive
server.log - logfile

Known Limitations:

Server and client must share same OS type (BufferedReader and Writer use default system encoding).
No strict validation on user commands.
No detailed command error feedback to users.
One thread per user.
Admin password sent over plaintext, not encrypted
Admin password persisted in plaintext, not encrypted
Admin login permits infinite retries
Renaming or deleting nonempty rooms removes all users from within, they can still rejoin a room later
Log file does not roll over - log printing is limited by String size.