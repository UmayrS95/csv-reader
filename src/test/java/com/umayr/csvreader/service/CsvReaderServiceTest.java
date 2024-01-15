package com.umayr.csvreader.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.umayr.csvreader.client.CustomerClient;
import com.umayr.csvreader.helper.TestUtils;
import com.umayr.csvreader.models.ApiResponse;
import com.umayr.csvreader.models.Customer;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.PrepareTestInstance;
import org.springframework.web.reactive.function.client.WebClient;

import static com.umayr.csvreader.LogUtils.logger;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CsvReaderServiceTest {

  @Mock
  private CustomerClient customerClient;

  @InjectMocks
  private CsvReaderService csvReaderService;

  private final TestUtils testUtils = new TestUtils();

  private final String path = "/Users/ushaffi/dev/csv-reader/src/test/java/com/umayr/csvreader/helper/csvexampletest.csv";

  private ListAppender<ILoggingEvent> logListAppender;

  @BeforeEach
  public void beforeEachTest(){
    logListAppender = logger();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCorrectLogsShow() {
    testUtils.provideInput(path);
    doNothing().when(customerClient).postCustomerData(any(Customer.class));

    csvReaderService.readAndPostCsvData();

    verify(customerClient, times(3)).postCustomerData(any(Customer.class));

//    await().untilAsserted(() -> assertThat(logListAppender.list)
//            .extracting(ILoggingEvent::getLevel, ILoggingEvent::getMessage)
//            .containsAnyOf(Tuple.tuple(Level.INFO, "Forwarding customer data with ref: kfl435")));
  }
}
