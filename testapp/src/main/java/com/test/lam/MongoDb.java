package com.test.lam;

// connect to database 
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
 
public class MongoDb {

    private Configs oConfigs = Configs.getInstance();
    private static MongoClient oClient; // ????? 

    private static class MongoDbModelHolder {
        private static final MongoDb INSTANCE = new MongoDb();
    }

    public static MongoDb getInstance() {
        return MongoDbModelHolder.INSTANCE;
    }

    public MongoClient getClient() {
        return oClient;
    }

    public void createIndex() {
        try {
            System.out.print(oClient.getDatabase("accounts"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void connectDb() {

        // Creating a Mongo client 
        // MongoClient mongo = new MongoClient("localhost", 8080);
        oClient = new MongoClient("localhost", 27017);

        // Creating Credentials 
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", "MyTest", "123456".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database 
        MongoDatabase database = oClient.getDatabase("MyTest");

        // Creating a collection 
        System.out.println("Collection created successfully");
    }
}