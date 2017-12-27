package com.test.lam;

// connect to database 
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
 
public class MongoDb {

    private Configs oConfigs = Configs.getInstance();
    private MongoClient oClient; // ????? 

    private static class MongoDbModelHolder {
        private static final MongoDb INSTANCE = new MongoDb();
    }

    public static MongoDb getInstance() {
        return MongoDbModelHolder.INSTANCE;
    }

    public void createIndex() {
        // try {
        //     oClient.getDatabase("test");
        // } catch (Exception ex) {
        //     ex.printStackTrace();
        // }
    }

    public static void connectDb() {

        // Creating a Mongo client 
        MongoClient mongo = new MongoClient("localhost", 8080);

        // Creating Credentials 
        MongoCredential credential = MongoCredential.createCredential("sampleUser", "myDb", "password".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database 
        MongoDatabase database = mongo.getDatabase("myDb");
        System.out.println("Credentials ::" + credential);

        // create database 
        MongoDb.getInstance().createIndex(); 
    }
}