package com.devsouzx.email.consumers;

import com.devsouzx.email.dtos.EmailDTO;
import com.devsouzx.email.model.Email;
import com.devsouzx.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${broker.queue.email.name}" )
    public void listenEmailQueue(@Payload EmailDTO emailDTO) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        emailService.sendEmail(email);
    }
}
