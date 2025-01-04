package com.service;

import java.util.Optional;

public interface IService<T> {
    Iterable<T> findAll();

    T save(T t);

    Optional<T> findById(Long id);

    void remove(Long id);
}
