package com.easy.travel.internalization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class InternalizationController {

    private final Logger logger = LoggerFactory.getLogger(InternalizationController.class);

    @RequestMapping(path = "/fetch/{module_name}")
    public String getInternalizationKeys(@PathVariable(value = "module_name") String moduleName, @RequestParam("locale") String locale){
        String suffix="";
        StringBuilder sb = new StringBuilder();
        if(locale.endsWith("en.properties")) {
            suffix = "en.properties";
        }else if(locale.endsWith("cn.properties")) {
            suffix = "cn.properties";
        }

        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(moduleName + "/messages_" + suffix);
            if(inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String str = null;
                while ((str = reader.readLine()) != null) {
                    sb.append(str).append("\n");
                }
            }
        } catch (IOException e) {
            logger.error("Error while fetching properties from message files");
        }
        return sb.toString();
    }
}
