package com.qualit.kirolrz.storage.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public abstract class  AbstractStorageService<T, Long> implements StorageService<T, Long>{

    protected JpaRepository<T, Long> repository;

    @Override
    public T getById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T object) {
        return repository.save(object);
    }

    @Override
    public T update(T object) {
        return repository.save(object);
    }

    @Override
    public T delete(Long id) {
        try{
            T object = repository.getOne(id);
            repository.deleteById(id);
            return object;
        }catch (Exception ex){
            throw new NullPointerException("Id not found [id:" + id.toString() + "]");
        }
    }
}
