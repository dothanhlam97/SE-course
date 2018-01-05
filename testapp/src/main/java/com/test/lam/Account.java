package com.test.lam;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import org.bson.Document;

public class Account { 
    public String Name;
    public String _id;
    public String ID;
    public String Email;
    public String Password;
    public String isHire;
    public String isWork;
    public Map<String, String> Options;
    public Integer CreatedDate;
    public String Avatar;
    public Integer Permission;
    public Integer UpdatedDate;    

    public Account() {
        Options = new HashMap<>();
    }

    // public Account(String Name_, String Password_, String ID_, String Email_) {
    //     setName(Name_);
    //     setPassword(Password_);
    //     setID(ID_);
    //     setEmail(Email_);
    //     setCreatedDate(CommonUtil.getCurrentUnixTimestampAsInt());
    //     Options = new HashMap<>();
    // }

    public Account(String Email_, String Password_, String isHire_, String isWork_) {
        setPassword(Password_);
        setEmail(Email_);
        setHire(isHire_);
        setWork(isWork_);
    }

    public Account(Account oldAccount) {
        setName(oldAccount.Name);
        setPassword(oldAccount.Password);
        setID(oldAccount.ID);
        setEmail(oldAccount.Email);
        setCreatedDate(oldAccount.CreatedDate);
        setPermission(oldAccount.Permission);
        Options = oldAccount.Options;
    }

    public String getName() {
        return Name;
    }

    public String getAvatar() {
        return Avatar;
    }

    public String getID() {
        return ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setName(String Name_) {
        Name = Name_;
    }

    public void setHire(String isHire_) {
        isHire = isHire_;
    }

    public void setWork(String isWork_) {
        isWork = isWork_;
    }

    public String getHire() {
        return isHire;
    }

    public String getWork() {
        return isWork;
    }

    public void setCreatedDate(int CreatedDate_) {
        CreatedDate = CreatedDate_;
        UpdatedDate = CreatedDate_;
    }

    public void setPassword(String Password_) {
        Password = Password_;
    }

    public void setID(String ID_) {
        ID = ID_;
        _id = ID_;
    }

    public void setPermission(Integer Permission_) {
        Permission = Permission_;
    }

    public void setEmail(String Email_) {
        Email = Email_;
    }

    public String toString() {
        Gson gson = new Gson();
        String jsonData = gson.toJson(this);
        return jsonData;
    }

    public static java.lang.String getCurrentAccount() {
        MongoDatabase database = MongoDb.getInstance().getClient().getDatabase("MyTest");
        MongoCollection<Document> collection = database.getCollection("curentAccount");
        Document arrDocument = collection.find().first();
        if (arrDocument == null) {
            return "";
        } else {
            return arrDocument.getString("Email");
        }
    }

    public static void setCurrentAccount(Account oAccount) {
        MongoDatabase database = MongoDb.getInstance().getClient().getDatabase("MyTest");
        MongoCollection<Document> collection = database.getCollection("curentAccount");
        Document document = Document.parse(oAccount.toString());
        collection.insertOne(document);
    }
}

