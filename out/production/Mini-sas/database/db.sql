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

DELIMITER //

CREATE TRIGGER after_change_book_quantity
    AFTER UPDATE ON livre FOR EACH ROW
BEGIN
    IF NEW.quantite > OLD.quantite THEN
    UPDATE emprunter
    SET status = 'returned'
    WHERE ISBN_emp = NEW.ISBN;
END IF;
END;

//

DELIMITER ;

DELIMITER //

CREATE EVENT check_return_status
ON SCHEDULE EVERY 1 DAY
DO
BEGIN
    -- Set the status to 'Perdu' for books where the return date has passed
UPDATE emprunter
SET status = 'Perdu'
WHERE status = 'borrowed' AND date_retour < NOW();
END;
//

DELIMITER ;