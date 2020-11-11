package com.myfleamarket.store.service.imp;

import com.myfleamarket.store.dao.GoodsDao;
import com.myfleamarket.store.dao.imp.GoodsDaoImpJdbc;
import com.myfleamarket.store.domain.Goods;
import com.myfleamarket.store.service.GoodsService;

import java.util.List;

public class GoodsServiceImp implements GoodsService {

    GoodsDao goodsDao = new GoodsDaoImpJdbc();

    @Override
    public List<Goods> queryAll() {
        return goodsDao.findAll();
    }

    @Override
    public List<Goods> queryByStartEnd(int start, int end) {
        return goodsDao.findStartEnd(start, end);
    }

    @Override
    public Goods goodDetail(long goodId) {
        return goodsDao.findByPk(goodId);
    }
}
