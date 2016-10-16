/*
 * TWIM Copyright (c) 2016
 *
 * This document is the property of TWIM, you cannot copy or reproduce this without authorization.
 */

package io.twim.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.io.Serializable;
import java.util.*;


/**
 * This entity allows us to manage application information (Cliento to the service)
 * Created by christmo on 29/9/16.
 */
@Table(name = "applications_by_client")
public class Client implements Serializable {
    private static final long serialVersionUID = -5550840247125850922L;

    @PartitionKey
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "client_secret")
    private String clientSecret;
    @Column(name = "application_name")
    private String applicationName;
    @Column(name = "application_id")
    private UUID applicationId;
    @Column(name = "application_logo_uri")
    private String applicationLogoUri;
    @Column(name = "application_description")
    private String applicationDescription;

    private boolean confidential;
    @Column(name = "client_ip_address")
    private String clientIpAddress;

    @Column(name = "allowed_grant_types")
    private List<String> allowedGrantTypes;
    @Column(name = "registered_scopes")
    private List<String> registeredScopes;
    @Column(name = "redirect_uris")
    private List<String> redirectUris;
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "resource_owner_subject")
    private UserSubject resourceOwnerSubject;

    private UserSubject subject;

    private Map<String, String> properties;
    @Column(name = "application_web_uri")
    private String applicationWebUri;
    //private List<String> applicationCertificates;
    //private List<String> registeredAudiences;

    public Client() {
        //this.applicationCertificates = new LinkedList();
        this.redirectUris = new LinkedList();
        this.allowedGrantTypes = new LinkedList();
        this.registeredScopes = new LinkedList();
        //this.resourceOwnerSubject = new HashMap<>();
        //this.registeredAudiences = new LinkedList();
        this.properties = new HashMap();
    }

    public Client(String clientId, String clientSecret, boolean confidential) {
        //this.applicationCertificates = new LinkedList();
        //this.registeredAudiences = new LinkedList();
        this.redirectUris = new LinkedList();
        this.allowedGrantTypes = new LinkedList();
        this.registeredScopes = new LinkedList();
        this.properties = new HashMap();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.confidential = confidential;
    }

    public Client(String clientId, String clientSecret, boolean confidential, String applicationName, String applicationWebUri) {
        this(clientId, clientSecret, confidential);
        this.applicationName = applicationName;
        this.applicationWebUri = applicationWebUri;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientIpAddress() {
        return this.clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return this.applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public UUID getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(UUID applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationWebUri() {
        return this.applicationWebUri;
    }

    public void setApplicationWebUri(String applicationWebUri) {
        this.applicationWebUri = applicationWebUri;
    }

    public String getApplicationLogoUri() {
        return this.applicationLogoUri;
    }

    public void setApplicationLogoUri(String applicationLogoUri) {
        this.applicationLogoUri = applicationLogoUri;
    }

    //public List<String> getApplicationCertificates() {
    //    return this.applicationCertificates;
    //}

    //public void setApplicationCertificates(List<String> applicationCertificates) {
    //    this.applicationCertificates = applicationCertificates;
    //}

    public List<String> getRedirectUris() {
        return this.redirectUris;
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public boolean isConfidential() {
        return this.confidential;
    }

    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }

    public List<String> getAllowedGrantTypes() {
        return this.allowedGrantTypes;
    }

    public void setAllowedGrantTypes(List<String> allowedGrantTypes) {
        this.allowedGrantTypes = allowedGrantTypes;
    }

    public List<String> getRegisteredScopes() {
        return this.registeredScopes;
    }

    public void setRegisteredScopes(List<String> registeredScopes) {
        this.registeredScopes = registeredScopes;
    }

    //public List<String> getRegisteredAudiences() {
    //    return this.registeredAudiences;
    //}

    //public void setRegisteredAudiences(List<String> registeredAudiences) {
    //    this.registeredAudiences = registeredAudiences;
    //}

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public UserSubject getSubject() {
        return this.subject;
    }

    public void setSubject(UserSubject subject) {
        this.subject = subject;
    }

    public UserSubject getResourceOwnerSubject() {
        return this.resourceOwnerSubject;
    }

    public void setResourceOwnerSubject(UserSubject resourceOwnerSubject) {
        this.resourceOwnerSubject = resourceOwnerSubject;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationdate) {
        this.creationDate = creationdate;
    }

}
