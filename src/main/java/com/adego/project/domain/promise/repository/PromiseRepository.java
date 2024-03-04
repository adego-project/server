package com.adego.project.domain.promise.repository;

import com.adego.project.domain.promise.Promise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromiseRepository extends JpaRepository<Promise, Long> {

}
