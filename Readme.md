#EpolsoftExample

##Desсription
This is Spring Boot Application works with the "Actors" database
 and the PosgreSQl database management system. 
 
 ##The Task
 1. Create a database with some kind of entity. There must be fields of different types, as well as several tables with different types of relationships (one-to-one, one-to-many, many-to-many).
 2. Create a service with a process that reads data from a prepared test file every 5 minutes 5 records (remembering the last read).
 3. Create REST API to work with the database.
 4. Create a process that will change the value of a field in the next 3 records every 2 minutes (remembering the last id).
 5. Output to the log file of operations performed with id.
 6. REST API (get only) to read the log.
 
 ##How to use the app
 
 Used entities:
+ Actor
+ Address
+ Counter
+ Movie
+ MovieGenre

1. Create database with name Actors and epol_user.(read application.properties)
2. Run initDB.sql
3. Run Application.java and after that:
+ WriteDataFromCsvService will fill database with MovieGenre and Movie entity from prepared test file. Every 5 minutes 5 records from prepared test file will fill the database with Actor entity.
+ UpdateActorService every 2 minutes invert Actors firstname and lastname in next 3 records.
+ Read log file.