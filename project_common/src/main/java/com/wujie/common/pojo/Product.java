package com.wujie.common.pojo;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Product {
    @Id
    private String pid;
    private Double price;
    private String pname;
    private String productDesc;
}
