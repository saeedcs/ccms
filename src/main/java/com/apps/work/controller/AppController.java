package com.apps.work.controller;

import com.apps.work.model.Page;
import com.apps.work.service.PageService;
import com.apps.work.util.KeyValue;
import net.minidev.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/app")
public class AppController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private PageService pageService;

    //@Secured("USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderAppList(ModelMap model) {
        try {
            Map<String, String> appMap = new HashMap<>();
            appMap.put("firstkey", "value1");
            appMap.put("secondkey", "value2");
            model.addAttribute("appMap", appMap);
        } catch(Exception e) {
            logger.error(e);
        }
        return "app/app";
    }

    //@Secured("USER")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String appPost(@RequestBody List<KeyValue> keyValues) {
        try {
            for(KeyValue keyValue : keyValues) {
                System.out.println(keyValue);
            }
        } catch(Exception e) {
            logger.error(e);
        }
        return "app/app";
    }

}
