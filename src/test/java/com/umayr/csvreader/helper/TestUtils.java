package com.umayr.csvreader.helper;

import java.io.ByteArrayInputStream;

public class TestUtils {
  public void provideInput(String data) {
    ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }
}
