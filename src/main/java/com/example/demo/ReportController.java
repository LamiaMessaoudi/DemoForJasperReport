package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private SubReportService subReportService;

    @PostMapping("/generate")
    public void generate(@RequestBody Report report)
    {
        try {
            subReportService.loadreport();
            subReportService.generateParameters( report.getParameters());
            subReportService.generateFields(report.getFields());
            subReportService.generateQuery(report.getQuery());
            subReportService.generateTitle(report.getFields());
            subReportService.generateDetails(report.getFields());

            subReportService.createSubReport();
            subReportService.exportReport("html");

           // subReportService.raporti();
        }catch (Exception e){

        }
    }


}
