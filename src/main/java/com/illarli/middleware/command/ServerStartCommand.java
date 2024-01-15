package com.illarli.middleware.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ServerStartCommand {
    private static final Logger logger = LoggerFactory.getLogger(ServerStartCommand.class);

    public static void init(String port) {
        try {
            createClearBat();
            createClearBash();
            URL.setURLStreamHandlerFactory(new CustomURLStreamHandlerFactory("a"));
            File myObj = new File("port.txt");
            Scanner myReader = new Scanner(myObj);
            String data = null;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            if (data != null) {
                System.out.println(data);
                URL url = new URL("http://localhost:" + data + "/server/shut-down");
                closeLastServer(url);
            }
        } catch (Exception e) {
            logger.warn("Error init");
            logger.error(e.getMessage(), e);
        } finally {
            try {
                FileWriter myWriter = new FileWriter("port.txt");
                myWriter.write(port);
                myWriter.close();
            } catch (IOException e) {
                logger.warn("Error writing port txt");
                logger.error(e.getMessage(), e);
            }
        }
    }

    private static void createClearBat() {
        try {
            FileWriter myWriter = new FileWriter("clear.bat");
            myWriter.write("@echo off\n" +
                    ":: BatchGotAdmin\n" +
                    ":-------------------------------------\n" +
                    "REM  --> Check for permissions\n" +
                    ">nul 2>&1 \"%SYSTEMROOT%\\system32\\cacls.exe\" \"%SYSTEMROOT%\\system32\\config\\system\"\n" +
                    "REM --> If error flag set, we do not have admin.\n" +
                    "if '%errorlevel%' NEQ '0' (    echo Requesting administrative privileges...    goto UACPrompt) else ( goto gotAdmin )\n" +
                    ":UACPrompt\n" +
                    "    echo Set UAC = CreateObject^(\"Shell.Application\"^) > \"%temp%\\getadmin.vbs\"\n" +
                    "    echo UAC.ShellExecute \"%~s0\", \"\", \"\", \"runas\", 1 >> \"%temp%\\getadmin.vbs\"\n" +
                    "    \"%temp%\\getadmin.vbs\"\n" +
                    "    exit /B\n" +
                    ":gotAdmin\n" +
                    "net stop spooler &\n" +
                    "del %systemroot%\\System32\\spool\\printers\\* /Q &\n" +
                    "net start spooler");
            myWriter.close();
        } catch (IOException e) {
            logger.warn("Error writing clear bat");
            logger.error(e.getMessage(), e);
        }

    }

    private static void createClearBash() {
        try {
            FileWriter myWriter = new FileWriter("clear.bash");
            myWriter.write("ls");
            myWriter.close();
        } catch (IOException e) {
            logger.warn("Error writing clear bash");
            logger.error(e.getMessage(), e);
        }

    }

    private static void closeLastServer(URL obj) throws IOException {
        try {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("GET request did not work.");
            }
        } catch (Exception e) {

        }
    }
}

