package com.umayr.csvreader.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.umayr.csvreader.client.CustomerClient;
import com.umayr.csvreader.models.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
@Service
public class CsvReaderService {

  private final CustomerClient customerClient = new CustomerClient();

  public void readAndPostCsvData() {
    Scanner inputScanner = new Scanner(System.in);
    String filePath = inputScanner.nextLine();

    try {
      CsvToBeanBuilder<Customer> csvToBeanBuilder = new CsvToBeanBuilder<>(new FileReader(filePath));
      List<Customer> customerList = csvToBeanBuilder
              .withType(Customer.class)
              .build()
              .parse();

      customerList.forEach(customer -> {
        log.info("Forwarding customer data with ref: " + customer.getCustomerRef());
        customerClient.postCustomerData(customer);
      });
    } catch (FileNotFoundException e) {
      System.err.println("No matching file path found for path input: " + filePath);
    }
  }
}
