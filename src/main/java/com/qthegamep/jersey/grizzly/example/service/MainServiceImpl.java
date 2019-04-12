package com.qthegamep.jersey.grizzly.example.service;

public class MainServiceImpl implements MainService {

    @Override
    public String getMain() {
        return "jersey.grizzly.example";
    }

    @Override
    public String getInfo() {
        return "Created by qThegamEp";
    }
}
