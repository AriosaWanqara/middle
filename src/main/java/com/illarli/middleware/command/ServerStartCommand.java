package com.illarli.middleware.command;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ServerStartCommand {

    public static void init(String port) {
        try {
            createClearBat();
            File myObj = new File("filename.txt");
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
            System.out.println("An error occurred.");
        } finally {
            try {
                FileWriter myWriter = new FileWriter("filename.txt");
                myWriter.write(port);
                myWriter.close();
            } catch (IOException e) {
                System.out.println(e);
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
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private static void closeLastServer(URL obj) throws IOException {
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

    }
}
