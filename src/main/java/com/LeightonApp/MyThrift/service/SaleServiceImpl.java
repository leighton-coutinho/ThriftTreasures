package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.dao.SaleRepository;
import com.LeightonApp.MyThrift.dao.UserRepository;
import com.LeightonApp.MyThrift.entity.Sale;
import com.LeightonApp.MyThrift.entity.SaleId;
import com.LeightonApp.MyThrift.entity.Store;
import com.LeightonApp.MyThrift.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
    private SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository theSaleRepository) {
        saleRepository = theSaleRepository;
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> findById(SaleId saleID) {
        return saleRepository.findById(saleID);
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void deleteById(SaleId saleID) {
        saleRepository.deleteById(saleID);
    }

    @Override
    public List<Sale> findByStore(Store store) {
        return saleRepository.findByStore(store);
    }
}
