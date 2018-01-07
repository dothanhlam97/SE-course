package com.test.lam;
import static spark.Spark.*;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App 
{
    
    public static final String CONFIG_FILE_NAME = "./server.ini";
    public static void main(String[] args) {
        Configs oConfigs = Configs.getInstance();
        oConfigs.initConfigPath(CONFIG_FILE_NAME);
        System.out.println("TEST: " + oConfigs.prefs.node("global").get("server_name_ahihi", "default value"));
        //Initialization port SERVER
        setIpAddress("localhost");
        setPort(8080);

        // Set public directory
        String documentRoot = ".";
        String currDirectory = Paths.get(documentRoot).toAbsolutePath().normalize().toString(); 
        externalStaticFileLocation(currDirectory + "/public");
        
        MongoDb.connectDb();

        get(Path.Web.INDEX, IndexController.getIndex);
        get(Path.Web.SIGNUP, IndexController.getSignUp);
        get(Path.Web.LOGIN, IndexController.getLogin);
        post(Path.Web.POST_ACCOUNT, IndexController.postAccount);
        get(Path.Web.LOGIN_ACCOUNT, IndexController.loginAccount);
        get(Path.Web.POST_PROJECT_PAGE, IndexController.getPostProject);
        get(Path.Web.PROFILE, IndexController.getProfile);
        post(Path.Web.LOGOUT, IndexController.postLogOut);
        post(Path.Web.POST_PROJECT, IndexController.postProject);
        get(Path.Web.SHOW_PROJECT, IndexController.showListProject);
    }
}
