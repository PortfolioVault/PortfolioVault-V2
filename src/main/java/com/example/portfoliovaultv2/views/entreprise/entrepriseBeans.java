package com.example.portfoliovaultv2.views.entreprise;

import com.example.portfoliovaultv2.activeMQ.SimpleQueue;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
@Named
@ViewScoped
public class entrepriseBeans implements Serializable {
    private static final String QUEUE_NAME = "pendingPostulat";
    private List<String> receivedMessages;

    @PostConstruct
    public void init() {
        try {
            receivedMessages = receiveAsync().get();
        } catch (Exception e) {
            e.printStackTrace();
            receivedMessages = Collections.emptyList();
        }
    }


    public Future<List<String>> receiveAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SimpleQueue queue = new SimpleQueue(QUEUE_NAME);
                String msg = queue.receive();
                queue.close();

                List<String> receivedMessages = Collections.singletonList(msg);
                System.out.println("le msg est " + receivedMessages);
                return receivedMessages;
            } catch (Exception e) {
                e.printStackTrace(); // Gérer les erreurs correctement dans un environnement de production
                return Collections.emptyList();
            }
        });
    }


    public List<String> getReceivedMessages() {
        return receivedMessages;
    }
}
