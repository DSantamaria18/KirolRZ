package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.CentroSalud;
import com.qualit.kirolrz.storage.repository.CentrosSaludRepository;
import org.springframework.stereotype.Service;

@Service
public class CentrosSaludStorageServiceImpl extends AbstractStorageService<CentroSalud, Long> {

    public CentrosSaludStorageServiceImpl(CentrosSaludRepository centrosSaludRepository){
        this.repository = centrosSaludRepository;
    }

   /* @Override
    public CentroSalud getById(Long id) {
        return super.getById(id);
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
    }*/
}
