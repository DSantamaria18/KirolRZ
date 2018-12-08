/*
package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.Paciente;
import com.qualit.kirolrz.storage.service.PacientesStorageServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/Pazienteak")
public class PacientesControllerImpl extends AbstractStorageController<Paciente, Long> {

    public PacientesControllerImpl(PacientesStorageServiceImpl pacientesStorageService){
        this.storageService = pacientesStorageService;
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Paciente getById (@PathVariable Long id) {
        return storageService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Collection<Paciente> get() {
        return storageService.findAll();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Paciente post(Paciente object) {
        return storageService.save(object);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Paciente put(@PathVariable Long id, Paciente paciente) {
        return storageService.update(paciente);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Paciente delete(@PathVariable Long id) {
        return storageService.delete(id);
    }
}
*/
