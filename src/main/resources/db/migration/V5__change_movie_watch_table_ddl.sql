ALTER TABLE public.movie_watch RENAME TO movie_view_record;

ADD COLUMN movie_title VARCHAR(255) NOT NULL;