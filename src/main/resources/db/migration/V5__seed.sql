DO $$BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'user_role_enum') THEN
CREATE TYPE user_role_enum AS ENUM ('ADMIN', 'USER', 'MANAGER');
END IF;
END$$;
    DO $$BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'manager_status_enum') THEN
CREATE TYPE manager_status_enum AS ENUM ('ACTIVE', 'PASSIVE');
END IF;
END$$;


CREATE TABLE IF NOT EXISTS Users (
                                     User_ID SERIAL PRIMARY KEY,
                                     First_Name VARCHAR(250) NOT NULL,
                                     Last_Name VARCHAR(255) NOT NULL,
                                     Email VARCHAR(255) NOT NULL UNIQUE,
                                     Password VARCHAR(255) NOT NULL,
                                     Role user_role_enum NOT NULL,
                                     Photo BYTEA,
                                     Registration_Data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

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
    Status manager_status_enum NOT NULL,
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
CREATE INDEX IF NOT EXISTS idx_excursions_manager_id ON Excursions (Manager_ID);
CREATE INDEX IF NOT EXISTS idx_trips_manager_id ON Trips (Manager_ID);
CREATE INDEX IF NOT EXISTS idx_managers_user_id ON Managers (User_ID);
CREATE INDEX IF NOT EXISTS idx_reviews_user_id ON Reviews (User_ID);
CREATE INDEX IF NOT EXISTS idx_reviews_trip_id ON Reviews (Trip_ID);
CREATE INDEX IF NOT EXISTS idx_reviews_excursion_id ON Reviews (Excursion_ID);
CREATE INDEX IF NOT EXISTS idx_trip_participants_trip_id ON TripParticipants (Trip_ID);
CREATE INDEX IF NOT EXISTS idx_trip_participants_user_id ON TripParticipants (User_ID);
CREATE INDEX IF NOT EXISTS idx_excursion_participants_excursion_id ON ExcursionParticipants (Excursion_ID);
CREATE INDEX IF NOT EXISTS idx_excursion_participants_user_id ON ExcursionParticipants (User_ID);

