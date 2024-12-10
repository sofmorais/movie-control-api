CREATE TABLE viewer (
    id UUID PRIMARY KEY NOT NULL DEFAULT public.uuid_generate_v4(),
    name VARCHAR(255) NOT NULL
);