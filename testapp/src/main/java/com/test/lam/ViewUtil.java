package com.test.lam;

import spark.Request;
import spark.Response;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.*;
import org.apache.velocity.app.*;

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
        // configuredEngine.setProperty("userdirective",
        //         "com.googlecode.htmlcompressor.velocity.HtmlCompressorDirective,"
        //                 + "com.googlecode.htmlcompressor.velocity.XmlCompressorDirective,"
        //                 + "com.googlecode.htmlcompressor.velocity.JavaScriptCompressorDirective,"
        //                 + "com.googlecode.htmlcompressor.velocity.CssCompressorDirective");
        configuredEngine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
        configuredEngine.setProperty(VelocityEngine.RUNTIME_LOG, "logs/velocity.log");
        configuredEngine.setProperty("resource.loader", "class");
        configuredEngine.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // configuredEngine.setProperty("velocimacro.library", "VM_global_library.vm");
        configuredEngine.setProperty("input.encoding", "UTF-8");
        configuredEngine.setProperty("parser.pool.size", 32);
        // System.out.print(oConfigs.prefs.node("global"));
        // if (oConfigs.prefs.node("global").getBoolean("debug", false) == true) {
        //     configuredEngine.setProperty("velocimacro.library.autoreload", "true");
        //     configuredEngine.setProperty("runtime.references.strict", true);
        //     configuredEngine.setProperty("file.resource.loader.cache", false);
        // } else {
        //     configuredEngine.setProperty("classpath.resource.loader.cache", "true");
        //     configuredEngine.setProperty("velocimacro.library.autoreload", "false");
        //     configuredEngine.setProperty("file.resource.loader.cache", true);
        //     configuredEngine.setProperty("file.resource.loader.modificationCheckInterval", -1);
        // }
        return new VelocityTemplateEngine(configuredEngine);
    }
}