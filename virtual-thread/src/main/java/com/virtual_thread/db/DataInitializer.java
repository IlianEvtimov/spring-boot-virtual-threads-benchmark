package com.virtual_thread.db;

import com.virtual_thread.model.Customer;
import com.virtual_thread.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository repository;

    @Override
    public void run(String... args) {
        List<Customer> customers = new ArrayList<>();
        String[] genders = {"Male", "Female"};
        String[] regions = {"Europe", "Asia", "North America", "South America", "Africa"};

        for (int i = 1; i <= 500; i++) {
            customers.add(new Customer(
                    i,
                    "Customer" + i,
                    "customer" + i + "@example.com",
                    genders[i % genders.length],
                    regions[i % regions.length]
            ));
        }

        repository.saveAll(customers);
    }
}
