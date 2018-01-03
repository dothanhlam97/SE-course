package com.test.lam;

import spark.Request;
import spark.Response;
import spark.Route;
import java.util.Map;
import java.util.HashMap;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

public class IndexController {
    public static Route getIndex = (Request request, Response response) -> { 
        try {
            Map<String, Object> arrData = new HashMap<String, Object>();
            arrData.put("host_url", Configs.getInstance().prefs.node("global").get("host_url", "failed to get"));
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
            collection.insertOne(document);
            arrResponse.put("redirectURL", redirectURL);
            return ViewUtil.sendJsonContent(request, response, arrResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    };
}