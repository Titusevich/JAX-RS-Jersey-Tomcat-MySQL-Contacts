package org.example.app.config;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import org.example.app.repository.contact.ContactRepository;
import org.example.app.repository.contact.ContactRepositoryImpl;
import org.example.app.service.contact.ContactService;
import org.example.app.service.contact.ContactServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

public class ApplicationBinder implements Feature {
    @Override
    public boolean configure(FeatureContext context) {
        context.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(ContactRepositoryImpl.class).to(ContactRepository.class);
                bind(ContactServiceImpl.class).to(ContactService.class);
                bind(JacksonJsonProvider.class);
            }
        });
        return true;
    }
}
