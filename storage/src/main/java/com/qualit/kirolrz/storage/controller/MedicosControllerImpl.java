package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.Medico;
import com.qualit.kirolrz.storage.service.MedicosStorageServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/Medikuak")
public class MedicosControllerImpl extends AbstractStorageController<Medico, Long> {

    public MedicosControllerImpl(MedicosStorageServiceImpl medicosStorageService) {
        this.storageService = medicosStorageService;
    }

}
