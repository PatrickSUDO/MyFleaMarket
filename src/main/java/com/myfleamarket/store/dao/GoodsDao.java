package com.myfleamarket.store.dao.imp;

import com.myfleamarket.store.domain.Goods;

import java.util.List;

public interface GoodsDao {
    Goods findByPk(long pk);


    /**
     * Provide search
     *
     * @param start starting id from 0
     * @param end   ending id from 0
     * @return goods list
     */

    List<Goods> findStartEnd(int start, int end);

    void create(Goods good);

    void modify(Goods good);

    void remove(long pk);

}
