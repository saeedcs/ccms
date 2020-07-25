package com.apps.work.controller;

import com.apps.work.model.ForgetPassword;
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
                ForgetPassword forgetPassword = new ForgetPassword();
                forgetPassword.setUsername(email);
                forgetPassword.setCode(generatedString);
                forgetPassword.setUsed(false);
                forgetPassword.setCreatedOn(new Date(Calendar.getInstance().getTimeInMillis()));
                authService.saveForgetCode(forgetPassword);
                String link = "http://localhost:8080/auth/reset?code=" + generatedString; //Fix hardcoded string
                resultMap.put(AppConstants.MESSAGE_KEY, messageSource.getMessage("forget.message",
                        new Object[]{title, link}, Locale.getDefault()));
                return new ResponseEntity<>(resultMap, HttpStatus.OK);

            }
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resultMap.put(AppConstants.MESSAGE_KEY, "");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    //@Secured("USER")
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String reset(ModelMap model, @RequestParam String code) {
        String template = "auth/reset";
        if(code != null) {
            try {
                ForgetPassword forgetPassword = authService.findByCode(code);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR_OF_DAY, -2);
                if(forgetPassword != null && forgetPassword.getCreatedOn().getTime() > calendar.getTimeInMillis()) {
                    model.addAttribute("username", forgetPassword.getUsername());
                    return "auth/reset";
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return "auth/forget";
    }

    //@Secured("USER")
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> resetPost(@RequestParam String code, @RequestParam String password,
                                                         @RequestParam String confirmPassword) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            Optional<ForgetPassword> pageOptional = Optional.ofNullable(authService.findByCode(code));
            if (pageOptional.isPresent()) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR_OF_DAY, -2);
                ForgetPassword forgetPassword = pageOptional.get();
                if(forgetPassword.getCreatedOn().getTime() > calendar.getTimeInMillis() && CcmsUtil.isValidPasswords(password, confirmPassword)) {
                    User user = authService.getUserByUsername(forgetPassword.getUsername());
                    if(user != null) {
                        user.setPassword(password);
                        authService.saveUser(user);
                        forgetPassword.setUsed(true);
                        authService.saveForgetCode(forgetPassword);
                        resultMap.put(AppConstants.RESET_KEY, "login");
                        return new ResponseEntity<>(resultMap, HttpStatus.OK);
                    }
                }

            }
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resultMap.put(AppConstants.RESET_KEY, "");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
