package com.apps.work.controller;

import com.apps.work.config.AskToExpireSessionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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
