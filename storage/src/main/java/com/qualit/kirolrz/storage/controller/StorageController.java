package com.qualit.kirolrz.storage.controller;

import java.util.Collection;

public interface StorageController<T, Long> {
    T getById(Long id);

    Collection<T> get();

    T post(T object);

    T put(Long identifier, T object);

    T delete(Long identifier);
}
