# Distributed_Systems_TCP_Chat
## TCP-based Chat System with Server and Client.

### Umgebungseinstellungen
JDK 11
### WICHTIGE constrains
zu verwendende java.packages: java.io*, java.net*, java.util.*, java.swing.*
### Serverfunktionen:
- Anmeldung mit Kennung und Passwort
Alle angemeldeten Nutzer sollen gespeichert werden(Server vergibt ID)
- Nachrichten verschicken über IDs(Client1 -><- Server -><- Client2)
- Abmeldemöglichkeit



### Klassen & Attribute
User:
- UserID
- Password
- Kennung
Client:
- Port
- host

### ToDo next
- 2ter Server für Fehlertoleranz, soll einspringen wenn erster ausfällt
- Synchronisation der Daten auf Server1 und Server2
- Fehlerbehandlung: ExceptionHandling & JUnit-Tests
- Demo: Fehler eines Server -> Serverausfall - anderer soll einsrpingen
- Trafficregelungen: Datenlast aufteilen
- 
