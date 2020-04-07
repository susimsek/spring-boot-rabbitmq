package com.rabbitmq.springbootrabbitmq.producer;

import com.rabbitmq.springbootrabbitmq.config.RabbitMQProperties;
import com.rabbitmq.springbootrabbitmq.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

//kuyruğa data gönderir
@Service//servis olrak tanımladık
@RequiredArgsConstructor
public class NotificationProducer {

    //rabit templateyi çağırdık.rabitmqnin connectionu gibi
    private final RabbitTemplate rabbitTemplate;

    //rabbimq properitesi çağırdık
    private final RabbitMQProperties rabbitMQProperties;


    //aldığı notificationu kuyruğa atacak
    public void sendToQueue(Notification notification){
        System.out.println("notification sent id : " + notification.getId());
        //exchange name ile routingkey ile mesajı gönderdik
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(),rabbitMQProperties.getRoutingKey(),notification);//notifikasyonu gönderdik
    }
}
