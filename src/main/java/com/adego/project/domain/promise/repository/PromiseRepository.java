package com.adego.project.domain.promise.repository;

import com.adego.project.domain.promise.Promise;
import com.adego.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromiseRepository extends JpaRepository<Promise, Long> {

  Promise findByPromiseName(String promiseName);

  @Query("SELECT u.name FROM Promise p JOIN p.user u WHERE p.id = u.id")
  String findUserNameByUserId(User user);
}
