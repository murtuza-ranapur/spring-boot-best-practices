package com.best.spring.search;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Transactional
public class BuildSearchIndex implements ApplicationListener<ApplicationReadyEvent> {

  private final EntityManager entityManager;

  public BuildSearchIndex(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
    try {
      fullTextEntityManager.createIndexer().startAndWait();
    } catch (InterruptedException e) {
      log.error("Error in indexing", e);
    }
  }
}
