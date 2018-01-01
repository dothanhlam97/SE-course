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

        get(Path.Web.INDEX, IndexController.getIndex);
        get(Path.Web.SIGNUP, IndexController.getSignUp);
        MongoDb.connectDb();
    }
}
