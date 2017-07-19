package tech.simter.demo.rest.jaxrs.javatime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tech.simter.test.JaxrsTestConfiguration;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author RJ
 */
@RunWith(SpringRunner.class)
@SpringBootApplication
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {JaxrsTestConfiguration.class, YearMonthResource.class})
public class YearMonthConverterTest {
  private final static Logger logger = LoggerFactory.getLogger(YearMonthConverterTest.class);
  @Inject
  private TestRestTemplate restTemplate;

  @Test
  public void test() {
    int year = 2017;
    int month = 10;

    // build request
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
    String body = Json.createObjectBuilder()
      .add("ym1", year + "-" + month)
      .add("ym2", year + "/" + month)
      .add("ym3", year + "" + month)
      .build().toString();
    logger.debug("request-body={}", body);
    HttpEntity<String> httpEntity = new HttpEntity<>(body, requestHeaders);

    // send request
    ResponseEntity<String> response = this.restTemplate.postForEntity("/ym", httpEntity, String.class);
    body = response.getBody();
    logger.debug("response-body={}", body);

    // verify
    assertThat(response.getStatusCode(), is(HttpStatus.OK));
    JsonObject dto = Json.createReader(new StringReader(body)).readObject();
    assertThat(dto.getString("ym1"), is(year + "-" + month));
    assertThat(dto.getString("ym2"), is(year + "/" + month));
    assertThat(dto.getString("ym3"), is(year + "" + month));
  }
}