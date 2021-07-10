package com.tryton.adv.importer.service;

import com.tryton.adv.importer.model.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class CsvImporter {
    private final CsvReader csvReader;
    private final AdvertisementWriter advertisementWriter;

    @Autowired
    public CsvImporter(CsvReader csvReader, @Qualifier("retryableAdvertisementWriter") AdvertisementWriter advertisementWriter) {
        this.csvReader = requireNonNull(csvReader);
        this.advertisementWriter = requireNonNull(advertisementWriter);
    }

    public void importCsv(Reader reader) throws IOException {
        List<Advertisement> advertisements = csvReader.read(reader);
        advertisements.forEach(advertisementWriter::write);
    }
}
