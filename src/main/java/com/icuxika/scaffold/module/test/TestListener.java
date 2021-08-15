package com.icuxika.scaffold.module.test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Component
public class TestListener {

    @Autowired
    private ConnectionFactory connectionFactory;

    public void init() throws IOException, TimeoutException {
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(false);
        channel.exchangeDeclare("mainExchange", "direct", true);
        channel.queueDeclare("mainQueue", true, false, false, null);
        channel.queueBind("mainQueue", "mainExchange", "mainRoute");

        channel.basicConsume("mainQueue", true, "", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
        channel.close();
        connection.close();
    }

}
