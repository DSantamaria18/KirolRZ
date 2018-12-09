package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.entity.CentroSalud;
import com.qualit.kirolrz.storage.service.CentrosSaludStorageServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/OsasunZentroak")
public class CentrosSaludControllerImpl extends AbstractStorageController<CentroSalud, Long> {

    public CentrosSaludControllerImpl(CentrosSaludStorageServiceImpl centrosSaludStorageService){
        this.storageService = centrosSaludStorageService;
    }

    @GetMapping(value = "/kaixo")
    @ResponseBody
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
