package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.TecnicoGK;
import com.qualit.kirolrz.storage.service.TecnicosStorageServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/Teknikariak")
public class TecnicosControllerImpl extends AbstractStorageController<TecnicoGK, Long> {

    public TecnicosControllerImpl(TecnicosStorageServiceImpl tecnicosStorageService){
        this.storageService = tecnicosStorageService;
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public TecnicoGK getById(@PathVariable Long id) {
        return storageService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Collection<TecnicoGK> findAll() {
        return storageService.findAll();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public TecnicoGK save(TecnicoGK tecnicoGK) {
        return storageService.save(tecnicoGK);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public TecnicoGK update(@PathVariable Long id, TecnicoGK tecnicoGK) {
        return storageService.update(tecnicoGK);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public TecnicoGK delete(@PathVariable Long id) {
        return storageService.delete(id);
    }
}
