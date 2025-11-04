package org.example.microserviciotrip.utils;


import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class DataLoarder {

    @Autowired
    private DataLoaderService service;

    @PostConstruct
    public void init() {
        try {
            service.populateDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
