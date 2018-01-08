package com.test.lam;

import spark.Request;
import spark.Response;
import spark.Route;
import java.util.Map;
import java.util.HashMap;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import org.json.simple.JSONObject;
import java.util.List;
import java.util.ArrayList;;

public class IndexController {
    public static Route getIndex = (Request request, Response response) -> { 
        try {
            Map<String, Object> arrData = new HashMap<String, Object>();
            arrData.put("host_url", Configs.getInstance().prefs.node("global").get("host_url", "failed to get"));
            String currentAccount = Account.getCurrentAccount();
            MongoDatabase database = MongoDb.getInstance().getClient().getDatabase("MyTest");
            MongoCollection<Document> collection = database.getCollection("accounts");
            Document arrDocument = collection.find(eq("Email", currentAccount)).first();
            if (arrDocument != null) {
                arrData.put("current_account", arrDocument.getString("Email"));
                arrData.put("is_hire", arrDocument.getString("isHire"));
                arrData.put("is_work", arrDocument.getString("isWork"));
            } else { 
                arrData.put("current_account", "");
                arrData.put("is_hire", "true");
                arrData.put("is_work", "true");
            }
            
            return ViewUtil.sendUtf8HtmlContent(request, response,
                    ViewUtil.render(request, arrData, Path.Template.INDEX));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    };

    public static Route getSignUp = (Request request, Response response) -> {
        try {
            Map <String, Object> arrData = new HashMap<String, Object>();
            arrData.put("host_url", Configs.getInstance().prefs.node("global").get("host_url", "fail to load"));
            String currentAccount = Account.getCurrentAccount();
            arrData.put("current_account", "");
            return ViewUtil.sendUtf8HtmlContent(request, response, 
                ViewUtil.render(request, arrData, Path.Template.SIGNUP));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    };

    public static Route getLogin = (Request request, Response response) -> {
        try {
            Map <String, Object> arrData = new HashMap<String, Object>();
            arrData.put("host_url", Configs.getInstance().prefs.node("global").get("host_url", "fail to load"));
            arrData.put("current_account", "");
            return ViewUtil.sendUtf8HtmlContent(request, response, 
                ViewUtil.render(request, arrData, Path.Template.LOGIN));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    };

    public static Route getPostProject = (Request request, Response response) -> {
        try {
            Map <String, Object> arrData = new HashMap<String, Object>();
            arrData.put("host_url", Configs.getInstance().prefs.node("global").get("host_url", "fail to load"));
            String currentAccount = Account.getCurrentAccount();
            arrData.put("current_account", currentAccount);
            return ViewUtil.sendUtf8HtmlContent(request, response, 
                ViewUtil.render(request, arrData, Path.Template.POST_PROJECT_PAGE));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    };

    public static Route getProfile = (Request request, Response response) -> {
        try {
            Map <String, Object> arrData = new HashMap<String, Object>();
            arrData.put("host_url", Configs.getInstance().prefs.node("global").get("host_url", "fail to load"));
            arrData.put("acc_name", Configs.getInstance().prefs.node("global").get("account_name", "fail to load"));
            arrData.put("acc_email", Configs.getInstance().prefs.node("global").get("account_email", "fail to load"));
            arrData.put("acc_passd", Configs.getInstance().prefs.node("global").get("account_passd", "fail to load"));
            arrData.put("acc_hire", Configs.getInstance().prefs.node("global").get("account_hire", "fail to load"));
            arrData.put("acc_work", Configs.getInstance().prefs.node("global").get("account_work", "fail to load"));
            String currentAccount = Account.getCurrentAccount();
            arrData.put("current_account", currentAccount);
            return ViewUtil.sendUtf8HtmlContent(request, response, 
                ViewUtil.render(request, arrData, Path.Template.PROFILE));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    };

    public static Route postAccount = (Request request, Response response) -> {
        try {
            Map <String, Object> arrResponse = new HashMap<>();
            String email = request.queryParams("email");
            String passd = request.queryParams("passd");
            String isHire = request.queryParams("isHire");
            String isWork = request.queryParams("isWork");
            String redirectURL = Configs.getInstance().prefs.node("global").get("host_url", "fail to load");
            Account oAccount = new Account(email, passd, isHire, isWork);
            MongoDatabase database = MongoDb.getInstance().getClient().getDatabase("MyTest");
            Document document = Document.parse(oAccount.toString());
            MongoCollection<Document> collection = database.getCollection("accounts");
            Document arrDocument = collection.find(eq("Email", email)).first();
            if (arrDocument != null) {
                return Configs.FAIL;
            }
            System.out.print(arrDocument);
            collection.insertOne(document);
            arrResponse.put("redirectURL", redirectURL);
            return ViewUtil.sendJsonContent(request, response, arrResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    };

    public static Route loginAccount = (Request request, Response response) -> {
        try {
            Map<String, Object> arrResponse = new HashMap<>();
            String email = request.queryParams("email");
            String passd = request.queryParams("passd");
            MongoDatabase database = MongoDb.getInstance().getClient().getDatabase("MyTest");
            MongoCollection<Document> collection = database.getCollection("accounts");
            Document arrDocument = collection.find(eq("Email", email)).first();
            
            if (arrDocument == null) {
                return Configs.WRONG_EMAIL;
            }
            System.out.print(!arrDocument.getString("Password").equals(passd));
            if (!arrDocument.getString("Password").equals(passd)) {
                return Configs.WRONG_PASS;
            }
            // set current Account 
            Account oAccount = new Account(arrDocument.getString("Email"), "", "", "");
            Account.setCurrentAccount(oAccount);
            return ViewUtil.sendJsonContent(request, response, arrResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    };

    public static Route postLogOut = (Request request, Response response) -> {
        try {
            Map<String, Object> arrResponse = new HashMap<>();
            String currentAccount = Account.getCurrentAccount();
            MongoDatabase database = MongoDb.getInstance().getClient().getDatabase("MyTest");
            MongoCollection<Document> collection = database.getCollection("accounts");
            Document arrDocument = collection.find(eq("Email", currentAccount)).first();
            String email = arrDocument.getString("Email");
            collection = database.getCollection("curentAccount");
            collection.drop();
            return ViewUtil.sendJsonContent(request, response, arrResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    };

    public static Route postProject = (Request request, Response response) -> {
        try {
            Map<String, Object> arrResponse = new HashMap<>();
            String currentAccount = Account.getCurrentAccount();
            MongoDatabase database = MongoDb.getInstance().getClient().getDatabase("MyTest");
            MongoCollection<Document> collection = database.getCollection("accounts");
            Document arrDocument = collection.find(eq("Email", currentAccount)).first();
            String email = arrDocument.getString("Email");
            collection = database.getCollection("project");
            Document document = Document.parse(
                "{\"name\": \"" + request.queryParams("name") + 
                "\",\"about\": \"" + request.queryParams("about") + 
                "\",\"requirement\": \"" + request.queryParams("requirement") + 
                "\",\"Email\": \"" + email + "\"}");
            collection.insertOne(document);
            return ViewUtil.sendJsonContent(request, response, arrResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }; 

    public static Route showListProject = (Request request, Response response) -> {
        try {
            Map<String, Object> arrData = new HashMap<String, Object>();
            arrData.put("host_url", Configs.getInstance().prefs.node("global").get("host_url", "fail to load"));
            String currentAccount = Account.getCurrentAccount();
            arrData.put("current_account", currentAccount);
            MongoDatabase database = MongoDb.getInstance().getClient().getDatabase("MyTest");
            MongoCollection<Document> collection = database.getCollection("project");
            FindIterable<Document> arrDocument = collection.find();
            List <JSONObject> project = new ArrayList<JSONObject>();
            for (Document document : arrDocument) {
                if (document != null) {
                    project.add(new JSONObject(document));
                }
            }
            arrData.put("project", project);
            return ViewUtil.sendUtf8HtmlContent(request, response,
                    ViewUtil.render(request, arrData, Path.Template.SHOW_PROJECT));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }; 


}