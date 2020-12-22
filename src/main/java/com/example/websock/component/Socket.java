package com.example.websock.component;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
@Component
@ServerEndpoint(value = "/websocket")  //서버가 바인딩된 주소를 뜻함.
public class Socket {
    private Session session;
    public static List<Socket> listeners = new ArrayList<>();
    private static int onlineCount = 0;

    private TestComponent testComponent;

    @OnOpen //클라이언트가 소켓에 연결되때 마다 호출
    public void onOpen(Session session) {
        onlineCount++;
        log.info("onOpen called, userCount:" + onlineCount);
        this.testComponent = SpringContext.getBean(TestComponent.class);
        this.testComponent.printTestString();
        this.session = session;
        listeners.add(this);
    }

    @OnClose //클라이언트와 소켓과의 연결이 닫힐때 (끊길떄) 마다 호
    public void onClose(Session session) {
        onlineCount--;
        log.info("onClose called, userCount:" + onlineCount);
        listeners.remove(this);
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("onMessage called, message:" + message);
        broadcast(message);
    }

    @OnError //의도치 않은 에러 발생
    public void onError(Session session, Throwable throwable) {
        log.warning("onClose called, error:" + throwable.getMessage());
        listeners.remove(this);
    }

    public void broadcast(String message) {
        for (Socket listener : listeners) {
            if (listener == this) continue;
            listener.sendMessage(message);
        }
    }

    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.warning("Caught exception while sending message to Session " + this.session.getId() + "error:" + e.getMessage());
        }
    }
}
