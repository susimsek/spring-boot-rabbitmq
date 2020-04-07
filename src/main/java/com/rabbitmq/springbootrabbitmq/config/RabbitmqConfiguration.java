package com.rabbitmq.springbootrabbitmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    //kuyruğa json şeklinde veri göndermek için kullanılır.default byte[] gönderiliyor
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
       RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
