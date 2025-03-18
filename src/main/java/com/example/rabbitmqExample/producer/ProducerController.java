package com.example.rabbitmqExample.producer;

import com.example.rabbitmqExample.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProducerController {

	@Value("${spring.rabbitmq.notification.exchange}")
	private String exchange;

	@Value("${spring.rabbitmq.notification.routekey}")
	private String routingKey;

	private final RabbitTemplate rabbitTemplate;

	public ProducerController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@PostMapping("/sendMessage")
	public void sendMessage(@RequestBody Customer customer) {
		rabbitTemplate.convertAndSend(exchange, routingKey, customer);
		log.info("Mesaj Gönderildi [{}]", customer);
	}

	@GetMapping("/send")
	public void send() {
		Customer customer = new Customer();
		customer.setCustomerId(System.currentTimeMillis());
		customer.setMessage("RabbitMQ mesaj gönderme örneği");
		rabbitTemplate.convertAndSend(exchange, routingKey, customer);
		log.info("Mesaj Gönderildi: [{}]", customer);
	}
}
