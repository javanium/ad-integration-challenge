package com.adchallenge.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemorySelfCleaningProviderTokenServices;
import org.springframework.security.web.session.ConcurrentSessionFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private ConsumerDetailsService consumerDetailsService;

  @Autowired
  private ADOauthConfig adConfig;

  private static class OAuthProviderFilter extends OAuthProviderProcessingFilter {

    @Override
    protected void onValidSignature(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {
      chain.doFilter(request, response);
    }

  }

  @Override
  public void configure(HttpSecurity http) throws Exception {

    OAuthProviderFilter filter = new OAuthProviderFilter();
    filter.setFilterProcessesUrl("/subscription_notifications");
    filter.setTokenServices(new InMemorySelfCleaningProviderTokenServices());
    filter.setConsumerDetailsService(consumerDetailsService);
    filter.afterPropertiesSet();

    // setup
    http.antMatcher("/subscription_notifications").authorizeRequests().anyRequest().authenticated().and()
        .addFilterAfter(filter, ConcurrentSessionFilter.class);
  }

}