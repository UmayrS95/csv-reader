package com.umayr.csvreader.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {
  @CsvBindByPosition(position = 0)
  private String customerRef;

  @CsvBindByPosition(position = 1)
  private String customerName;

  @CsvBindByPosition(position = 2)
  private String addressLine1;

  @CsvBindByPosition(position = 3)
  private String addressLine2;

  @CsvBindByPosition(position = 4)
  private String town;

  @CsvBindByPosition(position = 5)
  private String county;

  @CsvBindByPosition(position = 6)
  private String country;

  @CsvBindByPosition(position = 7)
  private String postcode;
}
