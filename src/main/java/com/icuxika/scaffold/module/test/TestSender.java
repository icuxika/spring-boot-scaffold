package com.icuxika.scaffold.module.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Component
public class TestSender {

    @Autowired
    private ConnectionFactory connectionFactory;

    public void init() throws IOException, TimeoutException {
        Connection connection = connectionFactory.createConnection();
        System.out.println("连接是否打开：" + connection.isOpen());
        Channel channel = connection.createChannel(false);
        channel.exchangeDeclare("mainExchange", "direct", true);
        channel.queueDeclare("mainQueue", true, false, false, null);
        channel.queueBind("mainQueue", "mainExchange", "mainRoute");

        byte[] message = "Hello, world!".getBytes();
        channel.basicPublish("mainExchange", "mainRoute", MessageProperties.PERSISTENT_TEXT_PLAIN, message);
        channel.close();
        connection.close();
    }
}
