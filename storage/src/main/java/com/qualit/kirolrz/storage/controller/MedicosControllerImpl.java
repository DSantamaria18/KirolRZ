package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.Medico;
import com.qualit.kirolrz.storage.service.MedicosStorageServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "Medikuak")
public class MedicosControllerImpl extends AbstractStorageController<Medico, Long> {

    public MedicosControllerImpl(MedicosStorageServiceImpl medicosStorageService) {
        this.storageService = medicosStorageService;
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Medico getById(@PathVariable Long id) {
        return storageService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Collection<Medico> get() {
        return storageService.findAll();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Medico post(Medico medico) {
        return storageService.save(medico);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Medico put(@PathVariable Long id, Medico medico) {
        return storageService.update(medico);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Medico delete(@PathVariable Long id) {
        return storageService.delete(id);
    }
}
