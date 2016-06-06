package com.adchallenge.clients;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.adchallenge.config.ADConfig;
import com.adchallenge.dto.EventNotificationResponse;
import com.adchallenge.dto.event.Event;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;

@Component
public class ADClient {

  private static Logger LOGGER = Logger.getLogger(ADClient.class);

  @Autowired
  private ADConfig adConfig;

  private RestTemplate restTemplate = new RestTemplate();

  public Event getEventDetails(String eventUrl) {
    // OAuthConsumer consumer = new DefaultOAuthConsumer("Dummy", "secret");
    // HttpURLConnection request = (HttpURLConnection) url.openConnection();
    // consumer.sign(request);
    // request.connect();
    return restTemplate.getForObject(eventUrl, Event.class);
  }

  public void postReturnUrl(String returnUrl, EventNotificationResponse eventNotification)
      throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
    OAuthConsumer consumer = new DefaultOAuthConsumer(adConfig.getKey(), adConfig.getSecret());
    consumer.setSigningStrategy(new QueryStringSigningStrategy());
    String signedUrl = consumer.sign(returnUrl);
    LOGGER.info(signedUrl);
    restTemplate.postForObject(signedUrl, eventNotification, void.class);
  }

}
