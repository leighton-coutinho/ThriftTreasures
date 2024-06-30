package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.entity.Sale;
import com.LeightonApp.MyThrift.entity.SaleId;
import com.LeightonApp.MyThrift.entity.Store;
import com.LeightonApp.MyThrift.entity.User;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    List<Sale> findAll();
    Optional<Sale> findById(SaleId saleId);
    Sale save(Sale sale);
    void deleteById(SaleId saleId);

    List<Sale> findByStore(Store store);

    public Sale saveSale(Sale sale);

}
