package com.virtual_thread.repository;

import com.virtual_thread.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByRegion(String name);
}
