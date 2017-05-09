package tech.simter.demo.rest.jaxrs;

import tech.simter.data.Created;
import tech.simter.data.Page;
import tech.simter.data.Ts;
import tech.simter.demo.po.Demo;
import tech.simter.persistence.CommonState;
import tech.simter.rest.jaxrs.CreatedStatus;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author RJ
 */
@Path("demo")
public interface DemoResource {
  /**
   * 获取指定主键的{demo}
   *
   * @param id 主键
   * @return 指定主键的{demo}
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  Demo get(@PathParam("id") @NotNull Integer id);

  /**
   * 获取指定状态的所有{demo}
   *
   * @param status 状态，为空则忽略状态条件
   * @return 指定状态的所有{demo}
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<Demo> find(@QueryParam("status") CommonState status);

  /**
   * 获取指定状态的所有{demo}
   *
   * @param status 状态，为空则忽略状态条件
   * @return 指定状态的所有{demo}
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("page")
  Page<Demo> findPage(@QueryParam("page-no") int pageNo,
                      @QueryParam("page-size") int pageSize,
                      @QueryParam("status") CommonState status);

  /**
   * 删除指定主键的{demo}
   *
   * @param id 主键
   */
  @DELETE
  @Path("{id}")
  void delete(@PathParam("id") @NotNull Integer id);

  /**
   * 删除特定主键的{demo}
   *
   * @param ids 要删除的主键集
   */
  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  void delete(@NotNull Integer[] ids);

  /**
   * 创建一个新的{demo}
   *
   * @param demo 新的{demo}信息
   * @return 已创建的新{demo}
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @CreatedStatus
  Created<Integer> create(@NotNull Demo demo);

  /**
   * 创建一个新的{demo}
   *
   * @param demo 新的{demo}信息
   * @return 已创建的新{demo}
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Created<Integer> createByPUT(@NotNull Demo demo);

  /**
   * 更新{demo}信息
   *
   * @param id   主键
   * @param demo {demo}信息
   * @return 更新时间
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  Ts update(@PathParam("id") @NotNull Integer id, @NotNull Demo demo);
}