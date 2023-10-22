package com.example.portfoliovaultv2.services;

import com.example.portfoliovaultv2.models.Education;
import com.example.portfoliovaultv2.models.MongoDBConnectionManager;
import com.example.portfoliovaultv2.models.User;
import com.example.portfoliovaultv2.services.utils.DatabaseUtils;
import com.example.portfoliovaultv2.session.UserSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.bson.BsonValue;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


@Stateless
public class EducationService {
    private static final String COLLECTION_NAME = "Education";
    private final MongoCollection<Document> collection = DatabaseUtils.getCollection(COLLECTION_NAME);
    private static String DB_NAME = "PortfolioVault";


    @Inject
    private UserSession userSession;

    public BsonValue addEducation(String emailUser, String diplomat, String university, String yearOfObtention) {
        MongoClient mongoClient = MongoDBConnectionManager.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        // Create a document with user data and insert it into the collection
        Document userDocument = new Document()
                .append("emailUser", emailUser)
                .append("diplomat", diplomat)
                .append("university", university)
                .append("yearOfObtention", yearOfObtention);

        InsertOneResult result = collection.insertOne(userDocument);
        return result.getInsertedId();
    }
    public Education getAllEducations(String email){
        Document Educations =  collection.find().first();
        return Education.documentToEducation(Educations);
    }

}
