package com.myfleamarket.store.dao.imp;

import com.myfleamarket.store.domain.Customer;

import java.util.List;

public interface CustomerDao {

    Customer findByPk(String pk);

    List<Customer> findAll();

    void create(Customer customer);

    void modify(Customer customer);

    void remove(String pk);
}
