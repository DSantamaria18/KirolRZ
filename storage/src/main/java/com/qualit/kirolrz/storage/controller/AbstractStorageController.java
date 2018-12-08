package com.qualit.kirolrz.storage.controller;


import com.qualit.kirolrz.storage.service.StorageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

public abstract class AbstractStorageController<T, Long> implements StorageController<T, Long> {

    protected StorageService<T, Long> storageService;

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public T getById(@PathVariable Long id) {
        return storageService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Collection<T> get() {
        return storageService.findAll();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public T post(T object) {
        return storageService.save(object);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public T put(@PathVariable Long identifier, T object) {
        return storageService.update(object);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public T delete(@PathVariable Long id) {
        return storageService.delete(id);
    }
}


