package com.adchallenge.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.adchallenge.domain.entities.UserAccount;

/**
 *
 *
 */
@RepositoryRestResource
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

  UserAccount findByUuid(@Param("uuid") String uuid);

  UserAccount findByOpenId(@Param("openId") String openId);

}
