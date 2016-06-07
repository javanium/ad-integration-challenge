package com.adchallenge.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemorySelfCleaningProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;

import com.adchallenge.security.CustomResourceProcessingFilter;

@Configuration
@ComponentScan
public class BeanConfiguration extends BaseProtectedResourceDetails {

  @Value("${oauth.consumerKey}")
  private String oauthConsumerKey;

  @Value("${oauth.consumerSecret}")
  private String oauthConsumerSecret;

  @Bean
  public OAuthRestTemplate appDirectRestTemplate() {
    BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
    resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(oauthConsumerSecret));
    resourceDetails.setConsumerKey(oauthConsumerKey);
    return new OAuthRestTemplate(resourceDetails);
  }

  @Bean
  OAuthProviderProcessingFilter appDirectProcessingFilter() {
    CustomResourceProcessingFilter filter = new CustomResourceProcessingFilter();
    filter.setFilterProcessesUrl("/subscription_notifications");
    filter.setTokenServices(providerTokenServices());
    filter.setConsumerDetailsService(consumerDetailsService());
    return filter;
  }

  @Bean
  public ConsumerDetailsService consumerDetailsService() {
    InMemoryConsumerDetailsService consumerDetailsService = new InMemoryConsumerDetailsService();

    BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
    consumerDetails.setConsumerKey(oauthConsumerKey);
    consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(oauthConsumerSecret));
    consumerDetails.setRequiredToObtainAuthenticatedToken(false);

    Map<String, BaseConsumerDetails> consumerDetailsStore = new HashMap<>();
    consumerDetailsStore.put(oauthConsumerKey, consumerDetails);
    consumerDetailsService.setConsumerDetailsStore(consumerDetailsStore);
    return consumerDetailsService;
  }

  @Bean
  public OAuthProviderTokenServices providerTokenServices() {
    return new InMemorySelfCleaningProviderTokenServices();
  }
}