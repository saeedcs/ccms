package com.apps.work.controller;

import com.apps.work.model.Page;
import com.apps.work.model.Todo;
import com.apps.work.repository.TodoRepository;
import com.apps.work.service.PageService;
import com.apps.work.service.TodoService;
import net.minidev.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/page")
public class PageController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private PageService pageService;

    //@Secured("USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderPageList(ModelMap model) {
        try {
            model.addAttribute("pages", pageService.getPageList());
        } catch(Exception e) {
            logger.error(e);
        }
        return "page";
    }

    //@Secured("USER")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewPage(ModelMap model, @RequestParam String id) {
        try {
            Optional<Page> pageOptional = pageService.getPage(Integer.parseInt(id));
            if (pageOptional.isPresent()) {
                model.addAttribute("page", pageOptional.get());
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return "view-page";
    }

    //@Secured("USER")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPage(ModelMap model, @RequestParam(required=false) String id) {
        try {
            if(id != null && !id.equals("")) {
                Optional<Page> pageOptional = pageService.getPage(Integer.parseInt(id));
                if (pageOptional.isPresent()) {
                    model.addAttribute("page", pageOptional.get());
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return "create-page";
    }

    //@Secured("USER")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JSONObject> postCreatePage(ModelMap model, @RequestBody(required = false) Page page) {
        JSONObject result = new JSONObject();
        try {



        } catch (Exception e) {
            logger.error(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //@Secured("USER")
    @RequestMapping(value = "/check-seo-uri", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> checkSeoUri(ModelMap model, @RequestParam String seoUri, @RequestParam(required = false) String id) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        try {
            if(seoUri != null && !seoUri.equals("")) {
                if(id == null || id.equals("")) {
                    id = new String("0");
                }
                Optional<Page> pageOptional = Optional.ofNullable(pageService.findBySeoUri(seoUri, id));
                if (pageOptional.isPresent()) {
                    resultMap.put("exists", true);
                    return resultMap;
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return resultMap;
    }

}
