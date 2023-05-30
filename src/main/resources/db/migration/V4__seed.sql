CREATE TABLE IF NOT EXISTS Users
(
    User_ID
    SERIAL
    PRIMARY
    KEY,
    First_Name
    VARCHAR
(
    250
) NOT NULL,
    Last_Name VARCHAR
(
    255
) NOT NULL,
    Email VARCHAR
(
    255
) NOT NULL UNIQUE,
    Password VARCHAR
(
    255
) NOT NULL, -- remember to hash passwords
    Role VARCHAR
(
    255
) NOT NULL,
    Photo BYTEA,
    Registration_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX idx_users_email ON Users (Email);

CREATE TABLE IF NOT EXISTS Managers
(
    Manager_ID
    SERIAL
    PRIMARY
    KEY,
    User_ID
    INTEGER
    NOT
    NULL,
    Status
    VARCHAR
(
    255
) NOT NULL,
    Photo BYTEA,
    FOREIGN KEY
(
    User_ID
) REFERENCES Users
(
    User_ID
) ON DELETE CASCADE
  ON UPDATE CASCADE
    );

CREATE INDEX idx_managers_user_id ON Managers (User_ID);

CREATE TABLE IF NOT EXISTS Trips
(
    Trip_ID
    SERIAL
    PRIMARY
    KEY,
    Name
    VARCHAR
(
    250
) NOT NULL,
    Description TEXT,
    Start_Date DATE,
    End_Date DATE,
    Location VARCHAR
(
    255
) NOT NULL,
    Manager_ID INTEGER NOT NULL,
    Photo BYTEA,
    FOREIGN KEY
(
    Manager_ID
) REFERENCES Managers
(
    Manager_ID
) ON DELETE CASCADE
  ON UPDATE CASCADE
    );

CREATE INDEX idx_trips_manager_id ON Trips (Manager_ID);

CREATE TABLE IF NOT EXISTS Excursions
(
    Excursion_ID
    SERIAL
    PRIMARY
    KEY,
    Name
    VARCHAR
(
    255
) NOT NULL,
    Description TEXT,
    Date DATE,
    Location VARCHAR
(
    255
) NOT NULL,
    Manager_ID INTEGER NOT NULL,
    Photo BYTEA,
    FOREIGN KEY
(
    Manager_ID
) REFERENCES Managers
(
    Manager_ID
) ON DELETE CASCADE
  ON UPDATE CASCADE
    );

CREATE INDEX idx_excursions_manager_id ON Excursions (Manager_ID);

CREATE TABLE IF NOT EXISTS Reviews
(
    Review_ID
    SERIAL
    PRIMARY
    KEY,
    Rating
    INTEGER
    NOT
    NULL,
    Review_Text
    TEXT,
    User_ID
    INTEGER
    NOT
    NULL,
    Trip_ID
    INTEGER,
    Excursion_ID
    INTEGER,
    Photo
    BYTEA,
    FOREIGN
    KEY
(
    User_ID
) REFERENCES Users
(
    User_ID
) ON DELETE CASCADE
  ON UPDATE CASCADE,
    FOREIGN KEY
(
    Trip_ID
) REFERENCES Trips
(
    Trip_ID
)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
    FOREIGN KEY
(
    Excursion_ID
) REFERENCES Excursions
(
    Excursion_ID
)
  ON DELETE CASCADE
  ON UPDATE CASCADE
    );

CREATE INDEX idx_reviews_user_id ON Reviews (User_ID);
CREATE INDEX idx_reviews_trip_id ON Reviews (Trip_ID);
CREATE INDEX idx_reviews_excursion_id ON Reviews (Excursion_ID);
CREATE TABLE IF NOT EXISTS TripParticipants
(
    Trip_ID
    INTEGER
    NOT
    NULL,
    User_ID
    INTEGER
    NOT
    NULL,
    FOREIGN
    KEY
(
    Trip_ID
) REFERENCES Trips
(
    Trip_ID
) ON DELETE CASCADE
  ON UPDATE CASCADE,
    FOREIGN KEY
(
    User_ID
) REFERENCES Users
(
    User_ID
)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
    PRIMARY KEY
(
    Trip_ID,
    User_ID
)
    );

CREATE INDEX idx_trip_participants_trip_id ON TripParticipants (Trip_ID);
CREATE INDEX idx_trip_participants_user_id ON TripParticipants (User_ID);

CREATE TABLE IF NOT EXISTS ExcursionParticipants
(
    Excursion_ID
    INTEGER
    NOT
    NULL,
    User_ID
    INTEGER
    NOT
    NULL,
    FOREIGN
    KEY
(
    Excursion_ID
) REFERENCES Excursions
(
    Excursion_ID
) ON DELETE CASCADE
  ON UPDATE CASCADE,
    FOREIGN KEY
(
    User_ID
) REFERENCES Users
(
    User_ID
)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
    PRIMARY KEY
(
    Excursion_ID,
    User_ID
)
    );

CREATE INDEX idx_excursion_participants_excursion_id ON ExcursionParticipants (Excursion_ID);
CREATE INDEX idx_excursion_participants_user_id ON ExcursionParticipants (User_ID);
