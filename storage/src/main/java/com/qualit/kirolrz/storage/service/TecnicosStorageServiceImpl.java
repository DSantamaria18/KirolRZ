package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.TecnicoGK;
import com.qualit.kirolrz.storage.repository.TecnicosGKRepository;
import org.springframework.stereotype.Service;

@Service
public class TecnicosStorageServiceImpl extends AbstractStorageService<TecnicoGK, Long> {

    public TecnicosStorageServiceImpl(TecnicosGKRepository tecnicosGKRepository){
        this.repository = tecnicosGKRepository;
    }


   /* @Override
    public TecnicoGK getById(Long id) {
        return super.getById(id);
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
    }*/
}
