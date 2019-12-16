package com.best.spring.service;

import com.best.spring.dto.PagedResponseDTO;
import com.best.spring.dto.StudentCourseViewDTO;
import java.util.List;

public interface StudentCourseViewService {

  PagedResponseDTO<List<StudentCourseViewDTO>> getAll(
      Integer pageNo, Integer pageSize, String sortBy, boolean isAscending, String search);
}
