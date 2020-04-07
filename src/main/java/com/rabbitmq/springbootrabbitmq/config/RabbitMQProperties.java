package com.rabbitmq.springbootrabbitmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//kuyruk adını,exchange adını ve routing keyi alıyoruz
//@ConfigurationProperties(prefix = "rabbitmq") application.propertiesden rabbitmq ile başlayan değerleri aldık
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Data
public class RabbitMQProperties {

    private String queueName;
    private String exchangeName;
    private String routingKey;

}
