package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.utilities;


import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtils {
    private BasicTextEncryptor textEncryptor;


    public EncryptionUtils() {
        this.textEncryptor = new BasicTextEncryptor();
        this.textEncryptor.setPassword("mySecretKey1234!");
    }

    public String encrypt(String data) {
        return this.textEncryptor.encrypt(data);
    }

    public String decrypt(String encryptedData) {
        return this.textEncryptor.decrypt(encryptedData);
    }
}
