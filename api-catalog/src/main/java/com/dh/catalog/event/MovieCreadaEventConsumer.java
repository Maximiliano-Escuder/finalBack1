package com.dh.catalog.event;


import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.repository.MovieRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MovieCreadaEventConsumer {

    private final MovieRepository movieRepository;

    public MovieCreadaEventConsumer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE_CREADA)
    public void listen(Movie message){
        System.out.print("Movie: " + message.getName());
        //procesamiento
        //Vamos a guardar la movie en la base de mongodb de catalogo a traves del repositorio
        movieRepository.save(message);
    }

//
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    @Setter
//    public static class Data{
//        private String nombreCurso;
//        private int nota;
//        private String saludo;
//    }
}
