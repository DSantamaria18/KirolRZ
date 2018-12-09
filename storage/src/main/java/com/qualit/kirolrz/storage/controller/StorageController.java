package com.qualit.kirolrz.storage.controller;

import java.util.Collection;

public interface StorageController<T, Long> {
    T getById(Long id);

    Collection<T> findAll();

    T save(T object);

    T update(Long identifier, T object);

    T delete(Long identifier);
}
