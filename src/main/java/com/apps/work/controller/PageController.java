package com.apps.work.controller;

import com.apps.work.model.Page;
import com.apps.work.model.Todo;
import com.apps.work.repository.TodoRepository;
import com.apps.work.service.PageService;
import com.apps.work.service.TodoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
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

}
