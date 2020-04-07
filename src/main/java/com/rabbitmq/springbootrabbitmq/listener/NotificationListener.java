package com.rabbitmq.springbootrabbitmq.listener;

import com.rabbitmq.springbootrabbitmq.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//kuyruktan notifikasyonları dinler
@Service //servis olrak tanımladık
public class NotificationListener {

    //kuyruktan aldığı notificationu ekrana yazar
    //dinleyeceği kuyruğu belirtmemiz gerekir
    @RabbitListener(queues = "notification-queue" )//bir message geldiğinde bu listener tetiklesin
    public void handleMessage(Notification notification){
        System.out.println("mesaj alındı");
        System.out.println(notification.toString());//aldığı her mesajı terminale yazar
    }
}
