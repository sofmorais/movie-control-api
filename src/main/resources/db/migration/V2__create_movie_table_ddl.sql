CREATE TABLE movie (
    id UUID PRIMARY KEY NOT NULL DEFAULT public.uuid_generate_v4(),
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    release_year INT
);
