package com.dh.catalog.client;

import com.dh.catalog.model.serie.Serie;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name="api-serie")
public interface SerieServiceClient {

	@GetMapping("/api/v1/series/{genre}")
	List<Serie> getSerieByGenre(@PathVariable (value = "genre") String genre);


}
