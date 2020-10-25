package com.myfleamarket.store.service.imp;

import com.myfleamarket.store.dao.CustomerDao;
import com.myfleamarket.store.dao.imp.CustomerDaoImpJdbc;
import com.myfleamarket.store.domain.Customer;
import com.myfleamarket.store.service.CustomerService;
import com.myfleamarket.store.service.ServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImpTest {

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImp();
    }

    @AfterEach
    void tearDown() {
        customerService = null;
    }

    @Test
    @DisplayName("Login Success")
    void loginShouldSuccess() {
        Customer customer = new Customer();
        customer.setId("s61");
        customer.setPassword("111");

        assertTrue(customerService.login(customer));
        assertNotNull(customer);
        assertEquals("s61", customer.getId());
        assertEquals("sarah", customer.getName());
        assertEquals("111", customer.getPassword());
        assertEquals("Dublin", customer.getAddress());
        assertEquals("5555", customer.getPhone());
        assertEquals(123123123, customer.getBirthday().getTime());

    }

    @Test
    @DisplayName("Login Fail")
    void loginShouldFail() {
        Customer customer = new Customer();
        customer.setId("s61");
        customer.setPassword("100");
        assertFalse(customerService.login(customer));


    }

    @Test
    @DisplayName("Register Success")
    void registerShouldSuccess() throws ServiceException {
        Customer customer = new Customer();
        customer.setId("tom");
        customer.setName("tomcat");
        customer.setPassword("666");
        customer.setAddress("Hsinchu");
        customer.setPhone("5550222333");
        customer.setBirthday(new Date(111111112341L));

        customerService.register(customer);

        Customer customer1 = new Customer();
        customer1.setId("tom");
        customer1.setPassword("666");

        customerService.login(customer1);
        assertNotNull(customer1);
        assertEquals("tom", customer1.getId());
        assertEquals("tomcat", customer1.getName());
        assertEquals("666", customer1.getPassword());
        assertEquals("Hsinchu", customer1.getAddress());
        assertEquals("5550222333", customer1.getPhone());
        assertEquals(111111112341L, customer1.getBirthday().getTime());
    }

    @Test
    @DisplayName("Register Fail because the customer id is already exist")
    void registerShouldFail() {

        Customer customer = new Customer();
        customer.setId("tom");
        customer.setPassword("666");

        assertThrows(ServiceException.class, () -> customerService.register(customer));

    }
}