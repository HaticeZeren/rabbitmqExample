package com.example.rabbitmqExample.consumer;

import com.example.rabbitmqExample.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerListener {

	@RabbitListener(queues = "${spring.rabbitmq.notification.queue}")
	public void listen(Customer message) {
		log.info("Mesaj Alındı: [{}]", message);
	}
}
