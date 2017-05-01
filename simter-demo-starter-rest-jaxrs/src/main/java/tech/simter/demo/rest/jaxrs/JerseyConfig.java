package tech.simter.demo.rest.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import tech.simter.rest.jaxrs.LogRequestFilter;
import tech.simter.rest.jaxrs.ThrowableExceptionMapper;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.xml.ws.Endpoint;
import java.util.HashMap;

/**
 * The JAX-RS application initiator for jersey.
 *
 * @author RJ
 */
//@Component
//@ApplicationPath("/") // define the root path
public class JerseyConfig extends ResourceConfig implements ApplicationContextAware {
  private ApplicationContext applicationContext;

  @PostConstruct
  public void init() {
//    register(Endpoint.class);
    // auto register all jax-rs annotation resources（@Path、@Provider）
    packages("tech.simter");
    // register all spring manage rest-resource
    System.out.println("init");
//    applicationContext.getBeansWithAnnotation(Path.class).forEach(
//      (k, v) -> {
//        System.out.println("key=" + k + ", value=" + v.getClass());
//        register(v);
//      });

    // filter
    //register(CharsetResponseFilter.class);
    register(new LogRequestFilter(), Priorities.AUTHENTICATION);
    register(new ThrowableExceptionMapper(new HashMap<Class<?>, Object>() {{
      ThrowableExceptionMapper.ErrorMapper em = new ThrowableExceptionMapper.ErrorMapper(Response.Status.FORBIDDEN, "无权限");
      put(SecurityException.class, em);
    }}));

    // 单独注册类
    //register(RootResource.class);

    // 注册默认 utf-8 编码的过滤器
    // 推荐在 web.xml 中进行统一配置而不是在这里特殊配置
    // 注册登录认证过滤器
    //register(AuthRequestFilter.class, Priorities.AUTHENTICATION);

    // 注册记录请求日志过滤器: for test
    //register(LogRequestFilter.class, Priorities.AUTHORIZATION);

    // 设置 spring 配置文件的位置
    //property("contextConfigLocation", "spring.xml");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}