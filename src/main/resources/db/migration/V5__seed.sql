
CREATE TABLE IF NOT EXISTS Users (
                                     User_ID SERIAL PRIMARY KEY,
                                     First_Name VARCHAR(250) NOT NULL,
                                     Last_Name VARCHAR(255) NOT NULL,
                                     Email VARCHAR(255) NOT NULL UNIQUE,
                                     Password VARCHAR(255) NOT NULL,
                                     Role VARCHAR(255) NOT NULL,
                                     Registration_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Managers (
                                        Manager_ID SERIAL PRIMARY KEY,
                                        User_ID INTEGER NOT NULL,
                                        Status VARCHAR(255) NOT NULL,
                                        FOREIGN KEY (User_ID) REFERENCES Users (User_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Trips (
                                     Trip_ID SERIAL PRIMARY KEY,
                                     Name VARCHAR(250) NOT NULL,
                                     Description TEXT,
                                     Start_Date DATE,
                                     End_Date DATE,
                                     Location VARCHAR(255) NOT NULL,
                                     Manager_ID INTEGER NOT NULL,
                                     FOREIGN KEY (Manager_ID) REFERENCES Managers (Manager_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Excursions (
                                          Excursion_ID SERIAL PRIMARY KEY,
                                          Name VARCHAR(255) NOT NULL,
                                          Description TEXT,
                                          Date DATE,
                                          Location VARCHAR(255) NOT NULL,
                                          Manager_ID INTEGER NOT NULL,
                                          FOREIGN KEY (Manager_ID) REFERENCES Managers (Manager_ID) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Trip_Participants (
                                                 trip_participants_id SERIAL PRIMARY KEY,
                                                 Trip_ID INTEGER NOT NULL,
                                                 User_ID INTEGER NOT NULL,
                                                 FOREIGN KEY (Trip_ID) REFERENCES Trips (Trip_ID) ON DELETE CASCADE ON UPDATE CASCADE,
                                                 FOREIGN KEY (User_ID) REFERENCES Users (User_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Excursion_Participants (
                                                      excursion_participants_id SERIAL PRIMARY KEY,
                                                      Excursion_ID INTEGER NOT NULL,
                                                      User_ID INTEGER NOT NULL,
                                                      FOREIGN KEY (Excursion_ID) REFERENCES Excursions (Excursion_ID) ON DELETE CASCADE ON UPDATE CASCADE,
                                                      FOREIGN KEY (User_ID) REFERENCES Users (User_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_excursions_manager_id ON Excursions (Manager_ID);
CREATE INDEX IF NOT EXISTS idx_trips_manager_id ON Trips (Manager_ID);
CREATE INDEX IF NOT EXISTS idx_managers_user_id ON Managers (User_ID);
CREATE INDEX IF NOT EXISTS idx_trip_participants_trip_id ON Trip_Participants (Trip_ID);
CREATE INDEX IF NOT EXISTS idx_trip_participants_user_id ON Trip_Participants (User_ID);
CREATE INDEX IF NOT EXISTS idx_excursion_participants_excursion_id ON Excursion_Participants (Excursion_ID);
CREATE INDEX IF NOT EXISTS idx_excursion_participants_user_id ON Excursion_Participants (User_ID);