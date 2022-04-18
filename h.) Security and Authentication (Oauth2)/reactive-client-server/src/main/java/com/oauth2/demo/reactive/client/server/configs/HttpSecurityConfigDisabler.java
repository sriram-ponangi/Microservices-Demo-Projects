package com.oauth2.demo.reactive.client.server.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class disables the spring security added to the REST endpoint of this project.
 * Since this is an oauth-client securing REST endpoints with Oauth is not required.
 * REST endpoints are secured by the resource-server project.
 */
@Configuration
public class HttpSecurityConfigDisabler extends WebSecurityConfigurerAdapter {

    public HttpSecurityConfigDisabler() {
        super(true); // Disable defaults
    }

    @Override
    protected void configure(HttpSecurity http) {
        // Do nothing, this is just overriding
        // the default behavior in WebSecurityConfigurerAdapter

    }
}