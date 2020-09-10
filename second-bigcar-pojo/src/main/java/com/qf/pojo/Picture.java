package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "picture")
@Entity
public class Picture {

    @Id
    @Column(name = "pic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer picId;
    /**
     * 商品id（逻辑外键）
     */
    @Column(name = "car_id")
    private Integer carId;
    /**
     * 图片链接
     */
    @Column(name = "pic_url")
    private String picUrl;
}
