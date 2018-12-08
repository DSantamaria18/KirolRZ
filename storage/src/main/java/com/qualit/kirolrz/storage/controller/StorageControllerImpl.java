package com.qualit.kirolrz.storage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageControllerImpl {

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String sayHello(){
        return "Hola!!!";
    }
}
