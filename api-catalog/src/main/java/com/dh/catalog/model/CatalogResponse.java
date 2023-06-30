package com.dh.catalog.model;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.model.serie.Serie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CatalogResponse {
    private List<Serie> series = new ArrayList<>();
    private List<MovieServiceClient.MovieDto> movies = new ArrayList<>();

    public CatalogResponse(List<Serie> series, List<MovieServiceClient.MovieDto> movies) {
        this.movies = movies;
        this.series = series;
    }

    public CatalogResponse() {
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public List<MovieServiceClient.MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieServiceClient.MovieDto> movies) {
        this.movies = movies;
    }

}
