package com.ftn.sbnz.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.ftn.sbnz")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "mydatabase"; // Naziv vaše MongoDB baze podataka
    }

    @Override
    public MongoClient mongoClient() {
        // Konfiguracija za povezivanje s MongoDB Atlas instancom
        return MongoClients.create("mongodb+srv://kikapopov22:ukHt6vVC4Yh9eo6y@sbnz.ciqefml.mongodb.net/?retryWrites=true&w=majority");
    }
}