package com.myfleamarket.store.service.imp;

import com.myfleamarket.store.dao.CustomerDao;
import com.myfleamarket.store.dao.imp.CustomerDaoImpJdbc;
import com.myfleamarket.store.domain.Customer;
import com.myfleamarket.store.service.CustomerService;
import com.myfleamarket.store.service.ServiceException;

public class CustomerServiceImp implements CustomerService {

    CustomerDao customerDao = new CustomerDaoImpJdbc();

    @Override
    public boolean login(Customer customer) {
        Customer dbCustomer = customerDao.findByPk(customer.getId());

        if (dbCustomer != null &&
                dbCustomer.getPassword().equals(customer.getPassword())) {
            //send customer info from db to representation layer

            customer.setName(dbCustomer.getName());
            customer.setBirthday(dbCustomer.getBirthday());
            customer.setAddress(dbCustomer.getAddress());
            customer.setPhone(dbCustomer.getPhone());

            return true;
        }
        return false;
    }

    @Override
    public void register(Customer customer) throws ServiceException {
        Customer dbCustomer = customerDao.findByPk(customer.getId());
        if (dbCustomer != null){
            throw new ServiceException("The user name " + customer.getId() + " has already been registered!" );
        }
        customerDao.create(customer);
    }
}
