package com.myfleamarket.store.dao;

import com.myfleamarket.store.domain.Goods;

import java.util.List;

public interface GoodsDao {
    Goods findByPk(long pk);

    List<Goods> findAll();
    /**
     * Provide search by page
     *
     * @param start starting id from 0
     * @param end   ending id from 0
     * @return goods list
     */

    List<Goods> findStartEnd(int start, int end);

    void create(Goods goods);

    void modify(Goods goods);

    void remove(long pk);

}
