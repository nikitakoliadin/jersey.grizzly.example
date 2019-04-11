package com.qthegamep.jersey.grizzly.example;

import com.qthegamep.jersey.grizzly.example.exception.CouldNotStartServerException;
import com.qthegamep.jersey.grizzly.example.starter.ApplicationStarter;

public class Application {

    public static void main(String[] args) throws CouldNotStartServerException {
        ApplicationStarter.startApplication();
    }
}
