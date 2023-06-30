package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.CatalogResponse;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final MovieServiceClient movieServiceClient;
    private final MovieRepository movieRepository;
    private final SerieServiceClient serieServiceClient;
    private final SerieRepository serieRepository;

    public CatalogService(MovieServiceClient movieServiceClient, MovieRepository movieRepository, SerieServiceClient serieServiceClient, SerieRepository serieRepository) {
        this.movieServiceClient = movieServiceClient;
        this.movieRepository = movieRepository;
        this.serieServiceClient = serieServiceClient;
        this.serieRepository = serieRepository;
    }

    public CatalogResponse getMoviesAndSeriesByGenre(String genre) {
        CatalogResponse catalogResponse = new CatalogResponse();

        List<Movie> movies = getMoviesByGenre(genre);
        catalogResponse.setMovies(movies);

        List<Serie> series = getSeriesByGenre(genre);
        catalogResponse.setSeries(series);

        return catalogResponse;
    }

    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "clientCatalog", fallbackMethod = "getMoviesFallback")
    private List<Movie> getMoviesByGenre(String genre) {
        return movieServiceClient.getMovieByGenre(genre);
    }

    public List<Movie> getMoviesFallback(String genre, Throwable e) throws Exception {
        //Aqui llamar a las movies que tiene catalog en su base mongo con movieRepository
        return movieRepository.findAllByGenre(genre);
    }


    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "clientCatalog", fallbackMethod = "getSeriesFallback")
    private List<Serie> getSeriesByGenre(String genre) {
        return serieServiceClient.getSerieByGenre(genre);
    }

    public List<Serie> getSeriesFallback(String genre, Throwable e) throws Exception {
        //Aqui llamar a las series que tiene catalog en su base mongo con serieRepository
        return serieRepository.findAllByGenre(genre);
    }
}
