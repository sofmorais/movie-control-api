CREATE TABLE movie_watch (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    movie_id UUID,
    viewer_id UUID,
    viewingDate TIMESTAMP,
    CONSTRAINT fk_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
    CONSTRAINT fk_viewer FOREIGN KEY(viewer_id) REFERENCES viewer(id)
);
