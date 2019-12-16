package com.best.spring.service.impl;

import com.best.spring.domain.Student;
import com.best.spring.dto.PagedResponseDTO;
import com.best.spring.dto.StudentDTO;
import com.best.spring.exception.EntityNotFoundException;
import com.best.spring.mapper.StudentMapper;
import com.best.spring.repository.StudentRepository;
import com.best.spring.search.CustomRsqlVisitor;
import com.best.spring.service.StudentService;
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
public class StudentServiceImpl extends IdCheckingService<Student, Long> implements StudentService {

  private final StudentRepository studentRepository;
  private final StudentMapper mapper;
  private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

  public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
    super(studentRepository);
    this.studentRepository = studentRepository;
    this.mapper = studentMapper;
  }

  @Override
  public List<StudentDTO> getAll() {
    return studentRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public PagedResponseDTO<List<StudentDTO>> getAll(
      Integer pageNo, Integer pageSize, String sortBy, boolean isAscending, String search) {
    Sort sort = isAscending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable paging = PageRequest.of(pageNo, pageSize, sort);
    Page<Student> pagedResult = null;
    if (StringUtils.isEmpty(search)) {
      pagedResult = studentRepository.findAll(paging);
    } else {
      Node rootNode = new RSQLParser().parse(search);
      Specification<Student> spec = rootNode.accept(new CustomRsqlVisitor<>());
      pagedResult = studentRepository.findAll(spec, paging);
    }
    logger.info(
        "Total element {}, total page {}, current page {}",
        pagedResult.getTotalElements(),
        pagedResult.getTotalPages(),
        pagedResult.getNumber());
    if (pagedResult.hasContent()) {
      List<StudentDTO> studentDTOList =
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

  @Override
  public StudentDTO get(Long id) {
    Student student = getIfExistById(id);
    return mapper.toDto(student);
  }

  @Override
  public StudentDTO add(StudentDTO studentDTO) {
    Student student = mapper.toModel(studentDTO);
    student = studentRepository.save(student);
    return mapper.toDto(student);
  }

  @Override
  public StudentDTO update(StudentDTO studentDTO) {
    getIfExistById(studentDTO.getId());
    Student studentUpdated = mapper.toModel(studentDTO);
    studentUpdated = studentRepository.save(studentUpdated);
    return mapper.toDto(studentUpdated);
  }

  @Override
  public void delete(Long id) {
    Student student = getIfExistById(id);
    studentRepository.delete(student);
  }

  @Override
  public StudentDTO findByStudentIdAndCourseId(Long studentId, Long courseId) {
    Student student =
        studentRepository
            .findByIdAndCourseId(studentId, courseId)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "entity.student.idnotpresent", studentId, courseId));
    return mapper.toDto(student);
  }
}
