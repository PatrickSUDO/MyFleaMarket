package com.myfleamarket.store.dao.imp;

import com.myfleamarket.store.dao.OrderLineItemDao;
import com.myfleamarket.store.domain.Goods;
import com.myfleamarket.store.domain.OrderLineItem;
import com.myfleamarket.store.domain.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineItemDaoImpJdbcTest {

    OrderLineItemDao dao;

    @BeforeEach
    void setUp() {
        dao = new OrderLineItemDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {
        OrderLineItem lineItem = dao.findByPk(1);
        assertNotNull(lineItem);
        assertEquals(1, lineItem.getId());
        assertEquals(3, lineItem.getQuantity());
        assertEquals(9297, lineItem.getSubTotal());
        assertEquals(3, lineItem.getGoods().getId());
        assertEquals("0000", lineItem.getOrders().getId());
    }

    @Test
    void findAll() {
        List<OrderLineItem> list = dao.findAll();
        assertEquals(1, list.size());

        OrderLineItem lineItem = list.get(1);
        assertNotNull(lineItem);
        assertEquals(2, lineItem.getId());
        assertEquals(5, lineItem.getQuantity());
        assertEquals(10000, lineItem.getSubTotal());
        assertEquals(4, lineItem.getGoods().getId());
        assertEquals("0000", lineItem.getOrders().getId());
    }

    @Test
    void create() {
        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setId(7);
        lineItem.setQuantity(4);
        lineItem.setSubTotal(9265);

        Goods goods = new Goods();
        goods.setId(23);
        lineItem.setGoods(goods);

        Orders orders = new Orders();
        orders.setId("0000");
        lineItem.setOrders(orders);

        dao.create(lineItem);
        OrderLineItem lineItem1 = dao.findByPk(7);
        assertNotNull(lineItem);
        assertEquals(7, lineItem1.getId());
        assertEquals(4, lineItem1.getQuantity());
        assertEquals(9265, lineItem1.getSubTotal());
        assertEquals(23, lineItem1.getGoods().getId());
        assertEquals("0000", lineItem1.getOrders().getId());


    }

    @Test
    void modify() {
        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setId(7);
        lineItem.setQuantity(2);
        lineItem.setSubTotal(9266);

        Goods goods = new Goods();
        goods.setId(24);
        lineItem.setGoods(goods);

        Orders orders = new Orders();
        orders.setId("0000");
        lineItem.setOrders(orders);

        dao.modify(lineItem);

         OrderLineItem lineItem1 = dao.findByPk(7);
        assertNotNull(lineItem);
        assertEquals(7, lineItem1.getId());
        assertEquals(2, lineItem1.getQuantity());
        assertEquals(9266, lineItem1.getSubTotal());
        assertEquals(24, lineItem1.getGoods().getId());
        assertEquals("0000", lineItem1.getOrders().getId());
    }

    @Test
    void remove() {
        dao.remove(7);
        OrderLineItem lineItem = dao.findByPk(7);
        assertNull(lineItem);
    }
}