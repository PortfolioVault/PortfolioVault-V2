package com.example.portfoliovaultv2.services;


import com.example.portfoliovaultv2.models.Experience;
import com.example.portfoliovaultv2.models.User;
import com.example.portfoliovaultv2.services.utils.DatabaseUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import jakarta.ejb.Stateless;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

@Stateless
public class ExperienceServiceEJB {

    private static final String COLLECTION_NAME = "portfolios";
    private final MongoCollection<Document> collection = DatabaseUtils.getCollection(COLLECTION_NAME);

    public void addExperience(String email, Experience experience) {
        Document lastExperience = experience.toDocument();
        Bson filter = Filters.eq("email", email);
        Bson update = Updates.addToSet("experiences", lastExperience);
        UpdateOptions options = new UpdateOptions().upsert(true);

        // Update the user document based on the userId
        collection.updateOne(filter, update, options);
    }

    public LinkedList<Experience> getExperiences(String email) {
        Document user = collection.find(eq("email", email)).first();
        if(user == null){
            return null;
        }
        List<Document> experiencesDocuments = user.getList("experiences", Document.class);
        LinkedList<Experience> experiences = new LinkedList<>();
        for(Document document:experiencesDocuments){
            experiences.add(Experience.documentToExperience(document));
        }
        return experiences;
    }
}