package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.Paciente;
import com.qualit.kirolrz.storage.service.PacientesStorageServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Pazienteak")
public class PacientesControllerImpl extends AbstractStorageController<Paciente, Long> {

    public PacientesControllerImpl(PacientesStorageServiceImpl pacientesStorageService) {
        this.storageService = pacientesStorageService;
    }
}
