package com.illarli.middleware.command;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class CustomURLStreamHandlerFactory implements URLStreamHandlerFactory {

    private String carlitos;

    public CustomURLStreamHandlerFactory(String carlitos) {
        this.carlitos = carlitos;
    }

    private CustomURLStreamHandlerFactory() {
    }

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if ("printer-desktop".equals(protocol)) {
            return new CustomURLStreamHandler();
        }

        return null;
    }

}
