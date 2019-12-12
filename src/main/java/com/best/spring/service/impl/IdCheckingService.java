package com.best.spring.service.impl;

import java.lang.reflect.ParameterizedType;
import org.springframework.data.repository.CrudRepository;

public class IdCheckingService<Domain, IdType> {

  private final CrudRepository<Domain, IdType> repository;

  public IdCheckingService(CrudRepository<Domain, IdType> repository) {
    this.repository = repository;
  }

  protected Domain getIfExistById(IdType id) {
    String entity =
        ((Class<Domain>)
                ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0])
            .getSimpleName();
    return repository
        .findById(id)
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    String.format("'%s' with id %s doesn't exist", entity, id)));
  }
}
