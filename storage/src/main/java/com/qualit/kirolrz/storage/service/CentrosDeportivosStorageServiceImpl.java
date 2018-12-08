package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CentrosDeportivosStorageServiceImpl extends AbstractStorageService<CentroDeportivo, Long> {
    @Override
    public CentroDeportivo getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Collection<CentroDeportivo> findAll() {
        return super.findAll();
    }

    @Override
    public CentroDeportivo save(CentroDeportivo object) {
        return super.save(object);
    }

    @Override
    public CentroDeportivo update(CentroDeportivo object) {
        return super.update(object);
    }

    @Override
    public CentroDeportivo delete(Long id) {
        return super.delete(id);
    }
}
