package com.test.lam;

import spark.Request;
import spark.Response;
import spark.Route;
import java.util.Map;
import java.util.HashMap;

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
}