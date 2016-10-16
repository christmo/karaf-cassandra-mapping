/*
 * TWIM Copyright (c) 2016
 *
 * This document is the property of TWIM, you cannot copy or reproduce this without authorization.
 */

package io.twim.services.command;

import io.twim.model.Client;
import io.twim.model.UserSubject;
import io.twim.services.ClientRegistrationImpl;
import io.twim.services.Services;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Command used to test a cassandra connection
 * Created by christmo on 9/8/16.
 */
@Command(scope = "twim", name = "cassandra", description = "Cassandra Tester Cluster")
@Service
public class CassandraCommand implements Action {

    @Reference
    private Services registration;

    @Override
    public Object execute() throws Exception {
        System.out.println(registration);

        List<Client> clients = new ArrayList<>();
        Client client = new Client();
        client.setClientId("christmo");

        UserSubject us = new UserSubject("christmo");
        us.setId("id123455");
        us.setRoles(Collections.singletonList("admin"));
        client.setResourceOwnerSubject(us);

        UserSubject sub = new UserSubject("demo subject");
        sub.setId("0987654321");
        sub.setRoles(Collections.singletonList("demo"));
        client.setSubject(sub);

        client.setClientSecret("123456");
        client.setConfidential(true);
        client.setApplicationName("app-name");
        client.setApplicationWebUri("app web uri");
        client.setApplicationDescription("app description");
        client.setClientIpAddress("192.168.2.2");
        client.setApplicationLogoUri("http://demo.demo.demo");
        client.setRegisteredScopes(Collections.singletonList("update"));
        client.setProperties(Collections.singletonMap("porperty", "property"));

        ArrayList uris = new ArrayList();
        uris.add("http://localhost:8181/twim/user/show");
        uris.add("http://localhost:8181/twim/oauth/auth");
        client.setRedirectUris(uris);

        List<String> keys = new ArrayList<>();
        keys.add("read");
        keys.add("update");
        client.setAllowedGrantTypes(keys);

        clients.add(client);

        client.setApplicationId(UUID.randomUUID());
        client.setCreationDate(new Date());
        registration.saveClient(client, Client.class);

        return "hello";
    }

    public void setRegistration(Services registration) {
        this.registration = registration;
    }
}
