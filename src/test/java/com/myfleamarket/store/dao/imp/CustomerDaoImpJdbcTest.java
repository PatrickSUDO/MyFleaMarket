package com.myfleamarket.store.dao.imp;

import com.myfleamarket.store.dao.CustomerDao;
import com.myfleamarket.store.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoImpJdbcTest {

    CustomerDao dao;

    @BeforeEach
    void setUp() {
        dao = new CustomerDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {
        Customer customer = dao.findByPk("s61");
        assertNotNull(customer);
        assertEquals("s61", customer.getId());
        assertEquals("sarah", customer.getName());
        assertEquals("111", customer.getPassword());
        assertEquals("Dublin", customer.getAddress());
        assertEquals("5555", customer.getPhone());
        assertEquals(123123123, customer.getBirthday().getTime());
    }

    @Test
    void findAll() {
        List<Customer> list = dao.findAll();
        assertEquals(list.size(), 1);
    }

    @Test
    void create() {
        Customer customer = new Customer();

        customer.setId("patrick666");
        customer.setName("pie");
        customer.setPassword("321");
        customer.setAddress("Taiwan");
        customer.setPhone("0204");
        customer.setBirthday(new Date(111111112341L));

        dao.create(customer);

        Customer customer1 = dao.findByPk("patrick666");
        assertNotNull(customer);
        assertEquals("patrick666", customer.getId());
        assertEquals("pie", customer.getName());
        assertEquals("321", customer.getPassword());
        assertEquals("Taiwan", customer.getAddress());
        assertEquals("0204", customer.getPhone());
        assertEquals(111111112341L, customer.getBirthday().getTime());

    }

    @Test
    void modify() {

        Customer customer = new Customer();
        customer.setId("patrick666");
        customer.setName("piepie");
        customer.setPassword("456");
        customer.setAddress("Netherlands");
        customer.setPhone("7777");
        customer.setBirthday(new Date(1111444444341L));

        dao.modify(customer);

        Customer customer1 = dao.findByPk("patrick666");
        assertNotNull(customer);
        assertEquals("patrick666", customer1.getId());
        assertEquals("piepie", customer1.getName());
        assertEquals("456", customer1.getPassword());
        assertEquals("Netherlands", customer1.getAddress());
        assertEquals("7777", customer1.getPhone());
        assertEquals(1111444444341L, customer1.getBirthday().getTime());


    }

    @Test
    void remove() {
        dao.remove("patrick666");
        Customer customer = dao.findByPk("patrick666");
        assertNull(customer);
    }
}