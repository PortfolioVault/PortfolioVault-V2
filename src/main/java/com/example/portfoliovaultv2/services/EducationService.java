package com.example.portfoliovaultv2.services;

import com.example.portfoliovaultv2.models.Education;
import com.example.portfoliovaultv2.models.MongoDBConnectionManager;
import com.example.portfoliovaultv2.services.utils.DatabaseUtils;
import com.example.portfoliovaultv2.session.UserSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


@Stateless
public class EducationService {
    private static final String COLLECTION_NAME = "Education";
    private final MongoCollection<Document> collection = DatabaseUtils.getCollection(COLLECTION_NAME);
    private static String DB_NAME = "PortfolioVault";


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
    public List<Education> getAllEducations(String email){
        FindIterable<Document> educations =  collection.find(eq("emailUser",email));
        List<Education> educationList = new ArrayList<>();
        for (Document document : educations) {
            // Convertir chaque document en objet Education et l'ajouter à la liste
            Education education = documentToEducation(document);
            educationList.add(education);
        }
        return educationList;
    }
    public  Education documentToEducation(Document document){
        if(document == null){
            return null;
        }
        Education education = new Education(document.getString("university"),document.getString("diplomat"), document.getString("yearOfObtention"), document.getString("emailUser"));
        education.setId(document.getObjectId("_id").toString());
        return education;

    }
    public void deleteById(String id) {

        Bson filter = Filters.eq("_id", new ObjectId(id));
        collection.findOneAndDelete(filter);
    }

}

