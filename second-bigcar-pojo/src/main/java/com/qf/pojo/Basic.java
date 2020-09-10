package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "car_basic")
public class Basic {
    @Id
    @Column(name = "basic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer basicId;
    /**
     * 品牌id（逻辑外键）
     */
    @Column(name = "cb_id")
    private Integer cbId;
    /**
     * 厂商
     */
    private String firm;
    /**
     * 级别
     */
    private String rank;
    /**
     * 发动机
     */
    private String engine;
    /**
     * 变速箱
     */
    private String gearbox;
    /**
     * 车身结构
     */
    private String structure;
    /**
     * 长/宽/高(m)
     */
    private String size;
    /**
     * 轴距(mm)
     */
    private Integer wheelbase;
    /**
     * 行李箱容积(L)
     */
    private Integer volume;
    /**
     * 整备质量(kg)
     */
    private Integer quality;
    /**
     * 备用字段1
     */
    private String setaside1;
    /**
     * 备用字段2
     */
    private String setaside2;
}
