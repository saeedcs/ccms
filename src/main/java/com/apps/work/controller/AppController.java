package com.apps.work.controller;

import com.apps.work.model.Article;
import com.apps.work.model.Comment;
import com.apps.work.model.Page;
import com.apps.work.service.AppService;
import com.apps.work.service.PageService;
import com.apps.work.service.RssFeedView;
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
import org.springframework.web.servlet.View;

import java.util.*;

@Controller
@RequestMapping(value = "/app")
public class AppController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private PageService pageService;

    @Autowired
    private AppService appService;

    @Autowired
    private RssFeedView view;

    //@Secured("USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderAppList(ModelMap model) {
        try {
            Map<String, String> appMap = appService.getApplicationMap();
            model.addAttribute("appMap", new  TreeMap<String,String>(appMap));
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
                appService.addApplicationValue(keyValue.getKey(), keyValue.getValue());
            }
        } catch(Exception e) {
            logger.error(e);
        }
        return "app/app";
    }

    @GetMapping("/rss")
    @ResponseBody
    public View getFeed() {
        return view;
    }

    //@Secured("USER")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewComments(ModelMap model, @PathVariable Boolean isApproved) {
        try {
                List<Comment> commentList = appService.getCommentsByIsApproved(isApproved);
                model.addAttribute("comments", commentList);

        } catch (Exception e) {
            logger.error(e);
        }
        model.addAttribute("pagesMain", appService.getPageTitles());
        if(isApproved == true)
            return "view-article";
        else
            return "comments-approval";
    }

}
