package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.TecnicoGK;
import com.qualit.kirolrz.storage.service.TecnicosStorageServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/Teknikariak")
public class TecnicosControllerImpl extends AbstractStorageController<TecnicoGK, Long> {

    public TecnicosControllerImpl(TecnicosStorageServiceImpl tecnicosStorageService){
        this.storageService = tecnicosStorageService;
    }

}
