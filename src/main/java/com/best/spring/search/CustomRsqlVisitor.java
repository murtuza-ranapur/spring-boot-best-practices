package com.best.spring.search;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;

public class CustomRsqlVisitor<T> implements RSQLVisitor<Specification<T>, Void> {

  private GenericRsqlSpecBuilder<T> builder;

  public CustomRsqlVisitor() {
    builder = new GenericRsqlSpecBuilder<T>();
  }

  @Override
  public Specification<T> visit(AndNode node, Void param) {
    return builder.createSpecification(node);
  }

  @Override
  public Specification<T> visit(OrNode node, Void param) {
    return builder.createSpecification(node);
  }

  @Override
  public Specification<T> visit(ComparisonNode node, Void params) {
    return builder.createSpecification(node);
  }
}
