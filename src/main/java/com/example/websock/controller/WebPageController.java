package com.example.websock.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
public class WebPageController {

    @GetMapping("/sample")
    public void websocket(Model model) {
        log.info("websocket called...");
        model.addAttribute("result", "SUCCESS");
    }
    @GetMapping("/sockjs")
    public void websocketSockJs(Model model) {
        log.info("websocketSockJs called...");
        model.addAttribute("result", "SUCCESS");
    }
}
