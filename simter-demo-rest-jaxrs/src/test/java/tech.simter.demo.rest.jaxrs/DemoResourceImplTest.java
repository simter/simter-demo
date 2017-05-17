package tech.simter.demo.rest.jaxrs;

import com.owlike.genson.Genson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tech.simter.data.Page;
import tech.simter.demo.po.Demo;
import tech.simter.demo.service.DemoService;
import tech.simter.persistence.CommonState;
import tech.simter.test.JaxrsTestConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author RJ
 */
@RunWith(SpringRunner.class)
@SpringBootApplication
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {JaxrsTestConfiguration.class, DemoResourceImpl.class})
@MockBean(DemoService.class)
public class DemoResourceImplTest {
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private DemoService service;

  @Test
  public void get() {
    // mock service method
    Demo dto = mock(Demo.class);
    dto.id = 1;
    when(service.get(dto.id)).thenReturn(dto);

    // execute rest
    ResponseEntity<Demo> response = this.restTemplate.getForEntity("/demo/1", Demo.class);

    // verify
    verify(service, times(1)).get(dto.id);
    assertThat(response.getStatusCode(), is(HttpStatus.OK));
    assertThat(response.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON));
    assertThat(response.getBody().id, is(dto.id));
  }

  @Test
  public void findList() {
    // mock service method
    List<Demo> list = new ArrayList<>();
    Demo dto = mock(Demo.class);
    dto.id = 1;
    list.add(dto);
    when(service.find(null, null)).thenReturn(list);

    // execute rest
    ResponseEntity<List> response = this.restTemplate.getForEntity("/demo", List.class);

    // verify
    verify(service, times(1)).find(null, null);
    assertThat(response.getStatusCode(), is(HttpStatus.OK));
    assertThat(response.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON));
    List body = response.getBody();
    for (int i = 0; i < body.size(); i++) {
      assertThat(((Map) body.get(i)).get("id"), is(list.get(i).id));
    }
  }

  @Test
  public void findPage() {
    // mock service method
    int pageNo = 1;
    int pageSize = 25;
    long totalCount = 100;
    CommonState status = CommonState.Enabled;
    Integer id = 1;
    List<Demo> list = new ArrayList<>();
    Demo dto = mock(Demo.class);
    dto.id = id;
    list.add(dto);
    Page<Demo> page = Page.build(pageNo, pageSize, list, totalCount);
    when(service.find(pageNo, pageSize, status)).thenReturn(page);

    // execute rest
    ResponseEntity<Map> response = this.restTemplate.getForEntity("/demo/page?pageNo={0}&pageSize={1}&status={2}",
      Map.class, pageNo, pageSize, status);

    // verify
    verify(service, times(1)).find(pageNo, pageSize, status);
    assertThat(response.getStatusCode(), is(HttpStatus.OK));
    assertThat(response.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON));
    Map body = response.getBody();
    assertThat(body.get("pageNo"), is(pageNo));
    assertThat(body.get("pageSize"), is(pageSize));
    assertThat(Long.parseLong(body.get("count").toString()), is(totalCount));
    List rows = (List) body.get("rows");
    assertThat(rows, notNullValue());
    assertThat(rows.size(), is(list.size()));
    assertThat(((Map) rows.get(0)).get("id"), is(list.get(0).id));
  }

  @Test
  public void create() {
    // mock service method
    Demo dto = mock(Demo.class);
    dto.id = 1;
    when(service.save(any())).thenReturn(dto);

    // execute rest
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<>(new Genson().serialize(dto), requestHeaders);
    ResponseEntity<Map> response = this.restTemplate.postForEntity("/demo", httpEntity, Map.class);

    // verify
    verify(service, times(1)).save(any());
    assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    assertThat(response.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON));
    Map body = response.getBody();
    assertThat(body.get("id"), is(dto.id));
    assertThat(body.containsKey("ts"), is(true));
  }

  @Test
  public void update() {
    // mock service method
    Demo dto = mock(Demo.class);
    dto.id = 1;
    when(service.save(any())).thenReturn(dto);

    // execute rest
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<>(new Genson().serialize(dto), requestHeaders);
    ResponseEntity<Map> response = this.restTemplate.exchange("/demo/" + dto.id, HttpMethod.PUT, httpEntity, Map.class);

    // verify
    verify(service, times(1)).save(any());
    assertThat(response.getStatusCode(), is(HttpStatus.OK));
    assertThat(response.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON));
    Map body = response.getBody();
    assertThat(body.containsKey("ts"), is(true));
  }

  @Test
  public void deleteOne() {
    // mock service method
    Integer id = 1;
    doNothing().when(service).delete(id);

    // execute rest
    ResponseEntity<Void> response = this.restTemplate.exchange("/demo/" + id, HttpMethod.DELETE, null, Void.class);

    // verify
    verify(service, times(1)).delete(id);
    assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
    assertThat(response.getBody(), nullValue());
  }

  @Test
  public void deleteBatch() {
    // mock service method
    Integer[] ids = new Integer[]{1, 2};
    doNothing().when(service).delete(ids);

    // execute rest
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<>(new Genson().serialize(ids), requestHeaders);
    ResponseEntity<Void> response = this.restTemplate.exchange("/demo", HttpMethod.DELETE, httpEntity, Void.class);

    // verify
    verify(service, times(1)).delete(ids);
    assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
    assertThat(response.getBody(), nullValue());
  }
}