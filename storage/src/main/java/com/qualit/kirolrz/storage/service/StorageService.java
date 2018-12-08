package com.qualit.kirolrz.storage.service;

import java.util.Collection;

public interface StorageService <T, Long>{

    T getById(Long id);

    Collection<T> findAll();

    T save(T object);

    T update(T object);

    T delete(Long id);
}
