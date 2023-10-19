package com.wujie.common.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;

@Data
public class Cart {
    @Id
    private String cid;
    private String pid;
    private Double price;
    private String uid;
    private Integer count;
    @Transient
    private Double differencePrice;
}
