package com.rabbitmq.springbootrabbitmq.producer;

import com.rabbitmq.springbootrabbitmq.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//kuyruğa data gönderir
@Service//servis olrak tanımladık
@RequiredArgsConstructor
public class NotificationProducer {

    //rabibt templateyi çağırdık.rabitmqnin connectionu gibi
    private final RabbitTemplate rabbitTemplate;

    //routingName yi aldık
    @Value("${rabbit.routing.name}")
    private String routingName;

    @Value("${rabbit.exchange.name}")
    private String exchangeName;

    //aldığı notificationu kuyruğa atacak
    public void sendToQueue(Notification notification){
        System.out.println("notification sent id : " + notification.getId());
        rabbitTemplate.convertAndSend(exchangeName,routingName,notification);//notifikasyonu gönderdik
    }
}
