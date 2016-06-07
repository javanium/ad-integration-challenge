package com.adchallenge.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;

public class CustomResourceProcessingFilter extends OAuthProviderProcessingFilter {

  @Override
  protected void onValidSignature(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    chain.doFilter(request, response);
  }

}