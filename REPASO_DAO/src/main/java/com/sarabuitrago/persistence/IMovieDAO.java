package com.sarabuitrago.persistence;

import com.sarabuitrago.model.Movie;

import java.util.List;

public interface IMovieDAO {

        public void create(Movie movie);

        public Movie read(Movie movie);
        public List<Movie> readAll();
        public void update(Movie movie);
        public void delete(Movie movie);


}
