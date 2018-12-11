package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.TecnicoGK;
import com.qualit.kirolrz.storage.repository.TecnicosGKRepository;
import org.springframework.stereotype.Service;

@Service
public class TecnicosStorageServiceImpl extends AbstractStorageService<TecnicoGK, Long> {

    public TecnicosStorageServiceImpl(TecnicosGKRepository tecnicosGKRepository){
        this.repository = tecnicosGKRepository;
    }
}
