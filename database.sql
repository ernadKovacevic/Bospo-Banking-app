CREATE DATABASE bospo;
USE bospo;
SELECT * FROM Komitent;
CREATE TABLE Komitent(
JMBG CHAR(13) PRIMARY KEY, 
ime CHAR(50),
prezime CHAR(50),
datumRodjenja DATE,
grad CHAR(50),
adresaStan CHAR(100) 
);

CREATE TABLE Kredit(
JMBGkomitent CHAR(13), 
idKredit CHAR(15) PRIMARY KEY,
datumIsplata DATE,
iznosIsplKredit DECIMAL (10,2),
kamatnaStopa DECIMAL (5,2),
rokOtplKredit DECIMAL (3,0),
uredIsplata CHAR(50)
);
