# badminton_app
##School project second semester

##Installation
###Opret database.properties
Opret filen i src/main/resources

Slet denne linje: 
"//This file needs to be renamed database.properties" hvis den er til stede
Ændrer username og password til dit eget SQL login
Ændrer development.url=jdbc:mysql://HOST:3306/DB til:
"development.url=jdbc:mysql://127.0.0.1:3306/<navnet på din database>"

###Setup Maven Framework
Right Click Project -> Add Framework Support -> Maven

###Project Settings
Right Click src/main/java -> Mark Directory as -> Source Root

###Error-handling
Hvis du får errors som du ikke rigtig kan finde sourcen til, kan du 
forsøge med at gøre "mvn install" i terminalen.

http://javalite.io/database_migrations
