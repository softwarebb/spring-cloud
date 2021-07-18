package com.example.tmy;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudZipkinServiceMiyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZipkinServiceMiyaApplication.class, args);
    }
    @Bean
    public Sampler getSample() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
