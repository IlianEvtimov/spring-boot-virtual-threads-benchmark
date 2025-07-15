package com.virtual_thread.controller;

import com.virtual_thread.service.PlatformReportService;
import com.virtual_thread.service.ReportService;
import com.virtual_thread.service.VirtualReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final PlatformReportService platformReportService;
    private final VirtualReportService virtualReportService;

    @GetMapping("/generate-report")
    public void generateReport() {
        reportService.generateReport();
    }


    @GetMapping("/platform-generate-report")
    public void generateReportPlatform() throws InterruptedException {
        platformReportService.generateReport();
    }

    @GetMapping("/virtual-generate-report")
    public void generateReportVirtual() throws InterruptedException {
        virtualReportService.generateReport();
    }



}
