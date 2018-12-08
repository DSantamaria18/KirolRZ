package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.CentroSalud;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CentrosSaludStorageServiceImpl extends AbstractStorageService<CentroSalud, Long> {
    @Override
    public CentroSalud getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Collection<CentroSalud> findAll() {
        return super.findAll();
    }

    @Override
    public CentroSalud save(CentroSalud object) {
        return super.save(object);
    }

    @Override
    public CentroSalud update(CentroSalud object) {
        return super.update(object);
    }

    @Override
    public CentroSalud delete(Long id) {
        return super.delete(id);
    }
}
