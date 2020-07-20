package com.best.spring.search;

import com.best.spring.domain.Course;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CourseSearchService {

  private final EntityManager entityManager;

  public List<Course> searchCourses(String field, String value) {
    FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
    QueryBuilder queryBuilder =
        fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Course.class).get();
    Query courseQuery =
        queryBuilder.keyword().wildcard().onField(field).matching(value).createQuery();

    FullTextQuery fullTextQuery =
        fullTextEntityManager.createFullTextQuery(courseQuery, Course.class);
    log.info("Matching Hits:" + fullTextQuery.getResultSize());
    return fullTextQuery.getResultList();
  }
}
