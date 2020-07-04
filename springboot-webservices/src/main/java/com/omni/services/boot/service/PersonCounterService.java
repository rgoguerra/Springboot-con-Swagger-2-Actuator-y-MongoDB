package com.omni.services.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter.Type;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class PersonCounterService {

    private final Counter personAddCounter;
    private final Counter personDeleteCounter;

    @Autowired
    public PersonCounterService(MeterRegistry registry) {       
        this.personAddCounter = registry.counter("services.person.add");
        this.personDeleteCounter = registry.counter("services.person.deleted");
    }

    public void countNewPersons() {     
        this.personAddCounter.increment();
    }

    public void countDeletedPersons() {
        this.personDeleteCounter.increment();
    }

    
    @Bean
    MeterRegistryCustomizer<MeterRegistry> addPersonRegistry() {
        return registry -> registry.config().namingConvention().name("services.person.add", Type.COUNTER);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> deletePersonRegistry() {
        return registry -> registry.config().namingConvention().name("services.person.deleted", Type.COUNTER);
    }
}