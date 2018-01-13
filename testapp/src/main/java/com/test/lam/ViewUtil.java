package com.test.lam;

import spark.Request;
import spark.Response;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.*;
import org.apache.velocity.app.*;
import com.google.gson.Gson;

public class ViewUtil {
    // singleton 
    private static Configs oConfigs = Configs.getInstance();

    //Add UTF-8 html content
    // parse HTML
    // sData = html 
    public static String sendUtf8HtmlContent(Request request, Response response, String sData) {
        try {
            response.header("Server", "servername");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response.header("Content-Type", "text/html; charset=UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sData;
    }

    // public static String render 
    public static String render(Request request, Map<String, Object> model, String templatePath) {
        // return new ModelAndView(model, templatePath);
        return strictVelocityEngine().render(new ModelAndView(model, templatePath));
    }

    private static VelocityTemplateEngine strictVelocityEngine() {
        VelocityEngine configuredEngine = new VelocityEngine();
        configuredEngine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
        configuredEngine.setProperty(VelocityEngine.RUNTIME_LOG, "logs/velocity.log");
        configuredEngine.setProperty("resource.loader", "class");
        configuredEngine.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        configuredEngine.setProperty("input.encoding", "UTF-8");
        configuredEngine.setProperty("parser.pool.size", 32);
        return new VelocityTemplateEngine(configuredEngine);
    }

    public static String sendJsonContent(Request request, Response response, Map<String, Object> model) {
        String jsonData = new Gson().toJson(model);
        Configs oConfigs = Configs.getInstance();
        response.header("Server", oConfigs.prefs.node("global").get("server_name", "Freelancer"));
        response.header("Content-Type", "application/json");
        response.header("Cache-Control", "private, max-age=3600");
        return jsonData;
    }
}