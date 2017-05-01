package tech.simter.rest.jaxrs;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author RJ 2017-04-29
 */
public class ThrowableExceptionMapperTest {
  @Test
  public void classNameAsBody() throws Exception {
    ThrowableExceptionMapper m = new ThrowableExceptionMapper();
//    m = spy(m);
//    when(m.getErrorResponseBuilder()).thenReturn(new Response.ResponseBuilder() {
//    });

    Response response = m.toResponse(new NullPointerException());
    assertThat(response.getStatus(), is(500));
  }
}