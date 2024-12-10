CREATE TABLE movie (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    releaseYear INT
);
