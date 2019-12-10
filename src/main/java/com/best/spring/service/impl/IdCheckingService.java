package com.best.spring.service.impl;

import org.springframework.data.repository.CrudRepository;

public class IdCheckingService<Domain, Dto, IdType> {

  private final CrudRepository<Domain, IdType> repository;

  public IdCheckingService(CrudRepository<Domain, IdType> repository) {
    this.repository = repository;
  }

  protected Domain getIfExistById(IdType id) {
    return repository
        .findById(id)
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    String.format("'Student' with id %s doesn't exist", id)));
  }
}
