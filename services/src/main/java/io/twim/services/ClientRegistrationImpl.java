/*
 * TWIM Copyright (c) 2016
 *
 * This document is the property of TWIM, you cannot copy or reproduce this without authorization.
 */

package io.twim.services;

import io.twim.interfaces.CRUD;
import io.twim.interfaces.Services;
import io.twim.model.Client;
import io.twim.model.UserSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by christmo on 8/9/16.
 */
public class ClientRegistrationImpl implements Services {

    private static final Logger log = LoggerFactory.getLogger(ClientRegistrationImpl.class);

    private CRUD services;

    public ClientRegistrationImpl() {
    }

    public void save() {
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
        try {
            client.setApplicationId(UUID.randomUUID());
            client.setCreationDate(new Date());
            services.save(client, Client.class);
        } catch (Exception e) {
            log.error("Error saving", e);
        }

    }

    public void setServices(CRUD services) {
        this.services = services;
    }

    @Override
    public void saveClient(Client client, Class<Client> clientClass) {
        System.out.println(services);
        services.save(client, clientClass);
    }
}
