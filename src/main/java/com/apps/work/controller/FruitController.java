package com.apps.work.controller;

import com.apps.work.model.Fruit;
import com.apps.work.model.Todo;
import com.apps.work.repository.TodoRepository;
import com.apps.work.service.FruitService;
import com.apps.work.service.TodoService;
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
import java.util.Calendar;
import java.util.Date;

@Controller
public class FruitController {

    @Autowired
    private FruitService service;

    @RequestMapping(value = "/fruit", method = RequestMethod.GET)
    public String saveFruit() {
        Fruit fruit = new Fruit();
        fruit.setTaste("good");
        fruit.setPurchaseDateTime(new Date(Calendar.getInstance().getTimeInMillis()));
        service.saveFruit(fruit);
        return "fruit";
    }



}
