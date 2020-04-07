package com.rabbitmq.springbootrabbitmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//rabbitmq ile konuşmak için kullanacağız
@Configuration//inject ettik
@RequiredArgsConstructor
public class RabbitmqConfiguration {


    //rabbitmq propertiesi inject ettik,kuyruk,exchange ve routing adını aldık
    private final RabbitMQProperties rabbitMQProperties;


    //kuyruğu oluşturduk
    @Bean
    public Queue initQueue(){
        return new Queue(rabbitMQProperties.getQueueName());
    }

    //direkt exchange oluşturduk
    @Bean
    public DirectExchange initDirectExchange(){
        return new DirectExchange(rabbitMQProperties.getExchangeName());
    }

    //kuyruğu ve exchangeyi ilişkilerdirdik
    //routingi oluşturduk
    @Bean
    public Binding initBinding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(rabbitMQProperties.getRoutingKey());//kuyruğu exchangeye bağladık
    }

}
