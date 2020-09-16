

# Dette trenger kun å kjøre om dere ikke har en schema (en database partisjon.)
#lager databasen om den ikke finnes. navnet er bare tilfeldig.
CREATE DATABASE if not EXISTS otra;


#denne må dere kjøre for at skript filen skal skjønne at
# <otra> er databasen dere vil lage tables og inserte data i.
USE otra;


#alt under her må kjøres for at eksemplet jeg har lagd skal kunne kjøre.

#lager table Users i databasen otra
create table if not EXISTS otra.user
(
    User_id        integer UNIQUE auto_increment,
    User_firstName varchar(255),
    User_lastName  varchar(255),
    User_Email     varchar(255),
    User_password  varchar(255),
    CONSTRAINT U_User_ID_PK PRIMARY KEY (User_id)
);


#inserter en record av en bruker inn i databasen otra.
insert into otra.user (User_firstName,
                       User_lastName,
                       User_Email,
                       User_password)
values (
        'trym',
        'Staurheim',
        'trym@example.com',
        '12345');