package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.CentroSalud;
import com.qualit.kirolrz.storage.service.CentrosSaludStorageServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/OsasunZentroak")
public class CentrosSaludControllerImpl extends AbstractStorageController<CentroSalud, Long> {

    public CentrosSaludControllerImpl(CentrosSaludStorageServiceImpl centrosSaludStorageService){
        this.storageService = centrosSaludStorageService;
    }
}
