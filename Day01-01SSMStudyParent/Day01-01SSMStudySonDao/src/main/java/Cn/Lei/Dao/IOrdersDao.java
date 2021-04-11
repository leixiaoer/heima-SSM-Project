package Cn.Lei.Dao;

import Cn.Lei.Domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    /**
     *查询所有订单
     * id为是否为主键
     * column为数据库字段名，
     * property为实体类属性名
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            //根据column给的字段查询产品表 然后赋值
            @Result(column = "productId", property = "product",
                    one = @One(select = "Cn.Lei.Dao.IProductDao.findById"))
    })
    public List<Orders> findAll() throws Exception;


    /**
     * 多表操作
     * id为是否为主键
     * column为数据库字段名，
     * property为实体类属性名
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            //根据column给的字段查询产品表 然后赋值
            @Result(column = "productId", property = "product",
                    one = @One(select = "Cn.Lei.Dao.IProductDao.findById")),
            //根据column给的字段查询产品表 然后赋值
            @Result(column = "memberId", property = "member",
                    one = @One(select = "Cn.Lei.Dao.IMemberDao.findById")),
            //根据column给的字段查询产品表 然后赋值 注意这里多条数据使用many
            @Result(column = "id", property = "travellers",javaType = java.util.List.class,
                    many = @Many(select = "Cn.Lei.Dao.ITravellerDao.findById"))
    })
    public Orders findById(String id) throws Exception;
}
