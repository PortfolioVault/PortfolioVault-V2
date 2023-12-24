package com.example.portfoliovaultv2.views.entreprise;

import com.example.portfoliovaultv2.activeMQ.SimpleQueue;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
@Named
@ViewScoped
public class entrepriseBeans implements Serializable {
    private static final String QUEUE_NAME = "pendingPostulat";
    private List<List<String>> receivedMessages;

    @PostConstruct
    public void init() {
        try {
            receivedMessages = receiveAsync().get();
        } catch (Exception e) {
            e.printStackTrace();
            receivedMessages = Collections.emptyList();
        }
    }

    public Future<List<List<String>>> receiveAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SimpleQueue queue = new SimpleQueue(QUEUE_NAME);
                byte[] msgBytes = queue.receive();
                queue.close();

                // Convertir les octets en chaîne
                String msg = new String(msgBytes, StandardCharsets.UTF_8);

                System.out.println("le msg receverest " + msg);

                // Supposons que msg est une chaîne au format "Nom:Prenom:PDFBytes"
                String[] parts = msg.split(":");
                String nom = parts[0];
                String prenom = parts[1];
                String pdfBytes = parts[2];

                // Créer une liste contenant le nom, le prénom et le PDF
                List<String> receivedMessage = Arrays.asList(nom, prenom, pdfBytes);

                // Ajouter la liste à receivedMessages
                List<List<String>> receivedMessages = Collections.singletonList(receivedMessage);

                System.out.println("le msg est " + msg);
                return receivedMessages;
            } catch (Exception e) {
                e.printStackTrace(); // Gérer les erreurs correctement dans un environnement de production
                return Collections.emptyList();
            }
        });
    }


    public List<List<String>> getReceivedMessages() {
        return receivedMessages;
    }


}
