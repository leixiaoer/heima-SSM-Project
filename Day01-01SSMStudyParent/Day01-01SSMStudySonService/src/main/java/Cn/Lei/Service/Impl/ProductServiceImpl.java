package Cn.Lei.Service.Impl;

import Cn.Lei.Dao.IProductDao;
import Cn.Lei.Domain.Product;
import Cn.Lei.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    @RolesAllowed("ADMIN")
    public List<Product> findAll() throws Exception{
        return productDao.findAll();
    }
}
