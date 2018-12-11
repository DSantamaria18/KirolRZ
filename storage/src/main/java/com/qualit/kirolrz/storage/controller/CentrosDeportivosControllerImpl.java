package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import com.qualit.kirolrz.storage.service.CentrosDeportivosStorageServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/Kiroldegiak", "/Kiroldegiak/"})
public class CentrosDeportivosControllerImpl extends AbstractStorageController<CentroDeportivo, Long> {

    public CentrosDeportivosControllerImpl(CentrosDeportivosStorageServiceImpl centrosDeportivosStorageService) {
        this.storageService = centrosDeportivosStorageService;
    }
}