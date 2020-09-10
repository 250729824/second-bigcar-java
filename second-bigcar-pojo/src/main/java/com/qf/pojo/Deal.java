package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "deal")
@Entity
public class Deal {

    @Id
    @Column(name = "deal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dealId;
    /**
     * 商品id（逻辑外键）
     */
    @Column(name = "car_id")
    private Integer carId;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

}
