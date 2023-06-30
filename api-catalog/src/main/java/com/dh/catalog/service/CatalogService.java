package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.CatalogResponse;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {

    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;

    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }

    public CatalogResponse getMoviesAndSeriesByGenre(String genre) {
        CatalogResponse catalogResponse = new CatalogResponse();

        List<MovieServiceClient.MovieDto> movies = movieServiceClient.getMovieByGenre(genre);
        catalogResponse.setMovies(movies);

        List<Serie> series = serieServiceClient.getSerieByGenre(genre);
        catalogResponse.setSeries(series);

        return catalogResponse;
    }

}
