package com.icuxika.scaffold.module.test.controller;

import com.icuxika.scaffold.config.RabbitConfig;
import com.icuxika.scaffold.module.mq.entity.MQMessageSendLog;
import com.icuxika.scaffold.module.mq.mapper.MQMessageSendLogMapper;
import com.icuxika.scaffold.module.test.entity.Parameter;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("test")
@Validated
public class TestController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MQMessageSendLogMapper mqMessageSendLogMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

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

    /**
     * 必须在Controller类上加上@Validated注解，加在参数上无效
     */
    @RequestMapping("validate")
    public void validate(@NotNull Long id) {
        System.out.println(id);
    }

    /**
     * 必须在参数上加上注解 @Validated，类上的无效
     */
    @PostMapping("validatePost")
    public void validatePost(@RequestBody @Validated Parameter parameter) {
        System.out.println(parameter.toString());
    }

    /**
     * 必须在参数上加上注解 @Validated，类上的无效
     */
    @GetMapping("validateGet")
    public void validateGet(@Validated Parameter parameter) {
        System.out.println(parameter);
    }

    @GetMapping("sendKafkaMessage")
    public void sendKafkaMessage() {
        kafkaTemplate.send("", 0, "", "");
    }

    @PostMapping("upload")
    public void upload(Info info, MultipartFile file) {
        System.out.println(info.toString());
        System.out.println(file.getOriginalFilename());
    }
}

class Info {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
