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
import org.springframework.test.context.junit4.SpringRunner;
import tech.simter.data.Page;
import tech.simter.demo.po.Demo;
import tech.simter.demo.service.DemoService;
import tech.simter.persistence.CommonState;
import tech.simter.rest.jaxrs.CreatedStatusResponseFilter;
import tech.simter.rest.jaxrs.jersey.JerseyConfiguration;
import tech.simter.rest.jaxrs.jersey.JerseyResourceConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author RJ
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  classes = {DemoResourceImplTest.class, JerseyResourceConfig.class, CreatedStatusResponseFilter.class}
)
@SpringBootApplication
@MockBean(DemoService.class)
@MockBean(JerseyConfiguration.class)
public class DemoResourceImplTest {
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private DemoService demoService;
  @Autowired
  private JerseyConfiguration jerseyConfiguration;

  @Test
  public void get() {
    // mock service method
    Demo demo = mock(Demo.class);
    demo.id = 1;
    when(demoService.get(demo.id)).thenReturn(demo);

    // execute rest
    ResponseEntity<Demo> entity = this.restTemplate.getForEntity("/demo/1", Demo.class);

    // verify
    verify(demoService, times(1)).get(demo.id);
    assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    assertThat(entity.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON));
    assertThat(entity.getBody().id, is(demo.id));
  }

  @Test
  public void findList() {
    // mock service method
    List<Demo> demos = new ArrayList<>();
    Demo demo = mock(Demo.class);
    demo.id = 1;
    demos.add(demo);
    when(demoService.find(any())).thenReturn(demos);

    // execute rest
    ResponseEntity<List> entity = this.restTemplate.getForEntity("/demo", List.class);

    // verify
    verify(demoService, times(1)).find(any());
    assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    List result = entity.getBody();
    for (int i = 0; i < result.size(); i++) {
      assertThat(((Map) result.get(i)).get("id"), is(demos.get(i).id));
    }
  }

  @Test
  public void findPage() {
    // mock service method
    List<Demo> demos = new ArrayList<>();
    Demo demo = mock(Demo.class);
    demo.id = 1;
    demos.add(demo);
    Page<Demo> page = Page.build(1, 25, demos, 100);
    when(demoService.find(1, 25, CommonState.Enabled)).thenReturn(page);

    // execute rest
    ResponseEntity<Map> entity = this.restTemplate.getForEntity("/demo", Map.class);

    // verify
    verify(demoService, times(1)).find(1, 25, CommonState.Enabled);
    assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    Map result = entity.getBody();
    assertThat(result.get("pageNo"), is(1));
    assertThat(result.get("pageSize"), is(25));
    assertThat(result.get("count"), is(100));
    assertThat(result.get("rows"), notNull());
    assertThat(((List) result.get("rows")).size(), is(demos.size()));
    assertThat(((Map) ((List) result.get("rows")).get(0)).get("id"), is(demos.get(0).id));
  }

  @Test
  public void create() {
    // mock service method
    Demo demo = mock(Demo.class);
    demo.id = 1;
    when(demoService.save(any())).thenReturn(demo);

    // execute rest
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<>(new Genson().serialize(demo), requestHeaders);
    ResponseEntity<Map> entity = this.restTemplate.postForEntity("/demo", httpEntity, Map.class);

    // verify
    verify(demoService, times(1)).save(any());
    assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
    Map result = entity.getBody();
    assertThat(result.get("id"), is(demo.id));
    assertThat(result.containsKey("ts"), is(true));
  }

  @Test
  public void update() {
    // mock service method
    Demo demo = mock(Demo.class);
    demo.id = 1;
    when(demoService.save(any())).thenReturn(demo);

    // execute rest
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<>(new Genson().serialize(demo), requestHeaders);
    ResponseEntity<Map> entity = this.restTemplate.exchange("/demo/" + demo.id, HttpMethod.PUT, httpEntity, Map.class);

    // verify
    verify(demoService, times(1)).save(any());
    assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    Map result = entity.getBody();
    assertThat(result.containsKey("ts"), is(true));
  }

  @Test
  public void deleteOne() {
    // mock service method
    Integer id = 1;
    doNothing().when(demoService).delete(id);

    // execute rest
    ResponseEntity<Void> entity = this.restTemplate.exchange("/demo/" + id, HttpMethod.DELETE, null, Void.class);

    // verify
    verify(demoService, times(1)).delete(id);
    assertThat(entity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    assertThat(entity.getBody(), nullValue());
  }

  @Test
  public void deleteBatch() {
    // mock service method
    Integer[] ids = new Integer[]{1, 2};
    doNothing().when(demoService).delete(ids);

    // execute rest
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<>(new Genson().serialize(ids), requestHeaders);
    ResponseEntity<Void> entity = this.restTemplate.exchange("/demo", HttpMethod.DELETE, httpEntity, Void.class);

    // verify
    verify(demoService, times(1)).delete(ids);
    assertThat(entity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    assertThat(entity.getBody(), nullValue());
  }
}