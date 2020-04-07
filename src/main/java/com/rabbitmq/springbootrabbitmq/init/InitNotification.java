package com.rabbitmq.springbootrabbitmq.init;

import com.rabbitmq.springbootrabbitmq.model.Notification;
import com.rabbitmq.springbootrabbitmq.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitNotification {


    //notification produceri çağırdık
    private final NotificationProducer notificationProducer;

    @PostConstruct
    public void init(){
        Notification notification= new Notification();
        notification.setId(UUID.randomUUID().toString());//random id ürettik
        notification.setCreadtedAt(new Date());//oluşturulma tarihini atadık
        notification.setMessage("test message");//mesaj atadık
        notification.setSeen(false);//false atadık

        //mesajı kuyruğa atadık
        notificationProducer.sendToQueue(notification);

    }
}
