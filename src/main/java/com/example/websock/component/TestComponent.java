package com.example.websock.component;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class TestComponent {
    private String testString = "testString";

    public void printTestString() {
        log.info(testString);
    }
}
