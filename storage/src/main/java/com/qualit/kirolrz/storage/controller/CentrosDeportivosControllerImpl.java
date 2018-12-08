package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import com.qualit.kirolrz.storage.service.AbstractStorageService;
import com.qualit.kirolrz.storage.service.CentrosDeportivosStorageServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/Kiroldegiak")
public class CentrosDeportivosControllerImpl extends AbstractStorageController<CentroDeportivo, Long> {

    public CentrosDeportivosControllerImpl(CentrosDeportivosStorageServiceImpl centrosDeportivosStorageService){
        this.storageService = centrosDeportivosStorageService;
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public CentroDeportivo getById(Long id) {
        return this.storageService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Collection<CentroDeportivo> get() {
        return this.storageService.findAll();
    }


   /* @Override
    public CentroDeportivo post(CentroDeportivo object) {
        return super.post(object);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public CentroDeportivo put(Long identifier, CentroDeportivo object) {
        return super.put(identifier, object);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public CentroDeportivo delete(Long id) {
        return super.delete(id);*/
    }


    /*@RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public CentroDeportivo getById(@PathVariable Long id) {
        return storageService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Override
    public Collection<CentroDeportivo> findAll() {
        return storageService.findAll();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Override
    public CentroDeportivo save(CentroDeportivo centroDeportivo) {
        return storageService.save(centroDeportivo);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Override
    public CentroDeportivo update(@PathVariable Long id, CentroDeportivo centroDeportivo) {
        return storageService.update(centroDeportivo);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public CentroDeportivo delete(@PathVariable Long id) {
        return storageService.delete(id);
    }*/

