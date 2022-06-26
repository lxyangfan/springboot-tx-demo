package com.frank.springtransactiondemo.model.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Order {

    private int id;
    private String orderId;
    private String itemId;
    private LocalDateTime createTime;
}
