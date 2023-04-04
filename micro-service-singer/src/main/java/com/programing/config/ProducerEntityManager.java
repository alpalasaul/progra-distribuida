package com.programing.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class ProducerEntityManager {

    @Produces
    public EntityManager createEntityManager() {
        return Persistence
                .createEntityManagerFactory("PUsinger")
                .createEntityManager();
    }

}
