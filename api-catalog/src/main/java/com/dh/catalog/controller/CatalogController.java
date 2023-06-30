package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;

import com.dh.catalog.model.CatalogResponse;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieServiceClient movieServiceClient;
	private final CatalogService catalogService;

	public CatalogController(MovieServiceClient movieServiceClient, CatalogService catalogService) {
		this.movieServiceClient = movieServiceClient;
		this.catalogService = catalogService;
	}

	@GetMapping("/{genre}")
	ResponseEntity<CatalogResponse> getGenre(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getMoviesAndSeriesByGenre(genre));
	}

//	@GetMapping("/{genre}")
//	ResponseEntity<List<MovieServiceClient.MovieDto>> getGenre(@PathVariable String genre) {
//		return ResponseEntity.ok(movieServiceClient.getMovieByGenre(genre));
//	}

}
