package com.best.spring.service;

import java.util.List;

public interface CrudService<DTO, IdType> {

  List<DTO> getAll();

  DTO get(IdType id);

  DTO add(DTO dto);

  DTO update(DTO dto);

  void delete(IdType id);
}
