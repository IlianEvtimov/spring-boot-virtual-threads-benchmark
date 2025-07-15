package com.virtual_thread.service;

import com.virtual_thread.model.Customer;
import com.virtual_thread.repository.CustomerRepository;
import com.virtual_thread.utils.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Slf4j
@Service
@RequiredArgsConstructor
public class PlatformReportService {

    private final Executor executor = Executors.newFixedThreadPool(5);
    private final CustomerRepository repository;

    // we can use platform threads to generate report but since java 21 we can use virtual threads
    public void generateReport(){
        executor.execute(() -> {
            log.info("Platform generating report. thread: {}", Thread.currentThread());

            List<Customer> customers = repository.findByRegion("Europe");
            try {
                CsvReportUtil.writeCustomersToCsv("platform_", customers);
            } catch (Exception e) {
                System.out.println("CSV error: " + e.getMessage());
            }
        });

    }
}
