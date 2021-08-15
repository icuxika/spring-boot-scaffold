package com.icuxika.scaffold.config;

import com.icuxika.scaffold.module.mq.mapper.MQMessageSendLogDynamicSqlSupport;
import com.icuxika.scaffold.module.mq.mapper.MQMessageSendLogMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback, InitializingBean {

    public static final String EXCHANGE_NAME = "main_exchange";

    public static final String QUEUE_NAME = "main_queue";

    public static final String ROUTING_KEY_NAME = "main_route";

    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MQMessageSendLogMapper mqMessageSendLogMapper;

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    Queue queue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 60000); // 消息生存期
        arguments.put("x-expires", 1800000); // 队列生存期
        arguments.put("x-max-length", 10); // 队列可以容纳的消息的最大条数
        arguments.put("x-max-length-bytes", 10); // 队列可以容纳的消息的最大字节数
        arguments.put("x-overflow", "reject-publish"); // 队列中的消息溢出后如何处理
        arguments.put("x-dead-letter-exchange", ""); // 溢出的消息需要发送到绑定该死信交换机的队列
        arguments.put("x-dead-letter-routing-key", ""); // 溢出的消息需要发送到绑定该死信交换机，并且路由键匹配的队列
        arguments.put("x-single-active-consumer", true);
        arguments.put("x-max-priority", 10); // 最大优先级
        arguments.put("x-queue-mode", "lazy"); // 懒人模式

        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue())
                .to(directExchange())
                .with(ROUTING_KEY_NAME);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("{}:消息成功到达交换器", correlationData.getId());
            mqMessageSendLogMapper.update(u ->
                    u.set(MQMessageSendLogDynamicSqlSupport.status).equalTo(1)
                            .where().where(MQMessageSendLogDynamicSqlSupport.messageId, SqlBuilder.isEqualTo(correlationData.getId()))
            );
        } else {
            logger.error("{}:消息发送失败", correlationData.getId());
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        logger.error("{}:消息未成功路由到队列", returned.getMessage().getMessageProperties().getHeaders().get("spring_returned_message_correlation"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }
}
