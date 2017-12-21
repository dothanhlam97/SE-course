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
        try {
            System.out.println("LUL LAM");
            get("/", (req, res) -> "Hello ahihi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
