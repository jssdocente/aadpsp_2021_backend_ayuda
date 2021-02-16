package com.iesvi.shared.domain.repos;

import org.springframework.stereotype.Repository;

public interface GenericRepo<T,K> {

    T findOne(K numero);
    Iterable<T> findAll();
    T save(T entidad);
    Boolean delete(K entidad);
}
