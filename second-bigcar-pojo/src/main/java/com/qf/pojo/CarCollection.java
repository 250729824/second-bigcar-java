package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "car_collection")
@Entity
public class CarCollection {

    @Id
    @Column(name = "cc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ccId;
    /**
     * 商品编号(逻辑外键)
     */
    @Column(name = "car_id")
    private Integer carId;
    /**
     * 用户id(逻辑外键)
     */
    @Column(name = "user_id")
    private Integer userId;
}
