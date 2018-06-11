package com.efs.core.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.efs.core.jpa.model.BaseEntity;

@NoRepositoryBean
public interface ExistJpaService<E extends BaseEntity>
    extends JpaRepository<E, Long>, QuerydslPredicateExecutor<E> {

}
