package com.esimorp.shs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esimorp.shs.handler.HandlerFactory;
import com.esimorp.shs.handler.HttpHandler;
import com.esimorp.shs.http.HttpServer;
import com.esimorp.shs.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException {
        String configString = FileUtil.readFile(new File("./shs_config.json"));
        JSONObject config = JSON.parseObject(configString);
        List<HttpHandler> handlers = HandlerFactory.initHandlersWithServersJSON(config.getString("Servers"));

        try {
            HttpServer server = new HttpServer(8081, handlers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
