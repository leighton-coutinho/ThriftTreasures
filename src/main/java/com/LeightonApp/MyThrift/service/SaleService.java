package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.entity.*;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    List<Sale> findAll();
    Optional<Sale> findById(SaleId saleId);
    Sale save(Sale sale);
    void deleteById(SaleId saleId);

    List<Sale> findByStore(Store store);
    List<Sale> findByCustomer(Customer customer);

    public Sale saveSale(Sale sale);

    public void completeSale(SaleId saleId);

}
