package com.adchallenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.adchallenge.web.SubscriptionNotificationController;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  OAuthProviderProcessingFilter appDirectProcessingFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers(SubscriptionNotificationController.INTEGRATION_PATH).authenticated().anyRequest()
        .permitAll();
    http.addFilterAfter(appDirectProcessingFilter, BasicAuthenticationFilter.class);
  }
}