package com.myfleamarket.store.service.imp;

import com.myfleamarket.store.dao.GoodsDao;
import com.myfleamarket.store.dao.OrderDao;
import com.myfleamarket.store.dao.OrderLineItemDao;
import com.myfleamarket.store.dao.imp.GoodsDaoImpJdbc;
import com.myfleamarket.store.dao.imp.OrderDaoImpJdbc;
import com.myfleamarket.store.dao.imp.OrderLineItemDaoImpJdbc;
import com.myfleamarket.store.domain.Goods;
import com.myfleamarket.store.domain.OrderLineItem;
import com.myfleamarket.store.domain.Orders;
import com.myfleamarket.store.service.OrdersService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrdersServiceImp implements OrdersService {

    GoodsDao goodsDao = new GoodsDaoImpJdbc();
    OrderDao orderDao = new OrderDaoImpJdbc();
    OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImpJdbc();

    @Override
    public String submitOrders(List<Map<String, Object>> cart) {

        Orders orders = new Orders();
        Date date = new Date();

        String orderID = String.valueOf(date.getTime())
                + String.valueOf((int) Math.random() * 100);

        orders.setId(orderID);
        orders.setOrderDate(date);
        orders.setStatus(1);
        orders.setTotal(0.0f);

        orderDao.create(orders);
        double total = 0.0;

        for (Map item : cart) {
            Long goodId = (Long) item.get("goodsid");
            Integer quantity = (Integer) item.get("quantity");
            Goods goods = goodsDao.findByPk(goodId);

            double subtotal = goods.getPrice() * quantity;
            total += subtotal;

            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setQuantity(quantity);
            lineItem.setSubTotal(subtotal);
            lineItem.setOrders(orders);
            lineItem.setGoods(goods);

            orderLineItemDao.create(lineItem);
        }

        orders.setTotal(total);
        orderDao.modify(orders);

        return orderID;
    }
}
