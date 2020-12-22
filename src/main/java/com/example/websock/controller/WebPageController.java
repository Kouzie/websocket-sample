package com.example.websock.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
public class WebPageController {

    @GetMapping("/sample")
    public void websocketSample(Model model) {
        log.info("websocketSample called...");
        model.addAttribute("result", "SUCCESS");
    }
}
