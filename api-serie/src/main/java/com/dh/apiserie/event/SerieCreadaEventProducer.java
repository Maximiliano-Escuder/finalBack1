package com.dh.apiserie.event;

import com.dh.apiserie.config.RabbitMQConfig;
import com.dh.apiserie.model.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class SerieCreadaEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public SerieCreadaEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishSerieCreadaEvent(Serie message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.TOPIC_SERIE_CREADA,message);
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
