package com.frank.springtransactiondemo.service;

import com.frank.springtransactiondemo.model.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ItemDao itemDao;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void makeAnOrder(String itemId, int count) {
        Order order = new Order().setOrderId(UUID.randomUUID().toString())
                .setItemId(itemId);

        orderDao.saveOrder(order);
        itemDao.decreaseItem(itemId, count);

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void makeAnOrderSubRequiresNew1(String itemId, int count) {
        Order order = new Order().setOrderId(UUID.randomUUID().toString())
                .setItemId(itemId);

        orderDao.saveOrder(order);
        itemDao.decreaseItemTxRequiresNew(itemId, count);
        throw new RuntimeException();

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void makeAnOrderSubRequiresNew2(String itemId, int count) {
        Order order = new Order().setOrderId(UUID.randomUUID().toString())
                .setItemId(itemId);

        orderDao.saveOrder(order);
        itemDao.decreaseItemTxRequiresNewThrows(itemId, count);

    }

}
