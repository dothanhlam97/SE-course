package com.test.lam;
import static spark.Spark.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App 
{
    public static void main(String[] args) {
        //Initialization port SERVER
        setIpAddress("localhost");
        setPort(8080);

        // get(Path.Web.INDEX, IndexController.getIndex());

        try {
            // System.out.println(Path.Web.INDEX);
            // get("/", (req, res) -> "Hello ahihi");
            get(Path.Web.INDEX, IndexController.getIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
