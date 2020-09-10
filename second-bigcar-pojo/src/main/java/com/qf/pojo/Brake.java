package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car_brake")
public class Brake {

    @Id
    @Column(name = "chassis_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chassisId;
    /**
     * 品牌id(逻辑外键)
     */
    @Column(name = "cb_id")
    private Integer cbId;
    /**
     * 驱动方式
     */
    @Column(name = "driver_mode")
    private String driverMode;
    /**
     * 助力类型
     */
    @Column(name = "power_type")
    private String powerType;
    /**
     * 前悬挂类型
     */
    @Column(name = "fsus_type")
    private String fsusType;
    /**
     * 后悬挂类型
     */
    @Column(name = "busu_type")
    private String bsusType;
    /**
     * 前制动类型
     */
    @Column(name = "fbreak_type")
    private String fbreakType;
    /**
     * 后制动定制
     */
    @Column(name = "bbreak_type")
    private String bbreakType;
    /**
     * 驱车制动类型
     */
    @Column(name = "dbreak_type")
    private String dbreakType;
    /**
     * 前轮胎规格
     */
    @Column(name = "ftire_size")
    private String ftireSize;
    /**
     * 后轮胎规格
     */
    @Column(name = "btire_size")
    private String btireSize;
    /**
     * 备用字段1
     */
    private String setaside1;
    /**
     * 备用字段2
     */
    private String setaside2;
}
