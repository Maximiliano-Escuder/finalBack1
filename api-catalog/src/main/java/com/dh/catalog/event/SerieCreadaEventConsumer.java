package com.dh.catalog.event;


import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.SerieRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SerieCreadaEventConsumer {

    private final SerieRepository serieRepository;

    public SerieCreadaEventConsumer(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE_CREADA)
    public void listen(Serie message){
        System.out.print("Serie: " + message.getName());
        //procesamiento
        //Vamos a guardar la serie en la base de mongodb de catalogo a traves del repositorio
        serieRepository.save(message);
    }


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
