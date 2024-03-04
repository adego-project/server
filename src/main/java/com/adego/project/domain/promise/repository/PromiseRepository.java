package com.adego.project.domain.promise.repository;

import com.adego.project.domain.promise.Promise;
import com.adego.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromiseRepository extends JpaRepository<Promise, Long> {

  Promise findByPromiseName(String promiseName);

  @Query("SELECT p FROM Promise p JOIN FETCH p.user")
  List<Promise> findAllByUser();
}
