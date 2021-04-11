package Cn.Lei.Service;


import Cn.Lei.Domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
