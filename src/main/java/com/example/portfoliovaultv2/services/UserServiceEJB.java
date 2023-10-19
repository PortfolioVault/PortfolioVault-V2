package com.example.portfoliovaultv2.services;

import com.example.portfoliovaultv2.exceptions.EmailAlreadyTakenException;
import com.example.portfoliovaultv2.models.MongoDBConnectionManager;
import com.example.portfoliovaultv2.models.User;
import com.example.portfoliovaultv2.session.UserSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.logging.Logger;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Stateless
public class UserServiceEJB {
    private static final String COLLECTION_NAME = "portfolios";
    private static final String DB_NAME = "PortfolioVault";
    Logger logger = Logger.getLogger(UserServiceEJB.class.getName());
    @Inject
    private UserSession userSession;
    public BsonValue registerUser(String firstName, String lastName, String email, String password) throws EmailAlreadyTakenException{

        MongoClient mongoClient = MongoDBConnectionManager.getMongoClient();
        // Get the database and collection (replace "your-database-name" and "users" with your actual names)
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        User user = findUserByEmail(email);
        if(user != null){
            //check if a user with same email exists
            throw  new EmailAlreadyTakenException();
        }else{

        // Create a document with user data and insert it into the collection
        Document userDocument = new Document()
                .append("firstname", firstName)
                .append("lastname", lastName)
                .append("email", email)
                .append("password", password);

        InsertOneResult result = collection.insertOne(userDocument);
        userSession.setEmail(email);
        return result.getInsertedId();
        }
    }

    public User findUserByEmail(String email){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoClient client = MongoDBConnectionManager.getMongoClient();
        MongoDatabase database = client.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
        MongoCollection<User> collection = database.getCollection(COLLECTION_NAME, User.class);
        User user = collection.find(eq("email",email)).first();
        return collection.find(eq("email",email)).first();
    }

    public void savePersonalInfos(BsonValue userId,String phoneNumber,String age, String professionalTitle,String address){
        MongoClient client = MongoDBConnectionManager.getMongoClient();
        MongoDatabase database = client.getDatabase("PortfolioVault");
        MongoCollection<Document> collection = database.getCollection("portfolios");
        Document document = new Document("$set",new Document()
                .append("professionalTitle",professionalTitle)
                .append("phoneNumber",phoneNumber)
                .append("address",address)
                .append("age",age));
        collection.updateOne(Filters.eq("_id",userId),document);
    }
}
