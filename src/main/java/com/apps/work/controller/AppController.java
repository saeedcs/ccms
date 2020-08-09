package com.apps.work.controller;

import com.apps.work.model.*;
import com.apps.work.service.AppService;
import com.apps.work.service.AuthService;
import com.apps.work.service.PageService;
import com.apps.work.service.RssFeedView;
import com.apps.work.util.AssignRoles;
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
    private AuthService authService;

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
    @RequestMapping(value = "/approval", method = RequestMethod.GET)
    public String viewComments(ModelMap model) {
        try {
                List<Comment> commentList = appService.getCommentsByIsApproved(false);
                model.addAttribute("comments", commentList);

        } catch (Exception e) {
            logger.error(e);
        }
        model.addAttribute("pagesMain", appService.getPageTitles());
        return "comments-approval";
    }

    //@Secured("USER")
    @RequestMapping(value = "/approve-comment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JSONObject> approveComment(@RequestParam String commentId, @RequestParam Boolean isApproved) {
        JSONObject result = new JSONObject();
        try {
            appService.approveComment(commentId, isApproved);

        } catch (Exception e) {
            logger.error(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //@Secured("USER")
    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JSONObject> postUserSubscribe(@RequestParam String email) {
        JSONObject result = new JSONObject();
        try {
            appService.userSubscribe(email);
            result.put("subscribed", true);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    //@Secured("USER")
    @RequestMapping(value = "/assign-roles", method = RequestMethod.GET)
    public String assignRoles(ModelMap model) {
        try {
            model.addAttribute("users", authService.getAllUsers());
            model.addAttribute("roles", authService.getAllRoles());
        } catch (Exception e) {
            logger.error(e);
        }
        model.addAttribute("pagesMain", appService.getPageTitles());
        model.addAttribute("catMain", appService.getCategoryList());
        return "assign-roles";
    }

    //@Secured("USER")
    @RequestMapping(value = "/assign-roles-enabled", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JSONObject> postEnabledChange(@RequestParam String username, @RequestParam Boolean enabled) {
        JSONObject result = new JSONObject();
        try {
            User user = authService.getUserByUsername(username);
            user.setEnabled(enabled);
            authService.saveUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    //@Secured("USER")
    @RequestMapping(value = "/assign-roles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JSONObject> postRolesChange(@RequestBody AssignRoles assignRoles) {
        JSONObject result = new JSONObject();
        try {
            User user = authService.getUserByUsername(assignRoles.getUsername());
            Set<Role> roles = new HashSet<>();
            for(String role: assignRoles.getRoles()) {
                Role role1 = authService.getRoleByName(role);
                roles.add(role1);
            }
            user.setRoles(roles);
            authService.saveUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
