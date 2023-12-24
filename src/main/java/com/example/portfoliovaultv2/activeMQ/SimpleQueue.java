package com.example.portfoliovaultv2.activeMQ;


import static jakarta.jms.Session.AUTO_ACKNOWLEDGE;

import jakarta.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SimpleQueue {

    private static final String CLIENTID = "TrishInfotechActiveMQ";
    private String queueName;
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private MessageConsumer consumer;

    public SimpleQueue(String queueName) throws Exception {
        super();
        // The name of the queue.
        this.queueName = queueName;
        // URL of the JMS server is required to create connection factory.
        // DEFAULT_BROKER_URL is : tcp://localhost:61616 and is indicates that JMS
        // server is running on localhost
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // Getting JMS connection from the server and starting it
        connection = connectionFactory.createConnection();
        connection.setClientID(CLIENTID);
        connection.start();
        // Creating a non-transactional session to send/receive JMS message.
        session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        // Destination represents here our queue ’MyFirstActiveMQ’ on the JMS
        // server.
        // The queue will be created automatically on the JSM server if its not already
        // created.
        destination = session.createQueue(this.queueName);
        // MessageProducer is used for sending (producing) messages to the queue.
        producer = session.createProducer(destination);
        // MessageConsumer is used for receiving (consuming) messages from the queue.
        consumer = session.createConsumer(destination);
    }

    public void send(byte[] fileBytes) throws Exception {
        // Créez un message de type BytesMessage
        BytesMessage message = session.createBytesMessage();

        // Ajoutez les octets du fichier au message
        message.writeBytes(fileBytes);

        // Envoyez le message dans la file d'attente
        producer.send(message);

        System.out.println("Le fichier a été envoyé avec succès à la file d'attente."+message);
    }

    public byte[] receive() {
        try {
            // Réception du message de la file d'attente
            Message message = consumer.receive();

            if (message instanceof BytesMessage) {
                BytesMessage bytesMessage = (BytesMessage) message;

                // Lire les octets du message
                byte[] fileBytes = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(fileBytes);

                System.out.println("Received file with " + fileBytes.length + " bytes.");

                return fileBytes;
            } else {
                System.out.println("Received a message that is not a BytesMessage.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gérer les erreurs correctement dans un environnement de production
        }

        return null;
    }


    public void close() throws JMSException {
        producer.close();
        producer = null;
        consumer.close();
        consumer = null;
        session.close();
        session = null;
        connection.close();
        connection = null;
    }
}