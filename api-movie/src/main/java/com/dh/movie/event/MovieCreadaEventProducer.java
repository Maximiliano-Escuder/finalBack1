package com.dh.movie.event;

import com.dh.movie.config.RabbitMQConfig;
import com.dh.movie.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MovieCreadaEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public MovieCreadaEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishMovieCreadaEvent(Movie message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TOPIC_MOVIE_CREADA,message);
    }

//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    @Setter
//    public static class Data{
//         private String nombreCurso;
//         private int nota;
//         private String saludo;
//    }

}
