package com.umayr.csvreader.client;

import com.umayr.csvreader.models.ApiResponse;
import com.umayr.csvreader.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import static net.logstash.logback.argument.StructuredArguments.kv;
@Component
@Slf4j
public class CustomerClient {

  public WebClient customerWebClient() {
    return WebClient.builder()
            .baseUrl("http://localhost:8083")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
  }

  public void postCustomerData(Customer customer) {
    WebClient.RequestHeadersSpec<?> request = customerWebClient().post()
            .uri(uriBuilder -> uriBuilder.path("/customer").build())
            .bodyValue(customer)
            .acceptCharset(StandardCharsets.UTF_8);

    request.retrieve()
            .bodyToMono(ApiResponse.class)
            .doOnNext(apiResponse ->
                    log.info("Sent customer data and received response: {}",
                            kv("message", apiResponse.getMessage())
                    ))
            .block();
  }
}

