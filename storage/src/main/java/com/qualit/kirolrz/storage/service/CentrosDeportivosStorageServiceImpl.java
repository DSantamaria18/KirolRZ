package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import com.qualit.kirolrz.storage.repository.CentrosDeportivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentrosDeportivosStorageServiceImpl extends AbstractStorageService<CentroDeportivo, Long> {

    public CentrosDeportivosStorageServiceImpl(CentrosDeportivosRepository centrosDeportivosRepository){
        this.repository = centrosDeportivosRepository;
    }

   /* @Override
    public CentroDeportivo getById(Long id) {
        return super.getById(id);
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
    }*/
}
