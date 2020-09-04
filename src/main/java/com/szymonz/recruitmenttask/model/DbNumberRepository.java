package com.szymonz.recruitmenttask.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface DbNumberRepository extends JpaRepository<DbNumber, Integer> {
}
