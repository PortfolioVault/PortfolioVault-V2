package com.example.portfoliovaultv2.services.utils;

import com.example.portfoliovaultv2.models.MongoDBConnectionManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseUtils {
    private static final String DB_NAME = "PortfolioVault";

    public static MongoCollection<Document> getCollection(String collectionName){
        MongoClient client = MongoDBConnectionManager.getMongoClient();
        MongoDatabase database = client.getDatabase(DB_NAME);
        return database.getCollection(collectionName);
    }
}
