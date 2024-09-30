package com.devsouzx.user.producers;

import com.devsouzx.user.dtos.EmailDTO;
import com.devsouzx.user.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user) {
        EmailDTO emailDto = new EmailDTO(user.getUserId(), user.getEmail(), "Cadastro realizado com sucesso!", user.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!");
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}