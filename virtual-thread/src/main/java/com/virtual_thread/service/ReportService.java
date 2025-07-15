package com.virtual_thread.service;

import com.virtual_thread.model.Customer;
import com.virtual_thread.repository.CustomerRepository;
import com.virtual_thread.utils.CsvReportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final CustomerRepository repository;

    // by default tomcat will give you 200 threads
    // org.apache.tomcat.util.threads.ThreadPoolExecutor
    // 200 requests will process at a time
    public void generateReport() {

//        log.info("generating report. thread: {}", Thread.currentThread());

        List<Customer> customers = repository.findByRegion("Europe");
        try {
            CsvReportUtil.writeCustomersToCsv("tomcat_", customers);
        } catch (Exception e) {
            System.out.println("CSV error: " + e.getMessage());
        }

    }
}
