package com.myfleamarket.store.service;

import com.myfleamarket.store.domain.Customer;

public interface CustomerService {
    /**
     * Process customer sign in
     * @param customer
     * @return true if success, false if fail
     */
    boolean login(Customer customer);

    /**
     * Handle customer register
     * @param customer
     * @throws ServiceException if fail, throw exception
     */

    void register(Customer customer) throws ServiceException;
}
