package com.sarabuitrago.model;

public class Movie {

    private String titulo;
    private int iD;
    private String director;

    public Movie() {
    }

    public Movie(String titulo, int iD, String director) {
        this.titulo = titulo;
        this.iD = iD;
        this.director = director;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
