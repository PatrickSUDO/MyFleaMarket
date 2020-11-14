package com.myfleamarket.store.service;

import com.myfleamarket.store.domain.Orders;

import java.util.List;
import java.util.Map;

public interface OrdersService {
    String submitOrders(List<Map<String, Object>> cart);
}
