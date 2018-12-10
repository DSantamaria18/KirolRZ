package com.qualit.kirolrz.storage.service;

import com.qualit.kirolrz.storage.entity.Medico;
import com.qualit.kirolrz.storage.repository.MedicosRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MedicosStorageServiceImpl extends AbstractStorageService<Medico, Long> {

    public MedicosStorageServiceImpl(MedicosRepository medicosRepository){
        this.repository = medicosRepository;
    }

   public Collection<Medico> findAllByNombre(String nombre){
        return ((MedicosRepository) repository).findAllByNombre(nombre);
   }

   public Collection<Medico> findAllByNss(Long nss){
       return ((MedicosRepository) repository).findAllByNss(nss);
   }
}
