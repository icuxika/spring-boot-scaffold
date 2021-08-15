package com.icuxika.scaffold.module.test.controller;

import com.icuxika.scaffold.config.RabbitConfig;
import com.icuxika.scaffold.module.mq.entity.MQMessageSendLog;
import com.icuxika.scaffold.module.mq.mapper.MQMessageSendLogMapper;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("test")
public class TestController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MQMessageSendLogMapper mqMessageSendLogMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("getBeans")
    public String[] getBeans() {
        return applicationContext.getBeanDefinitionNames();
    }

    @RequestMapping("send")
    public void send() {
        String id = UUID.randomUUID().toString();

        // 记录 队列消息 发送日志
        MQMessageSendLog mqMessageSendLog = new MQMessageSendLog();
        mqMessageSendLog.setMessageId(id);
        mqMessageSendLog.setExchange(RabbitConfig.EXCHANGE_NAME);
        mqMessageSendLog.setRoutingKey(RabbitConfig.ROUTING_KEY_NAME);
        mqMessageSendLog.setCount(0);
        mqMessageSendLog.setCreateTime(new Date());
        mqMessageSendLog.setUpdateTime(new Date());
        mqMessageSendLogMapper.insertSelective(mqMessageSendLog);

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY_NAME, "hello", message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        }, new CorrelationData(id));
    }
}
