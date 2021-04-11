package Cn.Lei.Service;

import Cn.Lei.Domain.Orders;

import java.util.List;

public interface IOrdersService {
    /**
     * 查询所有订单
     * @return
     * @throws Exception
     */
    public List<Orders> findAll(int pageNum, int pageSize) throws Exception;

    /**
     * id查询  订单详情
     * @param id
     * @return
     * @throws Exception
     */
    public Orders findById(String id) throws Exception;

}
