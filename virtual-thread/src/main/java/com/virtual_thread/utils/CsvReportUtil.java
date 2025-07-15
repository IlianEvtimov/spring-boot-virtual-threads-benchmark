package com.virtual_thread.utils;

import com.virtual_thread.model.Customer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvReportUtil {


    public static void writeCustomersToCsv(String region, List<Customer> customers) throws IOException {

        Path path = Paths.get("reports", region + "_report.csv");
        Files.createDirectories(path.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(path);
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.builder().setHeader("id", "name", "email", "gender", "region").get())) {

            for (Customer customer : customers) {
                printer.printRecord(customer.getId(), customer.getName(), customer.getEmail(),
                        customer.getGender(), customer.getRegion());
            }

            printer.flush();
        }
    }
}
