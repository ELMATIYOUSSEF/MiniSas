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
CREATE TRIGGER after_emprunter_update
    AFTER UPDATE ON emprunter
    FOR EACH ROW
BEGIN
    -- Check if the 'status' column has been updated to 'returned'
    IF NEW.status = 'returned' THEN
        -- Insert the book information into the Bookreturned table
        INSERT INTO Bookreturned (date_retour, date_demprunt, status, ISBN_emp, id_Bib, id_Ben)
        VALUES (NOW(), NEW.date_demprunt, NEW.status, NEW.ISBN_emp, NEW.id_Bib, NEW.id_Ben);
        -- Delete the book from the emprunter table
    DELETE FROM emprunter WHERE ISBN_emp = NEW.ISBN_emp AND id_Ben = NEW.id_Ben;
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