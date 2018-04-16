package com.mynt.core.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.mynt.core.jpa.model.BaseEntity;

@NoRepositoryBean
public interface MyntJpaService<E extends BaseEntity>
    extends JpaRepository<E, Long>, QueryDslPredicateExecutor<E> {

}
