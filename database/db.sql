create database minisas;
use minisas;

CREATE TABLE Bibliothecaires  (
                          id INT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(30) NOT NULL,
                          email VARCHAR(30),
                          password VARCHAR(100) NOT NULL,
                          PRIMARY KEY (id)
);
CREATE TABLE Beneficiaires   (
                                  id INT NOT NULL AUTO_INCREMENT,
                                  name VARCHAR(30) NOT NULL,
                                  num_phone VARCHAR(30) NOT NULL,
                                  PRIMARY KEY (id)
);

CREATE TABLE Livre  (
                                  ISBN INT NOT NULL,
                                  titre VARCHAR(30) NOT NULL,
                                  auteur VARCHAR(30),
                                  quantite int not null ,
                                  status VARCHAR(100) NOT NULL check ( status ='disponible' or status = 'emprunte' ),
                                  PRIMARY KEY (ISBN)
);

CREATE TABLE Emprunter (
                           id INT NOT NULL AUTO_INCREMENT,
                           date_retour DATE,
                           date_demprunt DATE,
                           status VARCHAR(30) DEFAULT 'emprunte',
                           ISBN_emp INT,
                           id_Bib INT,
                           id_Ben INT,
                           PRIMARY KEY (id),
                           FOREIGN KEY (ISBN_emp) REFERENCES Livre(ISBN),
                           FOREIGN KEY (id_Bib) REFERENCES Bibliothecaires(id),
                           FOREIGN KEY (id_Ben) REFERENCES Beneficiaires(id)
);
