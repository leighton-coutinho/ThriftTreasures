package com.LeightonApp.MyThrift.dao;
import com.LeightonApp.MyThrift.entity.Customer;
import com.LeightonApp.MyThrift.entity.Sale;
import com.LeightonApp.MyThrift.entity.SaleId;
import com.LeightonApp.MyThrift.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// entity and integer primary key type
public interface SaleRepository extends JpaRepository<Sale, SaleId> {
    // can define custom queries here to find sales based on only itemID or only StoreID, etc
    List<Sale> findByStore(Store store);
    List<Sale> findByCustomer(Customer customer);
}