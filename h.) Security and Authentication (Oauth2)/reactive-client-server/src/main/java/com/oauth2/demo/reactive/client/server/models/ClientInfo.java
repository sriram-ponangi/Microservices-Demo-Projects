package com.oauth2.demo.reactive.client.server.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.security.oauth2.client.registration.keycloak")
public class ClientInfo {
    private String grantType;
    private String clientId;
    private String clientSecret;
    private String accessTokenUri;


    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
    }

    @Override
    public String toString() {
        return "\n\nClientInfo{" +
                "grantType='" + grantType + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", accessTokenUri='" + accessTokenUri + '\'' +
                '}';
    }
}
