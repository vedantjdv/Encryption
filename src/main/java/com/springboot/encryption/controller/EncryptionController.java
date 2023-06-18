package com.springboot.encryption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.encryption.service.EncryptionService;

@RestController
@RequestMapping("encryption")
public class EncryptionController {

    @Autowired
    EncryptionService encryptionService;
    
@PostMapping(path = "/inputData")
public String encryptData(@RequestParam String data) throws Exception{
  String encryptedData = encryptionService.encryptData(data);
  return encryptedData;

}


    
}
