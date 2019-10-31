package net.akul.customrequestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomRequestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomRequestServiceApplication.class, args);
    }

}
