package com.arthurpasqualon.appmovies;

public class Movie {

    public static final int MIN_YEAR = 1950;
    public int id;
    public String name;
    private int year;

    public Movie() {
    }

    public Movie(String name, int year) {
        this.name = name;
        this.setYear(year);
    }

    public Movie(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year >= MIN_YEAR) {
            this.year = year;
        }
    }

    @Override
    public String toString() {
        return  "Nome: " + name  +  ", Ano: " + year;
    }
}
