package com.apps.work.controller;

import com.apps.work.model.Todo;
import com.apps.work.repository.TodoRepository;
import com.apps.work.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/test")
public class TestController {
    /** {@link SessionRegistry} does not exists in unit tests */
    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping(value = "/invalidate", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam String username) {
        List<String> users = sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());
        boolean expieredAtLeastOneSession = false;
        for (final Object user  : sessionRegistry.getAllPrincipals()) {
            User actualUser = (User) user;
            List<SessionInformation> sessions = sessionRegistry.getAllSessions(user, true);
            for(SessionInformation session : sessions) {
                session.expireNow();
                sessionRegistry.removeSessionInformation(session.getSessionId());
                publisher.publishEvent(AskToExpireSessionEvent.of(session.getSessionId()));
                expieredAtLeastOneSession = true;
            }
        }

        return "list-todos";
    }

}
