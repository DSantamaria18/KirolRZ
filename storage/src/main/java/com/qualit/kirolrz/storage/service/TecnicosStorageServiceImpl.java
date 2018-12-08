package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.TecnicoGK;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TecnicosStorageServiceImpl extends AbstractStorageService<TecnicoGK, Long> {
    @Override
    public TecnicoGK getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Collection<TecnicoGK> findAll() {
        return super.findAll();
    }

    @Override
    public TecnicoGK save(TecnicoGK object) {
        return super.save(object);
    }

    @Override
    public TecnicoGK update(TecnicoGK object) {
        return super.update(object);
    }

    @Override
    public TecnicoGK delete(Long id) {
        return super.delete(id);
    }
}
