package Cn.Lei.Dao;

import Cn.Lei.Domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 旅客操作
 */
@Repository
public interface ITravellerDao {

    /**
     * 利用中间表
     * 查询中间表中 订单表id 等于 传入id 的 旅客表id
     * 然后查询旅客表 id属于 查询出来的旅客表id 的数据
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from traveller where id in " +
            "(select travellerId from order_traveller where orderId=#{id})")
    public List<Traveller> findById(String id) throws Exception;
}
