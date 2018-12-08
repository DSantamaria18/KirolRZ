package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.CentroSalud;
import com.qualit.kirolrz.storage.service.CentrosSaludStorageServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/OsasunZentroak")
public class CentrosSaludControllerImpl extends AbstractStorageController<CentroSalud, Long> {

    public CentrosSaludControllerImpl(CentrosSaludStorageServiceImpl centrosSaludStorageService){
        this.storageService = centrosSaludStorageService;
    }

    @RequestMapping(value = {"/kaixo", "/kaixo/"}, method = RequestMethod.GET)
    public String kaixo(){
        return "Kaixo!";
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public CentroSalud getById(@PathVariable Long id) {
        return storageService.getById(id);
    }

   /* @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Override
    public Collection<CentroSalud> findAll() {
        return storageService.findAll();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Override
    public CentroSalud save(CentroSalud centroSalud) {
        return storageService.save(centroSalud);
    }

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Override
    public CentroSalud update(@PathVariable Long id, CentroSalud centroSalud) {
        return storageService.update(centroSalud);
    }*/

    @RequestMapping(value = {"/id", "/id/"}, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public CentroSalud delete(@PathVariable Long id) {
        return storageService.delete(id);
    }
}
