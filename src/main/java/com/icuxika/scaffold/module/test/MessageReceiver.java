package com.icuxika.scaffold.module.test;

import com.icuxika.scaffold.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
public class MessageReceiver {

    private final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @RabbitHandler
    public void processHandler(String msg, Channel channel, Message message) throws IOException {
        try {
            System.out.println(msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                logger.error("rabbitmq中大量消息未确认");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                logger.error("消息即将再次返回队列处理");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
