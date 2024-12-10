CREATE TABLE movie_watch (
    id UUID PRIMARY KEY NOT NULL DEFAULT public.uuid_generate_v4(),
    movie_id uuid NOT NULL,
    viewer_id uuid NOT NULL,
    viewing_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT fk_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
    CONSTRAINT fk_viewer FOREIGN KEY(viewer_id) REFERENCES viewer(id)
);
