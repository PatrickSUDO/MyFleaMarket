package com.myfleamarket.store.service.imp;

import com.myfleamarket.store.domain.Goods;
import com.myfleamarket.store.service.GoodsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceImpTest {

    GoodsService goodsService;

    @BeforeEach
    void setUp() {
        goodsService = new GoodsServiceImp();
    }

    @AfterEach
    void tearDown() {
        goodsService = null;
    }

    @Test
    void queryAll() {
        List<Goods> list = goodsService.queryAll();
        assertEquals(35, list.size());

        Goods goods = list.get(34);
        assertEquals(35, goods.getId());
        assertEquals("test", goods.getName());
        assertEquals(6666, goods.getPrice());
        assertEquals("test des", goods.getDescription());
        assertEquals("apple", goods.getBrand());
        assertEquals("amd", goods.getCpuBrand());
        assertEquals("ryzen5000", goods.getCpuType());
        assertEquals("8G", goods.getMemoryCapacity());
        assertEquals("1T", goods.getHdCapacity());
        assertEquals("GTX1060", goods.getCardModel());
        assertEquals("24inch", goods.getDisplaysize());
        assertEquals("5b20f41eNd56a5b75.jpg", goods.getImage());
    }

    @Test
    void queryByStartEnd() {
        List<Goods> list = goodsService.queryByStartEnd(25, 35);
        assertEquals(10, list.size());

        Goods goods = list.get(9);
        assertEquals(35, goods.getId());
        assertEquals("test", goods.getName());
        assertEquals(6666, goods.getPrice());
        assertEquals("test des", goods.getDescription());
        assertEquals("apple", goods.getBrand());
        assertEquals("amd", goods.getCpuBrand());
        assertEquals("ryzen5000", goods.getCpuType());
        assertEquals("8G", goods.getMemoryCapacity());
        assertEquals("1T", goods.getHdCapacity());
        assertEquals("GTX1060", goods.getCardModel());
        assertEquals("24inch", goods.getDisplaysize());
        assertEquals("5b20f41eNd56a5b75.jpg", goods.getImage());

    }

    @Test
    void goodDetail() {
        Goods goods = goodsService.goodDetail(35);
        assertNotNull(goods);

        assertEquals(35, goods.getId());
        assertEquals("test", goods.getName());
        assertEquals(6666, goods.getPrice());
        assertEquals("test des", goods.getDescription());
        assertEquals("apple", goods.getBrand());
        assertEquals("amd", goods.getCpuBrand());
        assertEquals("ryzen5000", goods.getCpuType());
        assertEquals("8G", goods.getMemoryCapacity());
        assertEquals("1T", goods.getHdCapacity());
        assertEquals("GTX1060", goods.getCardModel());
        assertEquals("24inch", goods.getDisplaysize());
        assertEquals("5b20f41eNd56a5b75.jpg", goods.getImage());

    }
}