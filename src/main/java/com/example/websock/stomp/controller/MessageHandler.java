package com.example.websock.stomp.controller;

import com.example.websock.model.ClientMessage;
import com.example.websock.model.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;

@Slf4j
@Controller
public class MessageHandler {

    @MessageMapping("/message")
    @SendTo("/topic/greetings")
    public Greeting greeting(ClientMessage message) throws Exception {
        log.info("message received, message:{}", message.getMessage());
        Thread.sleep(1000); // simulated delay
        return Greeting.builder()
                .content(HtmlUtils.htmlEscape(message.getMessage()))
                .now(LocalDateTime.now())
                .build();
    }
}
