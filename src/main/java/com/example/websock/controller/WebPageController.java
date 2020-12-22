package com.example.websock.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
public class WebPageController {

    @GetMapping("/sample")
    public void websocket_stomp(Model model) {
        log.info("websocket_stomp called...");
        model.addAttribute("result", "SUCCESS");
    }
}
