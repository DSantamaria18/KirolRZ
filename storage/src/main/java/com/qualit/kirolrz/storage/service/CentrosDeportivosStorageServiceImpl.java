package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import com.qualit.kirolrz.storage.repository.CentrosDeportivosRepository;
import org.springframework.stereotype.Service;

@Service
public class CentrosDeportivosStorageServiceImpl extends AbstractStorageService<CentroDeportivo, Long> {

    public CentrosDeportivosStorageServiceImpl(CentrosDeportivosRepository centrosDeportivosRepository){
        this.repository = centrosDeportivosRepository;
    }

}
