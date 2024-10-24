package com.ulixe.confluence2mongodb.helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class ResourceProvider {

    private final ResourceConfig resourceConfig;

    @Autowired
    public ResourceProvider(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    public FileSystemResource getResource() {
        return new FileSystemResource(resourceConfig.getPath()); // Restituisce la risorsa dal path configurato
    }
}
