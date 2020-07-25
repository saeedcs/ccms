package com.apps.work.controller;

import com.apps.work.model.Page;
import com.apps.work.model.Role;
import com.apps.work.model.User;
import com.apps.work.service.AuthService;
import com.apps.work.service.PageService;
import com.apps.work.util.AppConstants;
import com.apps.work.util.CcmsUtil;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    //@Secured("USER")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderAuth(ModelMap model) {
        try {
            model.addAttribute("isLoggedIn", null);
        } catch(Exception e) {
            logger.error(e);
        }
        return "auth/auth";
    }

    //@Secured("USER")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        try {

        } catch (Exception e) {
            logger.error(e);
        }
        return "auth/register";
    }

    //@Secured("USER")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> registerPost(ModelMap model, @RequestParam String email,
                                             @RequestParam String password,
                                             @RequestParam String confirmPassword) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        if(CcmsUtil.isValidEmail(email) && CcmsUtil.isValidPasswords(password, confirmPassword)) {
            try {
                User user = new User();
                user.setUsername(email);
                user.setPassword(passwordEncoder.encode(password));
                user.setEnabled(true);
                Role role = authService.getRoleByName(AppConstants.ROLE_DEFAULT);
                user.setRoles(new HashSet<Role>());
                user.getRoles().add(role);
                Optional<User> pageOptional = Optional.ofNullable(authService.saveUser(user));
                if (pageOptional.isPresent()) {
                    resultMap.put(AppConstants.REGISTERED_KEY, true);
                    return resultMap;
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
        resultMap.put(AppConstants.REGISTERED_KEY, false);
        return resultMap;
    }

    //@Secured("USER")
    @RequestMapping(value = "/user-exists", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> checkUserExists(ModelMap model, @RequestParam String username) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        try {
            Optional<User> pageOptional = Optional.ofNullable(authService.checkUserExists(username));
            if (pageOptional.isPresent()) {
                resultMap.put(AppConstants.USER_EXISTS_KEY, true);
                return resultMap;
            }
        } catch (Exception e) {
            logger.error(e);
        }

        resultMap.put(AppConstants.USER_EXISTS_KEY, false);
        return resultMap;
    }

    //@Secured("USER")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        try {

        } catch (Exception e) {
            logger.error(e);
        }
        return "auth/login";
    }

    //@Secured("USER")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> loginPost(ModelMap model, @RequestParam String email, @RequestParam String password) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        try {
            Optional<User> pageOptional = Optional.ofNullable(authService.checkUserExists(email));
            if (pageOptional.isPresent()) {
                User user = pageOptional.get();
                if(passwordEncoder.matches(password, user.getPassword())) {
                    resultMap.put(AppConstants.LOGIN_KEY, true);
                    return resultMap;
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }

        resultMap.put(AppConstants.LOGIN_KEY, false);
        return resultMap;
    }

    //@Secured("USER")
    @RequestMapping(value = "/forget", method = RequestMethod.GET)
    public String forget(ModelMap model) {
        try {

        } catch (Exception e) {
            logger.error(e);
        }
        return "auth/forget";
    }

    //@Secured("USER")
    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> forgetPost(ModelMap model, @RequestParam String email) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            Optional<User> pageOptional = Optional.ofNullable(authService.checkUserExists(email));
            if (pageOptional.isPresent()) {
                User user = pageOptional.get();
                String title = messageSource.getMessage("ccms.title", null,
                        Locale.getDefault());
                String generatedString = RandomStringUtils.randomAlphanumeric(10);
                String link = "http://localhost:8080/auth/reset?code=" + generatedString; //Fix hardcoded string
                resultMap.put(AppConstants.MESSAGE_KEY, messageSource.getMessage("forget.message",
                        new Object[]{title, link},
                        Locale.getDefault()));
                return new ResponseEntity<>(resultMap, HttpStatus.OK);

            }
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resultMap.put(AppConstants.MESSAGE_KEY, "");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
