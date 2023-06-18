package com.springboot.encryption.service;

import org.springframework.stereotype.Service;

@Service
public interface EncryptionService {

public String encryptData(String data) throws Exception;
    
}
