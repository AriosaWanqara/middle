package com.illarli.middleware;

import com.illarli.middleware.command.ServerStartCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiddlewareApplication {

    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                try {
                    ServerStartCommand.init(args[0]);
                } catch (Exception e) {
                    System.out.println("An error occurred.");
                }
            }
            String puerto = args[0];
            System.setProperty("server.port", puerto);
            SpringApplication.run(MiddlewareApplication.class, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println("Ocurrió un error: " + e.getMessage());
            System.exit(1);
        }
    }

}
