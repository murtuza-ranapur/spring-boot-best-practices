package com.best.spring.service.impl;

import com.best.spring.domain.StudentCourseView;
import com.best.spring.dto.PagedResponseDTO;
import com.best.spring.dto.StudentCourseViewDTO;
import com.best.spring.mapper.StudentCourseViewMapper;
import com.best.spring.repository.StudentCourseViewRepository;
import com.best.spring.search.CustomRsqlVisitor;
import com.best.spring.service.StudentCourseViewService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class StudentCourseViewServiceImpl implements StudentCourseViewService {

  private final StudentCourseViewRepository studentCourseViewRepository;
  private final StudentCourseViewMapper mapper;
  private static final Logger logger = LoggerFactory.getLogger(StudentCourseViewServiceImpl.class);

  public StudentCourseViewServiceImpl(
      StudentCourseViewRepository studentCourseViewRepository, StudentCourseViewMapper mapper) {
    this.studentCourseViewRepository = studentCourseViewRepository;
    this.mapper = mapper;
  }

  @Override
  public PagedResponseDTO<List<StudentCourseViewDTO>> getAll(
      Integer pageNo, Integer pageSize, String sortBy, boolean isAscending, String search) {
    Sort sort = isAscending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable paging = PageRequest.of(pageNo, pageSize, sort);
    Page<StudentCourseView> pagedResult = null;
    if (StringUtils.isEmpty(search)) {
      pagedResult = studentCourseViewRepository.findAll(paging);
    } else {
      Node rootNode = new RSQLParser().parse(search);
      Specification<StudentCourseView> spec = rootNode.accept(new CustomRsqlVisitor<>());
      pagedResult = studentCourseViewRepository.findAll(spec, paging);
    }
    logger.info(
        "Total element {}, total page {}, current page {}",
        pagedResult.getTotalElements(),
        pagedResult.getTotalPages(),
        pagedResult.getNumber());
    if (pagedResult.hasContent()) {
      List<StudentCourseViewDTO> studentDTOList =
          pagedResult.getContent().stream().map(mapper::toDto).collect(Collectors.toList());
      return new PagedResponseDTO<>(
          pagedResult.getTotalElements(),
          pagedResult.getTotalPages(),
          pagedResult.getNumber(),
          studentDTOList);
    } else {
      return new PagedResponseDTO<>(0L, 0, pageNo, new ArrayList<>());
    }
  }
}
