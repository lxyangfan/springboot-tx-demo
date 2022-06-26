package com.frank.springtransactiondemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void decreaseItem(String itemId, int item_count) {
        String sql = "update public.item set item_count = item_count - ? where item_id = ?";
        jdbcTemplate.update(sql, itemId, item_count);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void decreaseItemTxRequiresNew(String itemId, int item_count) {
        String sql = "update public.item set item_count = item_count - ? where item_id = ?";
        jdbcTemplate.update(sql,  item_count, itemId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void decreaseItemTxRequiresNewThrows(String itemId, int item_count) {
        String sql = "update public.item set item_count = item_count - ? where item_id = ?";
        jdbcTemplate.update(sql, item_count, itemId);
        throw new RuntimeException();
    }
}
