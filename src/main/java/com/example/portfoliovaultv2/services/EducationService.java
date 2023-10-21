package com.example.portfoliovaultv2.services;

import com.example.portfoliovaultv2.models.MongoDBConnectionManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.bson.BsonValue;
import org.bson.Document;

@Named
@ApplicationScoped
public class EducationService {
    private static String DB_NAME = "PortfolioVault";
    private static String COLLECTION_NAME = "Education";

    public BsonValue addEducation(String idUser,String diplomat, String university, String yearOfObtention) {
        MongoClient mongoClient = MongoDBConnectionManager.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        // Create a document with user data and insert it into the collection
        Document userDocument = new Document()
                .append("idUser", idUser)
                .append("diplomat", diplomat)
                .append("university", university)
                .append("yearOfObtention", yearOfObtention);

        InsertOneResult result = collection.insertOne(userDocument);
        return result.getInsertedId();
    }
}