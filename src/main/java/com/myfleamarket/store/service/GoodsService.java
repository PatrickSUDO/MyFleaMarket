package com.myfleamarket.store.service;

import com.myfleamarket.store.domain.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> queryAll();

    List<Goods> queryByStartEnd(int start, int end);

    Goods goodDetail(long goodId);

}
