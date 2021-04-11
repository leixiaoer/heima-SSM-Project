package Cn.Lei.Service.Impl;

import Cn.Lei.Dao.IOrdersDao;
import Cn.Lei.Domain.Orders;
import Cn.Lei.Service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int pageNum, int pageSize) throws Exception {
        /**
         * 参数:
         * pageNum – 页码
         * pageSize – 每页显示数量
         * 从第几页开始 每页显示多少条
         */
        PageHelper.startPage(pageNum,pageSize);

        //查询数据
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findById(id);
    }

}
