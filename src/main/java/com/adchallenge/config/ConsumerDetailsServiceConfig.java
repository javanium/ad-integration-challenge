package com.adchallenge.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDetailsServiceConfig {

  @Autowired
  private ADOauthConfig adConfig;

  @Bean
  public ConsumerDetailsService buildInMemoryConsumerDetailsService() {
    Map<String, ConsumerDetails> consumerDetailsStore = new HashMap<>();
    ConsumerDetails consumerDetails = buildConsumerDetails();
    consumerDetailsStore.put(consumerDetails.getConsumerKey(), consumerDetails);
    InMemoryConsumerDetailsService consumerDetailsService = new InMemoryConsumerDetailsService();
    consumerDetailsService.setConsumerDetailsStore(consumerDetailsStore);
    return consumerDetailsService;
  }

  private ConsumerDetails buildConsumerDetails() {
    BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
    consumerDetails.setConsumerKey(adConfig.getConsumerKey());
    consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(adConfig.getConsumerSecret()));
    consumerDetails.setRequiredToObtainAuthenticatedToken(false);
    return consumerDetails;
  }

}
