package org.example.app.config;

import jakarta.ws.rs.ApplicationPath;
import org.example.app.controller.ContactController;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/v1")
public class AppResourceConfig extends ResourceConfig {

    public AppResourceConfig() {
        register(ContactController.class);
        register(ApplicationBinder.class);
    }
}
