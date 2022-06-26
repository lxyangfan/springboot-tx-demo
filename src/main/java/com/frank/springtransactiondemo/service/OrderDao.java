package com.frank.springtransactiondemo.service;

import com.frank.springtransactiondemo.model.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void saveOrder(Order order) {
        String sql = "insert into public.order (order_id, item_id, create_time) values ( ?, ?, current_timestamp) ";
        jdbcTemplate.update(sql, order.getOrderId(), order.getItemId());
    }

}
