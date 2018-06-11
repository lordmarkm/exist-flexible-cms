package com.efs.core.jpa.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.efs.core.dto.BaseInfo;
import com.efs.core.jpa.model.BaseEntity;
import com.efs.core.jpa.rql.RsqlParserVisitor;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

/**
 *
 * @author mbmartinez, Sep 27, 2017
 *
 * @param <E> - Entity
 * @param <D> - DTO
 * @param <R> - Repo
 */
@Transactional
public abstract class ExistJpaServiceCustomImpl<E extends BaseEntity, D extends BaseInfo, R extends ExistJpaService<E>>
    extends MappingService<E, D> implements ExistJpaServiceCustom<E, D> {

    private static final Logger LOG = LoggerFactory.getLogger(ExistJpaServiceCustomImpl.class);

    @Autowired
    protected R repo;

    @Autowired
    protected TaskExecutor taskExecutor;

    public D findOneInfo(Long id) {
        return toDto(repo.findById(id).get());
    }

    public D findOneInfo(Predicate p) {
        return toDto(repo.findOne(p).get());
    }

    public D saveInfo(D dto) {
        return toDto(repo.save(toEntity(dto)));
    }

    public E saveInfoAndGetEntity(D dto) {
        return repo.save(toEntity(dto));
    }

    public List<D> saveInfo(Iterable<D> dtos) {
        return toDto(repo.saveAll(toEntity(dtos)));
    }

    public List<E> saveInfoAndGetEntity(Iterable<D> dtos) {
        return repo.saveAll(toEntity(dtos));
    }

    public Page<D> pageInfo(Pageable page) {
        Page<E> results = repo.findAll(page);
        return toPageInfo(results);
    }

    public Page<D> pageInfo(Predicate predicate, Pageable page) {
        Page<E> results = repo.findAll(predicate, page);
        return toPageInfo(results);
    }

    public Page<D> toPageInfo(Page<E> page) {
        Page<D> converted = page.map(this::toDto);
        return converted;
    }

    @Override
    public D deleteInfo(Long id) {
        Optional<E> entity = repo.findById(id);
        entity.get().setDeleted(true);
        return toDto(entity.get());
    }

    @Override
    public List<D> findAllInfo() {
        return toDto(repo.findAll());
    }

    @Override
    public List<D> findAllInfo(Predicate predicate) {
        List<E> entities = (List<E>) repo.findAll(predicate);
        return toDto(entities);
    }

    @Override
    public List<D> findAllInfo(Predicate predicate, Sort sort) {
        List<E> entities = (List<E>) repo.findAll(predicate, sort);
        return toDto(entities);
    }

    @Override
    public Page<D> rqlSearch(String term, ImmutableMap<String, Path<?>> fieldMapping, Pageable page) {
        Predicate predicate = composePredicate(term, fieldMapping);
        if (null == predicate) {
            return toPageInfo(repo.findAll(page));
        }
        return pageInfo(predicate, page);
    }

    @Override
    public Page<E> rqlSearchForEntities(String term, ImmutableMap<String, Path<?>> fieldMapping, Pageable page) {
        Predicate predicate = composePredicate(term, fieldMapping);
        if (null == predicate) {
            return repo.findAll(page);
        }
        return repo.findAll(predicate, page);
    }

    @Override
    public List<D> rqlSearch(String term, ImmutableMap<String, Path<?>> fieldMapping, OrderSpecifier<?>... sort) {
        Predicate predicate = composePredicate(term, fieldMapping);
        LOG.debug("RQL query prepared. Query={}", predicate);
        return toDto(repo.findAll(predicate, sort));
    }

    @Override
    public D saveEntityAndGetInfo(E e) {
        return toDto(repo.save(e));
    }

    @Override
    public void purgeEntity(Iterable<D> dtos) {
        repo.deleteAll(toEntity(dtos));
    }

    protected BooleanExpression composePredicate(String term, ImmutableMap<String, Path<?>> fieldMapping) {
        BooleanExpression predicate = null;

        Set<ComparisonOperator> operators = RSQLOperators.defaultOperators();
        operators.add(new ComparisonOperator("=like=", true));

        if (!StringUtils.isBlank(term)) {
            try {
                Node rootNode = new RSQLParser(operators).parse(term);
                RsqlParserVisitor visitor = new RsqlParserVisitor();
                predicate = rootNode.accept(visitor, fieldMapping);
            } catch (Exception e) {
                LOG.error("Error parsing or interpreting rql term. term={}, error={}", term, e.getMessage());
                return null;
            }
        }

        return predicate;
    }

}
