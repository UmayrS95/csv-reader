package com.umayr.csvreader;

import com.umayr.csvreader.service.CsvReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvReaderApplication.class, args);

		CsvReaderService csvReaderService = new CsvReaderService();

		csvReaderService.readAndPostCsvData();
		System.exit(0);
	}
}
