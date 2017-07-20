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
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author RJ
 */
@RunWith(SpringRunner.class)
@SpringBootApplication
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {JaxrsTestConfiguration.class, NestedDtoResource.class})
public class NestedDtoResourceTest {
  private final static Logger logger = LoggerFactory.getLogger(NestedDtoResourceTest.class);
  @Inject
  private TestRestTemplate restTemplate;

  @Inject

  @Test
  public void test() {
    // build request
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
    JsonObjectBuilder firstChild = Json.createObjectBuilder().add("name", "firstChild");
    JsonObjectBuilder secondChild = Json.createObjectBuilder().add("name", "secondChild");
    String body = Json.createObjectBuilder()
      .add("name", "parentName")
      .add("firstChild", firstChild)
      .add("children", Json.createArrayBuilder().add(firstChild).add(secondChild))
      .build().toString();
    logger.debug("request-body={}", body);
    HttpEntity<String> httpEntity = new HttpEntity<>(body, requestHeaders);

    // send request
    ResponseEntity<String> response = this.restTemplate.postForEntity("/nested-dto", httpEntity, String.class);
    body = response.getBody();
    logger.debug("response-body={}", body);

    // verify
    assertThat(response.getStatusCode(), is(HttpStatus.OK));
    JsonObject dto = Json.createReader(new StringReader(body)).readObject();
    assertThat(dto.getString("name"), is("parentName"));

    assertThat(dto.containsKey("firstChild"), is(true));
    assertThat(dto.getJsonObject("firstChild").getString("name"), is("firstChild"));

    assertThat(dto.containsKey("children"), is(true));
    JsonArray children = dto.getJsonArray("children");
    assertThat(children.size(), is(2));
    assertThat(children.getJsonObject(0).getString("name"), is("firstChild"));
    assertThat(children.getJsonObject(1).getString("name"), is("secondChild"));
  }
}