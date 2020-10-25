package com.myfleamarket.store.dao.imp;

import com.myfleamarket.store.dao.OrderDao;
import com.myfleamarket.store.domain.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImpJdbcTest {

    OrderDao dao;

    @BeforeEach
    void setUp() {
        dao = new OrderDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {
        Orders orders = dao.findByPk("0000");
        assertNotNull(orders);
        assertEquals("0000", orders.getId());
        assertEquals(123123123, orders.getOrderDate().getTime());
        assertEquals(1, orders.getStatus());
        assertEquals(4129890, orders.getTotal());
    }

    @Test
    void findAll() {
        List<Orders> list = dao.findAll();
        assertEquals(2, list.size());
        Orders orders = list.get(1);
        assertEquals("00000", orders.getId());
        assertEquals(123123124, orders.getOrderDate().getTime());
        assertEquals(0, orders.getStatus());
        assertEquals(4129880, orders.getTotal());
    }

    @Test
    void create() {
        Orders orders = new Orders();
        orders.setId("300");
        orders.setStatus(0);
        orders.setOrderDate(new Date(1122222224562222L));
        orders.setTotal(3560.0);

        dao.create(orders);
        Orders orders1 = dao.findByPk("300");
        assertNotNull(orders1);
        assertEquals("300", orders.getId());
        assertEquals(1122222224562222L, orders.getOrderDate().getTime());
        assertEquals(0, orders.getStatus());
        assertEquals(3560.0, orders.getTotal());
    }

    @Test
    void modify() {
        Orders orders = new Orders();
        orders.setId("300");
        orders.setStatus(1);
        orders.setOrderDate(new Date(1122234222562222L));
        orders.setTotal(900.5);

        dao.modify(orders);

        Orders orders1 = dao.findByPk("300");
        assertNotNull(orders1);
        assertEquals("300", orders.getId());
        assertEquals(1122234222562222L, orders.getOrderDate().getTime());
        assertEquals(1, orders.getStatus());
        assertEquals(900.5, orders.getTotal());

    }

    @Test
    void remove() {
        dao.remove("300");
        Orders orders =  dao.findByPk("300");
        assertNull(orders);
    }
}