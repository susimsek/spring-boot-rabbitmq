package com.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//rabbitmq ile konuşmak için kullanacağız
@Configuration//inject ettik
public class RabbitmqConfiguration {

    @Value("${rabbit.queue.name}")
    private String queueName;

    @Value("${rabbit.routing.name}")
    private String routingName;

    @Value("${rabbit.exchange.name}")
    private String exchangeName;


    //kuyruğu oluşturduk
    @Bean
    public Queue initQueue(){
        return new Queue(queueName);

    }

    //direkt exchange oluşturduk
    @Bean
    public DirectExchange initDirectExchange(){
        return new DirectExchange(exchangeName);
    }

    //kuyruğu ve exchangeyi ilişkilerdirdik
    //routingi oluşturduk
    @Bean
    public Binding initBinding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingName);//kuyruğu exchangeye bağladık
    }

}
