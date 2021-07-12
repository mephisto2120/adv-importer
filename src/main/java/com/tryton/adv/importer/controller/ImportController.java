package com.tryton.adv.importer.controller;

import com.tryton.adv.importer.service.CsvImporter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping(value = "/import")
@Api(value = "Imports data")
public class ImportController {

    private final CsvImporter csvImporter;

    @Autowired
    public ImportController(CsvImporter csvImporter) {
        this.csvImporter = requireNonNull(csvImporter);
    }

    @PostMapping("/data-csv")
    @ApiOperation(value = "Imports data from CSV. A required format is: \n" +
            "  Datasource,Campaign,Daily,Clicks,Impressions\n" +
            "  Special Ads,Waterfall Touristik,25/12/21,7,22425")
    public void importCsvData(@RequestBody String csvFile) throws IOException {
        Reader reader = new StringReader(csvFile);
        csvImporter.importCsv(reader);
    }
}
