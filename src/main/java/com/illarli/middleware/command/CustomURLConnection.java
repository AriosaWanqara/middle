package com.illarli.middleware.command;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomURLConnection extends HttpURLConnection {
    private byte[] data;

    protected CustomURLConnection(URL url) {
        super(url);
    }


    @Override
    public InputStream getInputStream() throws IOException {
        connect();
        return new ByteArrayInputStream(data);
    }

    @Override
    public boolean getDoInput() {
        return true;
    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() throws IOException {
        // Do your job here. As of now it merely prints "Connected!".
        System.out.println("Connected!");
    }

    @Override
    public String getHeaderField(String name) {
        if ("Content-Type".equalsIgnoreCase(name)) {
            return getContentType();
        } else if ("Content-Length".equalsIgnoreCase(name)) {
            return "" + getContentLength();
        }
        return null;
    }

    @Override
    public String getContentType() {
        String fileName = getURL().getFile();
        String ext = fileName.substring(fileName.lastIndexOf('.'));
        return "image/" + ext; // TODO: switch based on file-type
    }

    @Override
    public int getContentLength() {
        return data.length;
    }

    @Override
    public long getContentLengthLong() {
        return data.length;
    }


    @Override
    public OutputStream getOutputStream() throws IOException {
        // this might be unnecessary - the whole method can probably be omitted for our purposes
        return new ByteArrayOutputStream();
    }

    @Override
    public java.security.Permission getPermission() throws IOException {
        return null; // we need no permissions to access this URL
    }

}

