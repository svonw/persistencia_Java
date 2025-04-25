package com.sarabuitrago.persistence;

import com.sarabuitrago.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDaoFakeImp implements IMovieDAO{
    private List<Movie> movies= new ArrayList<>();
    @Override
    public void create(Movie movie) {
    this.movies.add(movie);
    }

    @Override
    public Movie read(Movie movie) {
        return null;
    }

    @Override
    public List<Movie> readAll() {
        return List.of();
    }

    @Override
    public void update(Movie movie) {

    }

    @Override
    public void delete(Movie movie) {

    }
}
