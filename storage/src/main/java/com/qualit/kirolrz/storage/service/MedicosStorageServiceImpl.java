package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.Medico;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MedicosStorageServiceImpl extends AbstractStorageService<Medico, Long> {
    @Override
    public Medico getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Collection<Medico> findAll() {
        return super.findAll();
    }

    @Override
    public Medico save(Medico object) {
        return super.save(object);
    }

    @Override
    public Medico update(Medico object) {
        return super.update(object);
    }

    @Override
    public Medico delete(Long id) {
        return super.delete(id);
    }
}
