package com.virtual_thread.service;

import com.virtual_thread.model.Customer;
import com.virtual_thread.repository.CustomerRepository;
import com.virtual_thread.utils.CsvReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;


@Slf4j
@Service
public class VirtualReportService {

    private final Executor virtualThreadExecutor;
    private final CustomerRepository repository;

    public VirtualReportService(Executor virtualThreadExecutor, CustomerRepository repository) {
        this.virtualThreadExecutor = virtualThreadExecutor;
        this.repository = repository;
    }

    // we can use platform threads to generate report but since java 21 we can use virtual threads
    public void generateReport() {

        virtualThreadExecutor.execute(() -> {
            log.info("Virtual generating report. thread: {}", Thread.currentThread());
            List<Customer> customers = repository.findByRegion("Europe");
            try {
                CsvReportUtil.writeCustomersToCsv("virtual_", customers);
            } catch (Exception e) {
                System.out.println("CSV error: " + e.getMessage());
            }
        });

    }
}
