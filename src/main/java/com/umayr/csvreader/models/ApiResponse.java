package com.umayr.csvreader.models;

import lombok.Data;

@Data
public class ApiResponse {
  private String message;
  private Integer statusCode;
}
